import org.junit.Test;
import org.openqa.selenium.By;

public class Task11 extends TestBase {

    @Test
    public void newUserCreation() {

        driver.navigate().to("http://litecart/");
        driver.findElement(By.cssSelector("div#box-account-login a")).click();
        driver.findElement(By.cssSelector("[name=firstname]")).sendKeys("Vladimir");
        driver.findElement(By.cssSelector("[name=lastname]")).sendKeys("Shukhov");
        driver.findElement(By.cssSelector("[name=address1]")).sendKeys("1-1, Independance street");
        driver.findElement(By.cssSelector("[name=postcode]")).sendKeys("12345");
        driver.findElement(By.cssSelector("[name=city]")).sendKeys("OceanCity");
        driver.findElement(By.cssSelector("[name=phone]")).sendKeys("123456789y");



        driver.findElement(By.cssSelector("[name=email]")).sendKeys("a" + counter + "@mali.bu");




    }
}
