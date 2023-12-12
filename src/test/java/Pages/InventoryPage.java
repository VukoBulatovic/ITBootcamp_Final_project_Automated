package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class InventoryPage extends BaseTest {
    public InventoryPage(){
        PageFactory.initElements(driver,this);
    }



    @FindBy(id = "react-burger-menu-btn")
    public WebElement sideBarMenu;

    @FindBy(id = "logout_sidebar_link")
    public WebElement logoutButton;

    @FindBy(css = ".btn.btn_primary.btn_small.btn_inventory ")
    public List<WebElement> listOfAddToCartButtons;

    @FindBy(className = "inventory_item")
    public List<WebElement> listOfItems;

    @FindBy(css = ".btn.btn_secondary.btn_small.btn_inventory ")
    public List<WebElement> listOfRemoveButtons;

    @FindBy(className = "shopping_cart_badge")
    public WebElement cartIcon;

    @FindBy(id = "shopping_cart_container")
    public WebElement cart;




    public void clickOnAddToCartButton() throws InterruptedException {
        inventoryPage.listOfAddToCartButtons.get(0).click();
        Thread.sleep(3000);
    }

    public void clickOnSideBarMenu() throws InterruptedException {
        sideBarMenu.click();
        Thread.sleep(1500);
    }
    public void clickOnLogOutButton(){
        logoutButton.click();
    }
    public void clickOnCart(){
        cart.click();
    }

    public boolean checkIfCartIsEmpty(){
        return cartIcon.getText().isEmpty();
    }

    public void addAllItemsInCart(){
        for (int i = inventoryPage.listOfAddToCartButtons.size() - 1; i >= 0; i--) {
            inventoryPage.listOfAddToCartButtons.get(i).click();
        }
    }




}
