package example.springbootshortenurl;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author Kam
 */
@Validated
@RequestMapping("")
public interface ShortenUrlWebService {
    @Valid
    @ResponseBody
    @PostMapping("/url")
    CreateShortUrlResponse createUrl(@Valid @RequestBody CreateShortUrlRequest request);

    class CreateShortUrlRequest {
        @NotNull
        @NotBlank
        @Pattern(regexp = "^(https?)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]")
        @JsonProperty("url")
        public String url;
    }

    class CreateShortUrlResponse {
        @NotNull
        @NotBlank
        @JsonProperty("url")
        public String url;
    }
}
