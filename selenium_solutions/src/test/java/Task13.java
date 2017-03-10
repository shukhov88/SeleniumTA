import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class Task13 extends TestBase {

    @Test
    public void shoppingCart() {

        driver.navigate().to("http://localhost/litecart/");

        for (int i = 0; i < 3; i++) {
            List<WebElement> products = driver.findElements(By.cssSelector("a.link[title*=Duck]"));
            products.get(0).click();
            if (driver.findElement(By.cssSelector("[name*=options]")).isDisplayed()) {
                Select size = new Select(driver.findElement(By.cssSelector("[name*=options]")));
                size.selectByIndex(1);
            }
            driver.findElement(By.cssSelector("[name=add_cart_product]")).click();
            //здесь будет ожидание, пока товар добавится в корзину.
        }

        driver.findElement(By.cssSelector("a.link[href*=checkout]")).click();

        int num = driver.findElements(By.cssSelector("li.shortcut")).size();
        for (int i = 0; i < num; i++) {
            if (i < num - 1) {
                List<WebElement> previews = driver.findElements(By.cssSelector("li.shortcut"));
                previews.get(0).click();
                driver.findElement(By.cssSelector("[name=remove_cart_item]")).click();
                //здесь будет ожидание, пока товар добавится в корзину.
            } else {
                driver.findElement(By.cssSelector("[name=remove_cart_item]")).click();
                //здесь будет ожидание, пока товар добавится в корзину.
            }
        }
    }
}
