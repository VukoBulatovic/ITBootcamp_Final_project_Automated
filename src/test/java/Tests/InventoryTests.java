package Tests;

import Base.BaseTest;
import org.openqa.selenium.support.ui.Select;
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

    public void verifyUserCanAddRemoveAllItemsToCart() throws InterruptedException {
        Assert.assertTrue(inventoryPage.checkIfCartIsEmpty());
        inventoryPage.addRemoveAllItems("add");
        Thread.sleep(3000);
        Assert.assertEquals(inventoryPage.numberOnCartIcon.getText(),String.valueOf(inventoryPage.listOfItems.size()));
        inventoryPage.clickOnCart();
        Assert.assertEquals(String.valueOf(cartPage.numbersOfItemsInCart()),cartPage.cartIcon.getText());
        Assert.assertTrue(cartPage.checkOutButton.isDisplayed());
        Assert.assertTrue(cartPage.quantityCheck());
        driver.navigate().back();
        inventoryPage.addRemoveAllItems("remove");
        Assert.assertTrue(inventoryPage.checkIfCartIsEmpty());
    }

    @Test
    public void verifyUserHasItemsInCartAfterLogOut() throws InterruptedException {
        Assert.assertTrue(inventoryPage.checkIfCartIsEmpty());
        addAllItems();
        logOut();
        successfulLogIn();
        Assert.assertEquals(String.valueOf(inventoryPage.listOfItems.size()),inventoryPage.numberOnCartIcon.getText());
        Assert.assertTrue(inventoryPage.listOfAddToCartButtons.isEmpty());
    }

    @Test
    public void verifyItemsSorting() {
        Select select = new Select(inventoryPage.sortDropDownMenu);
        select.selectByValue("az");
        Assert.assertTrue(inventoryPage.alphabeticallySorted(inventoryPage.listOfItems));
        select.selectByValue("za");
        Assert.assertFalse(inventoryPage.alphabeticallySorted(inventoryPage.listOfItems));
        select.selectByValue("lohi");
        Assert.assertTrue(inventoryPage.itemsSortedByPrice(inventoryPage.listOfPrices));
        select.selectByValue("hilo");
        Assert.assertFalse(inventoryPage.itemsSortedByPrice(inventoryPage.listOfPrices));
    }

    @Test
    public void verifySocialMediaEndPoints(){
        inventoryPage.clickOnTwitter();
        inventoryPage.switchTabs();
        driver.switchTo().window(inventoryPage.listOfTabs.get(1));
        Assert.assertEquals(driver.getCurrentUrl(),"https://twitter.com/saucelabs");
        driver.switchTo().window(inventoryPage.listOfTabs.get(0));
        inventoryPage.clickOnFacebook();
        inventoryPage.switchTabs();
        driver.switchTo().window(inventoryPage.listOfTabs.get(2));
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.facebook.com/saucelabs");
        driver.switchTo().window(inventoryPage.listOfTabs.get(0));
        inventoryPage.clickOnLinkedIn();
        inventoryPage.switchTabs();
        driver.switchTo().window(inventoryPage.listOfTabs.get(3));
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.linkedin.com/company/sauce-labs/");
    }

    @Test

    public void verifyAllItemsAreProperlyDisplayed(){
        //TODO:
    }






}
