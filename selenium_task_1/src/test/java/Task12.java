import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class Task12 extends TestBase {

    @Test
    public void geoZonesAlphabeticallySorted() {
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

    }

}
