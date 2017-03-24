package by.stqa.selenium.tasks1_17;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;


public class Task9_2 extends TestBaseOld {

    @Test
    public void zonesAlphabeticallySorted() {

        driver.navigate().to("http://litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(titleIs("Countries | My Store"));

        int quantityOfCountries = driver.findElements(By.cssSelector("tr.row")).size();

        for (int i = 2; i < quantityOfCountries + 2; i++) {

            WebElement country = driver.findElement(By.cssSelector("tr.row:nth-child(" + i + ")"));
            String zonesNumber = country.findElement(By.cssSelector("td:nth-child(6)")).getAttribute("textContent");

            if (!zonesNumber.equals("0")) {
                System.out.println(country.getAttribute("outerText"));
                country.findElement(By.cssSelector("a:not([title=Edit])")).click();
                wait.until(titleIs("Edit Country | My Store"));
                List<WebElement> zonesList = driver.findElements(By.cssSelector("[type=hidden][name*=name]"));
                List<String> zonesNames = new ArrayList<String>();
                List<String> sortedZonesNames = new ArrayList<String>();

                for (int j = 0; j < zonesList.size(); j++) {
                    String value = zonesList.get(j).getAttribute("value");
                    zonesNames.add(value);
                    sortedZonesNames.add(value);
                    Collections.sort(sortedZonesNames);

                    for (int k = 0; k < zonesNames.size(); k++) {
                        if (!zonesNames.get(k).equals(sortedZonesNames.get(k))) {
                            System.out.println("Zones are not alphabetically sorted. Zone # " + i + " is on wrong place.");
                        }
                    }
                }

                if (driver.findElement(By.cssSelector("h1")).getAttribute("outerText").equals(" Edit Country")) {
                    driver.findElement(By.cssSelector("li.selected span.name")).click();
                }
                wait.until(titleIs("Countries | My Store"));

            }
        }
    }
}
