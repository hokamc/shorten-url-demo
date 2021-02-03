package example.springbootshortenurl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Kam
 */
@Component
public class Redis {
    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public Redis(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public long increaseBy(String key, long step) {
        return redisTemplate.opsForValue().increment(key, step);
    }

    public void del(String key) {
        redisTemplate.delete(key);
    }

    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public String get(String key) {
        return redisTemplate.opsForValue().get(key).toString();
    }

}
