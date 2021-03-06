package by.stqa.selenium.tasks1_17;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;


public class Task17 extends TestBaseOld {

    @Test
    public void newProductCreation() {
        driver.manage().window().maximize();
        driver.navigate().to("http://litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        driver.findElement(By.cssSelector("li#app-:nth-child(2)")).click();
        driver.findElement(By.cssSelector("table.dataTable tr:nth-child(3) a:not([href*=edit])")).click();

        int prodQuantity = driver.findElements(By.cssSelector("tr.row [href*=edit]:not([title=Edit])")).size();

        for (int i = 0; i < prodQuantity; i++) {
            List<WebElement> prodList = driver.findElements(By.cssSelector("tr.row [href*=edit]:not([title=Edit])"));
            prodList.get(i).click();

            driver.manage().logs().get("browser").forEach(l -> System.out.println(l));

            driver.findElement(By.cssSelector("li#app-:nth-child(2) li#doc-catalog")).click();
            driver.findElement(By.cssSelector("table.dataTable tr:nth-child(3) a:not([href*=edit])")).click();

        }
    }
}
