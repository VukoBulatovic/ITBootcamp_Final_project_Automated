package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends BaseTest {

    public CartPage(){
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = ".btn.btn_secondary.btn_small.cart_button")
    public WebElement removeButton;

    @FindBy(className = "cart_quantity")
    public List<WebElement> cartQuantity;

    @FindBy(className = "shopping_cart_badge")
    public WebElement cartIcon;

    @FindBy(id = "checkout")
    public WebElement checkOutButton;

    @FindBy(className = "cart_item")
    public List<WebElement> itemsInCart;

    @FindBy(linkText = "Your Cart")
    public WebElement yourCartHeader;






    //--------------------------------------------

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





}
