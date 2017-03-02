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
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(titleIs("My Store"));

        //находим количество основных пунктов меню
        int itemsQuantity = driver.findElements(By.cssSelector("li#app-")).size();

        //Цикл который прокликивает каждый основной пункт меню
        for (int i = 1; i < itemsQuantity + 1; i++) {
            WebElement menuItem = driver.findElement(By.cssSelector("li#app-:nth-child(" + i + ")"));
            menuItem.click();
            Assert.assertTrue(isElementPresent(By.tagName("h1")));

            //находим количество вложенных пунктов меню относительно текущего основного пункта
            int subItemsQuantity = driver.findElements(By.cssSelector("[id^=doc-]")).size();
            //System.out.println(subItemsQuantity);

            //Цикл который должен прокликать все вложенные пункты текущего основного пункта
            //И здесь что то не так, помогите разобраться
            for (int j = 1; j < subItemsQuantity + 1; j++) {
                WebElement subMenuItem = driver.findElement(By.cssSelector("[id^=doc-]:nth-child(" + i + ")"));
                subMenuItem.click();



                /*List <WebElement> subMenuItemsList = driver.findElements(By.cssSelector("[id^=doc-]"));
                System.out.println(subMenuItemsList.size());
                subMenuItemsList.get(i).click();*/

                //Assert.assertTrue(isElementPresent(By.tagName("h1")));
              }
        }


    }


    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}
