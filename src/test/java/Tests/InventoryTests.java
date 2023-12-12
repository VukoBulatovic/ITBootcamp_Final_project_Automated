package Tests;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class InventoryTests extends BaseTest {

    @BeforeMethod
    public void pageSetUp(){
        goToBaseUrl();
        loggedIn();
    }

    //-----------------------------------------------------------------------------------

    @Test
    public void verifyUserCanAddItemToCart() throws InterruptedException {

        inventoryPage.addToCartButton.get(0).click();
        Thread.sleep(3000);
        Assert.assertTrue(inventoryPage.removeButton.get(0).isDisplayed());
        Assert.assertEquals(inventoryPage.removeButton.get(0).getText(),"Remove");
        Assert.assertTrue(inventoryPage.cartItemsNumber.isDisplayed());
        Assert.assertEquals(inventoryPage.cartItemsNumber.getText(),"1");
        inventoryPage.clickOnCart();
        Assert.assertEquals(driver.getCurrentUrl(),baseURL + "cart.html");
        Assert.assertTrue(cartPage.yourCartHeader.isDisplayed());
        Assert.assertTrue(cartPage.itemInCart.isDisplayed());
        Assert.assertTrue(cartPage.removeButton.isDisplayed());
        Assert.assertEquals(cartPage.cartQuantity.get(0).getText(),"1");
        Assert.assertEquals(cartPage.numbersOfItemsOnCartIcon.getText(),"1");
    }

    @Test

    public void verifyUserCanAddAllItemsToCart(){

        for (int i = inventoryPage.addToCartButton.size() - 1; i >= 0; i--) {
            inventoryPage.addToCartButton.get(i).click();
        }
        Assert.assertEquals(inventoryPage.cartItemsNumber.getText(),"6");
        inventoryPage.clickOnCart();
        int currentItems = cartPage.numbersOfItemsInCart();
        int totalItems = 6;
        if(totalItems != currentItems){
            Assert.fail();
        }
        Assert.assertTrue(cartPage.checkOutButton.isDisplayed());
        for (int i = 0; i < cartPage.cartQuantity.size(); i++) {
            Assert.assertEquals(cartPage.cartQuantity.get(i).getText(),"1");
        }
    }







}
