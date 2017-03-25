package by.stqa.seleniun.Task19.app;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

public class TestBaseNew extends Application {

    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

    @Before
    public void start() {
        init();
    }

    @After
    public void quit() {
        stop();
    }

}
