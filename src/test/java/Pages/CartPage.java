package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class CartPage extends BaseTest {

    public CartPage(){
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = ".btn.btn_secondary.btn_small.cart_button")
    public WebElement removeButton;

    @FindBy(className = "cart_quantity")
    public List<WebElement> cartQuantity;

    @FindBy(id = "checkout")
    public WebElement checkOutButton;

    @FindBy(className = "cart_item")
    public List<WebElement> itemsInCart;

    @FindBy(className = "header_secondary_container")
    public WebElement yourCartHeader;

    @FindBy(id = "continue-shopping")
    public WebElement countinueShoppingButton;

    @FindBy(className = "shopping_cart_container")
    public WebElement cartIcon;

    @FindBy(className = "shopping_cart_badge")
    public WebElement numberOfItemsOnCartIcon;





    //--------------------------------------------

    public void clickOnCountinueShoppingButton(){
        countinueShoppingButton.click();
    }

    public void clickOnRemoveButton(){
        removeButton.click();
    }

    public void clickOnCheckoutButton(){
        checkOutButton.click();
    }



    public int numbersOfItemsInCart(){
        int sum = 0;
        for (int i = 0; i < itemsInCart.size(); i++) {
            sum++;
        }
        return sum;
    }

    public boolean quantityCheck() {
        for (int i = 0; i < cartQuantity.size(); i++) {
            if (cartQuantity.get(i).getText().equals("1")) {
                return true;
            }
        }
        return false;
    }

    public boolean checkIfCartIsEmpty(){
        return cartIcon.getText().isEmpty();
    }

    public void checkItemsAreDisplayedInCart(){
        for (int i = 0; i < itemsInCart.size(); i++) {
            Assert.assertTrue(itemsInCart.get(i).isDisplayed());
        }
    }

    public void checkItemsQuantity(){
        for (int i = 0; i < itemsInCart.size(); i++) {
            Assert.assertEquals(cartQuantity.get(i).getText(),"1");
        }
    }

    public void quantityIncreasing(){
        for (int i = 0; i < itemsInCart.size(); i++) {
            WebElement quantityField = itemsInCart.get(i);
            cartQuantity.clear();
            String two = "2";
            quantityField.sendKeys(two);
        }
    }



}
