import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.InterfaceImplementation;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class Task13 extends TestBase {

    @Test
    public void shoppingCart() throws InterruptedException {

        driver.navigate().to("http://litecart/");

        for (int i = 0; i < 3; i++) {
            List<WebElement> products = driver.findElements(By.cssSelector("a.link[title*=Duck]"));
            products.get(0).click();
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
                    Thread.sleep(1000);
                }
            }

            driver.navigate().to("http://litecart/");

        }

        driver.findElement(By.cssSelector("a.link[href*=checkout]")).click();
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
}
