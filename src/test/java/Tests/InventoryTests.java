package Tests;

import Base.BaseTest;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class InventoryTests extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        goToBaseUrl();
        successfulLogIn();
    }

    //-----------------------------------------------------------------------------------

    @Test(priority = 10)
    public void verifyUserCanAddItemToCart(){
        Assert.assertTrue(inventoryPage.checkIfCartIsEmpty());
        inventoryPage.clickOnAddToCartButton();
        Assert.assertTrue(inventoryPage.listOfRemoveButtons.get(0).isDisplayed());
        inventoryPage.clickOnCart();
        Assert.assertEquals(driver.getCurrentUrl(), baseURL + "cart.html");
        Assert.assertTrue(cartPage.itemsInCart.get(0).isDisplayed());
        Assert.assertEquals(cartPage.cartQuantity.get(0).getText(), "1");
        Assert.assertEquals(cartPage.cartIcon.getText(), "1");
    }

    @Test(priority = 20)
    public void verifyUserCanAddRemoveAllItemsToCart() throws InterruptedException {
        Assert.assertTrue(inventoryPage.checkIfCartIsEmpty());
        inventoryPage.addRemoveAllItems("add");
        Thread.sleep(3000);
        Assert.assertEquals(inventoryPage.numberOnCartIcon.getText(), String.valueOf(inventoryPage.listOfItems.size()));
        inventoryPage.clickOnCart();
        Assert.assertEquals(String.valueOf(cartPage.numbersOfItemsInCart()), cartPage.cartIcon.getText());
        Assert.assertTrue(cartPage.checkOutButton.isDisplayed());
        Assert.assertTrue(cartPage.quantityCheck());
        driver.navigate().back();
        inventoryPage.addRemoveAllItems("remove");
        Assert.assertTrue(inventoryPage.checkIfCartIsEmpty());
    }

    @Test(priority = 30)
    public void verifyUserHasItemsInCartAfterLogOut() throws InterruptedException {
        Assert.assertTrue(inventoryPage.checkIfCartIsEmpty());
        addAllItems();
        logOut();
        successfulLogIn();
        Assert.assertEquals(String.valueOf(inventoryPage.listOfItems.size()), inventoryPage.numberOnCartIcon.getText());
        Assert.assertTrue(inventoryPage.listOfAddToCartButtons.isEmpty());
    }

    @Test(priority = 40)
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

    @Test(priority = 1)
    public void verifySocialMediaEndPoints() throws InterruptedException {
        inventoryPage.clickOnTwitter();
        inventoryPage.switchTabs();
        driver.switchTo().window(inventoryPage.listOfTabs.get(1));
        Assert.assertEquals(driver.getCurrentUrl(), "https://twitter.com/saucelabs");
        driver.switchTo().window(inventoryPage.listOfTabs.get(0));
        inventoryPage.clickOnFacebook();
        inventoryPage.switchTabs();
        driver.switchTo().window(inventoryPage.listOfTabs.get(2));
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/saucelabs");
        driver.switchTo().window(inventoryPage.listOfTabs.get(0));
        inventoryPage.clickOnLinkedIn();
        inventoryPage.switchTabs();
        driver.switchTo().window(inventoryPage.listOfTabs.get(3));
        Thread.sleep(6000);
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.linkedin.com/company/sauce-labs/");
    }

    @Test(priority = 45)
    public void verifyAllItemsAreProperlyDisplayed() {
        Assert.assertTrue(inventoryPage.allNamesAreDisplayed());
        Assert.assertTrue(inventoryPage.allPricesAreDisplayed());
        Assert.assertTrue(inventoryPage.allDescriptionsAreDisplayed());
        Assert.assertTrue(inventoryPage.allImagesAreDisplayed());
        Assert.assertTrue(inventoryPage.allAddToCartButtonsAreDisplayed());
    }


    @Test(priority = 46)
    public void verifySideBarEndPoints() {
        inventoryPage.clickOnSideBar();
        Assert.assertTrue(inventoryPage.SideBarMenu.isDisplayed());
        inventoryPage.clickOnAllItems();
        Assert.assertEquals(driver.getCurrentUrl(),baseURL + "inventory.html");
        //Assert.assertFalse(inventoryPage.SideBarMenu.isDisplayed());
        //Notice : Ovdje test pada, "All items" ne zatvara sideBarMenu, pogledati dokumentaciju
        inventoryPage.clickOnAbout();
        Assert.assertEquals(driver.getCurrentUrl(),"https://saucelabs.com/");
        driver.navigate().back();
        inventoryPage.addRemoveAllItems("add");
        inventoryPage.clickOnSideBar();
        inventoryPage.clickOnResetAppState();
        Assert.assertTrue(inventoryPage.checkIfCartIsEmpty());
        //Assert.assertTrue(inventoryPage.allAddToCartButtonsAreDisplayed());
        //Ovdje test pada, "Reset App State" mijenja kolicinu korpe ali ne i "Remove" buttons
        inventoryPage.clickOnCloseSideBar();
    }

    @AfterMethod
    public void pageReset(){
        clearSesionAndLocalStorage();
    }

}