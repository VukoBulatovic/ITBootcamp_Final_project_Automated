package Tests;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckOutSecondPageTests extends BaseTest {

    @BeforeMethod
    public void pageSetUp(){
        goToBaseUrl();
        loggedIn();
        goToCheckout();
        checkoutFirstPage.submitValidData();
    }

    @Test
    public void verifyCalculationsOfPrices(){
        double tolerance = 0.01;
        Assert.assertEquals(String.valueOf(checkoutSecondPage.itemsList.size()),checkoutSecondPage.itemsOnCartIcon.getText());
        Assert.assertEquals(checkoutSecondPage.getPrice(),checkoutSecondPage.calculationOfItemTotalPrice());
        Assert.assertEquals(checkoutSecondPage.getTotalPrice(),checkoutSecondPage.calculationOfTotalPrice(),tolerance);
        Assert.assertEquals(checkoutSecondPage.getTotalTax(),checkoutSecondPage.calculationOfTotalTax(),tolerance);
    }

    @Test
    public void verifyUserCanRemoveItem(){
        double tolerance = 0.01;
        checkoutSecondPage.itemsList.get(0).findElement(By.className("inventory_item_name")).click();
        itemPage.removeButton.click();
        driver.navigate().back();
        Assert.assertEquals(String.valueOf(checkoutSecondPage.itemsList.size()),checkoutSecondPage.itemsOnCartIcon.getText());
        Assert.assertEquals(checkoutSecondPage.getPrice(),checkoutSecondPage.calculationOfItemTotalPrice());
        Assert.assertEquals(checkoutSecondPage.getTotalPrice(),checkoutSecondPage.calculationOfTotalPrice(),tolerance);
        Assert.assertEquals(checkoutSecondPage.getTotalTax(),checkoutSecondPage.calculationOfTotalTax(),tolerance);
    }

    @Test
    public void verifyCancelFinishOrder(){
        checkoutSecondPage.clickOnCancelButton();
        Assert.assertEquals(driver.getCurrentUrl(),baseURL+"inventory.html");
        Assert.assertTrue(inventoryPage.listOfAddToCartButtons.isEmpty());
        Assert.assertFalse(inventoryPage.checkIfCartIsEmpty());
        driver.navigate().back();
        checkoutSecondPage.clickOnFinishButton();
        Assert.assertEquals(driver.getCurrentUrl(),baseURL + "checkout-complete.html");
        Assert.assertTrue(checkOutFinalPage.successMessage.isDisplayed());
    }

    @AfterMethod
    public void pageReset(){
        clearSesionAndLocalStorage();
    }


}
