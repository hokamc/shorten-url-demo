package app;

import app.shorturl.ShortUrlServiceApp;
import core.framework.test.module.AbstractTestModule;


/**
 * @author Kam
 */
public class TestModule extends AbstractTestModule {
    @Override
    protected void initialize() {
        load(new ShortUrlServiceApp());
    }
}
