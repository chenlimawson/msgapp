package msgapp;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

/**
 * Created by chen on 19/7/17.
 */
public class LoginTest {
    @Test
    public void testLogIn() {

        WebDriver driver;
        if ("true".equals(System.getenv("TRAVIS"))){
            driver = new FirefoxDriver();
        } else {
            driver = new ChromeDriver();
        }
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
        driver.findElement(By.linkText("Earn Joobz")).click();
        driver.findElement(By.linkText("Daily Joobz")).click();
        String taskContainerText = driver.findElement(By.className("joobz-task-container")).getText();
        if (taskContainerText.contains("You already have claimed")){
            System.out.println(taskContainerText);
        } else {
            driver.findElement(By.cssSelector("input[value='Claim Your Daily Joobz']")).click();
            System.out.println("Joobz claimed");
        }

    }


}
