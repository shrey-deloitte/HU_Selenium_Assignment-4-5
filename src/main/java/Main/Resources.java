package Main;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;


public class Resources {

    static WebDriver driver;
    public static void launch(){

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get("https://www.saucedemo.com/");
    }

    public static void login() throws FileNotFoundException, InterruptedException {
        driver=new ChromeDriver();
        String excelFilePath="C:\\Users\\shredeshpande\\Documents\\Ass4UserIDPass.xlsx";
        FileInputStream fis=new FileInputStream(excelFilePath);
        XSSFWorkbook  workbook=new XSSFWorkbook();
        XSSFSheet sheet=workbook.getSheetAt(0);

        XSSFRow row=null;
        XSSFCell cell=null;
        String email=null;
        String password=null;

        for(int i=1;i<sheet.getLastRowNum();i++) {
            row = sheet.getRow(i);
            for (int j = 0; j < row.getLastCellNum(); j++) {
                cell = row.getCell(j);
                if (j == 0) {
                    email = cell.getStringCellValue();
                }
                if (j == 1) {
                    password = cell.getStringCellValue();
                }
            }


            System.out.println("********************************** Reading Data **********************************");
            driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys(email);
            driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);

            sleep(1000);
            driver.findElement(By.xpath("//input[@id='login-button']")).click();
            String result = null;

            String actualurl = driver.getCurrentUrl();
            sleep(3000);

            driver.quit();
        }



    }

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\shredeshpande\\Downloads\\chromedriver.exe");
         driver= new ChromeDriver();



        launch();
        login();
    }

}
