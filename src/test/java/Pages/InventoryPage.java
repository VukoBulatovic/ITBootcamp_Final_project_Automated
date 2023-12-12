package Pages;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ISelect;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;
import java.util.NoSuchElementException;

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
    public WebElement numberOnCartIcon;

    @FindBy(id = "shopping_cart_container")
    public WebElement cart;

    @FindBy(className = "product_sort_container")
    public WebElement sortDropDownMenu;

    @FindBy(linkText = "Name (A to Z)")
    public WebElement aToZSortOption;




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
        return cart.getText().isEmpty();
    }







}
