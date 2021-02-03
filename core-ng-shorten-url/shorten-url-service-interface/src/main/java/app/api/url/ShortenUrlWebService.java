package app.api.url;

import core.framework.api.web.service.POST;
import core.framework.api.web.service.Path;

/**
 * @author Kam
 */
public interface ShortenUrlWebService {
    @POST
    @Path("/url")
    CreateShortUrlResponse createUrl(CreateShortUrlRequest request);
}
