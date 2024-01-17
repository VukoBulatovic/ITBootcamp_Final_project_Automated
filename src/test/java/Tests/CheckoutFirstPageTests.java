package Tests;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckoutFirstPageTests extends BaseTest {

    @BeforeMethod
    public void pageSetUp(){
        goToBaseUrl();
        loggedIn();
        goToCheckout();
    }

    @Test
    public void verifyUserCanCountinueShoppingUsingValidDatas() {

        Assert.assertEquals(driver.getCurrentUrl(),baseURL + "checkout-step-one.html");
        Assert.assertEquals(cartPage.getNumbersOfItemsOnCartIcon(),checkoutFirstPage.numbersOfItemsOnCheckoutPage.getText());
        checkoutFirstPage.firstNameInput("Benjamin");
        Assert.assertEquals(checkoutFirstPage.firstNameField.getAttribute("value"), "Benjamin");
        checkoutFirstPage.lastNameInput("Button");
        Assert.assertEquals(checkoutFirstPage.lastNameField.getAttribute("value"), "Button");
        checkoutFirstPage.zipCodeInput("48009");
        Assert.assertEquals(checkoutFirstPage.postalCodeField.getAttribute("value"),"48009");
        checkoutFirstPage.clickOnCountinueButton();
        Assert.assertEquals(driver.getCurrentUrl(),baseURL + "checkout-step-two.html");
    }

    @Test
    public void verifyUserCanNotCountinueShoppingUsingInvalidDatas(){
        checkoutFirstPage.firstNameInput(" ");
        checkoutFirstPage.lastNameInput(" ");
        checkoutFirstPage.zipCodeInput(" ");
        checkoutFirstPage.clickOnCountinueButton();
        //Ovaj test pada, polja dozvoljavaju nevalidne unose i checkout-step-two stranica se otvara click-om na countinue!
        Assert.assertEquals(driver.getCurrentUrl(),baseURL + "checkout-step-one.html");
        Assert.assertTrue(checkoutFirstPage.erorrMessage.isDisplayed());
        Assert.assertEquals(checkoutFirstPage.erorrMessage.getText(),"Error: Input valid data!");
    }

    @Test
    public void verifyErrorMessageIsPropelyDisplayed() {
        checkoutFirstPage.lastNameInput("Musk");
        checkoutFirstPage.zipCodeInput("11000");
        checkoutFirstPage.clickOnCountinueButton();
        Assert.assertEquals(checkoutFirstPage.erorrMessage.getText(), "Error: First Name is required");
        checkoutFirstPage.firstNameInput("Elon");
        checkoutFirstPage.lastNameClear();
        checkoutFirstPage.clickOnCountinueButton();
        Assert.assertEquals(checkoutFirstPage.erorrMessage.getText(),"Error: Last Name is required");
        checkoutFirstPage.postalCodeClear();
        checkoutFirstPage.lastNameInput("Musk");
        checkoutFirstPage.clickOnCountinueButton();
        Assert.assertEquals(checkoutFirstPage.erorrMessage.getText(),"Error: Postal Code is required");
    }

    @Test
    public void verifyUserCanCancelCheckout(){
        checkoutFirstPage.clickOnCancelButton();
        Assert.assertEquals(driver.getCurrentUrl(),baseURL + "cart.html");
        Assert.assertTrue(cartPage.checkOutButton.isDisplayed());
    }

    @AfterMethod
    public void pageReset(){
        clearSesionAndLocalStorage();
    }
}
