package app.shorturl;

import app.api.url.ShortenUrlWebService;
import core.framework.http.HTTPMethod;
import core.framework.module.App;
import core.framework.module.SystemModule;

import java.util.concurrent.TimeUnit;

/**
 * @author Kam
 */
public class ShortUrlServiceApp extends App {
    @Override
    protected void initialize() {
        load(new SystemModule("sys.properties"));
        loadProperties("app.properties");

        bind(Encoder.class);
        bind(Counter.class);
        bind(new AppContext(requiredProperty("app.domain")));

        api().service(ShortenUrlWebService.class, bind(ShortenUrlWebServiceImpl.class));
        http().route(HTTPMethod.GET, "/:path(*)", bind(RedirectUrlController.class));
        http().route(HTTPMethod.GET, "/", bind(HomeController.class));
        http().limitRate().add("create-shorten-url", 5, 5, TimeUnit.HOURS);
    }
}
