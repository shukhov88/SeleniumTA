import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class Task10 extends TestBase {

    @Test
    public void productInfoComparance() {
        driver.navigate().to("http://localhost/litecart/");

        WebElement campaigns = driver.findElement(By.cssSelector("div#box-campaigns"));
        List<WebElement> products = campaigns.findElements(By.cssSelector("div#box-campaigns a.link"));

        String nameOfProd = products.get(0).findElement(By.cssSelector("div.name")).getAttribute("textContent");
        String priceOfProd = products.get(0).findElement(By.cssSelector("s.regular-price")).getAttribute("textContent");
        String salePriceOfProd = products.get(0).findElement(By.cssSelector("strong.campaign-price")).getAttribute("textContent");

        String colorOfProdPrice = products.get(0).findElement(By.cssSelector("s.regular-price")).getCssValue("color");
        String fontOfProdPrice = products.get(0).findElement(By.cssSelector("s.regular-price")).getCssValue("text-decoration");
        String colorOfProdSalePrice = products.get(0).findElement(By.cssSelector("strong.campaign-price")).getCssValue("color");
        String fontOfProdSalePrice = products.get(0).findElement(By.cssSelector("strong.campaign-price")).getCssValue("font-weight");
        String sizeOfRegularPrice = products.get(0).findElement(By.cssSelector("s.regular-price")).getCssValue("font-size");
        String sizeOfSalePrice = products.get(0).findElement(By.cssSelector("strong.campaign-price")).getCssValue("font-size");

        String[] temp1 = sizeOfRegularPrice.split("p");
        String[] temp2 = sizeOfSalePrice.split("p");
        float fontSizeRegular = Float.parseFloat(temp1[0]);
        float fontSizeSale = Float.parseFloat(temp2[0]);


        System.out.println("");
        System.out.println(colorOfProdPrice);
        System.out.println(fontOfProdPrice);
        System.out.println(colorOfProdSalePrice);
        System.out.println(fontOfProdSalePrice);
        System.out.println(sizeOfRegularPrice);
        System.out.println(sizeOfSalePrice);
        System.out.println("");


        if (!colorOfProdPrice.equals("rgba(119, 119, 119, 1)")) {
            System.out.println("Main page: Color of regular price is not grey.");
        }
        if (!fontOfProdPrice.equals("line-through")) {
            System.out.println("Main page: Regular price is not crossed out.");
        }
        if (!colorOfProdSalePrice.equals("rgba(204, 0, 0, 1)")) {
            System.out.println("Main page: Color of sale price is not red.");
        }
        if (!fontOfProdSalePrice.equals("bold")) {
            System.out.println("Main page: Sale price is not bold.");
        }
        if (fontSizeRegular > fontSizeSale) {
            System.out.println("Main page: Size of sale price is not bigger than regular price.");
        }

        products.get(0).click();

        String compareNameOfProd = driver.findElement(By.cssSelector("h1.title")).getAttribute("textContent");
        String comparePriceOfProd = driver.findElement(By.cssSelector("s.regular-price")).getAttribute("textContent");
        String compareSalePriceOfProd = driver.findElement(By.cssSelector("strong.campaign-price")).getAttribute("textContent");

        if (!nameOfProd.equals(compareNameOfProd)) {
            System.out.println("The name of product on main page is not equal to name on product page.");
        }
        if (!priceOfProd.equals(comparePriceOfProd)) {
            System.out.println("The regular price of product on main page is not equal to regular price on product page.");
        }
        if(!salePriceOfProd.equals(compareSalePriceOfProd)) {
            System.out.println("The sale price of product on main page is not equal to sale price on product page.");
        }

        String colorInsideProdPrice = driver.findElement(By.cssSelector("s.regular-price")).getCssValue("color");
        String fontInsideProdPrice = driver.findElement(By.cssSelector("s.regular-price")).getCssValue("text-decoration");
        String colorInsideProdPriceSale = driver.findElement(By.cssSelector("strong.campaign-price")).getCssValue("color");
        String fontInsideProdPriceSale = driver.findElement(By.cssSelector("strong.campaign-price")).getCssValue("font-weight");
        String sizeFontInsideProdPrice = driver.findElement(By.cssSelector("s.regular-price")).getCssValue("font-size");
        String sizeFontInsideProdPriceSale = driver.findElement(By.cssSelector("strong.campaign-price")).getCssValue("font-size");

        String[] temp3 = sizeFontInsideProdPrice.split("p");
        String[] temp4 = sizeFontInsideProdPriceSale.split("p");
        float fontSizeRegular2 = Float.parseFloat(temp3[0]);
        float fontSizeSale2 = Float.parseFloat(temp4[0]);

        System.out.println(colorInsideProdPrice);
        System.out.println(fontInsideProdPrice);
        System.out.println(colorInsideProdPriceSale);
        System.out.println(fontInsideProdPriceSale);
        System.out.println(sizeFontInsideProdPrice);

        if (!colorInsideProdPrice.equals("rgba(102, 102, 102, 1)")) {
            System.out.println("Product page: Color of regular price is not grey.");
        }
        if (!fontInsideProdPrice.equals("line-through")) {
            System.out.println("Product page: Regular price is not crossed out.");
        }
        if (!colorInsideProdPriceSale.equals("rgba(204, 0, 0, 1)")) {
            System.out.println("Product page: Color of sale price is not red.");
        }
        if (!fontInsideProdPriceSale.equals("bold")) {
            System.out.println("Product page: Sale price is not bold.");
        }
        if (fontSizeRegular2 > fontSizeSale2) {
            System.out.println("Product page: Size of sale price is not bigger than regular price.");
        }

    }
}
