package by.stqa.selenium.tasks1_17;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class Task11 extends TestBaseOld {

    @Test
    public void newUserCreation() {

        driver.navigate().to("http://localhost/litecart/");
        driver.findElement(By.cssSelector("div#box-account-login a")).click();
        driver.findElement(By.cssSelector("[name=firstname]")).sendKeys("Vladimir");
        driver.findElement(By.cssSelector("[name=lastname]")).sendKeys("Shukhov");
        driver.findElement(By.cssSelector("[name=address1]")).sendKeys("1-1, Independance street");
        driver.findElement(By.cssSelector("[name=postcode]")).sendKeys("12345");
        driver.findElement(By.cssSelector("[name=city]")).sendKeys("OceanCity");

        Select countryList = new Select(driver.findElement(By.cssSelector("select.select2-hidden-accessible")));
        countryList.selectByIndex(224);

        Select stateList = new Select(driver.findElement(By.cssSelector("select[name=zone_code]")));
        stateList.selectByIndex(5);

        String random = randomNum();

        driver.findElement(By.cssSelector("[name=phone]")).sendKeys("+1123456789");
        driver.findElement(By.cssSelector("[name=email]")).sendKeys("a" + random + "@mali.bu");
        driver.findElement(By.cssSelector("[name=password]")).sendKeys("Vladimir");
        driver.findElement(By.cssSelector("[name=confirmed_password]")).sendKeys("Vladimir");

        driver.findElement(By.cssSelector("[name=create_account]")).click();

        driver.findElement(By.cssSelector("div.content [href$=logout]")).click();

        driver.findElement(By.cssSelector("[name=email]")).sendKeys("a" + random + "@mali.bu");
        driver.findElement(By.cssSelector("[name=password]")).sendKeys("Vladimir");
        driver.findElement(By.cssSelector("[name=login]")).click();

        driver.findElement(By.cssSelector("div.content [href$=logout]")).click();
    }
}
