package okhttp3;

import com.android.tools.r8.GeneratedOutlineSupport;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes.dex */
public interface Dns {
    public static final Dns SYSTEM = new Dns() { // from class: okhttp3.Dns.1
        @Override // okhttp3.Dns
        public List<InetAddress> lookup(String str) throws UnknownHostException {
            if (str != null) {
                try {
                    return Arrays.asList(InetAddress.getAllByName(str));
                } catch (NullPointerException e) {
                    UnknownHostException unknownHostException = new UnknownHostException(GeneratedOutlineSupport.outline8("Broken system behaviour for dns lookup of ", str));
                    unknownHostException.initCause(e);
                    throw unknownHostException;
                }
            } else {
                throw new UnknownHostException("hostname == null");
            }
        }
    };

    List<InetAddress> lookup(String str) throws UnknownHostException;
}
