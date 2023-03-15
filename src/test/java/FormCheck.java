import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FormCheck {
    private WebDriver driver;

    //@BeforeEach
    @BeforeEach
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://demoqa.com/automation-practice-form");

    }
    @Test
    public void fillElements() {
        //заполнение формы
        driver.findElement(By.xpath("//*[@placeholder='First Name']")).sendKeys("Vasya");
        driver.findElement(By.xpath("//*[@placeholder='Last Name']")).sendKeys("Ivanov");
        driver.findElement(By.xpath("//*[@placeholder='name@example.com']")).sendKeys("Ivanov@mail.com");
        driver.findElement(By.id("userNumber")).sendKeys("89131234567");
        driver.findElement(By.xpath("//*[@for='gender-radio-1']")).click();
        driver.findElement(By.id("dateOfBirthInput")).click();
        driver.findElement(By.xpath("//div[text()='15']")).click();
        driver.findElement(By.xpath("//*[@id='subjectsContainer']//input")).sendKeys("Maths");
        driver.findElement(By.xpath("//*[@id='subjectsContainer']//input")).sendKeys(Keys.RETURN);
        driver.findElement(By.xpath("//*[@for='hobbies-checkbox-1']")).click();
        driver.findElement(By.xpath("//*[@placeholder='Current Address']")).sendKeys("Lenina 1");
        driver.findElement(By.id("uploadPicture")).sendKeys("selenium-snapshot.jpg");
//        driver.quit();//*[@for='hobbies-checkbox-1']
    }

}