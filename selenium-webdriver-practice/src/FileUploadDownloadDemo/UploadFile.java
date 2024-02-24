package FileUploadDownloadDemo;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import predefine.methods.VtigerLoginPage;

public class UploadFile  extends VtigerLoginPage {

	WebDriver driver;


	public static void main(String[] args) throws InterruptedException, AWTException  {

		UploadFile obj=new UploadFile();
		obj.uploadFileBySendKeys();
		//	obj.uploadFileOnGmail();



	}

	public void uploadFileBySendKeys() throws InterruptedException {

		driver=loginVtiger();
		driver.findElement(By.linkText("Marketing")).click();
		driver.findElement(By.linkText("Leads")).click();
		driver.findElement(By.xpath("//img[@alt='Import Leads']")).click();

		Thread.sleep(6000);

		WebElement we=driver.findElement(By.xpath("//input[@name='userfile']"));
//		we.sendKeys("C:\\Users\\hp\\Downloads\\Leads.csv");
		we.sendKeys("C:\\Users\\hp\\OneDrive\\Documents\\Hello.xlsx");


	}

	public void uploadFileOnGmail() throws InterruptedException, AWTException {

		driver=hitUrl("http://gmail.com");
		driver.findElement(By.xpath("//input[@name='identifier']")).sendKeys("evspractice12345@gmail.com");
		driver.findElement(By.xpath("//span[text()='Next']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@name='Passwd']")).sendKeys("@evswork12");
		Thread.sleep(4000);
		driver.findElement(By.xpath("(//span[@class='VfPpkd-vQzf8d'])[2]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='T-I T-I-KE L3']")).click();

		/*
		 * Thread.sleep(4000); WebElement
		 * weFile=driver.findElement(By.xpath("//input[@name='Filedata']"));
		 * weFile.sendKeys("C:\\Users\\hp\\Downloads\\Leads.csv"); 
		 * weFile.sendKeys("C:\\Users\\hp\\OneDrive\\Documents\\Hello.xlsx");
		 */


		
		  WebDriverWait weWait=new WebDriverWait(driver, Duration.ofSeconds(60));
		  weWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='Filedata']"))).click();
		  
		  StringSelection ss=new StringSelection("C:\\Users\\hp\\OneDrive\\Documents\\Hello.xlsx");
		  Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		  Robot rObj=new Robot(); 
		  rObj.delay(2000); 
		  rObj.keyPress(KeyEvent.VK_CONTROL);
		  rObj.keyPress(KeyEvent.VK_V); 
		  rObj.keyRelease(KeyEvent.VK_CONTROL);
		  rObj.keyRelease(KeyEvent.VK_V); 
		  rObj.keyPress(KeyEvent.VK_ENTER);
		  rObj.keyRelease(KeyEvent.VK_ENTER);
		 
		
	}

}
