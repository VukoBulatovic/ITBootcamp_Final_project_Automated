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

        inventoryPage.clickOnAddToCartButton();
        Assert.assertTrue(inventoryPage.listOfRemoveButtons.get(0).isDisplayed());
        Assert.assertTrue(inventoryPage.checkIfCartIsEmpty());
        inventoryPage.clickOnCart();
        Assert.assertEquals(driver.getCurrentUrl(),baseURL + "cart.html");
        Assert.assertTrue(cartPage.itemsInCart.get(0).isDisplayed());
        Assert.assertEquals(cartPage.cartQuantity.get(0).getText(),"1");
        Assert.assertEquals(cartPage.cartIcon.getText(),"1");
    }

    @Test

    public void verifyUserCanAddAllItemsToCart() throws InterruptedException {

        for (int i = inventoryPage.listOfAddToCartButtons.size() - 1; i >= 0; i--) {
            inventoryPage.listOfAddToCartButtons.get(i).click();
        }
        Thread.sleep(3000);
        Assert.assertEquals(inventoryPage.cartIcon.getText(),String.valueOf(inventoryPage.listOfItems.size()));
        inventoryPage.clickOnCart();
        Assert.assertEquals(String.valueOf(cartPage.numbersOfItemsInCart()),cartPage.cartIcon.getText());
        Assert.assertTrue(cartPage.checkOutButton.isDisplayed());
      //TODO:   //checkQuantity();
    }

    @Test

    public void verifyUserHasItemsInCartAfterLogOut() throws InterruptedException {
        Assert.assertTrue(inventoryPage.checkIfCartIsEmpty());
        addAllItems();
        logOut();
        successfulLogIn();
        Assert.assertEquals(String.valueOf(inventoryPage.listOfItems.size()),inventoryPage.cartIcon.getText());
        Assert.assertTrue(inventoryPage.listOfAddToCartButtons.isEmpty());
    }









}
