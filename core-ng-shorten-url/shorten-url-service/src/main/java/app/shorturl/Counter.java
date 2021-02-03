package app.shorturl;

import core.framework.inject.Inject;
import core.framework.redis.Redis;

/**
 * @author Kam
 */
public class Counter {
    private static final String KEY = "counter";

    @Inject
    Redis redis;
    @Inject
    Encoder encoder;

    long get() {
        long count = redis.increaseBy(KEY, 1) - 1;
        if (count > encoder.maximumNumber) {
            redis.del(KEY);
            return 0;
        }
        return count;
    }
}
