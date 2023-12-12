package Base;

import Pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.time.Duration;

public class BaseTest {

    public String baseURL = "https://www.saucedemo.com/";
    public static WebDriver driver;
    public WebDriverWait wait;
    public LoginPage loginPage;
    public InventoryPage inventoryPage;
    public CartPage cartPage;
    public CheckoutFirstPage checkoutFirstPage;
    public CheckoutSecondPage checkoutSecondPage;
    public CheckOutFinalPage checkOutFinalPage;
    public ExcelReader excelReader;
    public ItemPage itemPage;

    @BeforeClass
    public void setUp() throws IOException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver,Duration.ofSeconds(15));

        loginPage = new LoginPage();
        inventoryPage = new InventoryPage();
        cartPage = new CartPage();
        checkoutFirstPage = new CheckoutFirstPage();
        checkoutSecondPage = new CheckoutSecondPage();
        checkOutFinalPage = new CheckOutFinalPage();
        itemPage = new ItemPage();
        excelReader = new ExcelReader("src/test/java/TestData/TestData.xlsx");

    }

//----------------------------------------------------------------------------------------------------------------------

    public void goToBaseUrl(){
        driver.navigate().to(baseURL);
    }

    public void successfulLogIn(){
        String validUsername = excelReader.getStringData("Login",1,0);
        String validPassword = excelReader.getStringData("Login",1,1);
        String expectedUrl = baseURL + "inventory.html";
        loginPage.inputUsername(validUsername);
        loginPage.inputPassword(validPassword);
        loginPage.clickOnLoginButton();
        Assert.assertEquals(driver.getCurrentUrl(),expectedUrl);
    }

    public void loggedIn(){
        Cookie cookie = new Cookie("session-username","standard_user");
        driver.manage().addCookie(cookie);
        driver.navigate().refresh();
        driver.navigate().to(baseURL + "inventory.html");
        driver.navigate().refresh();
        Assert.assertEquals(driver.getCurrentUrl(),baseURL + "inventory.html");
    }

    public void addAllItems(){
        for (int i = inventoryPage.listOfAddToCartButtons.size() - 1; i >= 0; i--) {
            inventoryPage.listOfAddToCartButtons.get(i).click();
        }
    }

    public void logOut() throws InterruptedException {
        inventoryPage.clickOnSideBarMenu();
        inventoryPage.clickOnLogOutButton();
    }

   /* public boolean checkQuantity(){
        for (int i = 0; i < cartPage.cartQuantity.size(); i++) {
            if(Assert.assertEquals(cartPage.cartQuantity.get(i).getText(),"1")){
            return true;
        }
    }*/















    @AfterClass
    public void tearDown(){
        //driver.quit();
    }

}
