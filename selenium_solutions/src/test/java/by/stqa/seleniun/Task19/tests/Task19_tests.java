package by.stqa.seleniun.Task19.tests;

import by.stqa.seleniun.Task19.app.TestBaseNew;
import org.junit.Test;

public class Task19_tests extends TestBaseNew {

    @Test
    public void shoppingCart() throws InterruptedException {

        cartPageHelper.prodPageHelper.mainPageHelper.goToMainPage();
        cartPageHelper.prodPageHelper.addProductsToCart(3);
        cartPageHelper.openCart();
        cartPageHelper.deleteAllProdsFromCart();
    }
}
