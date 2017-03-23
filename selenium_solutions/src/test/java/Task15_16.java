import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

public class Task15_16 {

    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void start() throws MalformedURLException {
        DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
        caps.setPlatform(Platform.WIN10);
        driver = new RemoteWebDriver(new URL("http://192.168.254.184:4444/wd/hub"), caps);
        System.out.println(((HasCapabilities) driver).getCapabilities());
        wait = new WebDriverWait(driver, 10);
        //driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Test
    public void selStandaloneTest() {
        //driver.manage().window().maximize();
        driver.navigate().to("http://selenium2.ru/");
        System.out.println("1");
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
