package Pages;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import java.util.ArrayList;
import java.util.List;

public class InventoryPage extends BaseTest {
    public InventoryPage(){
        PageFactory.initElements(driver,this);
    }

    public ArrayList<String> listOfTabs = new ArrayList<>();

    @FindBy(id = "react-burger-menu-btn")
    public WebElement sideBarMenu;

    @FindBy(id = "logout_sidebar_link")
    public WebElement logoutButton;

    @FindBy(css = ".btn.btn_primary.btn_small.btn_inventory")
    public List<WebElement> listOfAddToCartButtons;

    @FindBy(className = "inventory_item")
    public List<WebElement> listOfItems;

    @FindBy(css = ".btn.btn_secondary.btn_small.btn_inventory")
    public List<WebElement> listOfRemoveButtons;

    @FindBy(className = "shopping_cart_badge")
    public WebElement numberOnCartIcon;

    @FindBy(id = "shopping_cart_container")
    public WebElement cart;

    @FindBy(className = "product_sort_container")
    public WebElement sortDropDownMenu;

    @FindBy(className = "inventory_item_price")
    public List<WebElement> listOfPrices;

    @FindBy(linkText = "Twitter")
    public WebElement twitterIcon;

    @FindBy(linkText = "Facebook")
    public WebElement facebookIcon;

    @FindBy(linkText = "LinkedIn")
    public WebElement linkedIn;

    @FindBy(className = "inventory_item_name")
    public WebElement itemName;




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

    public boolean alphabeticallySorted(List<WebElement> listOfItems){
        for (int i = 0; i < listOfItems.size()-1; i++) {
            if(listOfItems.get(i).getText().compareTo(listOfItems.get(i+1).getText()) > 0)
                    return false;
        }
        return true;
    }

    public boolean itemsSortedByPrice(List<WebElement> itemPrices){
            for (int i = 0; i < itemPrices.size()-1; i++) {
                var item = itemPrices.get(i).getText().substring(1);
                var itemNext = itemPrices.get(i+1).getText().substring(1);
                if( Double.parseDouble(item)  > Double.parseDouble(itemNext) ) {
                    return false;
                }
            }
            return true;
        }

    public void switchTabs(){
        listOfTabs = new ArrayList<>(driver.getWindowHandles());
    }    
        
    public void clickOnTwitter(){
        twitterIcon.click();
    }

    public void clickOnFacebook(){
        facebookIcon.click();
    }

    public void clickOnLinkedIn(){
        linkedIn.click();
    }


    public void addRemoveAllItems(String addORremove){

        if(addORremove.equals("add")){
            for (WebElement a : listOfItems) {
                a.findElement(By.cssSelector(".btn.btn_primary.btn_small.btn_inventory")).click();
            }
        } else if (addORremove.equals("remove")){
            for (WebElement b : listOfItems) {
                b.findElement(By.cssSelector(".btn.btn_secondary.btn_small.btn_inventory")).click();
            }
        }
    }

    public boolean allNamesAreDisplayed(){
        for (int i = 0; i < listOfItems.size(); i++) {
            WebElement a = listOfItems.get(i);
            if(a.findElement(By.className("inventory_item_name")).isDisplayed() &&
              !a.findElement(By.className("inventory_item_name")).getText().isEmpty())
                return true;
        }
        return false;
    }


    public boolean allPricesAreDisplayed(){
        for (int i = 0; i < listOfItems.size(); i++) {
            WebElement a = listOfItems.get(i);
            if(a.findElement(By.className("inventory_item_price")).isDisplayed() &&
              !a.findElement(By.className("inventory_item_price")).getText().isEmpty())
                return true;
        }
        return false;
    }


    public boolean allDescriptionsAreDisplayed(){
        for (int i = 0; i < listOfItems.size(); i++) {
            WebElement a = listOfItems.get(i);
            if(a.findElement(By.className("inventory_item_desc")).isDisplayed() &&
              !a.findElement(By.className("inventory_item_desc")).getText().isEmpty())
                return true;
        }
        return false;
    }

    public boolean allImagesAreDisplayed(){
        for (int i = 0; i < listOfItems.size(); i++) {
            WebElement a = listOfItems.get(i);
            if(a.findElement(By.className("inventory_item_img")).isDisplayed() &&
              !a.findElements(By.className("inventory_item_img")).isEmpty())
                return true;
        }
        return false;
    }

    public boolean allAddToCartButtonsAreDisplayed(){
        for (int i = 0; i < listOfItems.size(); i++) {
            WebElement a = listOfItems.get(i);
            if(a.findElement(By.className(".btn.btn_primary.btn_small.btn_inventory")).isDisplayed() &&
              !a.findElement(By.className(".btn.btn_primary.btn_small.btn_inventory")).getText().isEmpty())
                return true;
        }
        return false;
    }
    }





    }






