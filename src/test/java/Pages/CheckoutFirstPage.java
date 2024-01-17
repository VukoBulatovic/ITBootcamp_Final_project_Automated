package Pages;

import Base.BaseTest;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutFirstPage extends BaseTest {

    public CheckoutFirstPage(){
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "first-name")
    public WebElement firstNameField;

    @FindBy(id = "last-name")
    public WebElement lastNameField;

    @FindBy(id = "postal-code")
    public WebElement postalCodeField;

    @FindBy(id = "continue")
    public WebElement countinueButton;

    @FindBy(id = "cancel")
    public WebElement cancelButton;

    @FindBy(className = "shopping_cart_badge")
    public WebElement numbersOfItemsOnCheckoutPage;

    @FindBy(xpath = "//*[@data-test='error']")
    public WebElement erorrMessage;


    //--------------------------------------


    public void firstNameInput(String firstname){
        firstNameField.clear();
        firstNameField.sendKeys(firstname);
    }

    public void lastNameInput(String lastname){
        lastNameField.clear();
        lastNameField.sendKeys(lastname);
    }

    public void zipCodeInput(String zipcode){
        postalCodeField.clear();
        postalCodeField.sendKeys(zipcode);
    }

    public void clickOnCountinueButton(){
        countinueButton.click();
    }

    public void clickOnCancelButton(){
        cancelButton.click();
    }

    public void lastNameClear(){
        lastNameField.sendKeys(Keys.CONTROL + "a");
        lastNameField.sendKeys(Keys.DELETE);
    }
    public void postalCodeClear(){
        postalCodeField.sendKeys(Keys.CONTROL + "a");
        postalCodeField.sendKeys(Keys.DELETE);
    }

    public void submitValidData(){
        firstNameInput("Marko");
        lastNameInput("Polo");
        zipCodeInput("12003");
        countinueButton.click();
    }










}
