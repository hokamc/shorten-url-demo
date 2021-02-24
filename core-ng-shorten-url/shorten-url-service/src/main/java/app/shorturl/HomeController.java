package app.shorturl;

import core.framework.http.ContentType;
import core.framework.inject.Inject;
import core.framework.util.Files;
import core.framework.util.Strings;
import core.framework.web.Controller;
import core.framework.web.Request;
import core.framework.web.Response;
import core.framework.web.site.WebDirectory;

import java.nio.file.Path;

/**
 * @author Kam
 */
public class HomeController implements Controller {
    @Inject
    WebDirectory webDirectory;

    @Override
    public Response execute(Request request) {
        Path path = webDirectory.path("/index.html");
        return Response
                .file(path)
                .contentType(ContentType.TEXT_HTML)
                .header("Content-Encode", "gzip")
                .header("Cache-Control", "public, max-age=310950767")
                .header("Etag", Strings.format("W/\"{}\"", Files.size(path) + Files.lastModified(path).getEpochSecond()));
    }
}
