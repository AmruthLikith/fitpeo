package testfitpeo;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pomfitpeo.RevenueCalculator;

import static controlType.TestScriptForcontainerControlType.driver;

public class testRevenueCalculator {
    RevenueCalculator revcal;
    @BeforeClass
    public void openBrowser() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
        ChromeOptions options=new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        revcal = new RevenueCalculator(driver);
        driver.get("https://www.fitpeo.com/");
        Thread.sleep(3000);
        driver.manage().window().maximize();
        System.out.println("The Browser is Open Successfully");
    }
    @Test(priority = 1)
    public void navigateRevenueCalculator() throws InterruptedException {
        revcal = new RevenueCalculator(driver);
        revcal.clickrevenuecalculator();
        revcal.adjust_slider();
        revcal.update_TextField();
    }
    @Test(priority = 2)
    public void cpt_Checkbox() throws InterruptedException {
        revcal = new RevenueCalculator(driver);
        revcal.select_CPT_Codes();
    }
    @Test(priority = 3)
    public void validate_total_recurring_reimburement() throws InterruptedException {
        revcal = new RevenueCalculator(driver);
        revcal.total_reimbursement();
        driver.close();
    }


}
