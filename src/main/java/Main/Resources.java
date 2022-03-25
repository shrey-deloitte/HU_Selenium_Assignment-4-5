package Main;

import org.apache.poi.hssf.record.aggregates.RowRecordsAggregate;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;


public class Resources {

    static WebDriver driver;

    public static void launch() {

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get("https://www.saucedemo.com/");
    }

    public static void login() throws IOException, InterruptedException {
        // driver=new ChromeDriver();
        String filePath = "C:\\Users\\shredeshpande\\Documents\\Ass4UserIDPass.xlsx";
        File file = new File(filePath);

        FileInputStream inputStream = new FileInputStream(file);

        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

        XSSFSheet workbookSheet = workbook.getSheet("Sheet1");
        int rowCount = workbookSheet.getLastRowNum() - workbookSheet.getFirstRowNum();
        String email = null;
        String password = null;
        for (int i = 1; i < rowCount + 1; i++) {

            Row row = workbookSheet.getRow(i);
            for (int j = 0; j < row.getLastCellNum(); j++) {
                String data = row.getCell(j).getStringCellValue();

                System.out.println(data);

            }
        }

        XSSFRow row = null;
        XSSFCell cell = null;



        System.out.println("********************************** Reading Data **********************************");
        driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);

        sleep(1000);
        driver.findElement(By.xpath("//input[@id='login-button']")).click();
        String result = null;

        //  String actualurl = driver.getCurrentUrl();
        sleep(3000);
        System.out.println(email + " " + password);

    }


    public static void main(String[] args) throws IOException, InterruptedException ,Exception{
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\shredeshpande\\Downloads\\chromedriver.exe");
        driver = new ChromeDriver();


        launch();
        login();
    }
}


