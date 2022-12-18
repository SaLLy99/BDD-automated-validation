import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static java.lang.Thread.sleep;
import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class DemoQaBookStore {


    WebDriverWait wait;
    WebDriver driver;

    @Given("home page is open")
    public void homePageIsOpen() {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://demoqa.com/login");
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
        assertEquals(driver.getCurrentUrl(), "https://demoqa.com/login");
    }

    @Then("click on Go to Book store button")
    public void clickOnBookStore() {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
        new WebDriverWait(driver, Duration.ofSeconds(50)).until(ExpectedConditions.elementToBeClickable(By.id("gotoStore")));
        WebElement element = driver.findElement(By.id("gotoStore"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

        WebElement goToStoreButton= driver.findElement(By.id("gotoStore"));
        goToStoreButton.click();

    }

    @Then("make sure that we have enough  books in book store")
    public void haveEnoughBooks() {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"see-book-Git Pocket Guide\"]/a")));
        List<WebElement> bookList = driver.findElements(By.className("action-buttons"));
        assertEquals(bookList.size(),8);
    }

    @Then("return back to home page")
    public void goBackToHomePage() {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
        driver.navigate().back();
        assertEquals(driver.getCurrentUrl(), "https://demoqa.com/profile");
    }
}
