import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.opera.OperaDriver;

import java.util.List;
import java.util.Set;

public class SeleniumDriverTest {
private WebDriver driver;
    @BeforeEach
    public void setup() {
        //System.setProperty("webdriver.chrome.driver", "D:\\web_drivers/chrome/chromedriver.exe");
        //WebDriver driver = null;
        String browser = System.getProperty("browser");

        if (browser.equals("edge")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equals("opera")) {
            WebDriverManager.operadriver().setup();
            driver = new OperaDriver();
        } else {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        driver.get("https://demoqa.com/automation-practice-form");
        driver.manage().window().maximize();
    }

    @Test
    public void urlTest() {
    Assertions.assertEquals("https://demoqa.com/automation-practice-form", driver.getCurrentUrl());
    }

    @Test
    public void mainHeaderTest() {
        WebElement mainHeader = driver.findElement(By.className("main-header"));
        String mainHeaderText = mainHeader.getText();
        Assertions.assertEquals("Practice Form", mainHeaderText);
    }

@AfterEach
    public void stopDriver(){
        driver.quit();
}

}
