package life.memy.identity;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

public class NullHostNameVerifier implements HostnameVerifier {

    @Override   
    public boolean verify(String hostname, SSLSession session) {
        System.out.println("Approving certificate for " + hostname);
        return true;
    }


}
