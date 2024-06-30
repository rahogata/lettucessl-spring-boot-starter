## Autoconfiguration for Lettuce with TLS support

Spring Boot 2.x redis autoconfiguration don't support providing TLS information via externalized configuration or customizers.
I have used existing LettuceConnectionConfiguration source code to accept TLS details from externalized configuration and configure Lettuce SslOptions.

### How to Use?

1. Add this library to your spring project.

##### Maven

```xml
    <dependency>
        <groupId>in.co.rahogata</groupId>
        <artifactId>lettucessl-spring-boot-starter</artifactId>
        <version>${version}</version>
    </dependency>
```

##### Gradle

```groovy
    implementation 'in.co.rahogata:lettucessl-spring-boot-starter'
```

2. Add TLS details to ```application.yaml```.

```yaml
rahogata:
  lettuce:
    ssl:
      key-store-location: classpath:redis-client.p12
      key-store-password: ramana
      key-store-type: PKCS12
      trust-store-location: classpath:ca.p12
      trust-store-password: ramana
      verify-peer: true
```

