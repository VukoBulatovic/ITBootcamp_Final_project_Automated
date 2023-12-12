package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


public class LoginPage extends BaseTest {

    public LoginPage(){
        PageFactory.initElements(driver,this);
    }

    //Lokatori
    @FindBy(id = "user-name")
    public WebElement usernameField;

    @FindBy(id = "password")
    public WebElement passwordField;

    @FindBy(id = "login-button")
    public WebElement loginButton;

    @FindBy(css = ".error-message-container.error")
    public WebElement error;

    @FindBy(css = ".svg-inline--fa.fa-times-circle.fa-w-16.error_icon")
    public List<WebElement> errorIcon;

    //Metode
    public void inputUsername(String username){
        usernameField.clear();
        usernameField.sendKeys(username);
    }

    public void inputPassword(String password){
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickOnLoginButton(){
        loginButton.click();
    }

}
