package by.stqa.seleniun.Task19.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class ProdPageHelper {
    public final MainPageHelper mainPageHelper = new MainPageHelper();

    public void addProductsToCart(int quantity) throws InterruptedException {
        for (int i = 0; i < quantity; i++) {
            mainPageHelper.openProduct();
            if (isElementPresent(mainPageHelper.driver, By.cssSelector("[name*=options]"))) {
                Select size = new Select(mainPageHelper.driver.findElement(By.cssSelector("[name*=options]")));
                size.selectByIndex(1);
            }
            mainPageHelper.driver.findElement(By.cssSelector("[name=add_cart_product]")).click();

            String quant2 = Integer.toString(i+1);

            for (int j = 0;; j++) {
                String quant = mainPageHelper.driver.findElement(By.cssSelector("span.quantity")).getAttribute("textContent");
                if (j > 10)
                    throw new TimeoutException();
                if (quant.equals(quant2)) {
                    break;
                } else {
                    Thread.sleep(500);
                }
            }

            mainPageHelper.goToMainPage();
        }
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
