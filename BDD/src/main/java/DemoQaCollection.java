import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DemoQaCollection {

    WebDriverWait wait;
    WebDriver driver;

    @Given("home page  is open")
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

    @Then("Click on Table item Git Pocket Guide")
    public void chooseBook() {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
        new WebDriverWait(driver, Duration.ofSeconds(50)).until(ExpectedConditions.elementToBeClickable(By.id("gotoStore")));
        WebElement element = driver.findElement(By.id("gotoStore"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

        WebElement goToStoreButton= driver.findElement(By.id("gotoStore"));
        goToStoreButton.click();
        new WebDriverWait(driver, Duration.ofSeconds(50)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"see-book-Git Pocket Guide\"]/a"))).click();
        WebElement bookTitle=driver.findElement(By.xpath("//*[@id=\"title-wrapper\"]/div[2]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", bookTitle);
        assertEquals(bookTitle.getText(),"Git Pocket Guide");
    }


    @Then("Add book to your collection")
    public void addBookToYourCollection() {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
       //new WebDriverWait(driver, Duration.ofSeconds(50)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"Git Pocket Guide\"]/a")));
        //WebElement element = driver.findElement(By.xpath("//*[@id=\"Git Pocket Guide\"]/a"));
       // ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        //WebElement chooseBook=driver.findElement(By.xpath("//*[@id=\"Git Pocket Guide\"]/a"));
        //chooseBook.click();
        new WebDriverWait(driver, Duration.ofSeconds(50)).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Add To Your Collection']")));
        WebElement element = driver.findElement(By.xpath("//button[text()='Add To Your Collection']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        WebElement addToCollection = driver.findElement(By.xpath("//button[text()='Add To Your Collection']"));
        try{
            addToCollection.click();

        } catch (UnhandledAlertException f) {
            try {
                WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
                wait.until(ExpectedConditions.alertIsPresent());
                Alert alert = driver.switchTo().alert();
                //assertEquals(alert.getText(),"Book added to your collection.");
                alert.accept();
            } catch (NoAlertPresentException e) {
                e.printStackTrace();
            }
        }
    }

    @Then("go back to home page")
    public void goBackToHomePage() {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
        driver.navigate().back();
        assertEquals(driver.getCurrentUrl(), "https://demoqa.com/profile");
    }
}
