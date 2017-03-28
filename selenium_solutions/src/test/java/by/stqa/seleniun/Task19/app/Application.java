package by.stqa.seleniun.Task19.app;

import by.stqa.seleniun.Task19.pages.CartPageHelper;
import by.stqa.seleniun.Task19.pages.MainPageHelper;
import by.stqa.seleniun.Task19.pages.ProdPageHelper;
import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Application {

    WebDriver driver;
    public WebDriverWait wait;
    public MainPageHelper mainPage;
    public ProdPageHelper prodPage;
    public CartPageHelper cartPage;


    public void init() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        mainPage = new MainPageHelper(driver);
        prodPage = new ProdPageHelper(driver);
        cartPage = new CartPageHelper(driver);
    }

    public void stop() {
        driver.quit();
        driver = null;
    }

    public MainPageHelper getMainPage() {
        return mainPage;
    }

    public ProdPageHelper getProdPage() {
        return prodPage;
    }

    public CartPageHelper getCartPage() {
        return cartPage;
    }
}
