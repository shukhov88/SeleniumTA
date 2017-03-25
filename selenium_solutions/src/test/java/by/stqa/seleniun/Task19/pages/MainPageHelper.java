package by.stqa.seleniun.Task19.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MainPageHelper {
    public WebDriver driver;

    public void openProduct() {
        List<WebElement> products = driver.findElements(By.cssSelector("a.link[title*=Duck]"));
        products.get(0).click();
    }

    public void goToMainPage() {
        driver.navigate().to("http://litecart/");
    }
}
