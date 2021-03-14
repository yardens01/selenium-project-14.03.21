import java.util.*;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;

import java.io.*;


public class Test {
    public static void main(String[] args) throws IOException {
        //driver base url and webpage start
        String baseUrl="http://tutorialsninja.com/demo/index.php";
        WebDriver driver=HomePage.chromeDriver(baseUrl);
        //create a logger folder.
       
        // newLoggerFolder.makeNewFolder();
        // //get all links from home page and write them to a file name HomePageLink.txt
        // allLinks(driver);
        // //get only top bar links
        // TopLinks(driver);
        // //check if loggin works
        // LogginTests(driver);
        // System.out.println("end of program");
        // //write all FooterLinks
        // FooterLinks(driver);
        footerLinkCheck(driver);
       
 
        driver.close();

} 

//log all links.
       static void allLinks(WebDriver driver) throws IOException
 {
        System.out.println("-allLinks function started-");
        //create a new log txt file.
        FileWriter logger=new FileWriter("loggerFolder\\HomePageLinks.txt");
        String details;
           //Get list of web-elements with tagName  - a
        List<WebElement> allLinks = driver.findElements(By.tagName("a"));
        // logger.write("----start of log----" +"\n");
           //going through the list and logging all links by text name and atrribute
          for(WebElement link:allLinks){
            details= (link.getAttribute("textContent") + " - " + link.getAttribute("href"));
            logger.append(details+"\n");
           

    }
    // logger.append("-----end of log---- \n");
    logger.close();
    
    System.out.println("home page link has been logged.");
}

//check for top links and working links and log them by title and link.
  static void TopLinks(WebDriver driver) throws IOException
    {
        System.out.println("-TopLinks function started-");
        //create a new log txt file.
        FileWriter logger=new FileWriter("loggerFolder\\TopLinks.txt");
        System.out.println("Top Logger start");
        FileWriter lg=new FileWriter("loggerFolder\\workingTopLink.txt");
        System.out.println("working top logger start");
        String details;
           //Get list of web-elements with tagName  - a
        List<WebElement> TopLinks = driver.findElements(By.xpath("//*[@id='menu']/div[2]/ul//li//a"));
        // logger.write("----start of log----" +"\n");
           //going through the list and logging all links by text name and atrribute
          for(WebElement link:TopLinks){
            details= (link.getAttribute("textContent")+ " - " + link.getAttribute("href"));
            logger.append(details+"\n");
            //search for products active links and log the in a file
           if(workingLinks(link)) 
            {
           lg.append(details+ "\n");}

    }
    // logger.append("-----end of log---- \n");
    logger.close();
    lg.close();
    
    System.out.println("Top links and Working links has been logged.");
    }

    //check if links text>0
    static boolean workingLinks(WebElement link){
        
        if((!(link.getAttribute("textContent").contains("0"))
        &&(link.getAttribute("textContent").contains("(")) 
        &&(link.getAttribute("textContent").contains(")"))))
        return true;

        return false;
    }


static void logginNav(WebDriver driver){
    driver.findElement(By.xpath("//*[@id='top-links']/ul/li[2]/a/span[1]")).click();
    driver.findElement(By.xpath("//a[contains(text(),'Login')]")).click();
    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

}
    
static void logginCheck(WebDriver driver, String email, String password)
{
    String oldTitle=driver.getCurrentUrl();
    System.out.println(oldTitle);
    driver.findElement(By.id("input-email")).sendKeys(email);
    driver.findElement(By.id("input-password")).sendKeys(password);
    
    driver.findElement(By.xpath("//input[contains(@value,'Login')]")).click();;
    String correntTitle=driver.getCurrentUrl();
    System.out.println(correntTitle);
    if(oldTitle.equals(correntTitle))
    {
        String LoggingError=driver.findElement(By.xpath("//div[contains(@class,'alert-danger')]")).getText();
        System.out.println("failed to log in!"+ LoggingError);
    }
   
    
    else{System.out.println("success!");
    loggOut(driver);
    logginNav(driver);
}



    
}

static void LogginTests(WebDriver driver) throws FileNotFoundException
   {


       //navigate to loggin page.
    logginNav(driver);
    //scann file for passwords
    File f=new File("login.txt");
    Scanner scanner=new Scanner(f);
    String line;
    while (scanner.hasNextLine()) {
        line=scanner.nextLine();
        String details[]=line.split(",");
        String email=details[0];
        String password=details[1];
        logginCheck(driver, email, password);
       
        // driver.findElement(By.xpath("//*[@id='column-right']/div/a[13]]")).click();;
        // System.out.println("logged out.");
        
    }

    scanner.close();
   }

static void loggOut(WebDriver driver){
    driver.findElement(By.xpath("//*[@id='top-links']/ul/li[2]/a/span[1]")).click();
    driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();
    driver.findElement(By.xpath("//a[contains(text(),'Continue')]")).click();
    System.out.println("logged out.");
    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

}

static void FooterLinks(WebDriver driver) throws IOException
    {
        System.out.println("-FooterLinks function started-");
        //create a new log txt file.
        FileWriter logger=new FileWriter("loggerFolder\\FooterLinks.txt");
        System.out.println("footer Logger start");
        String details;
           //Get list of web-elements with tagName  - a
        List<WebElement> footerLinks = driver.findElements(By.xpath("//footer//a"));
        // logger.write("----start of log----" +"\n");
           //going through the list and logging all links by text name and atrribute
          for(WebElement link:footerLinks){
            details= (link.getAttribute("textContent")+ " - " + link.getAttribute("href"));
            logger.append(details+"\n");
            //search for products active links and log the in a file
      
          }
    // logger.append("-----end of log---- \n");
    logger.close();
  
    
    System.out.println("footer links has been logged.");
    }

static void footerLinkCheck(WebDriver driver) throws IOException
{
    File f=new File("loggerFolder\\FooterLinks.txt");
    Scanner scanner=new Scanner(f);
    String link;
    while (scanner.hasNextLine()) {
        link=scanner.nextLine();
        String details[]=link.split(" - ");
        String linkName=details[0];
        String linkUrl=details[1];
        // System.out.println(linkName);
        // System.out.println(linkUrl);
        System.out.println(linkCheck(driver, linkUrl));
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        

}
scanner.close();


 }

 static boolean linkCheck(WebDriver driver, String linkUrl)
 {

   String currentUrl= driver.getCurrentUrl();
    driver.get(linkUrl);
    String newUrl=driver.getCurrentUrl();
    driver.navigate().back();
    if(newUrl.equals(currentUrl))
    return true;
    return false;

 }
}
