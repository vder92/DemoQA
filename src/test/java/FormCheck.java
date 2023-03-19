import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FormCheck {
    private WebDriver driver;

    @BeforeEach
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://demoqa.com/automation-practice-form");
        driver.manage().window().maximize();
    }

    @AfterEach
    public void exit() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void fillElements() {
        //заполнение формы
        //ФИО, почта, номер
        driver.findElement(By.xpath("//*[@placeholder='First Name']")).sendKeys("Vasya");
        driver.findElement(By.xpath("//*[@placeholder='Last Name']")).sendKeys("Ivanov");
        driver.findElement(By.xpath("//*[@placeholder='name@example.com']")).sendKeys("Ivanov@mail.com");
        driver.findElement(By.id("userNumber")).sendKeys("8913123456");
        //gender
        driver.findElement(By.xpath("//*[@for='gender-radio-1']")).click();
        //birthday
        driver.findElement(By.id("dateOfBirthInput")).click();
        driver.findElement(By.xpath("//div[text()='15']")).click();
        //scroll down
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        //subject
        driver.findElement(By.xpath("//*[@id='subjectsContainer']//input")).sendKeys("Maths");
        driver.findElement(By.xpath("//*[@id='subjectsContainer']//input")).sendKeys(Keys.RETURN);
        //hobbies
        driver.findElement(By.xpath("//*[@for='hobbies-checkbox-1']")).click();
        //address
        driver.findElement(By.xpath("//*[@placeholder='Current Address']")).sendKeys("Lenina 1");
        // Загрузка файла
        File f = new File("1.png");
        String filepath = f.getAbsolutePath();
        driver.findElement(By.id("uploadPicture")).sendKeys(filepath);
        //state
        driver.findElement(By.xpath("//*[@id='react-select-3-input']")).sendKeys("nc");
        driver.findElement(By.xpath("//*[@id='react-select-3-input']")).sendKeys(Keys.RETURN);
        //city
        driver.findElement(By.xpath("//*[@id='react-select-4-input']")).sendKeys("de");
        driver.findElement(By.xpath("//*[@id='react-select-4-input']")).sendKeys(Keys.ENTER);
        //submit  form
        driver.findElement(By.xpath("//*[@placeholder='First Name']")).sendKeys(Keys.ENTER);
        //wait for the final form
        WebDriverWait wait = new WebDriverWait(driver, 30, 500);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='example-modal-sizes-title-lg']")));
        //submitted form checks
        //Name
        WebElement name = driver.findElement(By.xpath("//tbody//tr[1]//td[2]"));
        assertEquals("Vasya Ivanov", name.getText());
        //eMail
        WebElement eMail = driver.findElement(By.xpath("//tbody//tr[2]//td[2]"));
        assertEquals("Ivanov@mail.com", eMail.getText());
        //Gender
        WebElement gender = driver.findElement(By.xpath("//tbody//tr[3]//td[2]"));
        assertEquals("Male", gender.getText());
        //Mobile
        WebElement number = driver.findElement(By.xpath("//tbody//tr[4]//td[2]"));
        assertEquals("8913123456", number.getText());
        //Birthday
        WebElement birthday = driver.findElement(By.xpath("//tbody//tr[5]//td[2]"));
        assertEquals("15 March,2023", birthday.getText());
        //Subjects
        WebElement subjects = driver.findElement(By.xpath("//tbody//tr[6]//td[2]"));
        assertEquals("Maths", subjects.getText());
        //Hobbies
        WebElement hobbies = driver.findElement(By.xpath("//tbody//tr[7]//td[2]"));
        assertEquals("Sports", hobbies.getText());
        //Picture
        WebElement picture = driver.findElement(By.xpath("//tbody//tr[8]//td[2]"));
        assertEquals("1.png", picture.getText());
        //Address
        WebElement address = driver.findElement(By.xpath("//tbody//tr[9]//td[2]"));
        assertEquals("Lenina 1", address.getText());
        //State and City
        WebElement stateCity = driver.findElement(By.xpath("//tbody//tr[10]//td[2]"));
        assertEquals("NCR Delhi", stateCity.getText());

    }
}