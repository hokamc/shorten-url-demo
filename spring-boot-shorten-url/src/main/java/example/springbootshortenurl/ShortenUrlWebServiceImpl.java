package example.springbootshortenurl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;

/**
 * @author Kam
 */
@RestController
public class ShortenUrlWebServiceImpl implements ShortenUrlWebService {
    private final Encoder encoder;
    private final Counter counter;
    private final Redis redis;

    @Value("${domain}")
    private String domain;

    @Autowired
    ShortenUrlWebServiceImpl(Encoder encoder, Counter counter, Redis redis) {
        this.encoder = encoder;
        this.counter = counter;
        this.redis = redis;
    }

    @Override
    public CreateShortUrlResponse createUrl(CreateShortUrlRequest request) {
        String key = encoder.encode(counter.get());
        redis.set(key, request.url);

        var response = new CreateShortUrlResponse();
        response.url = MessageFormat.format("http://{0}/{1}", domain, key);
        return response;
    }
}
