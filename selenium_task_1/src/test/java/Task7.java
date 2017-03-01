import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class Task7 {

    private WebDriver driver;
    private WebDriverWait wait;

    private boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void menuItemsTest () {
        driver.get("http://litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(titleIs("My Store"));
        List <WebElement> toClick = driver.findElements(By.className("name"));
        for (int i = 0; i < toClick.size(); i++) {
            System.out.print("Element #" + i + " ");
            System.out.println(toClick.get(i).getText());
            toClick.get(i).click();
            Assert.assertTrue(isElementPresent(By.tagName("h1")));
        }
    }


    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}
