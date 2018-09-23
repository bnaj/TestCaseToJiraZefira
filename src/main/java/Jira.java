import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

public class Jira {

    public static WebDriver driver() {
        File driverFile = new File(System.getProperty("user.dir") + "/src/main/resources");

        System.setProperty("webdriver.chrome.driver", driverFile.getAbsoluteFile() + "/chromedriver");

        WebDriver driver = new ChromeDriver();

        System.out.println(driverFile.getAbsoluteFile().getParent());

        return driver;
    }
}
