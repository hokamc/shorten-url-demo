package app.shorturl;

import app.api.url.CreateShortUrlRequest;
import app.api.url.CreateShortUrlResponse;
import app.api.url.ShortenUrlWebService;
import core.framework.inject.Inject;
import core.framework.redis.Redis;
import core.framework.web.rate.LimitRate;

import java.text.MessageFormat;

/**
 * @author Kam
 */
public class ShortenUrlWebServiceImpl implements ShortenUrlWebService {
    @Inject
    Encoder encoder;
    @Inject
    Counter counter;
    @Inject
    Redis redis;
    @Inject
    AppContext appContext;

    @Override
    @LimitRate("create-shorten-url")
    public CreateShortUrlResponse createUrl(CreateShortUrlRequest request) {
        String key = encoder.encode(counter.get());
        redis.set(key, request.url);

        var response = new CreateShortUrlResponse();
        response.url = MessageFormat.format("https://{0}/{1}", appContext.domain, key);
        return response;
    }
}
