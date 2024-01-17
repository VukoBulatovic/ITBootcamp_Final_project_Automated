package Pages;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CheckoutSecondPage extends BaseTest {

    public CheckoutSecondPage(){
        PageFactory.initElements(driver,this);
    }

    @FindBy(className = "cart_item")
    public List<WebElement> itemsList;

    @FindBy(className = "shopping_cart_badge")
    public WebElement itemsOnCartIcon;

    @FindBy(className = "summary_subtotal_label")
    public WebElement itemTotalPrice;

    @FindBy(className = "summary_tax_label")
    public WebElement itemsTotalTax;

    @FindBy(css = ".summary_info_label.summary_total_label")
    public WebElement totalPrice;

    @FindBy(id = "finish")
    public WebElement finishButton;

    @FindBy(id = "cancel")
    public WebElement cancelButton;

    //--------------------------------------------

    public void clickOnFinishButton(){
        finishButton.click();
    }

    public void clickOnCancelButton(){
        cancelButton.click();
    }


    public double getPrice(){
        return Double.parseDouble(itemTotalPrice.getText().replace("Item total: $",""));
    }

    public double getTotalTax(){
        return Double.parseDouble(itemsTotalTax.getText().replace("Tax: $",""));
    }

    public double getTotalPrice(){
        return Double.parseDouble(totalPrice.getText().replace("Total: $",""));
    }

    public double calculationOfTotalPrice(){
        int quantity = Integer.parseInt(driver.findElement(By.className("cart_quantity")).getText());
        double sum = 0;
        double temp;
        double TAX = 0.08;
        for (int i = 0; i < itemsList.size(); i++) {
            temp = Double.parseDouble(itemsList.get(i).findElement(By.className("inventory_item_price")).getText().replace("$",""));
            sum += temp * quantity;
        }
        return sum + (sum * TAX);
    }

    public double calculationOfTotalTax() {
        int quantity = Integer.parseInt(driver.findElement(By.className("cart_quantity")).getText());
        double temp;
        double TAX = 0;
        for (int i = 0; i < itemsList.size(); i++) {
            temp = Double.parseDouble(itemsList.get(i).findElement(By.className("inventory_item_price")).getText().replace("$", ""));
            TAX += temp * 0.08 * quantity;
        }
        return TAX;
    }

    public double calculationOfItemTotalPrice(){
        int quantity = Integer.parseInt(driver.findElement(By.className("cart_quantity")).getText());
        double sum = 0;
        double temp;
        for (int i = 0; i < itemsList.size(); i++) {
            temp = Double.parseDouble(itemsList.get(i).findElement(By.className("inventory_item_price")).getText().replace("$",""));
            sum += temp * quantity;
        }
        return sum;
    }



}
