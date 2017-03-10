import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;


public class Task8 extends TestBase {

    @Test
    public void stickerIsPresent() {

        driver.navigate().to("http://litecart/");

        //Получаем список товаров на главной странице
        List<WebElement> productsList = driver.findElements(By.cssSelector("a.link[title*=Duck]"));

        //В цикле проверяем, какое количество стикеров имеет каждый товар
        for (int i = 0; i < productsList.size(); i++) {
            if (productsList.get(i).findElements(By.cssSelector("[class^=sticker]")).size() != 1) {
                //Если количесвто стикеров не равно 1, то в консоль будет выведена инфа по продукту и том, что у него > или < 1 стикера
                System.out.println(productsList.get(i).getAttribute("href") + " has more or less than 1 sticker");
            }
        }
    }
}
