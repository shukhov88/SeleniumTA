package by.stqa.seleniun.Task19.app;

import com.google.common.io.Files;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class TestBaseNew {

    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
    public WebDriver driver;
    public WebDriverWait wait;

    public void deleteAllProdsFromCart() throws InterruptedException {
        String strNum = driver.findElement(By.cssSelector("table.dataTable.rounded-corners tbody")).getAttribute("childElementCount");
        int str = Integer.parseInt(strNum);

        int num = driver.findElements(By.cssSelector("li.shortcut")).size();
        for (int i = 0; i < num; i++) {
            if (i < num - 1) {
                List<WebElement> previews = driver.findElements(By.cssSelector("li.shortcut"));
                previews.get(0).click();
                driver.findElement(By.cssSelector("[name=remove_cart_item]")).click();

                for (int j = 0; i < num-1; j++) {
                    String strNum2 = driver.findElement(By.cssSelector("table.dataTable.rounded-corners tbody")).getAttribute("childElementCount");
                    int str2 = Integer.parseInt(strNum2);
                    if (j > 10)
                        throw new TimeoutException();
                    if (str2 == (str - i - 1)) {
                        break;
                    } else {
                        Thread.sleep(1000);
                    }
                }
            } else {
                driver.findElement(By.cssSelector("[name=remove_cart_item]")).click();

                for (int j = 0; i < num-1; j++) {
                    String strNum2 = driver.findElement(By.cssSelector("table.dataTable.rounded-corners tbody")).getAttribute("childElementCount");
                    int str2 = Integer.parseInt(strNum2);
                    if (j > 10)
                        throw new TimeoutException();
                    if (str2 == (str - i - 1)) {
                        break;
                    } else {
                        Thread.sleep(1000);
                    }
                }
            }
        }
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("em")));
        Assert.assertTrue(driver.findElement(By.cssSelector("em")).getAttribute("textContent").equals(
                "There are no items in your cart."));
    }

    public void openCart() {
        driver.findElement(By.cssSelector("a.link[href*=checkout]")).click();
    }

    public void addProductsToCart(int quantity) throws InterruptedException {
        for (int i = 0; i < quantity; i++) {
            openProduct();
            if (isElementPresent(driver, By.cssSelector("[name*=options]"))) {
                Select size = new Select(driver.findElement(By.cssSelector("[name*=options]")));
                size.selectByIndex(1);
            }
            driver.findElement(By.cssSelector("[name=add_cart_product]")).click();

            String quant2 = Integer.toString(i+1);

            for (int j = 0;; j++) {
                String quant = driver.findElement(By.cssSelector("span.quantity")).getAttribute("textContent");
                if (j > 10)
                    throw new TimeoutException();
                if (quant.equals(quant2)) {
                    break;
                } else {
                    Thread.sleep(500);
                }
            }

            goToMainPage();
        }
    }

    public void openProduct() {
        List<WebElement> products = driver.findElements(By.cssSelector("a.link[title*=Duck]"));
        products.get(0).click();
    }

    public void goToMainPage() {
        driver.navigate().to("http://localhost/litecart/");
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

    public boolean isElementPresent(WebDriver driver, By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

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

    @Before
    public void start() {
        if (tlDriver.get() != null) {
            driver = tlDriver.get();
            wait = new WebDriverWait(driver, 10);
            //driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            return;
        }

        driver = new ChromeDriver();
        tlDriver.set(driver);
        //System.out.println(((HasCapabilities) driver).getCapabilities());
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
