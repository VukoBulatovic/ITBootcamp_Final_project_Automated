package Tests;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp(){
        driver.navigate().to("https://www.saucedemo.com/");
    }

    //VerifyLoginPageElements
//LoginWithValidCredentials
//LoginWithInvalidUsername
//LoginWithInvalidPassword
//VerifyLoginButtonFunctionality


//Ulogujte se na demoqa(https://demoqa.com/ -> Book Store Application) preko cookies-a ,
//dodati dve knjige na svoj nalog,
//zatim se izlogovati brisanjem cookies-a.
//Ulogovati se ponovo preko log-in forme
//i potvrditi da se knjige i dalje nalaze na nalogu.

    @Test
    public void verifyUserCanLogInUsingValidCredentials() throws InterruptedException {
        String validUsername = excelReader.getStringData("Login",1,0);
        String validPassword = excelReader.getStringData("Login",1,1);
        String expectedUrl = "https://www.saucedemo.com/inventory.html";

        loginPage.inputUsername(validUsername);
        loginPage.inputPassword(validPassword);
        loginPage.clickOnLoginButton();

        Assert.assertEquals(driver.getCurrentUrl(),expectedUrl);
        inventoryPage.clickOnDropDownMenu();
        Assert.assertTrue(inventoryPage.logoutButton.isDisplayed());
    }

    @Test
    public void verifyUserCanNotLogInUsingInvalidCredentials(){

        for (int i = 1; i < excelReader.getLastRow("Login"); i++) {
            String invalidUsername = excelReader.getStringData("Login",i,2);
            String invalidPassword = excelReader.getStringData("Login",i,3);
            loginPage.inputUsername(invalidUsername);
            loginPage.inputPassword(invalidPassword);
            loginPage.clickOnLoginButton();
            Assert.assertTrue(loginPage.error.isDisplayed());
        }






    }























}
