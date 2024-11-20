import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Task {
    static WebDriver driver = new ChromeDriver();

    @BeforeTest
    public void Login() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://uattaxpayer3.taxservice.am/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


        WebElement userName = driver.findElement(By.name("username"));
        String searchName = "admin";
        userName.sendKeys(searchName);

        WebElement userPass = driver.findElement(By.name("password"));
        String searchPass = "adminadmin";
        userPass.sendKeys(searchPass);

        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"login\"]/div/div[7]/input")));
        continueButton.click();
    }

    @Test
    public static void TC1() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        driver.switchTo().newWindow(WindowType.TAB);
        driver.navigate().to("https://uattaxpayer3.taxservice.am/documents/newDocument.jsf");

        WebElement hvhhInput = driver.findElement(By.name("newDocument:tin"));
        hvhhInput.click();
        String searchHvhh = "01012248";
        hvhhInput.sendKeys(searchHvhh);

        WebElement dateInput = driver.findElement(By.id("newDocument:receiveDateInputDate"));
        dateInput.click();
        String searchDate = "10.11.2022";
        dateInput.sendKeys(searchDate);

        WebElement dropdown1 = driver.findElement(By.id("newDocument:docType"));
        dropdown1.click();
        WebElement list1 = driver.findElement(By.xpath("//*[@id='newDocument:docType']/option[70]"));
        list1.click();

        Thread.sleep(10000);
        WebElement dropdown2 = wait.until(ExpectedConditions.elementToBeClickable(By.id("newDocument:taxYearList")));
        dropdown2.click();
        WebElement list2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"newDocument:taxYearList\"]/option[3]")));
        list2.click();

        Thread.sleep(10000);
        WebElement inputDelete = wait.until(ExpectedConditions.elementToBeClickable(By.id("newDocument:numberOfPages")));
        inputDelete.click();
        inputDelete.clear();
        String number = "1";
        inputDelete.sendKeys(number);

        WebElement saveButton = driver.findElement(By.id("newDocument:save"));
        saveButton.click();

        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("registeredDocument:submit")));
        submitButton.click();

        Thread.sleep(2000);
        WebElement phoneNumber = driver.findElement(By.name("mainForm:f_phone"));
        phoneNumber.click();
        String PhoneNumber = "+(374)10123456";
        phoneNumber.sendKeys(PhoneNumber);

        Thread.sleep(2000);
        WebElement dayTime = driver.findElement(By.name("mainForm:f_traceabilityInfoDateInputDate"));
        dayTime.click();
        dayTime.clear();
        String DayTime = "10.11.2022";
        dayTime.sendKeys(DayTime);

        WebElement pagination = driver.findElement(By.name("mainForm:j_id399"));
        pagination.click();
        WebElement pageNumber = driver.findElement(By.xpath("//*[@id=\"mainForm:personList\"]/table/tbody/tr[1]/td[3]/div/select/option[3]"));
        pageNumber.click();


        for (int i = 0; i < 300; i++) {

            if (i >= 20 && i % 20 == 0) {
                int count = ((i / 20) + 1);
                int columnIndex = (count > 14 ? count - 4 : (count > 10 ? count - 2 : count + 2));
                String baseXPath = "//*[@id='mainForm:personList']/table/tbody/tr[1]/td[2]/div/div/table/tbody/tr/td[%d]";
                String elementXPath = String.format(baseXPath, columnIndex);
                Thread.sleep(2000);
                WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementXPath)));
                Thread.sleep(2000);
                element.click();
                System.out.println("Clicked on element " + count);

                if (count == 11) {
                    WebElement count11 = driver.findElement(By.xpath("//*[@id=\"mainForm:personList\"]/table/tbody/tr[1]/td[2]/div/div/table/tbody/tr/td[10]"));
                    Thread.sleep(1000);
                    count11.click();
                }
            }

            String docTypeId = "mainForm:subjectRow:" + i + ":f_acquisitionDocType";
            Thread.sleep(2000);
            WebElement docType = wait.until(ExpectedConditions.elementToBeClickable(By.id(docTypeId)));
            docType.click();
            Thread.sleep(1000);

            WebElement otherOption = driver.findElement(By.xpath("//select[@id='" + docTypeId + "']/option[4]"));
            otherOption.click();

            String docNumberId = "mainForm:subjectRow:" + i + ":f_acquisitionDocNum3";
            Thread.sleep(2000);
            WebElement docNumber = wait.until(ExpectedConditions.elementToBeClickable(By.id(docNumberId)));
            Thread.sleep(2000);
            docNumber.click();
            docNumber.sendKeys("1000");

            String productNumberId = "mainForm:subjectRow:" + i + ":f_productNumber";
            WebElement productNumber = wait.until(ExpectedConditions.elementToBeClickable(By.id(productNumberId)));
            productNumber.click();
            productNumber.sendKeys("1111");

            String productNameId = "mainForm:subjectRow:" + i + ":f_productName";
            WebElement productName = wait.until(ExpectedConditions.elementToBeClickable(By.id(productNameId)));
            productName.click();
            productName.sendKeys("4444");

            String commodityCodeId = "mainForm:subjectRow:" + i + ":f_commodityCody";
            WebElement commodityCode = wait.until(ExpectedConditions.elementToBeClickable(By.id(commodityCodeId)));
            commodityCode.click();
            WebElement commodityOption = driver.findElement(By.xpath("//select[@id='" + commodityCodeId + "']/option[9]"));
            commodityOption.click();

            Thread.sleep(2000);
            String goodsCountId = "mainForm:subjectRow:" + i + ":f_goodsCount";
            WebElement goodsCount = wait.until(ExpectedConditions.elementToBeClickable(By.id(goodsCountId)));
            goodsCount.click();
            goodsCount.sendKeys("55");
            

            if (i == 299) {
                break;
            } else {
                WebElement addNewItem = driver.findElement(By.id("mainForm:j_id420"));
                addNewItem.click();
            }

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,250);");
        }

        WebElement submitButton2 = driver.findElement(By.id("mainForm:post_declaration"));
        submitButton2.click();
    }

//    @AfterTest
//    public void tearDown() {
//        driver.quit();
//    }
}




