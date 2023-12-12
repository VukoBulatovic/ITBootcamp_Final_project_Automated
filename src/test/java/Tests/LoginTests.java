package Tests;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    @BeforeMethod
    public void pageSetUp(){
        goToBaseUrl();
    }

    //Logovanje sa validnim kredencijalima
    @Test
    public void verifyUserCanLogInUsingValidCredentials() throws InterruptedException {
        String validUsername = excelReader.getStringData("Login",1,0);
        String validPassword = excelReader.getStringData("Login",1,1);

        loginPage.inputUsername(validUsername);
        loginPage.inputPassword(validPassword);
        loginPage.clickOnLoginButton();
        inventoryPage.clickOnSideBarMenu();
        Assert.assertEquals(driver.getCurrentUrl(),baseURL + "inventory.html");
        Assert.assertTrue(inventoryPage.logoutButton.isDisplayed());
    }

    //Logovanje sa kolacicima
    @Test
    public void verifyUserCanLogInWithCookies() throws InterruptedException {
        loginPage.addACookies();
        driver.navigate().to(baseURL + "inventory.html");
        driver.navigate().refresh();
        inventoryPage.clickOnSideBarMenu();
        Assert.assertTrue(inventoryPage.logoutButton.isDisplayed());
        Assert.assertEquals(driver.getCurrentUrl(),baseURL + "inventory.html");
    }

    //Logovanje sa nevalidnim username-om i validnom sifrom
    @Test
    public void verifyUserCanNotLogInUsingInvalidUsername(){

        for (int i = 1; i < excelReader.getLastRow("Login"); i++) {
            String invalidUsername = excelReader.getStringData("Login",i,2);
            String validPassword = excelReader.getStringData("Login",1,1);
            loginPage.inputUsername(invalidUsername);
            loginPage.inputPassword(validPassword);
            loginPage.clickOnLoginButton();
            Assert.assertTrue(loginPage.errorMessage.isDisplayed());
        }
    }

    //Logovanje sa validnim username-om i nevalidnim lozinkama
    @Test
    public void verifyUserCanNotLogInUsingInvalidPassword(){

        for (int i = 1; i < excelReader.getLastRow("Login"); i++) {
            String validUsername = excelReader.getStringData("Login",1,0);
            String invalidPassword = excelReader.getStringData("Login",i,3);
            loginPage.inputUsername(validUsername);
            loginPage.inputPassword(invalidPassword);
            loginPage.clickOnLoginButton();
            Assert.assertTrue(loginPage.errorMessage.isDisplayed());
        }
    }

    //Provjera izlogovanja
    @Test
    public void verifyUserCanLogOut() throws InterruptedException {
        successfulLogIn();
        inventoryPage.clickOnSideBarMenu();
        inventoryPage.clickOnLogOutButton();
        Assert.assertEquals(baseURL,driver.getCurrentUrl());
        Assert.assertTrue(loginPage.loginButton.isDisplayed());
    }
}
