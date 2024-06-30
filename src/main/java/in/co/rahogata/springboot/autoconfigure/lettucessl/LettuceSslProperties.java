package in.co.rahogata.springboot.autoconfigure.lettucessl;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;

/**
 * Configuration properties for Lettuce SSL connection.
 */
@Getter
@Setter
@ConfigurationProperties("rahogata.lettuce.ssl")
public class LettuceSslProperties {
    /**
     * Keystore resource location.
     */
    private Resource keyStoreLocation;

    /**
     * Keystore password.
     */
    private char[] keyStorePassword;

    /**
     * Truststore resource location.
     */
    private Resource trustStoreLocation;

    /**
     * Truststore password.
     */
    private char[] trustStorePassword;

    /**
     * Keystore type. Example PKCS12, JKS.
     */
    private String keyStoreType;

    /**
     * Whether to verify host/peer.
     */
    private boolean verifyPeer;
}
