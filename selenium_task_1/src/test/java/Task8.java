import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class Task8 extends TestBase {

    @Test
    public void stickerIsPresent() {

        driver.navigate().to("http://litecart/");

        //Получаем список товаров на главной странице
        List<WebElement> productsList = driver.findElements(By.cssSelector("[class^=product]"));

        //В цикле проверяем, какое количество стикеров имеет каждый товар
        for (int i = 0; i < productsList.size(); i++) {
            if (productsList.get(i).findElements(By.cssSelector("[class^=product]")).size() > 1) {
                //Если стикеров бодет больше 1, то в консоль будет выведена инфа по продукту и и том, что у него >1 стикера
                System.out.println(productsList.get(i).getText() + "has more than 1 sticker");
            }
        }
    }
}
