package by.stqa.seleniun.Task19.pages;


import by.stqa.seleniun.Task19.app.Application;
import by.stqa.seleniun.Task19.app.BaseHelper;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CartPageHelper extends BaseHelper{

    public CartPageHelper(WebDriver driver) {
        super(driver);
    }

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
        /*wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("em")));
        Assert.assertTrue(driver.findElement(By.cssSelector("em")).getAttribute("textContent").equals(
                "There are no items in your cart."));*/
    }

    public void openCart() {
        driver.findElement(By.cssSelector("a.link[href*=checkout]")).click();
    }
}
