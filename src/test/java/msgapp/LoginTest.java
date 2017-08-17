package msgapp;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by chen on 19/7/17.
 */
public class LoginTest {

    private WebDriver driver;

    public LoginTest() {
        if ("true".equals(System.getenv("TRAVIS"))){
            driver = new FirefoxDriver();
        } else {
            driver = new ChromeDriver();
        }
    }
    private WebElement waitForElement(By by) {
        Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(30, SECONDS).pollingEvery(5, SECONDS).ignoring(NoSuchElementException.class);
        return wait.until(driver -> driver.findElement(by));
    }

    @Test
    public void testLogIn() {
        driver.get("http://www.smsfun.com.au/joobz.php");
        WebElement username = driver.findElement(By.name("mobile"));
        username.sendKeys(System.getenv("MSGAPP_USERNAME"));
        WebElement pwd = driver.findElement(By.name("pwd"));
        pwd.sendKeys(System.getenv("MSGAPP_PWD"));
        driver.findElement(By.name("submit")).click();
        WebElement joobzNum = driver.findElement(By.className("notice-joobz"));
        System.out.println(joobzNum.getText());
        //driver.findElement(By.linkText("Joobz")).click();
        //driver.findElement(By.linkText("Earn Joobz")).click();
        Actions builder= new Actions(driver);
        builder.moveToElement(driver.findElement(By.linkText("Joobz"))).perform();
        waitForElement(By.linkText("Earn Joobz")).click();
        //driver.findElement(By.linkText("Earn Joobz")).click();
        waitForElement(By.linkText("Daily Joobz")).click();
        String taskContainerText = driver.findElement(By.className("joobz-task-container")).getText();
        if (taskContainerText.contains("You already have claimed")){
            System.out.println(taskContainerText);
        } else {
            driver.findElement(By.cssSelector("input[value='Claim Your Daily Joobz']")).click();
            System.out.println("Joobz claimed");
        }

    }




}
