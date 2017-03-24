package by.stqa.selenium.tasks1_17;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class Task9_1 extends TestBaseOld {

    @Test
    public void countriesAlphabeticallySorted() {

        driver.navigate().to("http://litecart/admin/?app=countries&doc=countries");
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
}
