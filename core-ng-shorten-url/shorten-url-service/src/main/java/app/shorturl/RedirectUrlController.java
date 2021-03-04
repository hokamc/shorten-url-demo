package app.shorturl;

import core.framework.api.http.HTTPStatus;
import core.framework.inject.Inject;
import core.framework.redis.Redis;
import core.framework.web.Controller;
import core.framework.web.Request;
import core.framework.web.Response;
import core.framework.web.exception.NotFoundException;

/**
 * @author Kam
 */
public class RedirectUrlController implements Controller {
    @Inject
    Redis redis;

    @Override
    public Response execute(Request request) {
        String pathParam = request.pathParam("path");
        if (pathParam.length() != Encoder.MAX_LENGTH || pathParam.contains("/")) {
            throw new NotFoundException("invalid url");
        } else {
            String url = redis.get(pathParam);
            if (url != null) {
                return Response.redirect(url, HTTPStatus.FOUND);
            } else {
                throw new NotFoundException("invalid url");
            }
        }
    }
}
