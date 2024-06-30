package in.co.rahogata.springboot.autoconfigure.lettucessl;

/**
 * Courtesy of {@link org.springframework.boot.autoconfigure.data.redis.RedisUrlSyntaxException}.
 */
class LettuceRedisUrlSyntaxException extends RuntimeException{

    private final String url;

    LettuceRedisUrlSyntaxException(String url, Exception cause) {
        super(buildMessage(url), cause);
        this.url = url;
    }

    LettuceRedisUrlSyntaxException(String url) {
        super(buildMessage(url));
        this.url = url;
    }

    String getUrl() {
        return this.url;
    }

    private static String buildMessage(String url) {
        return "Invalid Redis URL '" + url + "'";
    }

}

