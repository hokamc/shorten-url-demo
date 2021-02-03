package example.springbootshortenurl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Kam
 */
@Component
public class Counter {
    private static final String KEY = "counter";

    private final Encoder encoder;
    private final Redis redis;

    @Autowired
    public Counter(Redis redis, Encoder encoder) {
        this.redis = redis;
        this.encoder = encoder;
    }

    long get() {
        long count = redis.increaseBy(KEY, 1) - 1;
        if (count > encoder.maximumNumber) {
            redis.del(KEY);
            return 0;
        }
        return count;
    }
}
