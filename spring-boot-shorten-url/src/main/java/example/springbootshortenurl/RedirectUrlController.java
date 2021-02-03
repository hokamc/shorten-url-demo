package example.springbootshortenurl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author Kam
 */
@Controller
public class RedirectUrlController {
    private final Redis redis;

    @Autowired
    public RedirectUrlController(Redis redis) {
        this.redis = redis;
    }

    @GetMapping(value = "/{url}")
    public String redirect(@PathVariable("url") String path) {
        if (path.length() != Encoder.MAX_LENGTH || path.contains("/")) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "invalid url");
        } else {
            String url = redis.get(path);
            if (url != null) {
                return "redirect:" + url;
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "invalid url");
            }
        }
    }
}
