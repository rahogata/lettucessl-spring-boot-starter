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
     *
     * @param keyStoreLocation location where the keystore can be obtained.
     * @return an abstract resource to get keystore.
     */
    private Resource keyStoreLocation;

    /**
     * Keystore password.
     * @param keyStorePassword keystore password.
     * @return keystore password
     */
    private char[] keyStorePassword;

    /**
     * Truststore resource location.
     * @param trustStoreLocation Certificate truststore location.
     * @return an abstract resource to get truststore.
     */
    private Resource trustStoreLocation;

    /**
     * Truststore password.
     * @param trustStorePassword truststore password.
     * @return truststore password.
     */
    private char[] trustStorePassword;

    /**
     * Keystore type. Example PKCS12, JKS.
     * @param keyStoreType keystore type.
     * @return keystore type.
     */
    private String keyStoreType;

    /**
     * Whether to verify host/peer.
     * @param verifyPeer Flag to decide peer verification.
     * @return Whether to verify host/peer.
     */
    private boolean verifyPeer;
}
