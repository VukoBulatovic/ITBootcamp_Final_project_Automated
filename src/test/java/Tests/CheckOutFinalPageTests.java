package Tests;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckOutFinalPageTests extends BaseTest {

    @BeforeMethod
    public void pageSetUp(){
        goToBaseUrl();
        loggedIn();
        goToCheckout();
        checkoutFirstPage.submitValidData();
        checkoutSecondPage.clickOnFinishButton();
    }

    @Test
    public void verifyUserCanCompleteOrder(){
        checkoutSecondPage.clickOnFinishButton();
        Assert.assertEquals(driver.getCurrentUrl(),baseURL + "checkout-complete.html");
        Assert.assertTrue(checkOutFinalPage.successTitle.isDisplayed());
        Assert.assertTrue(checkOutFinalPage.successMessage.isDisplayed());
        Assert.assertEquals(checkOutFinalPage.successMessage.getText(),"Thank you for your order!");
        Assert.assertTrue(inventoryPage.checkIfCartIsEmpty());
        checkOutFinalPage.clickOnBackHomeButton();
        Assert.assertEquals(driver.getCurrentUrl(),baseURL + "inventory.html");
        Assert.assertTrue(inventoryPage.listOfRemoveButtons.isEmpty());
    }

    @AfterMethod
    public void pageReset(){
        clearSesionAndLocalStorage();
    }


}
