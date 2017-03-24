package by.stqa.selenium.tasks1_17;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class Task10 extends TestBaseOld {

    @Test
    public void productInfoComparance() {
        driver.navigate().to("http://litecart/");

        WebElement campaigns = driver.findElement(By.cssSelector("div#box-campaigns"));
        List<WebElement> products = campaigns.findElements(By.cssSelector("div#box-campaigns a.link"));

        String nameOfProd = products.get(0).findElement(By.cssSelector("div.name")).getAttribute("textContent");
        String priceOfProd = products.get(0).findElement(By.cssSelector("s.regular-price")).getAttribute("textContent");
        String salePriceOfProd = products.get(0).findElement(By.cssSelector("strong.campaign-price")).getAttribute("textContent");

        String colorOfProdPrice = colorToRGB(products.get(0).findElement(By.cssSelector("s.regular-price")).getCssValue("color"));
        String fontOfProdPrice = products.get(0).findElement(By.cssSelector("s.regular-price")).getCssValue("text-decoration");
        String colorOfProdSalePrice = colorToRGB(products.get(0).findElement(By.cssSelector("strong.campaign-price")).getCssValue("color"));
        String isTagStrong = products.get(0).findElement(By.cssSelector("strong.campaign-price")).getTagName();
        String sizeOfRegularPrice = products.get(0).findElement(By.cssSelector("s.regular-price")).getCssValue("font-size");
        String sizeOfSalePrice = products.get(0).findElement(By.cssSelector("strong.campaign-price")).getCssValue("font-size");

        String[] temp1 = sizeOfRegularPrice.split("p");
        String[] temp2 = sizeOfSalePrice.split("p");
        float fontSizeRegular = Float.parseFloat(temp1[0]);
        float fontSizeSale = Float.parseFloat(temp2[0]);

        if (!colorOfProdPrice.equals("(119, 119, 119)")) {
            System.out.println("Main page: Color of regular price is not grey.");
        }
        if (!fontOfProdPrice.equals("line-through")) {
            System.out.println("Main page: Regular price is not crossed out.");
        }
        if (!colorOfProdSalePrice.equals("(204, 0, 0)")) {
            System.out.println("Main page: Color of sale price is not red.");
        }
        if (!isTagStrong.equals("strong")) {
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

        String colorInsideProdPrice = colorToRGB(driver.findElement(By.cssSelector("s.regular-price")).getCssValue("color"));
        String fontInsideProdPrice = driver.findElement(By.cssSelector("s.regular-price")).getCssValue("text-decoration");
        String colorInsideProdPriceSale = colorToRGB(driver.findElement(By.cssSelector("strong.campaign-price")).getCssValue("color"));
        String sizeFontInsideProdPrice = driver.findElement(By.cssSelector("s.regular-price")).getCssValue("font-size");
        String isTagStrongInsideProd = driver.findElement(By.cssSelector("strong.campaign-price")).getTagName();
        String sizeFontInsideProdPriceSale = driver.findElement(By.cssSelector("strong.campaign-price")).getCssValue("font-size");

        String[] temp3 = sizeFontInsideProdPrice.split("p");
        String[] temp4 = sizeFontInsideProdPriceSale.split("p");
        float fontSizeRegular2 = Float.parseFloat(temp3[0]);
        float fontSizeSale2 = Float.parseFloat(temp4[0]);

        if (!colorInsideProdPrice.equals("(102, 102, 102)")) {
            System.out.println("Product page: Color of regular price is not grey.");
        }
        if (!fontInsideProdPrice.equals("line-through")) {
            System.out.println("Product page: Regular price is not crossed out.");
        }
        if (!colorInsideProdPriceSale.equals("(204, 0, 0)")) {
            System.out.println("Product page: Color of sale price is not red.");
        }
        if (!isTagStrongInsideProd.equals("strong")) {
            System.out.println("Product page: Sale price is not bold.");
        }
        if (fontSizeRegular2 > fontSizeSale2) {
            System.out.println("Product page: Size of sale price is not bigger than regular price.");
        }
    }
}
