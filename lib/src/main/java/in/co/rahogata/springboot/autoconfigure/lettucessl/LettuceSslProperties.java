package in.co.rahogata.springboot.autoconfigure.lettucessl;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;

@Getter
@Setter
@ConfigurationProperties("rahogata.lettuce.ssl")
class LettuceSslProperties {
    private Resource keyStoreLocation;
    private char[] keyStorePassword;
    private Resource trustStoreLocation;
    private char[] trustStorePassword;
    private String keyStoreType;
    private boolean verifyPeer;
}
