

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
public class HomePage {
    static WebDriver driver;

	//create a chrome driver
    public static WebDriver chromeDriver(String baseUrl)
    {

        System.setProperty("webdriver.chrome.driver","C:\\Users\\yarden schorr\\Documents\\project\\selenium project\\drivers\\Lib\\chromedriver.exe" );
        WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(baseUrl);
		return driver;

    }
    }    
