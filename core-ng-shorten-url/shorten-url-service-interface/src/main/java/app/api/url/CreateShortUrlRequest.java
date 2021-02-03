package app.api.url;

import core.framework.api.json.Property;
import core.framework.api.validate.NotBlank;
import core.framework.api.validate.NotNull;
import core.framework.api.validate.Pattern;

/**
 * @author Kam
 */
public class CreateShortUrlRequest {
    @NotNull
    @NotBlank
    @Pattern("^(https?)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]")
    @Property(name = "url")
    public String url;
}
