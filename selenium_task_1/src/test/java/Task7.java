import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;


public class Task7 extends TestBase {

    @Test
    public void menuItemsTest () {
        driver.get("http://litecart/admin/");
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
            Assert.assertTrue(isElementPresent(driver, By.tagName("h1")));

            //Определяем, есть ли у данного основного пункта меню, вложенные пункты
            if (areElementsPresent(driver, By.cssSelector("[id^=doc-]"))) {
                //Находим количество вложенных пунктов меню относительно текущего основного пункта
                int subItemsQuantity = driver.findElements(By.cssSelector("[id^=doc-]")).size();

                //Цикл который должен прокликать все вложенные пункты текущего основного пункта, если они существуют
                for (int j = 1; j < subItemsQuantity + 1; j++) {
                    WebElement subMenuItem = driver.findElement(By.cssSelector("[id^=doc-]:nth-child(" + j + ")"));
                    subMenuItem.click();
                    Assert.assertTrue(isElementPresent(driver, By.tagName("h1")));
                }
            }
        }
    }
}
