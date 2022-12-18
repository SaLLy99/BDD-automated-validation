import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DemoQaLogin {

    WebDriverWait wait;
    WebDriver driver;

    //Check authentification
    @Given("browser is open")
    public void browserIsOpen() {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait=new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @When("user is on login page")
    public void userIsOnLoginPage() {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
        driver.get("https://demoqa.com/login");
    }

    @Then("users enters username and password")
    public void usersEntersUsernameAndPassword() {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
        wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        //login
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("userName"))));
        WebElement userName= driver.findElement(By.id("userName")),
                pass= driver.findElement(By.id("password")),
                login= driver.findElement(By.id("login"));

        userName.sendKeys("test123");
        pass.sendKeys("Automation@123");
        login.click();
        driver.manage().window().maximize();
    }

    @Then("user is navigated to the home page")
    public void userIsNavigatedToTheHomePage() {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();

        assertEquals(driver.getCurrentUrl(), "https://demoqa.com/login");
    }
}
