package okhttp3;

import com.android.tools.r8.GeneratedOutlineSupport;
import java.lang.ref.Reference;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import okhttp3.internal.Util;
import okhttp3.internal.connection.RealConnection;
import okhttp3.internal.connection.RouteDatabase;
import okhttp3.internal.connection.StreamAllocation;
import okhttp3.internal.platform.Platform;
/* loaded from: classes.dex */
public final class ConnectionPool {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Executor executor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), Util.threadFactory("OkHttp ConnectionPool", true));
    private final Runnable cleanupRunnable;
    public boolean cleanupRunning;
    private final Deque<RealConnection> connections;
    private final long keepAliveDurationNs;
    private final int maxIdleConnections;
    public final RouteDatabase routeDatabase;

    public ConnectionPool() {
        this(5, 5L, TimeUnit.MINUTES);
    }

    private int pruneAndGetAllocationCount(RealConnection realConnection, long j) {
        List<Reference<StreamAllocation>> list = realConnection.allocations;
        int i = 0;
        while (i < list.size()) {
            Reference<StreamAllocation> reference = list.get(i);
            if (reference.get() != null) {
                i++;
            } else {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("A connection to ");
                outline13.append(realConnection.route().address().url());
                outline13.append(" was leaked. Did you forget to close a response body?");
                Platform.get().logCloseableLeak(outline13.toString(), ((StreamAllocation.StreamAllocationReference) reference).callStackTrace);
                list.remove(i);
                realConnection.noNewStreams = true;
                if (list.isEmpty()) {
                    realConnection.idleAtNanos = j - this.keepAliveDurationNs;
                    return 0;
                }
            }
        }
        return list.size();
    }

    public long cleanup(long j) {
        synchronized (this) {
            RealConnection realConnection = null;
            long j2 = Long.MIN_VALUE;
            int i = 0;
            int i2 = 0;
            for (RealConnection realConnection2 : this.connections) {
                if (pruneAndGetAllocationCount(realConnection2, j) > 0) {
                    i2++;
                } else {
                    i++;
                    long j3 = j - realConnection2.idleAtNanos;
                    if (j3 > j2) {
                        realConnection = realConnection2;
                        j2 = j3;
                    }
                }
            }
            long j4 = this.keepAliveDurationNs;
            if (j2 < j4 && i <= this.maxIdleConnections) {
                if (i > 0) {
                    return j4 - j2;
                } else if (i2 > 0) {
                    return j4;
                } else {
                    this.cleanupRunning = false;
                    return -1L;
                }
            }
            this.connections.remove(realConnection);
            Util.closeQuietly(realConnection.socket());
            return 0L;
        }
    }

    public boolean connectionBecameIdle(RealConnection realConnection) {
        if (realConnection.noNewStreams || this.maxIdleConnections == 0) {
            this.connections.remove(realConnection);
            return true;
        }
        notifyAll();
        return false;
    }

    public synchronized int connectionCount() {
        return this.connections.size();
    }

    public Socket deduplicate(Address address, StreamAllocation streamAllocation) {
        for (RealConnection realConnection : this.connections) {
            if (realConnection.isEligible(address, null) && realConnection.isMultiplexed() && realConnection != streamAllocation.connection()) {
                return streamAllocation.releaseAndAcquire(realConnection);
            }
        }
        return null;
    }

    public void evictAll() {
        ArrayList arrayList = new ArrayList();
        synchronized (this) {
            Iterator<RealConnection> it = this.connections.iterator();
            while (it.hasNext()) {
                RealConnection next = it.next();
                if (next.allocations.isEmpty()) {
                    next.noNewStreams = true;
                    arrayList.add(next);
                    it.remove();
                }
            }
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            Util.closeQuietly(((RealConnection) it2.next()).socket());
        }
    }

    public RealConnection get(Address address, StreamAllocation streamAllocation, Route route) {
        for (RealConnection realConnection : this.connections) {
            if (realConnection.isEligible(address, route)) {
                streamAllocation.acquire(realConnection, true);
                return realConnection;
            }
        }
        return null;
    }

    public synchronized int idleConnectionCount() {
        int i;
        i = 0;
        for (RealConnection realConnection : this.connections) {
            if (realConnection.allocations.isEmpty()) {
                i++;
            }
        }
        return i;
    }

    public void put(RealConnection realConnection) {
        if (!this.cleanupRunning) {
            this.cleanupRunning = true;
            executor.execute(this.cleanupRunnable);
        }
        this.connections.add(realConnection);
    }

    public ConnectionPool(int i, long j, TimeUnit timeUnit) {
        this.cleanupRunnable = new Runnable() { // from class: okhttp3.ConnectionPool.1
            @Override // java.lang.Runnable
            public void run() {
                while (true) {
                    long cleanup = ConnectionPool.this.cleanup(System.nanoTime());
                    if (cleanup != -1) {
                        if (cleanup > 0) {
                            long j2 = cleanup / 1000000;
                            long j3 = cleanup - (1000000 * j2);
                            synchronized (ConnectionPool.this) {
                                try {
                                    ConnectionPool.this.wait(j2, (int) j3);
                                } catch (InterruptedException unused) {
                                }
                            }
                        }
                    } else {
                        return;
                    }
                }
            }
        };
        this.connections = new ArrayDeque();
        this.routeDatabase = new RouteDatabase();
        this.maxIdleConnections = i;
        this.keepAliveDurationNs = timeUnit.toNanos(j);
        if (j <= 0) {
            throw new IllegalArgumentException("keepAliveDuration <= 0: " + j);
        }
    }
}
