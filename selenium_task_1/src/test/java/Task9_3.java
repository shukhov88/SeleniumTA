import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class Task9_3 extends TestBase {

    @Test
    public void geoZonesAlphabeticallySorted() {
        driver.navigate().to("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(titleIs("Geo Zones | My Store"));

        int zonesQuantity = driver.findElements(By.cssSelector("tr.row")).size();

        for (int i = 2; i < zonesQuantity + 2; i++) {
            WebElement zone = driver.findElement(By.cssSelector("tr.row:nth-child(" + i + ")"));
            zone.findElement(By.cssSelector("a:not([title=Edit])")).click();

            List<WebElement> geoList = driver.findElements(By.cssSelector("select[name*=zone_code] option[selected=selected]"));

            List<String> geoNames = new ArrayList<String>();
            List<String> sortedGeoNames = new ArrayList<String>();

            for (int j = 0; j < geoList.size(); j++) {
                String geoZone = geoList.get(i).getAttribute("textContent");
                geoNames.add(geoZone);
                sortedGeoNames.add(geoZone);
            }

            Collections.sort(sortedGeoNames);

            for (int k = 0; k < geoNames.size(); k++) {
                if (!geoNames.get(k).equals(sortedGeoNames.get(k))) {
                    System.out.println("Zones are not alphabetically sorted. Zone # " + i + " is on wrong place.");
                }
            }

            driver.findElement(By.cssSelector("li.selected span.name")).click();
            wait.until(titleIs("Geo Zones | My Store"));
        }
    }
}
