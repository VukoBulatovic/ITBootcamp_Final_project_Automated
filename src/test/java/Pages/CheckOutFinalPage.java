package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutFinalPage extends BaseTest {

    public CheckOutFinalPage(){
        PageFactory.initElements(driver,this);
    }

    @FindBy(className = "title")
    public WebElement successTitle;

    @FindBy(className = "complete-header")
    public WebElement successMessage;

    @FindBy(id = "back-to-products")
    public WebElement backHomeButton;


    //----------------------

    public void clickOnBackHomeButton(){
        backHomeButton.click();
    }

}
