import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class Task9_3 extends TestBase {

    @Test
    public void geoZonesAlphabeticallySorted() {
        driver.navigate().to("http://litecart/admin/?app=geo_zones&doc=geo_zones");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(titleIs("Geo Zones | My Store"));

        int zonesQuantity = driver.findElements(By.cssSelector("tr.row")).size();

        for (int i = 2; i < zonesQuantity + 2; i++) {
            WebElement zone = driver.findElement(By.cssSelector("tr.row:nth-child(" + i + ")"));
            zone.findElement(By.cssSelector("a:not([title=Edit])")).click();
        }
    }
}
