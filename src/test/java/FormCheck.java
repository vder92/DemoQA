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
    public void fillElements() throws InterruptedException {
        //заполнение формы
        //тестовые данные
        String nameInputData = "Vasya";
        String lastNameInputData = "Ivanov";
        String nameAndLastInputData = nameInputData + " " + lastNameInputData;
        String eMailInputData = "Ivanov@mail.com";
        String numberInputData = "8913123456";
        String genderInputData = "Male";
        String birthDayInputData = "15 March,2023";
        String subjectInputData = "Maths";
        String hobbyInputData = "Sports";
        String addressInputData = "Lenina 1";
        String fileFromForm = "1.png";
        String stateInputData = "NCR";
        String cityInputData = "Delhi";
        String stateCityInputData = "NCR Delhi";

        //ФИО, почта, номер
        driver.findElement(By.xpath("//*[@placeholder='First Name']")).sendKeys(nameInputData);
        driver.findElement(By.xpath("//*[@placeholder='Last Name']")).sendKeys(lastNameInputData);
        driver.findElement(By.xpath("//*[@placeholder='name@example.com']")).sendKeys(eMailInputData);
        driver.findElement(By.id("userNumber")).sendKeys(numberInputData);
        //gender
        driver.findElement(By.xpath("//*[@for='gender-radio-1']")).click();
        //birthday
        driver.findElement(By.id("dateOfBirthInput")).click();
        driver.findElement(By.xpath("//div[text()='15']")).click();
        //scroll down
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        //subject
        driver.findElement(By.xpath("//*[@id='subjectsContainer']//input")).sendKeys(subjectInputData);
        driver.findElement(By.xpath("//*[@id='subjectsContainer']//input")).sendKeys(Keys.RETURN);
        //hobbies
        driver.findElement(By.xpath("//*[@for='hobbies-checkbox-1']")).click();
        //address
        driver.findElement(By.xpath("//*[@placeholder='Current Address']")).sendKeys(addressInputData);
        // Загрузка файла
        File f = new File("1.png");
        String filepath = f.getAbsolutePath();
        driver.findElement(By.id("uploadPicture")).sendKeys(filepath);
        //state
        driver.findElement(By.xpath("//*[@id='react-select-3-input']")).sendKeys(stateInputData);
        driver.findElement(By.xpath("//*[@id='react-select-3-input']")).sendKeys(Keys.RETURN);
        //city
        driver.findElement(By.xpath("//*[@id='react-select-4-input']")).sendKeys(cityInputData);
        driver.findElement(By.xpath("//*[@id='react-select-4-input']")).sendKeys(Keys.ENTER);

        //submit  form

//        JavascriptExecutor executor = (JavascriptExecutor) driver;
//        executor.executeScript("document.body.style.zoom = '0.5'");
//        WebElement buttonSubmit = driver.findElement(By.xpath("//*[@type='submit']"));
//        executor.executeScript("arguments[0].click()", buttonSubmit);

        WebElement submitButton = driver.findElement(By.id("submit"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);
        Thread.sleep(500);
        submitButton.click();

        //wait for the final form
        WebDriverWait wait = new WebDriverWait(driver, 30, 500);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='example-modal-sizes-title-lg']")));
        //submitted form checks
        //executor.executeScript("document.body.style.zoom = '1'");
        //Name
        WebElement name = driver.findElement(By.xpath("//tbody//tr[1]//td[2]"));
        assertEquals(nameAndLastInputData, name.getText());
        //eMail
        WebElement eMail = driver.findElement(By.xpath("//tbody//tr[2]//td[2]"));
        assertEquals(eMailInputData, eMail.getText());
        //Gender
        WebElement gender = driver.findElement(By.xpath("//tbody//tr[3]//td[2]"));
        assertEquals(genderInputData, gender.getText());
        //Mobile
        WebElement number = driver.findElement(By.xpath("//tbody//tr[4]//td[2]"));
        assertEquals(numberInputData, number.getText());
        //Birthday
        WebElement birthday = driver.findElement(By.xpath("//tbody//tr[5]//td[2]"));
        assertEquals(birthDayInputData, birthday.getText());
        //Subjects
        WebElement subjects = driver.findElement(By.xpath("//tbody//tr[6]//td[2]"));
        assertEquals(subjectInputData, subjects.getText());
        //Hobbies
        WebElement hobbies = driver.findElement(By.xpath("//tbody//tr[7]//td[2]"));
        assertEquals(hobbyInputData, hobbies.getText());
        //Picture
        WebElement picture = driver.findElement(By.xpath("//tbody//tr[8]//td[2]"));
        assertEquals(fileFromForm, picture.getText());
        //Address
        WebElement address = driver.findElement(By.xpath("//tbody//tr[9]//td[2]"));
        assertEquals(addressInputData, address.getText());
        //State and City
        WebElement stateCity = driver.findElement(By.xpath("//tbody//tr[10]//td[2]"));
        assertEquals(stateCityInputData, stateCity.getText());
    }
}
