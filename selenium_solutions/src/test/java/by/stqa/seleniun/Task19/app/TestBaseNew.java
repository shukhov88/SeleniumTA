package by.stqa.seleniun.Task19.app;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

public class TestBaseNew {

    protected Application application = new Application();

    @Before
    public void start() {
        application.init();
    }

    @After
    public void quit() {
        application.stop();
    }

}
