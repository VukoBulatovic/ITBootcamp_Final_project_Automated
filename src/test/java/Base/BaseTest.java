package Base;

import Pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.time.Duration;

public class BaseTest {

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

    //Metode

    public void currentUrl(){
        driver.getCurrentUrl();
    }



    @AfterClass
    public void tearDown(){
        //driver.quit();
    }

}
