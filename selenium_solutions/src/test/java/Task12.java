import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.util.List;

public class Task12 extends TestBase {

    @Test
    public void newProductCreation() {
        driver.navigate().to("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        driver.findElement(By.cssSelector("li#app-:nth-child(2)")).click();
        driver.findElement(By.cssSelector("a.button:nth-child(2)")).click();
        driver.findElement(By.cssSelector("label:nth-child(3)")).click();
        driver.findElement(By.cssSelector("[name^=name]")).sendKeys("test");
        driver.findElement(By.cssSelector("[name=code]")).sendKeys("test");
        driver.findElement(By.cssSelector("[data-name*=Ducks]")).click();

        Select rootList = new Select(driver.findElement(By.cssSelector("[name=default_category_id]")));
        rootList.selectByIndex(1);

        List<WebElement> gender = driver.findElements(By.cssSelector("[name^=product_groups]"));
        gender.get(0).click();

        driver.findElement(By.cssSelector("[name=quantity]")).clear();
        driver.findElement(By.cssSelector("[name=quantity]")).sendKeys("10");

        Select status = new Select(driver.findElement(By.cssSelector("[name=sold_out_status_id]")));
        status.selectByIndex(0);

        File picPath = new File("src/images/test.png");
        driver.findElement(By.cssSelector("[type=file]")).sendKeys(picPath.getAbsolutePath());

        driver.findElement(By.cssSelector("[name=date_valid_from]")).sendKeys("03102017");
        driver.findElement(By.cssSelector("[name=date_valid_to]")).sendKeys("03202017");

        driver.findElement(By.cssSelector("[href*=information]")).click();

        Select manufacturer = new Select(driver.findElement(By.cssSelector("[name=manufacturer_id]")));
        manufacturer.selectByIndex(1);

        driver.findElement(By.cssSelector("[name*=short_description]")).sendKeys("test");
        driver.findElement(By.cssSelector("div.trumbowyg-editor")).sendKeys("test");
        driver.findElement(By.cssSelector("[name*=head_title]")).sendKeys("test");
        driver.findElement(By.cssSelector("[name*=meta_description]")).sendKeys("test");

        driver.findElement(By.cssSelector("[href*=tab-prices]")).click();

        driver.findElement(By.cssSelector("[name=purchase_price]")).clear();
        driver.findElement(By.cssSelector("[name=purchase_price]")).sendKeys("10");

        Select currency = new Select(driver.findElement(By.cssSelector("[name=purchase_price_currency_code]")));
        currency.selectByIndex(1);

        driver.findElement(By.cssSelector("[name*=gross_prices][name*=USD]")).clear();
        driver.findElement(By.cssSelector("[name*=gross_prices][name*=USD]")).sendKeys("20");

        driver.findElement(By.cssSelector("[name=save]")).click();
    }
}
