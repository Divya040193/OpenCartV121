package testBase;

import java.io.File;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.text.RandomStringGenerator;
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
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

   public static WebDriver driver;
   public Logger logger;
   public Properties p;
	 
	@BeforeClass (groups = {"Sanity","Regression","Master"})
	@Parameters({"os","browser"})
	public void setUp(String os, String  br) throws IOException
	{
		
		FileInputStream file= new FileInputStream("./src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
		
		logger=LogManager.getLogger(this.getClass());
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capabilities=new DesiredCapabilities();
			
			//os
			if(os.equalsIgnoreCase("windows"))
			{
				capabilities.setPlatform(Platform.WIN11);
			}
			else if (os.equalsIgnoreCase("mac"))
			{
				capabilities.setPlatform(Platform.MAC);
			}
			else
			{
				System.out.println("No matching os");
				return;
			}
			
			//browser
			switch(br.toLowerCase())
			{
			case "chrome": capabilities.setBrowserName("chrome"); break;
			case "edge": capabilities.setBrowserName("MicrosoftEdge"); break;
			default: System.out.println("No matching browser"); return;
			}
			
			driver=new RemoteWebDriver(new URL("http://192.168.56.1:4444/wd/hub"),capabilities);
		}
		
				
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{

			switch(br.toLowerCase())
			{
			case "chrome" : driver=new ChromeDriver(); break;
			case "edge" : driver=new EdgeDriver(); break;
			case "firefox": driver=new FirefoxDriver(); break;
			default : System.out.println("Invalid browser name.."); return;
			}
		}
		
		
//		switch(br.toLowerCase())
//		{
//		case "chrome": 
//			driver= new ChromeDriver(); 
//			break;
//		case "edge": 
//			driver= new EdgeDriver(); 
//			break;
//		case "firefox": 
//			driver= new FirefoxDriver(); 
//			break;
//		default: 
//			System.out.println("Invalid browser"); 
//		return;
//		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(p.getProperty("appURL1"));
		driver.manage().window().maximize();
	}
	
	@AfterClass (groups = {"Sanity","Regression","Master"})
	public void tearDown() {
	
		driver.quit();
	}
	
	public String randomeString()
	{
		RandomStringGenerator generator = new RandomStringGenerator.Builder()
		        .withinRange('a', 'z')
		        .build();

		String randomString = generator.generate(5);
		System.out.println(randomString);
		return randomString;
	}
	
	
	public String randomeNumber()
	{
		RandomStringGenerator generator = new RandomStringGenerator.Builder()
		        .withinRange('0', '9')
		        .build();
		String number = generator.generate(10); // "8734519287"
		System.out.println(number);
		return number;
	}
	
	public String randomAlphanumeric() {
		RandomStringGenerator generator = new RandomStringGenerator.Builder()
		        .withinRange('0', 'z')
		        .filteredBy(Character::isLetterOrDigit)
		        .build();

		String randomAlphanumeric = generator.generate(8);
		
		System.out.println(randomAlphanumeric);
		return randomAlphanumeric;
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
