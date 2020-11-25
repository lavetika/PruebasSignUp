package se1_dianacastro_203865;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author dianacastro
 */
public class PruebasSignUpTest {

    private static WebDriver driver;

    public PruebasSignUpTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        System.setProperty("webdriver.chrome.driver", "/Users/dianacastro/NetBeansProjects/se1_DianaCastro_203865/jars/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @AfterClass
    public static void tearDownClass() {
        driver.quit();
    }

    @Before
    public void setUp() {
        driver.get("https://ritwickdey.github.io/sample-login-signup-form/#/signup");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void pruebaPagina() {
        System.out.println("Prueba el título de la página web");

        String titulo = driver.getTitle();

        System.out.println("El título es : " + titulo);
        assertEquals("Basic login & Signup with Angular", titulo);
    }

    @Test
    public void registroCorrecto() {
        System.out.println("Prueba de un registro correcto");

        //Envía datos válidos a todos los campos
        driver.findElement(By.id("name")).sendKeys("Diana Castro");
        driver.findElement(By.id("phone")).sendKeys("1234567890");
        driver.findElement(By.id("email")).sendKeys("123@correo.com");
        driver.findElement(By.id("password")).sendKeys("contra");

        //Click en el botón signup
        driver.findElement(By.xpath("//button[text()='Signup']")).click();

        //Verifica que se abre la nueva url
        String ligaLogin = driver.getCurrentUrl();
        assertEquals("https://ritwickdey.github.io/sample-login-signup-form/#/login", ligaLogin);
    }

    @Test
    public void verificarVacios() {
        System.out.println("Verifica que ningún campo pueda quedar vacío");

        //Envía datos vacíos
        driver.findElement(By.id("name")).sendKeys("");
        driver.findElement(By.id("phone")).sendKeys("");
        driver.findElement(By.id("email")).sendKeys("");
        driver.findElement(By.id("password")).sendKeys("");
        //Regresa al primer elemento para que muestre el último mensaje de error
        driver.findElement(By.id("name")).sendKeys("");

        //Obtiene los elementos con los mensajes de Error
        List<WebElement> listaErrores = driver.findElements(By.className("form-error"));
        //Lista para agregar los mensajes de errores
        List<String> msjErrores = new ArrayList<>();
        //Obtiene cada uno de los errores y los agrega a la lista
        for (WebElement elementoError : listaErrores) {
            String msj = elementoError.findElement(By.tagName("div")).getText();
            msjErrores.add(msj);
        }

        //Verifica todos los mensajes de error
        assertEquals("Name is required", msjErrores.get(0));
        assertEquals("Phone is required", msjErrores.get(1));
        assertEquals("Email is required", msjErrores.get(2));
        assertEquals("Password is required", msjErrores.get(3));
    }

    @Test
    public void pruebaNombre() {
        //Longitud minima 3
        System.out.println("Prueba la validación de la longitud del nombre");

        driver.findElement(By.id("name")).sendKeys("yo");
        driver.findElement(By.id("phone")).sendKeys("1234567890");
        driver.findElement(By.id("email")).sendKeys("123@correo.com");
        driver.findElement(By.id("password")).sendKeys("contra");

        WebElement formError = driver.findElement(By.className("form-error"));
        WebElement msjError = formError.findElement(By.tagName("div"));

        //Verifica que coincida el mensaje de error
        assertEquals("Name must be at least 3 characters long.", msjError.getText());
    }

    @Test
    public void pruebaPhoneNumber() {
        System.out.println("Prueba la validación del número telefónico");

        driver.findElement(By.id("name")).sendKeys("Diana Castro");
        driver.findElement(By.id("phone")).sendKeys("12345abc");
        driver.findElement(By.id("email")).sendKeys("123@correo.com");
        driver.findElement(By.id("password")).sendKeys("contra");

        WebElement formError = driver.findElement(By.className("form-error"));
        WebElement msjError = formError.findElement(By.tagName("div"));

        //Verifica que coincida el mensaje de error
        assertEquals("Phone number must be 10 digit.", msjError.getText());
    }

    @Test
    public void pruebaValidacionCorreo() {
        System.out.println("Prueba de validación del correo electrónico");

        driver.findElement(By.id("name")).sendKeys("Diana Castro");
        driver.findElement(By.id("phone")).sendKeys("1234567890");
        driver.findElement(By.id("email")).sendKeys("123");
        driver.findElement(By.id("password")).sendKeys("contra");

        WebElement formError = driver.findElement(By.className("form-error"));
        WebElement msjError = formError.findElement(By.tagName("div"));

        //Verifica que coincida el mensaje de error
        assertEquals("Invalid Email address.", msjError.getText());

    }

    @Test
    public void verificaPassword() {
        System.out.println("Prueba la validación de la contraseña");

        driver.findElement(By.id("name")).sendKeys("Diana");
        driver.findElement(By.id("phone")).sendKeys("1234567890");
        driver.findElement(By.id("email")).sendKeys("123@correo.com");
        driver.findElement(By.id("password")).sendKeys("123");
        
        driver.findElement(By.id("name")).sendKeys(" Castro");
        
        WebElement formError = driver.findElement(By.className("form-error"));
        WebElement msjError = formError.findElement(By.tagName("div"));

        //Verifica que coincida el mensaje de error
        assertEquals("Password must be at least 4 characters long.", msjError.getText());
    }

    @Test
    public void pruebaLinkAccount() {
        System.out.println("Prueba el funcionamiento del link");
        
        driver.findElement(By.xpath("//a[text()='Already have an account?']")).click();

        //Verifica que se abre la nueva url
        String ligaLogin = driver.getCurrentUrl();
        assertEquals("https://ritwickdey.github.io/sample-login-signup-form/#/login", ligaLogin);
        
    }

}
