package by.stqa.seleniun.Task19.app;


import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class BaseHelper {

    public WebDriver driver;

    public BaseHelper(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isElementNotPresent(WebDriver driver, By locator) {
        try {
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            return driver.findElements(locator).size() == 0;
        } finally {
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        }

    }

    public boolean areElementsPresent(WebDriver driver, By locator) {
        return driver.findElements(locator).size() > 0;
    }

    public String colorToRGB(String rgba) {
        String delimiters = "[a-z(), ]+";
        String[] nums = rgba.split(delimiters);
        String color = Arrays.toString(Arrays.copyOfRange(nums, 1, 4));
        color = color.replace("[", "(").replace("]", ")");
        return color;
    }

    public String randomNum() {
        Date today = Calendar.getInstance().getTime();
        DateFormat df = new SimpleDateFormat("ddMMyyyyHHmmss");
        String num = df.format(today);
        return num;
    }

    public ExpectedCondition<String> anyWindowOtherThan(Set<String> oldWindows) {
        return new ExpectedCondition<String>() {
            public String apply(WebDriver driver) {
                Set<String> handles = driver.getWindowHandles();
                handles.removeAll(oldWindows);
                return handles.size() > 0 ? handles.iterator().next() : null;
            }
        };
    }

    public boolean isElementPresent(WebDriver driver, By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }
}
