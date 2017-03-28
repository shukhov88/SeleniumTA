package by.stqa.seleniun.Task19.tests;

import by.stqa.seleniun.Task19.app.TestBaseNew;
import org.junit.Test;

public class Task19_tests extends TestBaseNew {

    @Test
    public void shoppingCart() throws InterruptedException {

        application.getMainPage().goToMainPage();
        application.getProdPage().addProductsToCart(3);
        application.getCartPage().openCart();
        application.getCartPage().deleteAllProdsFromCart();
    }
}
