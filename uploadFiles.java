
import org.openqa.selenium.*;

import java.io.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class uploadFiles{


  
        public static void main(String[] args) throws IOException, AWTException,InterruptedException {
            //driver base url and webpage start
            String baseUrl="http://tutorialsninja.com/demo/index.php?route=product/product&product_id=42";
            WebDriver driver=HomePage.chromeDriver(baseUrl);

            WebElement element = driver.findElement(By.id("button-upload222"));
            element.click();

            StringSelection ss = new StringSelection("C:\\Users\\yarden schorr\\Pictures\\yad2\\bug2.png");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		// Ctrl + v
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(2000);
        driver.switchTo().alert().dismiss();
        
}
}