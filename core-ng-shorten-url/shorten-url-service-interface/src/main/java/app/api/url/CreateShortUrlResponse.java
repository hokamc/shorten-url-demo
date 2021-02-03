package app.api.url;

import core.framework.api.json.Property;
import core.framework.api.validate.NotBlank;
import core.framework.api.validate.NotNull;

/**
 * @author Kam
 */
public class CreateShortUrlResponse {
    @NotNull
    @NotBlank
    @Property(name = "url")
    public String url;
}
