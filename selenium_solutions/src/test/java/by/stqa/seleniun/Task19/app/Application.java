package by.stqa.seleniun.Task19.app;

import by.stqa.seleniun.Task19.pages.CartPageHelper;
import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Application {
    protected final CartPageHelper cartPageHelper = new CartPageHelper();

    public boolean isElementNotPresent(WebDriver driver, By locator) {
        try {
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            return driver.findElements(locator).size() == 0;
        } finally {
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        }

    }

    public boolean areElementsPresent(WebDriver driver, By locator) {
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

    public ExpectedCondition<String> anyWindowOtherThan(Set<String> oldWindows) {
        return new ExpectedCondition<String>() {
            public String apply(WebDriver driver) {
                Set<String> handles = driver.getWindowHandles();
                handles.removeAll(oldWindows);
                return handles.size() > 0 ? handles.iterator().next() : null;
            }
        };
    }

    protected void init() {
        if (TestBaseNew.tlDriver.get() != null) {
            cartPageHelper.prodPageHelper.mainPageHelper.driver = TestBaseNew.tlDriver.get();
            cartPageHelper.wait = new WebDriverWait(cartPageHelper.prodPageHelper.mainPageHelper.driver, 10);
            //driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            return;
        }

        cartPageHelper.prodPageHelper.mainPageHelper.driver = new ChromeDriver();
        TestBaseNew.tlDriver.set(cartPageHelper.prodPageHelper.mainPageHelper.driver);
        //System.out.println(((HasCapabilities) driver).getCapabilities());
        cartPageHelper.wait = new WebDriverWait(cartPageHelper.prodPageHelper.mainPageHelper.driver, 10);
        //driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> {
                    stop(); }));
    }

    protected void stop() {
        cartPageHelper.prodPageHelper.mainPageHelper.driver.quit();
        cartPageHelper.prodPageHelper.mainPageHelper.driver = null;
    }

    public static class MyListener extends AbstractWebDriverEventListener {
        @Override
        public void beforeFindBy(By by, WebElement element, WebDriver driver) {
            System.out.println(by);
        }

        @Override
        public void afterFindBy(By by, WebElement element, WebDriver driver) {
            System.out.println(by + " found");
        }

        @Override
        public void onException(Throwable throwable, WebDriver driver) {
            System.out.println(throwable);
            File temp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File screen = new File("screen" + System.currentTimeMillis() + ".png");
            try {
                Files.copy(temp, screen);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("ScreenShot made: " + screen);
        }
    }
}
