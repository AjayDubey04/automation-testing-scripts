package recape.selenium.evs;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.google.common.io.Files;


public class BrowserLaunch {


	private WebDriver driver;

	public static void main(String[] args) throws InterruptedException {


		BrowserLaunch obj=new BrowserLaunch();
		obj.launchBrowser();
		obj.windowHandle();
		
	}
	
	public void practiesAbouteMethods() {
		
		
		
		
	}


	public void launchBrowser() {

		System.setProperty("webdriver.chrome.driver", "driver//chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("http://localhost:8888");
		driver.manage().timeouts().implicitlyWait(-60,TimeUnit.SECONDS);
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.name("Login")).click();

	}


	public void waits() throws InterruptedException {


		// static wait
		Thread.sleep(5000);


		// implicitly wait  
		Timeouts time=driver.manage().timeouts();
		time.implicitlyWait(Duration.ofSeconds(60));
		time.implicitlyWait(60, TimeUnit.SECONDS);

		// implicitly wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);


		// Explicitly Wait
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(60));
		wait.until(ExpectedConditions.alertIsPresent());
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("hello")));

		// this is Explicitly wait of 60 second
		WebDriverWait wet=new WebDriverWait(driver, Duration.ofSeconds(60));
		wet.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("")));
		wet.until(ExpectedConditions.not(ExpectedConditions.visibilityOfElementLocated(By.xpath(""))));

		
		// Fluent Wait
		Wait<WebDriver> wt = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(2))
				.ignoring(NoSuchElementException.class);

		WebElement element=wt.until(ExpectedConditions.presenceOfElementLocated(By.name("Login")));
		element.click();

	}


	public void refreshCodes() {

		WebElement we=driver.findElement(By.name("user_name"));

		//      Refresh the page
		//		driver.navigate().refresh();
		//		we.sendKeys("admin");

		 //  Refresh the page by navigating to the same URL
		//   driver.navigate().to(driver.getCurrentUrl());
		//   we.sendKeys("admin");

		// Refresh the page using the F5 key
		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.F5).perform();
		we.sendKeys("admin");

		//  Refresh the page using JavaScript
		//		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		//		jsExecutor.executeScript("location.reload()");
		//		we.sendKeys("admin");


	}


	public void performScroll() throws InterruptedException {


		Thread.sleep(5000);

		//		JavascriptExecutor js=(JavascriptExecutor)driver;
		//		js.executeScript("window.scrollBy(0,1000)");
		//		Thread.sleep(5000);
		//		js.executeScript("window.scrollTo(0,-5000)");
		//		Thread.sleep(5000);
		//		js.executeScript("arguments[0].scrollIntoView()",driver.findElement(By.xpath("//b[contains(text(),'Top Potentials')]")));
		//		

		Actions act=new Actions(driver);
		act.scrollByAmount(0, 5000).perform();
		Thread.sleep(5000);
		act.scrollByAmount(0, -5000).perform();
		Thread.sleep(5000);
		act.scrollToElement(driver.findElement(By.xpath("//a[text()='Read License']"))).perform();

	}

	public void windowHandle() {

		String mainHandle=driver.getWindowHandle();
		driver.findElement(By.name("user_name")).sendKeys("admin");

		driver.switchTo().newWindow(WindowType.TAB);
		driver.get("http://amazon.com");

		driver.switchTo().newWindow(WindowType.TAB);
		driver.get("http://flipkart.com");

		Set<String> win=driver.getWindowHandles();
		for (String string : win) {
			driver.switchTo().window(string);
			String url=driver.getCurrentUrl();
			if(url.equalsIgnoreCase("https://www.flipkart.com/")) {
				driver.close();
				break;
			}
		}

		driver.switchTo().window(mainHandle);
		System.out.println("main page Url = "+driver.getCurrentUrl());
	}



	public void javascriptExecutor() {

		WebElement we=null;
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("alert(hello alert)");
		js.executeScript("window.scrollBy(5000,1000)");
		js.executeScript("window.scrollTo(5000,1000)");
		js.executeScript("arguments[0].scrollIntoView(5000,1000)",we);
		js.executeScript("argumets[0].click()",we);
		js.executeScript("arguments[0].value='admin'",we);
		js.executeScript("location.reload()",we);


	}


	public void takesScreenshot() throws IOException {

		// for full page 
		TakesScreenshot ts=(TakesScreenshot)driver;
		File Fobj=ts.getScreenshotAs(OutputType.FILE);
		File newObj=new File("paht//of//folder//file");
		Files.copy(Fobj, newObj);

		// for perticuler Element
		WebElement we=driver.findElement(By.xpath(""));
		File f=we.getScreenshotAs(OutputType.FILE);
		File nF=new File("folder//path");
		Files.copy(f, nF);

	}

	public void frame() {


		TargetLocator tl=driver.switchTo();
		tl.frame(0);
		tl.frame("");
		WebElement we=null;
		tl.frame( we );
		tl.defaultContent();
		tl.parentFrame();
		tl.activeElement();	

	}


	public void downloadFile() {

		// for downloaed file 
		System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
		HashMap<String,Object> perfs=new HashMap<String,Object>();
		perfs.put("download.prompt_for_download", false);
		perfs.put("download.default_directory", "file//path");
		
		ChromeOptions co=new ChromeOptions();
		co.setExperimentalOption("perfs",perfs);
		driver=new ChromeDriver(co);

		// for downloaed file 
		driver.findElement(By.xpath("//name[@type='file']")).sendKeys("file//path");

	}


	public void FDbyRobotClass() throws AWTException {

		// upload file by robot class
		WebElement we=driver.findElement(By.xpath("download_Options_Xpath"));
		we.click();

		StringSelection ss=new StringSelection("file//path");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

		Robot name = new Robot();
		name.delay(5000);

		name.keyPress(KeyEvent.VK_CONTROL);
		name.keyPress(KeyEvent.VK_V);
		name.keyRelease(KeyEvent.VK_CONTROL);
		name.keyRelease(KeyEvent.VK_V);
		name.keyPress(KeyEvent.VK_ENTER);
		name.keyRelease(KeyEvent.VK_ENTER);		


		// download file by robot class
		WebElement Dwe=driver.findElement(By.xpath("download_Options_Xpath"));
		Dwe.click();

		StringSelection Dss=new StringSelection("");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(Dss, null);

		Robot Dname = new Robot();
		Dname.delay(5000);

		Dname.keyPress(KeyEvent.VK_CONTROL);
		Dname.keyPress(KeyEvent.VK_V);
		Dname.keyRelease(KeyEvent.VK_CONTROL);
		Dname.keyRelease(KeyEvent.VK_V);
		Dname.keyPress(KeyEvent.VK_ENTER);
		Dname.keyRelease(KeyEvent.VK_ENTER);		




	}


	public void alert() {

		Alert al=driver.switchTo().alert();
		al.accept();
		al.dismiss();
		al.getText();
		al.sendKeys("hello alert");


	}

	public void selectClass() {

		WebElement we=null;
		Select sl=new Select(we);
		sl.selectByIndex(0);
		sl.selectByValue("value");
		sl.selectByVisibleText("innerText");


	}

	public void navigationClass() {

		Navigation ngt=driver.navigate();
		ngt.back();
		ngt.forward();
		ngt.refresh();
		
		ngt.to("http://localhost:8888");
		
		
	}

	
	public void actionsClass() {

		WebElement we=null;
		Actions act=new Actions(driver);
		Action inter=act.build();
		inter.perform();
		act.clickAndHold(we);
		act.doubleClick(we);
		act.contextClick(we);
		act.dragAndDrop(we,we);
		act.moveToElement(we);

	}

	public void addAndDeleteCookie() {

		Options op=driver.manage();
		op.addCookie(null);
		op.getCookies();
		op.getCookieNamed("");
		op.deleteAllCookies();
		op.deleteCookie(null);
		op.deleteCookieNamed(null);

	}

	public void minimizeAndMaximize() {

		Window d=driver.manage().window();
		d.maximize();
		d.minimize();
	}


	public static void DateTimeExample() {
		// Get the current date and time
		LocalDateTime currentDateTime = LocalDateTime.now();

		// Define the desired date and time format
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		// Format the current date and time as a string
		String formattedDateTime = currentDateTime.format(formatter);

		// Print the formatted date and time 
		System.out.println("Current Date and Time: " + formattedDateTime);
	}

}
