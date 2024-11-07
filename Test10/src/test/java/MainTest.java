import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Random;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MainTest {

    private WebDriver driver;

    @BeforeTest
    public void setUp() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.its4women.ie/");
        Thread.sleep(2000);
        driver.findElement(By.id("onetrust-accept-btn-handler")).click();
        Thread.sleep(1000);
        driver.findElement(By.name("Get a Quote")).click();
        Thread.sleep(2000);
    }

    @Test
    public void test01() throws InterruptedException {

        Actions actions = new Actions(driver);

        //1.1 Assert that the title is displayed correctly
        assertThat(driver.findElement(By.xpath("//h3[contains(text(),'* Pre-contractual duty of disclosure')]")).isDisplayed()).isTrue();

        driver.findElement(By.xpath("(//a[@href='#'])[1]")).click();
        Thread.sleep(1000);

        //1.5 asserting that the header is displayed correctly and the 'x' button beside it is displayed
        assertThat(driver.findElement(By.xpath("//h5[contains(text(),'Impact of Misrepresentation Section. Please read this section carefully.')]")).isDisplayed()).isTrue();
        assertThat(driver.findElement(By.xpath("//div[@id='modalImpact']//div[@class='modal-header']//button[@type='button']")).isDisplayed()).isTrue();

        driver.findElement(By.xpath("//div[@id='modalImpact']//div[@class='modal-header']//button[@type='button']")).click();
        Thread.sleep(1000);
        //1.7 asserting that after clicking the 'x' button in the pop-up the pop-up closes and section one is now displayed
        assertThat(driver.findElement(By.xpath("//span[contains(text(),'1. Your Details')]")).isDisplayed()).isTrue();

        driver.findElement(By.xpath("(//a[@href='#'])[1]")).click();
        Thread.sleep(1000);

        //1.8 asserting that the close button is displayed at the bottom of the pop-up menu
        assertThat(driver.findElement(By.xpath("(//button[contains(.,'Close')])[4]")).isDisplayed()).isTrue();

        driver.findElement(By.xpath("(//button[contains(.,'Close')])[4]")).click();
        Thread.sleep(1000);
        //1.9 asserting that when the close button is pressed the pop-up closes and section 1 is displayed
        assertThat(driver.findElement(By.xpath("//span[contains(text(),'1. Your Details')]")).isDisplayed()).isTrue();

        driver.findElement(By.xpath("(//a[@href='#'])[1]")).click();
        Thread.sleep(1000);

        actions.sendKeys("ABC").perform();

        //1.10 asserting that you cannot enter text on the pop-up
        assertThat(driver.findElement(By.xpath("//h5[contains(text(),'Impact of Misrepresentation Section. Please read this section carefully.')]")).isDisplayed()).isTrue();

        actions.sendKeys(Keys.ENTER).perform();
        //1.11 asserting that pressing enter on the pop-up window does not close the window
        assertThat(driver.findElement(By.xpath("//h5[contains(text(),'Impact of Misrepresentation Section. Please read this section carefully.')]")).isDisplayed()).isTrue();

    }

    @Test
    public void test02() throws InterruptedException{
        Actions actions = new Actions(driver);

        WebElement titleDropDown = driver.findElement(By.id("ctl00_Main_ProposerTitle"));
        WebElement firstNameField = driver.findElement(By.id("ctl00_Main_ProposerForename"));
        WebElement surnameField = driver.findElement(By.id("ctl00_Main_ProposerSurname"));
        WebElement emailAddressField = driver.findElement(By.id("ctl00_Main_ProposerEmail"));
        WebElement contactNumberField = driver.findElement(By.id("ctl00_Main_ProposerTelephone"));
        WebElement continueBtn1 = driver.findElement(By.id("ctl00_Main_Continue1"));

        titleDropDown.click();
        Thread.sleep(1000);

        //2.1 asserting the values that appear in the tile dropdown are correct
        assertThat(driver.findElement(By.xpath("//option[@value=' ']")).isDisplayed()).isTrue();
        assertThat(driver.findElement(By.xpath("//option[@value='Mrs']")).isDisplayed()).isTrue();
        assertThat(driver.findElement(By.xpath("//option[@value='Miss']")).isDisplayed()).isTrue();
        assertThat(driver.findElement(By.xpath("//option[@value='Ms']")).isDisplayed()).isTrue();
        assertThat(driver.findElement(By.xpath("//option[@value='Mr']")).isDisplayed()).isTrue();
        assertThat(driver.findElement(By.xpath("//option[@value='Dr']")).isDisplayed()).isTrue();

        //2.2 clicking the arrow keys to navigate the dropdown
        actions.sendKeys(Keys.ARROW_DOWN).perform();
        Thread.sleep(1000);
        actions.sendKeys(Keys.ARROW_DOWN).perform();
        Thread.sleep(1000);
        actions.sendKeys(Keys.ARROW_UP).perform();
        Thread.sleep(1000);
        actions.sendKeys(Keys.ARROW_UP).perform();
        Thread.sleep(1000);

        //2.3 clicking M selects "Mrs"
        actions.sendKeys("M").perform();
        Thread.sleep(1000);

        //2.4 clicking D selects "Dr"
        actions.sendKeys("D").perform();
        Thread.sleep(1000);

        firstNameField.click();
        actions.sendKeys(Keys.ENTER).perform();

        //3.1 assert that pressing enter on the first name textbox an error message will appear
        assertThat(driver.findElement(By.xpath("//span[contains(text(),'* Forename Required')]")).isDisplayed()).isTrue();

        firstNameField.click();
        firstNameField.sendKeys("A");
        actions.sendKeys(Keys.ENTER).perform();

        //3.5 assert that entering A in the first name textbox an error message will appear
        assertThat(driver.findElement(By.xpath("//span[contains(text(),'* Please enter your full forename')]")).isDisplayed()).isTrue();

        firstNameField.click();
        firstNameField.clear();
        firstNameField.sendKeys("1");
        actions.sendKeys(Keys.ENTER).perform();

        //3.6 assert that entering 1 in the first name textbox an error message will appear
        assertThat(driver.findElement(By.xpath("//span[contains(text(),'* Please enter your full forename')]")).isDisplayed()).isTrue();

        firstNameField.click();
        firstNameField.clear();
        firstNameField.sendKeys("!");
        actions.sendKeys(Keys.ENTER).perform();

        //3.7 assert that entering ! in the first name textbox an error message will appear
        assertThat(driver.findElement(By.xpath("//span[contains(text(),'* Please enter your full forename')]")).isDisplayed()).isTrue();

        firstNameField.click();
        firstNameField.clear();
        firstNameField.sendKeys("Colin");
        actions.sendKeys(Keys.ENTER).perform();
        //3.8 entering colin allows you to proceed and no error message is displayed, if an error message appears the test will fail
        try {
            if (driver.findElement(By.xpath("//span[contains(text(),'* Please enter your full forename')]")).isDisplayed()){
                Assert.fail("Error message appeared after a valid name was entered");
            }
        }catch (Exception ignored){
        }
    }

    @Test
    public void test03() throws InterruptedException{
        //Move to the second screen
        WebElement titleDropDown = driver.findElement(By.id("ctl00_Main_ProposerTitle"));
        WebElement firstNameField = driver.findElement(By.id("ctl00_Main_ProposerForename"));
        WebElement surnameField = driver.findElement(By.id("ctl00_Main_ProposerSurname"));
        WebElement emailAddressField = driver.findElement(By.id("ctl00_Main_ProposerEmail"));
        WebElement contactNumberField = driver.findElement(By.id("ctl00_Main_ProposerTelephone"));
        WebElement continueBtn1 = driver.findElement(By.id("ctl00_Main_Continue1"));

        titleDropDown.click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//option[@value='Mr']")).click();
        firstNameField.sendKeys("Damian");
        surnameField.sendKeys("McNeice");
        emailAddressField.sendKeys("damian@sharklasers.com");
        contactNumberField.sendKeys("07871457638");
        continueBtn1.click();
        Thread.sleep(2000);

        //Assert that section 2 appears when all information is correct on section 1
        assertThat(driver.findElement(By.id("divLabelAddress")).isDisplayed()).isTrue();
    }

    @AfterMethod
    public void closeDown(){
        driver.close();
    }
}
