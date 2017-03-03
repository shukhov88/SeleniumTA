import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class Task9 extends TestBase {

    @Test
    public void countriesAlphabeticallySorted() {

        driver.navigate().to("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(titleIs("Countries | My Store"));

        List<WebElement> countriesList = driver.findElements(By.cssSelector("tr.row a:not([title=Edit])"));

        List<String> countryNames = new ArrayList<String>();
        List<String> sortedCountryNames = new ArrayList<String>();

        for (int i = 0; i < countriesList.size(); i++) {
            String temp = countriesList.get(i).getAttribute("textContent");
            countryNames.add(temp);
            sortedCountryNames.add(temp);
        }

        Collections.sort(sortedCountryNames);

        for (int i = 0; i < countryNames.size(); i++) {
            if (!countryNames.get(i).equals(sortedCountryNames.get(i))) {
                System.out.println("Countries are not alphabetically sorted. Country # " + i + " is on wrong place.");
            }
        }
    }

    @Test
    public void zonesAlphabeticallySorted() {

        driver.navigate().to("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(titleIs("Countries | My Store"));

        int quantityOfCountris = driver.findElements(By.cssSelector("tr.row")).size();

        for (int i = 1; i < quantityOfCountris + 1; i++) {
            WebElement country = driver.findElement(By.cssSelector("tr.row:nth-child(" + i + ")"));
            String zonesNumber = country.findElement(By.cssSelector("td:nth-child(6)")).getAttribute("textContent");
            if (!zonesNumber.equals("0")) {
                country.findElement(By.cssSelector("a:not([title=Edit])")).click();
                List<WebElement> zonesList = driver.findElements(By.cssSelector("[type=hidden][name*=name]"));
                List<String> zonesNames = new ArrayList<String>();
                List<String> sortedZonesNames = new ArrayList<String>();
                for (int j = 0; j < zonesList.size(); j++) {
                    String value = zonesList.get(i).getAttribute("value");
                    zonesNames.add(value);
                    sortedZonesNames.add(value);
                    Collections.sort(sortedZonesNames);

                    for (int k = 0; k < zonesNames.size(); k++) {
                        if (!zonesNames.get(i).equals(sortedZonesNames.get(i))) {
                            System.out.println("Zones are not alphabetically sorted. Zone # " + i + " is on wrong place.");
                        }
                    }
                }
            }
        }
    }

}
