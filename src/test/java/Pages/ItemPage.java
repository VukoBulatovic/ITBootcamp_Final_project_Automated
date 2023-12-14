package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ItemPage extends BaseTest {
    public ItemPage(){
        PageFactory.initElements(driver,this);
    }


    @FindBy(css = ".inventory_details_name.large_size")
    public WebElement productName;


    @FindBy(css = ".btn.btn_primary.btn_small.btn_inventory")
    public WebElement addToCartButton;

    @FindBy(css = ".btn.btn_secondary.btn_small.btn_inventory")
    public WebElement removeButton;

    @FindBy(id = "back-to-products")
    public WebElement backToProductsButton;

    @FindBy(className = "inventory_details_price")
    public WebElement itemPrice;

    @FindBy(css = ".inventory_details_desc.large_size")
    public WebElement itemDescription;

    @FindBy(className = "shopping_cart_badge")
    public WebElement cartIconQuantity;

    //-------------------------------------------------------------------------------------------------



    public String getNameOfItem(){
        return productName.getText();
    }

    public String getPriceOfItem(){
        return itemPrice.getText();
    }

    public String getItemDesription(){
        return itemDescription.getText();
    }

    public String getButtonText(){
        return removeButton.getText();
    }

    public void clickOnBackToProductsButton(){
        backToProductsButton.click();
    }

    public void clickOnAddToCartButton(){
        addToCartButton.click();
    }


}
