package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.net.IDN;
import java.util.List;

public class InventoryPage extends BaseTest {
    public InventoryPage(){
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "react-burger-menu-btn")
    public WebElement dropDownMenu;

    @FindBy(id = "logout_sidebar_link")
    public WebElement logoutButton;

    @FindBy(css = ".btn.btn_primary.btn_small.btn_inventory ")
    public List<WebElement> addToCartButton;

    @FindBy(css = ".btn.btn_secondary.btn_small.btn_inventory ")
    public List<WebElement> removeButton;

    @FindBy(className = "shopping_cart_badge")
    public WebElement cartItemsNumber;

    @FindBy(id = "shopping_cart_container")
    public WebElement cart;







    public void clickOnSideBarMenu() throws InterruptedException {
        dropDownMenu.click();
        Thread.sleep(1500);
    }
    public void clickOnLogOutButton(){
        logoutButton.click();
    }
    public void clickOnCart(){
        cart.click();
    }






}
