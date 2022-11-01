package okhttp3.internal.connection;

import com.android.tools.r8.GeneratedOutlineSupport;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.net.UnknownServiceException;
import java.security.cert.CertificateException;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLProtocolException;
import javax.net.ssl.SSLSocket;
import okhttp3.ConnectionSpec;
import okhttp3.internal.Internal;
/* loaded from: classes.dex */
public final class ConnectionSpecSelector {
    private final List<ConnectionSpec> connectionSpecs;
    private boolean isFallback;
    private boolean isFallbackPossible;
    private int nextModeIndex = 0;

    public ConnectionSpecSelector(List<ConnectionSpec> list) {
        this.connectionSpecs = list;
    }

    private boolean isFallbackPossible(SSLSocket sSLSocket) {
        for (int i = this.nextModeIndex; i < this.connectionSpecs.size(); i++) {
            if (this.connectionSpecs.get(i).isCompatible(sSLSocket)) {
                return true;
            }
        }
        return false;
    }

    public ConnectionSpec configureSecureSocket(SSLSocket sSLSocket) throws IOException {
        ConnectionSpec connectionSpec;
        int i = this.nextModeIndex;
        int size = this.connectionSpecs.size();
        while (true) {
            if (i >= size) {
                connectionSpec = null;
                break;
            }
            connectionSpec = this.connectionSpecs.get(i);
            if (connectionSpec.isCompatible(sSLSocket)) {
                this.nextModeIndex = i + 1;
                break;
            }
            i++;
        }
        if (connectionSpec != null) {
            this.isFallbackPossible = isFallbackPossible(sSLSocket);
            Internal.instance.apply(connectionSpec, sSLSocket, this.isFallback);
            return connectionSpec;
        }
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Unable to find acceptable protocols. isFallback=");
        outline13.append(this.isFallback);
        outline13.append(", modes=");
        outline13.append(this.connectionSpecs);
        outline13.append(", supported protocols=");
        outline13.append(Arrays.toString(sSLSocket.getEnabledProtocols()));
        throw new UnknownServiceException(outline13.toString());
    }

    public boolean connectionFailed(IOException iOException) {
        this.isFallback = true;
        if (!this.isFallbackPossible || (iOException instanceof ProtocolException) || (iOException instanceof InterruptedIOException)) {
            return false;
        }
        boolean z = iOException instanceof SSLHandshakeException;
        if ((!z || !(iOException.getCause() instanceof CertificateException)) && !(iOException instanceof SSLPeerUnverifiedException)) {
            return z || (iOException instanceof SSLProtocolException) || (iOException instanceof SSLException);
        }
        return false;
    }
}
