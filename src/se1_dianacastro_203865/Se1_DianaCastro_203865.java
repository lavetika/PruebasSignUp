package se1_dianacastro_203865;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author dianacastro
 */
public class Se1_DianaCastro_203865 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "/Users/dianacastro/NetBeansProjects/se1_DianaCastro_203865/jars/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("https://ritwickdey.github.io/sample-login-signup-form/#/signup");

        driver.findElement(By.id("name")).sendKeys("Diana Castro");
        driver.findElement(By.id("phone")).sendKeys("1234567890");
        driver.findElement(By.id("email")).sendKeys("123@correo.com");
        driver.findElement(By.id("password")).sendKeys("123");

        WebElement formError = driver.findElement(By.className("form-error"));
        WebElement msjError = formError.findElement(By.tagName("div"));
        System.out.println(msjError.getText());

    }

}
