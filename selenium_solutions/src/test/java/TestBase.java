import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class TestBase {

    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
    public WebDriver driver;
    public WebDriverWait wait;

    boolean isElementPresent(WebDriver driver, By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    boolean areElementsPresent(WebDriver driver, By locator) {
        return driver.findElements(locator).size() > 0;
    }

    public String colorToRGB(String rgba) {
        String delimiters = "[a-z(), ]+";
        String[] nums = rgba.split(delimiters);
        String color = Arrays.toString(Arrays.copyOfRange(nums, 1, 4));
        color = color.replace("[", "(").replace("]", ")");
        return color;
    }

    public String randomNum() {
        Date today = Calendar.getInstance().getTime();
        DateFormat df = new SimpleDateFormat("ddMMyyyyHHmmss");
        String num = df.format(today);
        return num;
    }

    @Before
    public void start() {
        if (tlDriver.get() != null) {
            driver = tlDriver.get();
            wait = new WebDriverWait(driver, 10);
            //driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            return;
        }
        DesiredCapabilities caps = new DesiredCapabilities();
        driver = new ChromeDriver(caps);
        tlDriver.set(driver);
        System.out.println(((HasCapabilities) driver).getCapabilities());
        wait = new WebDriverWait(driver, 10);
        //driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> { driver.quit(); driver = null; }));
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}
