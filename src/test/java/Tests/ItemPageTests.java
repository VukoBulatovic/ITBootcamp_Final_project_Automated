package Tests;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ItemPageTests extends BaseTest {


    @BeforeMethod
    public void pageSetUp(){
        goToBaseUrl();
        loggedIn();
    }

    @Test
    public void verifyItemIsProperlyDisplayed(){
        String nameInvPage = inventoryPage.getNameOfItem();
        inventoryPage.clickOnItemName();
        String nameItemPage = itemPage.getNameOfItem();
        String priceItemPage = itemPage.getPriceOfItem();
        Assert.assertEquals(nameInvPage,nameItemPage);  //nameOfItemCheck
        itemPage.clickOnBackToProductsButton();
        String priceInvPage = inventoryPage.getPriceOfItem();
        Assert.assertEquals(priceItemPage,priceInvPage); //priceOfItemCheck
        String descriptionInvPage = inventoryPage.getDescriptionOfItem();
        inventoryPage.clickOnItemName();
        String decriptionItemPage = itemPage.getItemDesription();
        Assert.assertEquals(decriptionItemPage,descriptionInvPage); //descriptionOfItemCheck
    }

    @Test
    public void verifyAddRemoveButton(){
        inventoryPage.clickOnItemName();
        itemPage.clickOnAddToCartButton();
        Assert.assertTrue(itemPage.removeButton.isDisplayed());
        Assert.assertTrue(itemPage.cartIconQuantity.isDisplayed());
        Assert.assertEquals(itemPage.cartIconQuantity.getText(),"1");
        itemPage.clickOnBackToProductsButton();
        Assert.assertTrue(inventoryPage.listOfRemoveButtons.remove(0).isDisplayed());
        Assert.assertEquals(inventoryPage.getButtonText(),itemPage.getButtonText());
        inventoryPage.listOfRemoveButtons.get(0).click();
        inventoryPage.clickOnItemName();
        Assert.assertTrue(itemPage.addToCartButton.isDisplayed());
    }




    @AfterMethod
    public void pageReset(){
        clearSesionAndLocalStorage();
    }







}
