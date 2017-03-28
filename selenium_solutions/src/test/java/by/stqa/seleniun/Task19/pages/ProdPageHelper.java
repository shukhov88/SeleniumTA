package by.stqa.seleniun.Task19.pages;


import by.stqa.seleniun.Task19.app.Application;
import by.stqa.seleniun.Task19.app.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class ProdPageHelper extends BaseHelper {

    public ProdPageHelper(WebDriver driver) {
        super(driver);
    }

    public void addProductsToCart(int quantity) throws InterruptedException {
        for (int i = 0; i < quantity; i++) {
            new MainPageHelper(driver).openProduct();
            if (new BaseHelper(driver).isElementPresent(driver, By.cssSelector("[name*=options]"))) {
                Select size = new Select(driver.findElement(By.cssSelector("[name*=options]")));
                size.selectByIndex(1);
            }
            driver.findElement(By.cssSelector("[name=add_cart_product]")).click();

            String quant2 = Integer.toString(i+1);

            for (int j = 0;; j++) {
                String quant = driver.findElement(By.cssSelector("span.quantity")).getAttribute("textContent");
                if (j > 10)
                    throw new TimeoutException();
                if (quant.equals(quant2)) {
                    break;
                } else {
                    Thread.sleep(500);
                }
            }

            new MainPageHelper(driver).goToMainPage();
        }
    }

}
