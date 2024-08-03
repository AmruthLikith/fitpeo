package pomfitpeo;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RevenueCalculator {
    WebDriver driver;

    @FindBy(xpath = "//div[text()='Revenue Calculator']")
    private WebElement clickRevenue_Calculator;
    @FindBy(xpath = "//h4[text()='Medicare Eligible Patients']")
    private WebElement scroll;
    @FindBy(xpath = "((//span[@data-index='0']//ancestor::div)[6]//input)[1]")
    private WebElement slider;
    @FindBy(xpath = "((//span[@data-index='0']//ancestor::div)[6]//input)[2]")
    private WebElement textfield;
    @FindBy(xpath = "//p[text()='CPT-99091']")
    private WebElement scroll_to_CPT;
    @FindBy(xpath = "(//p[text()='CPT-99091']//ancestor::div)[4]//input")
    private WebElement CPT_99091_Checkbox;
    @FindBy(xpath = "(//p[text()='CPT-99453']//ancestor::div)[4]//input")
    private WebElement CPT_99453_Checkbox;
    @FindBy(xpath = "(//p[text()='CPT-99454']//ancestor::div)[4]//input")
    private WebElement CPT_99454_Checkbox;
    @FindBy(xpath = "//p[text()='Total Recurring Reimbursement for all Patients Per Month:']//ancestor::p//p")
    private WebElement total_recurring_reimburement;

    public RevenueCalculator(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickrevenuecalculator() throws InterruptedException {
        clickRevenue_Calculator.click();
        Thread.sleep(4000);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
    }

    public void adjust_slider() throws InterruptedException {
        Thread.sleep(4000);
        int target_value = 820;
        String Intialvalue = slider.getAttribute("value");
        System.out.println("The Intial Radius Value is :" + Intialvalue);
        Actions actions = new Actions(driver);
        actions.dragAndDropBy(slider, 94, 0).release().perform();
        String Finalvalue = slider.getAttribute("value");
        System.out.println("The Final Radius Value is :" + Finalvalue);
        if (Finalvalue != Intialvalue) {
            System.out.println("Test pass : The value is Changed ");
        } else {
            System.out.println(" Test Fail : The value is Not Changed");
        }
    }

    public void update_TextField() throws InterruptedException {
        Thread.sleep(4000);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", scroll);
        Thread.sleep(3000);
        textfield.clear();
        Thread.sleep(3000);
        textfield.sendKeys("520");
    }

    public void select_CPT_Codes() throws InterruptedException {
        Thread.sleep(4000);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", scroll_to_CPT);
        Thread.sleep(3000);
        CPT_99091_Checkbox.click();
        Thread.sleep(2000);
        CPT_99453_Checkbox.click();
        CPT_99454_Checkbox.click();
    }
    public void total_reimbursement() throws InterruptedException {
        Thread.sleep(4000);
        String actualValue = total_recurring_reimburement.getText();
        System.out.println("the value is : " + actualValue);
        String expectedValue = "$110700";
        if (actualValue.equals(expectedValue)) {
            System.out.println("Validation passed. The header shows the expected value: " + expectedValue);
        } else {
            System.out.println("Validation failed. Expected value: " + expectedValue + ", but found: " + actualValue);
        }
    }
}

