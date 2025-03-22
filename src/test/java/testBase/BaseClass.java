package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	
public static WebDriver driver;
public Logger logger;
public Properties p;
	
	//@BeforeClass(groups={"sanity","Regression","Master","DataDriven"})
	/*@BeforeClass(groups="sanity")
	@Parameters({"OS","browser"})
	public void setup(String os,String br) throws InterruptedException, IOException 
	{

		//Loading config.properties file
		FileReader file=new FileReader("./src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
		
		logger =LogManager.getLogger(this.getClass());*/
		
		//remote Selenium grid
		/*if(p.getProperty("execution_env").equalsIgnoreCase("remote"));
		{
			DesiredCapabilities capabilities=new DesiredCapabilities();
			
			//OS
			if(os.equalsIgnoreCase("windows"))
			{
				capabilities.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("mac"))
			{
				capabilities.setPlatform(Platform.WIN11);
			}
			else
			{
				System.out.println("No matching os");
				return;
			}
			
			//browser
			switch(br.toLowerCase())
			{
			case "chrome":capabilities.setBrowserName("chrome");break;
			case "edge":capabilities.setBrowserName("MicrosoftEdge");break;
			default:System.out.println("No matching browse");return;
			}
			
			driver =new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
		}*/
	
		
		//local Selenium grid
		
		@BeforeClass(groups="sanity")
		public void setup() throws InterruptedException, IOException 
		{

			//Loading config.properties file
			FileReader file=new FileReader("./src//test//resources//config.properties");
			p=new Properties();
			p.load(file);
			
			logger =LogManager.getLogger(this.getClass());
		
	/*if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
		switch(br.toLowerCase())
		{
		case "chrome" : driver=new ChromeDriver();break;
		case "edge" : driver=new EdgeDriver();break;
		case "firefox" : driver=new FirefoxDriver();break;
		default : System.out.println("Invalid browser name");return;

		}*/
	//}
		
		driver=new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("appURL"));
		driver.manage().window().maximize();
	}
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
	
	
	@SuppressWarnings("deprecation")
	public String randomString()
	{
		
		String generatedString =RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
	
	@SuppressWarnings("deprecation")
	public String randomNumber()
	{
		
	
		String generatedNumber =RandomStringUtils.randomNumeric(10);
		return generatedNumber;
	}
	
	@SuppressWarnings("deprecation")
	public String randomAlphaNumeric()
	{
		
	
		String generatedAlphaNumeric =RandomStringUtils.randomAlphanumeric(10);
		return generatedAlphaNumeric;
	}
	
	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
			
		return targetFilePath;

	}

}
