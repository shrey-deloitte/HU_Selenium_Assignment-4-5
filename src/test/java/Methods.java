

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;


public class Methods {

    static WebDriver driver;

    @BeforeTest
    public static void launch() {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\shredeshpande\\Downloads\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get("https://www.saucedemo.com/");
    }
    @AfterTest
    public static void exit(){
        driver.quit();
    }

    @Test(priority = 1)
    public  static  void dropdown(){
        Select dropdown=new Select(driver.findElement(By.xpath("//select[@class = 'product_sort_container']")));
        dropdown.selectByVisibleText("Price (high to low)");
    }

    @Test(priority = 2)
    public  static void addtoCart(){
        driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-fleece-jacket']")).click();

    }

    @Test(priority = 3)
    public static void Choosehighest() throws InterruptedException {
        driver.findElement(By.xpath("//button[@class='btn btn_primary btn_small btn_inventory']/descendant-or-self::button")).click();
        sleep(2000);
        driver.findElement(By.xpath("//button[@id='remove-sauce-labs-backpack']")).click();
    }

    @Test(priority = 0)
    public static void login() throws IOException, InterruptedException {
        String filePath = "C:\\Users\\shredeshpande\\Documents\\Ass4UserIDPass.xlsx";
        File file = new File(filePath);

        FileInputStream inputStream = new FileInputStream(file);

        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

        XSSFSheet workbookSheet = workbook.getSheet("Sheet1");
        int rowCount = workbookSheet.getLastRowNum() - workbookSheet.getFirstRowNum();
        String email = null;
        String password = null;
        ArrayList<User> list= new ArrayList<>();
        for (int i = 1; i < rowCount + 1; i++) {

            Row row = workbookSheet.getRow(i);
            User userObj = new User();
            for (int j = 0; j < row.getLastCellNum(); j++) {
                String data = row.getCell(j).getStringCellValue();

                switch (j){
                    case 0 -> userObj.setUserName(data);
                    case 1-> userObj.setPassword(data);
                }

                System.out.println(data);

            }
            list.add(userObj);
        }



        driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys(list.get(0).userName);
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys(list.get(0).password);

        sleep(1000);
        driver.findElement(By.xpath("//input[@id='login-button']")).click();

    }

    @Test(priority =4 )
    public static void cart() throws InterruptedException {
        driver.findElement(By.xpath("//div[@id='shopping_cart_container']")).click();
        sleep(2000);
    }

    @Test(priority =5 )
    public static void continueShopping() throws InterruptedException {
        driver.findElement(By.xpath("//button[@id='continue-shopping']")).click();
        sleep(2000);

    }

    @Test(priority =6 )
    public  static  void dropdownLowest() throws InterruptedException {
        Select dropdown=new Select(driver.findElement(By.xpath("//select[@class = 'product_sort_container']")));
        dropdown.selectByVisibleText("Price (low to high)");
        sleep(1000);
    }

    @Test(priority = 7)
    public static String badge(){
        String badgeNo=driver.findElement(By.xpath("//span[@class='shopping_cart_badge']")).getText();
        return badgeNo;
    }



    @Test(priority =9 )
    public  static void checkout(){
        driver.findElement(By.xpath("//button[@id='checkout']")).click();
    }

    @Test(priority = 8)
    public static void cart1() throws InterruptedException {
        driver.findElement(By.xpath("//div[@id='shopping_cart_container']")).click();
        sleep(2000);
    }

    @Test(priority = 9)
    public static void checkoutInfo(){
        driver.findElement(By.xpath("//input[@id='first-name']")).sendKeys("Standard");
        driver.findElement(By.xpath("//input[@id='last-name']")).sendKeys("User");
        driver.findElement(By.xpath("//input[@id='postal-code']")).sendKeys("510008");
        driver.findElement(By.xpath("//input[@id='continue']")).click();
    }
    @Test(priority = 10)
    public static void finish() throws InterruptedException {
        driver.findElement(By.xpath("//button[@id='finish']")).click();
        sleep(2000);
    }

    @Test(priority = 11)
    public static void msgCheck(){
        String msg=driver.findElement(By.xpath("//h2[@class='complete-header']")).getText();
        String mainMsg="THANK YOU FOR YOUR ORDER";
        if (msg.equals(mainMsg)){
            System.out.println("Success");
        }
        else {
            System.out.println("error");
        }


    }










    public static void mainLaunch(String[] args) throws IOException, InterruptedException ,Exception{
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\shredeshpande\\Downloads\\chromedriver.exe");
        driver = new ChromeDriver();


        launch();
        login();
        dropdown();
        addtoCart();
        Choosehighest();
        cart();
        continueShopping();
        dropdownLowest();
        driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-onesie']")).click();
        int badge=Integer.parseInt(badge());
        if(badge==badge+1){
            driver.navigate().refresh();
        }
        cart();
        checkout();


    }
}


