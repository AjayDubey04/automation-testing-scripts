package com.generic.layer;



import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.UnexpectedTagNameException;
import org.openqa.selenium.support.ui.WebDriverWait;




public class WebUtil {


	private static WebDriver driver;



	public static void launchBrowser(String browserName) {

		switch(browserName) {
		case "chrome" : {
			System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
			driver=new ChromeDriver();
			System.out.println("Chrome Browser Launched Successfully");
			break ;
		}
		case "firefox":{
			System.setProperty("webdriver.firefox.driver", "Driver\\firefoxdriver.exe");
			driver=new FirefoxDriver();
			System.out.println("FireFox Browser Launched Successfully");
			break ;
		}
		case "edge" :{
			System.setProperty("webdriver.edge.driver", "Driver\\msedgedriver.exe");
			driver=new EdgeDriver();
			System.out.println("Edge Browser Launched Successfully");
			break ;
		}
		default:{
			throw new IllegalArgumentException("Invalid browser option");
		}
		}
	}	



	// ======================  WebDriver Methods ====================== //


	public static void openUrl(String Url) throws InterruptedException {
		try{
			driver.get(Url);
			System.out.println(Url+" ==>> URL Entered successFully");
		}
		catch(InvalidArgumentException e) {
			System.out.println("InvalidArgumentException occurred: Because URL Syntax Was Invalid ");
			throw e;
		}
		catch(TimeoutException e) {
			Thread.sleep(4000);
			driver.get(Url);
			System.out.println(Url+" ==>> URL Entered successFully");
		}
		catch(WebDriverException e) {
			System.err.println("WebDriverException occurred: " );
			throw e;
		}
		catch(Exception e) {
			System.out.println("An unexpected error occurred: ");
			throw e;
		}
	}



	public static WebElement myFindElement(By locater,String elementValue) throws InterruptedException {

		WebElement we=null;
		try {
			we=driver.findElement(locater);
			System.out.println(elementValue+" Element Found SuccesFully");
		}
		catch(NoSuchElementException e) {
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(60));
			try {
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locater));
				System.out.println(elementValue+" Element Found SuccesFully");
			}
			catch(TimeoutException te) {
				threadWait(5000);
				we=driver.findElement(locater);
				System.out.println(elementValue+" Element Found SuccesFully");
			}
		}
		catch(InvalidSelectorException e) {
			System.out.println(elementValue+" Element Not Found SuccesFully Because Xpath Syntax is Invalid");
			throw e;
		}
		catch(Exception e) {
			System.out.println("An unexpected error occurred: ");
			throw e;
		}
		return we;
	}



	public static List<WebElement> myFindElements(By locater,String elementValue) {
		List<WebElement> we=null;
		try {
			we=driver.findElements(locater);
			System.out.println(elementValue+" Elements Found SuccessFully");
		}
		catch(NoSuchElementException e) {
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(60));
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locater));
			we=driver.findElements(locater);
			System.out.println(elementValue+" Elements Found SuccessFully");
		}
		catch(InvalidSelectorException e) {
			System.out.println(elementValue+" Elements Not Found SuccessFully Because Xpath Syntax is Invalid");
			throw e;
		}
		catch(Exception e) {
			System.out.println("An unexpected error occurred: ");
			throw e;
		}
		return we;
	}



	public static String myGetTitle(String elementName) {
		String title=null;
		try {
			title=driver.getTitle();	
			System.out.println(elementName+" Title Found SuccesFully ==> ");
		}
		catch(NoSuchWindowException e) {
			System.out.println("NoSuchWindowException occurred: "+e.getMessage());  
		}
		catch(Exception e) {
			System.out.println("An unexpected error occurred: ");
			throw e;
		}
		return title;
	}


	public static String myGetCurrentUrl(String elementName) {

		String url=null;
		try {
			url=driver.getCurrentUrl();
			System.out.println(elementName+" CurrentUrl Found SuccessFully Of ");
		}
		catch(Exception e) {
			System.out.println("An unexpected error occurred: "+ e.getMessage());
		}
		return url;	
	}



	public static void myClose() {

		try {
			driver.close();
			System.out.println("Current Browser Window closed SuccessFully.");
		}
		catch(Exception e) {
			System.out.println("An unexpected error occurred: "+ e.getMessage());
		}
	}


	public static void myQuit() {

		try {
			driver.quit();
			System.out.println("Browser Window quit SuccessFully.");
		}
		catch(Exception e) {
			System.out.println("An unexpected error occurred: "+ e.getMessage());
		}
	}


//	public static void switchToMainWindow() {
//		String handleValue;
//		try {
//			handleValue=driver.getWindowHandle();
//			driver.switchTo().window(handleValue);
//			System.out.println("Switched to Main Window  SuccessFully");
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	
	public static String fetchMainHandleValue() {
		String value=driver.getWindowHandle();
		return value;
	}
	
	public static void switchToMainWindow(String handleValue) {
		driver.switchTo().window(handleValue);
	}


	public static void switchToMultipleWindowByTitle(String expectedTitle) {
		Set<String> handleValue=driver.getWindowHandles();
		for (String we : handleValue) {
			WebDriver weD=driver.switchTo().window(we);	
			if(weD.getTitle().equalsIgnoreCase(expectedTitle)==true) {
				System.out.println("Switch to Window By Title SuccessFully And Expected Window Title is Same as Actual Window Title");
				break;
			}
		}
	}

	public static void switchToMultipleWindowByUrl(String expectedUrl) {
		Set<String> handleValue=driver.getWindowHandles();
		for (String we : handleValue) {
			WebDriver weD=driver.switchTo().window(we);	
			if(weD.getCurrentUrl().equalsIgnoreCase(expectedUrl)==true) {
				System.out.println("Switch to Window By Url SuccessFully And Expected Window URL is Same as Actual Window URL");
				break;
			}
		}	
	}


	public static void forwardWebPage() {
		try {
			driver.navigate().forward();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}


	public static void backWebPage() {
		try {
			driver.navigate().back();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}


	public static void refreshWebPage() {
		try {
			driver.navigate().refresh();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}


	public static void openNavigateToUrl(String url) {
		try {
			driver.navigate().to(url);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}



	public static void  acceptAlert() {
		try {
			driver.switchTo().alert().accept();
			System.out.println("Alert Accepted SuccesFully");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}


	public static void  dismissAlert() {
		try {
			driver.switchTo().alert().accept();
			System.out.println("Alert Dismissed SuccesFully");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}



	public static String  getAlertText() {
		String alert_text=null;
		try {
			alert_text=driver.switchTo().alert().getText();
			System.out.println("Alert Text Found SuccesFully");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return alert_text;
	}




	public static void  alertSendkeys(String alertInput) {

		try {
			driver.switchTo().alert().sendKeys(alertInput);
			System.out.println(alertInput+" Entered in Alert Box  SuccesFully");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}




	public static WebDriver  switchToFrame(String nameOrId) {
		WebDriver weD=null;
		try {
			weD=driver.switchTo().frame(nameOrId);
			System.out.println("Switched To Frame SuccessFully By Name Or Id");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return weD;     
	}


	public static WebDriver  switchToFrame(int indexNumber) {
		WebDriver weD=null;
		try {
			weD=driver.switchTo().frame(indexNumber);
			System.out.println("Switched To Frame SuccessFully By Index Number.");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return weD;     
	}



	public static WebDriver  switchToFrame(WebElement we) {
		WebDriver weD=null;
		try {
			weD=driver.switchTo().frame(we);
			System.out.println("Switched To Frame SuccessFully By WebElement.");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return weD;     
	}



	public static WebDriver switchToParentFrame() {
		WebDriver weD=null;
		try {
			weD=driver.switchTo().parentFrame();
			System.out.println("Switched To Parent Frame SuccessFully.");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return weD;     
	}




	public static WebDriver  switchToMainWindowToFrame() {
		WebDriver weD=null;
		try {
			weD=driver.switchTo().defaultContent();
			System.out.println("Switched To Main Window SuccessFully From Frame.");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return weD;
	}


	public static void threadWait(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void otherDriver() {

	}




	// ======================  WebElement Methods ====================== //


	public static void  clearInputValue(By locater,String elementName) throws InterruptedException {
		WebElement we=myFindElement(locater, elementName);
		try {
			we.clear();
			System.out.println("Cleared Input Value SuccessFully From "+elementName);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void mySubmit(By locater,String elementName) throws InterruptedException {
		WebElement we=myFindElement(locater, elementName);
		try {
			we.submit();
			System.out.println("Submited Performed SuccessFully on "+elementName);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static String myGetAttribute(By locater,String elementName,String value) throws InterruptedException {
		WebElement we=myFindElement(locater, elementName);
		String str=null;
		try {
			str=we.getAttribute(value);
			System.out.println("Attribute Value Found SuccessFully In "+elementName);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return str;
	}



	public static boolean displayedElement(By locater,String elementName) throws InterruptedException {
		WebElement we=myFindElement(locater, elementName);
		boolean str=false;
		try {
			str=we.isDisplayed();
			System.out.println(elementName+" Is Visiable SuccessFully ");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return str;
	}


	public static boolean enabledElement(By locater,String elementName) throws InterruptedException {
		WebElement we=myFindElement(locater, elementName);
		boolean str=false;
		try {
			str=we.isEnabled();
			System.out.println(elementName+" Is Enabled SuccessFully ");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return str;
	}


	public static boolean selectedElement(By locater,String elementName) throws InterruptedException {
		WebElement we=myFindElement(locater, elementName);
		boolean str=false;
		try {
			str=we.isSelected();
			System.out.println(elementName+" Is Selected SuccessFully ");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return str;
	}




	public static void mySendkeys(By locater, String inputValue, String elementValue) throws InterruptedException {

		WebElement we=myFindElement(locater, elementValue);

		try {
			we.sendKeys(inputValue);
			System.out.println(inputValue+" Value Entered in "+elementValue+"  SuccessFully");
		}
		catch(InvalidArgumentException e) {
			System.out.println("InvalidArgumentException occurred: Because Argument  Was Invalid "+e.getMessage());
		}
		catch(StaleElementReferenceException e) {
			we=myFindElement(locater, elementValue);
			we.sendKeys(inputValue);
			System.out.println(inputValue+" Value Entered in "+elementValue+"  SuccessFully");
		}
		catch(ElementNotInteractableException e) {
			JavascriptExecutor js=(JavascriptExecutor)driver;
			js.executeScript("arguments[0].value='+"+inputValue+"+'",we);
			System.out.println(inputValue+" Value Entered in "+elementValue+"  SuccessFully");
		} 
		catch(Exception e) {
			System.out.println("An unexpected error occurred: ");
			throw e;
		}
	}



	public static void myClick(By locater,String elementName) throws InterruptedException {

		WebElement we=myFindElement(locater, elementName);

		try {
			we.click();
			System.out.println("Clicked on the "+elementName+" element successfully ");
		}
		catch(StaleElementReferenceException e) {
			we=myFindElement(locater, elementName);
			we.click();
			System.out.println("Clicked on the "+elementName+" element successfully ");
		}
		catch(ElementClickInterceptedException e) {
			JavascriptExecutor js=(JavascriptExecutor)driver;
			js.executeScript("arguments[0].click()", we);
			System.out.println("Clicked on the "+elementName+" element successfully ");
		}
		catch(ElementNotInteractableException e) {
			JavascriptExecutor js=(JavascriptExecutor)driver;
			js.executeScript("arguments[0].click()", we);
			System.out.println("Clicked on the "+elementName+" element successfully ");
		}
		catch(Exception e) {
			System.out.println("An unexpected error occurred: ");
			throw e;
		}
	}




	public static String myGetText(By locater,String elementName) throws InterruptedException {

		WebElement we=myFindElement(locater,elementName);
		String inner_text=null;
		try {
			inner_text=we.getText();
			System.out.println("InnerText of "+elementName+" Found SuccessFully");
		}
		catch(StaleElementReferenceException e) {
			we=myFindElement(locater,elementName);
			inner_text=we.getText();
			System.out.println("InnerText of "+elementName+" Found SuccessFully");
		}
		catch(ElementNotInteractableException e) {
			JavascriptExecutor js=(JavascriptExecutor)driver;
			js.executeScript("return arguments[0].innerText", we);
			System.out.println("InnerText of "+elementName+" Found SuccessFully");
		}
		catch(Exception e) {
			System.out.println("An unexpected error occurred: ");
			throw e;
		}
		return inner_text;
	}



	// ======================  Actions Class Methods ====================== //


	public static void myMouseOver(By locater , String elementName) throws InterruptedException {

		Actions act=new Actions(driver);
		WebElement we=myFindElement(locater, elementName);
		try {
			act.moveToElement(we).build().perform();
			System.out.println("MouseOver Performed SuccessFully On "+elementName);
		} 
		catch (ElementNotInteractableException e) {
			System.out.println("ElementNotInteractableException occurred: " + e.getMessage());
		}
		catch(StaleElementReferenceException e) {
			we=myFindElement(locater, elementName);
			act.moveToElement(we).build().perform();
			System.out.println("MouseOver Performed SuccessFully On "+elementName);
		}
		catch(Exception e) {
			System.out.println("An unexpected error occurred: ");
			throw e;
		}
	}



	public static void myActionsClick(By locater,String elementName) throws InterruptedException {

		Actions act=new Actions(driver);
		WebElement we=myFindElement(locater, elementName);
		try {
			act.click(we).build().perform();;
			System.out.println("Actions Click Performed SuccessFully On "+elementName+" Element");
		}
		catch(StaleElementReferenceException e) {
			we=myFindElement(locater, elementName);
			act.click(we).build().perform();;
			System.out.println("Actions Click Performed SuccessFully On "+elementName+" Element");
		}
		catch(ElementClickInterceptedException e) {
			JavascriptExecutor js=(JavascriptExecutor)driver;
			js.executeScript("arguments[0].click()", we);
			System.out.println("Actions Click Performed SuccessFully On "+elementName+" Element");
		}
		catch(ElementNotInteractableException e) {
			JavascriptExecutor js=(JavascriptExecutor)driver;
			js.executeScript("arguments[0].click()", we);
			System.out.println("Actions Click Performed SuccessFully On "+elementName+" Element");
		} 
		catch(Exception e) {
			System.out.println("Actions Click Not Performed SuccessFully On "+elementName+" Element");
			System.err.println("An unexpected error occurred: "+e.getMessage());
		}
	}



	public static void myActionsDoubleClick(By locater,String elementName) throws InterruptedException {

		Actions act=new Actions(driver);
		WebElement we=myFindElement(locater, elementName);
		try {
			act.doubleClick(we).build().perform();;
			System.out.println("Actions DoubleClick Performed SuccessFully On "+elementName+" Element");
		}
		catch(StaleElementReferenceException e) {
			we=myFindElement(locater, elementName);
			act.doubleClick(we).build().perform();;
			System.out.println("Actions DoubleClick Performed SuccessFully On "+elementName+" Element");
		}
		catch(ElementClickInterceptedException e) {
			JavascriptExecutor js=(JavascriptExecutor)driver;
			js.executeScript("arguments[0].click()", we);
			System.out.println("Actions DoubleClick Performed SuccessFully On "+elementName+" Element");
		}
		catch(ElementNotInteractableException e) {
			JavascriptExecutor js=(JavascriptExecutor)driver;
			js.executeScript("arguments[0].click()", we);
			System.out.println("Actions DoubleClick Performed SuccessFully On "+elementName+" Element");
		} 
		catch(Exception e) {
			System.out.println("Actions DoubleClick Not Performed SuccessFully On "+elementName+" Element");
			System.err.println("An unexpected error occurred: "+e.getMessage());
		}
	}



	public static void myActionsDragAndDrop(By dragLocater,By dropLocater,String elementName) throws InterruptedException {

		Actions act=new Actions(driver);
		WebElement drag=myFindElement(dragLocater, elementName);
		WebElement drop=myFindElement(dropLocater, elementName);
		try {
			act.dragAndDrop(drag,drop).build().perform();;
			System.out.println("Drag and drop action performed successfully.");
		}
		catch(StaleElementReferenceException e) {
			drag=myFindElement(dragLocater, elementName);
			drop=myFindElement(dropLocater, elementName);
			act.dragAndDrop(drag,drop).build().perform();;
			System.out.println("Drag and drop action performed successfully.");
		}
		catch(InvalidArgumentException e){
			System.out.println("InvalidArgumentException occurred: "+e.getMessage());
		}
		catch(Exception e) {
			System.err.println("An unexpected error occurred: "+e.getMessage());
		}
	}


	public static void myActionsSendkeys(By locater, String inputValue, String elementValue) throws InterruptedException {

		Actions act=new Actions(driver);
		WebElement we=myFindElement(locater, elementValue);

		try {
			act.sendKeys(we,inputValue).build().perform();
			System.out.println(inputValue+" Value Entered in "+elementValue+"  SuccessFully");
		}
		catch(InvalidArgumentException e) {
			System.out.println("InvalidArgumentException occurred: Because Argument  Was Invalid");
		}
		catch(StaleElementReferenceException e) {
			we=myFindElement(locater, elementValue);
			we.sendKeys(inputValue);
			System.out.println(inputValue+" Value Entered in "+elementValue+"  SuccessFully");
		}
		catch(ElementNotInteractableException e) {
			JavascriptExecutor js=(JavascriptExecutor)driver;
			js.executeScript("arguments[0].value='+"+inputValue+"+'",we);
			System.out.println(inputValue+" Value Entered in "+elementValue+"  SuccessFully");
		} 
		catch(Exception e) {
			System.out.println("An unexpected error occurred: ");
			throw e;
		}
	}




	public static void myActionsRightClick(By locater,String elementName) throws InterruptedException {

		Actions act=new Actions(driver);
		WebElement we=myFindElement(locater, elementName);

		try {
			act.contextClick(we).build().perform();
			System.out.println("Right Click Performed SuccessFully On "+elementName);
		}
		catch(StaleElementReferenceException e) {
			we=myFindElement(locater, elementName);
			act.contextClick(we).build().perform();
			System.out.println("Right Click Performed SuccessFully On "+elementName);
		}
		catch(Exception e) {
			System.out.println("An unexpected error occurred: "+ e.getMessage());
		}
	}



	public static void myActionsClickAndHold(By locater,String elementName) throws InterruptedException {

		Actions act=new Actions(driver);
		WebElement we=myFindElement(locater, elementName);
		try {
			act.clickAndHold(we).build().perform();
			System.out.println("Actions ClickAndHold Performed SuccessFully On "+elementName);
		}
		catch(StaleElementReferenceException e) {
			we=myFindElement(locater, elementName);
			act.clickAndHold(we).build().perform();
			System.out.println("Actions Click And Hold Performed SuccessFully On "+elementName);
		}
		catch(Exception e) {
			System.out.println("An unexpected error occurred: "+ e.getMessage());
		}
	}





	// ======================== Select class Methods ======================== //

	public static boolean myIsMultiple(By locater,String elementName) throws InterruptedException {

		WebElement we=myFindElement(locater,elementName);
		Select set=new Select(we);
		boolean type;
		try {
			type=set.isMultiple();
			System.out.println("isMultiple Performed SuccessFully on "+elementName);
		}
		catch(StaleElementReferenceException e) {
			we=myFindElement(locater,elementName);
			Select sel=new Select(we);
			type=sel.isMultiple();
			System.out.println("isMultiple Performed SuccessFully on "+elementName);
		}
		catch(Exception e) {
			throw e;
		}
		return type;
	}




	public static void mySelectByInnerText(By locater,String elementname,String innerTextOfOption) throws InterruptedException {

		WebElement we=myFindElement(locater,elementname);
		Select set=new Select(we);
		try {
			set.selectByVisibleText(innerTextOfOption);
			System.out.println("Option Selected by innerText SuccesseFully From "+elementname);
		}
		catch(StaleElementReferenceException e) {
			we=myFindElement(locater,elementname);
			Select sel=new Select(we);
			sel.selectByVisibleText(innerTextOfOption);
			System.out.println("Option Selected by innerText SuccesseFully From "+elementname);
		}
		catch(Exception e) {
			throw e;
		}

	}


	public static void mySelectByIndex(By locater,String elementname,int indexOfOption) throws InterruptedException {

		WebElement we=myFindElement(locater,elementname);
		Select set=new Select(we);
		try {
			set.selectByIndex(indexOfOption);
			System.out.println("Option Selected by index SuccesseFully From "+elementname);
		}
		catch(StaleElementReferenceException e) {
			we=myFindElement(locater,elementname);
			Select sel=new Select(we);
			sel.selectByIndex(indexOfOption);
			System.out.println("Option Selected by index SuccesseFully From "+elementname);
		}
		catch(Exception e) {
			throw e;
		}

	}


	public static void mySelectByValue(By locater,String elementname,String valueOfAttribute) throws InterruptedException {

		WebElement we=myFindElement(locater,elementname);
		Select set=new Select(we);
		try {
			set.selectByValue(valueOfAttribute);
			System.out.println("Option Selected by attribute value SuccesseFully From "+elementname);
		}
		catch(StaleElementReferenceException e) {
			we=myFindElement(locater,elementname);
			Select sel=new Select(we);
			sel.selectByValue(valueOfAttribute);
			System.out.println("Option Selected by attribute value SuccesseFully From "+elementname);
		}
		catch(Exception e) {
			throw e;
		}
	}




	public static int myGetOptions(By locater,String elementname) throws InterruptedException {

		WebElement we=myFindElement(locater,elementname);
		Select set=new Select(we);
		List<WebElement> listWe=null;
		int optionsCount=0;
		try {
			listWe=set.getOptions();
			optionsCount=listWe.size();
			System.out.println("Option Count SuccessFully From "+elementname);
		}
		catch(StaleElementReferenceException e) {
			we=myFindElement(locater,elementname);
			Select st=new Select(we);
			listWe=st.getOptions();
			System.out.println("Option Count SuccessFully From "+elementname);
		} 
		catch(Exception e) {
			throw e;
		}
		return optionsCount;

	}

	public static String getSelectedOptionText(By locater,String elementname) throws InterruptedException {

		WebElement we=myFindElement(locater,elementname);
		Select set=new Select(we);
		WebElement defValue=null;
		String text=null;
		try {
			defValue=set.getFirstSelectedOption();
			text=defValue.getText();
			System.out.println(text+" Selected Default Option: From "+elementname);
		}
		catch(StaleElementReferenceException e) {
			we=myFindElement(locater,elementname);
			Select st=new Select(we);
			defValue=st.getFirstSelectedOption();
			text=defValue.getText();
			System.out.println(text+" Selected Default Option: From "+elementname);
		}
		catch(Exception e) {
			throw e;
		}
		return text;
	}

	public static String getInnerTextOfAllOptions(By locater,String elementname) throws InterruptedException {

		WebElement we=myFindElement(locater,elementname);
		Select set=null;
		try {
			set=new Select(we);
		}catch(UnexpectedTagNameException e) {
			throw e;
		}
		WebElement defValue=null;
		String text=null;
		try {
			defValue=set.getWrappedElement();
			text=defValue.getText();
			System.out.println("Found All Options InnerText Successfully: From "+elementname);
		}
		catch(StaleElementReferenceException e) {
			we=myFindElement(locater,elementname);
			Select st=new Select(we);
			defValue=st.getWrappedElement();
			text=defValue.getText();
			System.out.println("Found All Options InnerText Successfully: From "+elementname);
		}
		catch(Exception e) {
			throw e;
		}
		return text;
	}

	// ======================== Javascript Methods ======================== //

	public static void jsClick(By locater,String elementName) throws InterruptedException {

		WebElement we=myFindElement(locater, elementName);

		JavascriptExecutor js=(JavascriptExecutor)driver;
		try {
			js.executeScript("arguments[0].click()", we);
			System.out.println("Click Performed SuccessFully On "+elementName+" Element By Javascript");
		}
		catch(Exception e) {
			System.out.println("Click Not Performed SuccessFully On "+elementName+" Element By Javascript");
			System.err.println("An unexpected error occurred: ");
			e.printStackTrace();
		}
	}

	public static void jsSendkeys(By locater,String inputValue,String elementName) throws InterruptedException {

		WebElement we=myFindElement(locater, elementName);

		JavascriptExecutor js=(JavascriptExecutor)driver;
		try {
			js.executeScript("arguments[0].value='"+inputValue+"'", we);
			System.out.println(inputValue+" Value Entered  SuccessFully in "+elementName+" By Javascript");
		}
		catch(Exception e) {
			System.out.println(inputValue+" Value  Not Entered  SuccessFully in "+elementName+" By Javascript");
			System.err.println("An unexpected error occurred: "+e.getMessage());
		}
	}

	public static void jsScrolBy(int xOffset,int yOffset) {

		JavascriptExecutor js=(JavascriptExecutor)driver;
		try {
			js.executeScript("window.scrollBy("+xOffset+","+yOffset+")");
			System.out.println("Javascript Scrolled Performed SuccessFully By Using horizontal "+(xOffset)+" and vertical "+(yOffset)+" values.");
		}
		catch(Exception e) { 
			e.printStackTrace();
		}
	}


	public static void jsScrolTo() {

		JavascriptExecutor js=(JavascriptExecutor)driver;
		try {
			js.executeScript("window.scrollTo(0, 0);");
			System.out.println("Javascript Scrolled Performed SuccessFully To Top Page ");
		}
		catch(Exception e) { 
			e.printStackTrace();
		}
	}


	public static void jsScrollIntoView(By locater,String elementName) throws InterruptedException {

		WebElement we=myFindElement(locater, elementName);
		JavascriptExecutor js=(JavascriptExecutor)driver;
		try {
			js.executeScript("arguments[0].scrollIntoView(true);", we);
			System.out.println("Javascript Scrolled Performed SuccessFully Until This "+elementName+" Element Became Visiable ");
		}
		catch(Exception e) { 
			e.printStackTrace();
		}
	}




	// ======================== Others Methods ======================== //

	public static void verifyTextData(String expectedData , String actualData) {

		if(expectedData.equalsIgnoreCase(actualData)==true) {
			System.out.println("Expected Data - ( "+expectedData+" ) - Same As Actual Data - ( "+actualData+" ) - So It is Passed");
		}else{
			System.out.println("Expected Data - ( "+expectedData+" ) - Is Not Same As Actual Data - ( "+actualData+" ) - So It is Passed");
		}		
	}

	public static void verifyNumberData(int expectedData , int actualData) {

		if(expectedData==actualData) {
			System.out.println("Expected - ( "+expectedData+" ) - Same As  Actual - ( "+actualData+" ) - So It is Passed");
		}
		else{
			System.out.println("Expected - ( "+expectedData+" ) - Is Not Same As Actual - ( "+actualData+" ) - So It is Passed");
		}		
	}




	public static void checkAllCheckBoxes(By locater,String elementName) {

		List<WebElement> weList=myFindElements(locater, elementName);
		try {
			for (WebElement list : weList) {
				if(list.isSelected()==false) {
					list.click();
				}
			}
			System.out.println("Check All "+elementName+" CheckBoxes SuccessFully. ");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}


	public static void unCheckAllCheckBoxes(By locater,String elementName) {

		List<WebElement> weList=myFindElements(locater, elementName);
		try {
			for (WebElement list : weList) {
				if(list.isSelected()==true) {
					list.click();
				}
			}
			System.out.println("Uncheck All "+elementName+" CheckBoxes SuccessFully. ");
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}


	public static int getTableRowCount(By locater,String elementName) {

		List<WebElement> weList=myFindElements(locater, elementName);
		int count=0;
		try {
			count=weList.size();
			System.out.println(elementName+" Table Row Counted SuccessFully.");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return count;

	}


	public static ArrayList<String> getTableColumnNameInList(By locater,String elementName) {

		List<WebElement> list=myFindElements(locater, elementName);
		ArrayList<String> arr=new ArrayList<String>(); 
		try {
			for (WebElement we : list) {
				arr.add(we.getText());
			}
			System.out.println(elementName+" Table Column Names Text Found SuccessFully.");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return arr;	
	}


	public static int getColumnNumberByColumnName(By locater,String columnName,String elementName) {

		List<WebElement> list=myFindElements(locater, elementName);
		int count=0;
		try {
			for (int i=0; i<list.size(); i++) {
				String text=list.get(i).getText();
				if(text.equalsIgnoreCase(columnName)==true) {
					count=i+1;
					break;
				}
			}
			System.out.println(elementName+" Table Column Number Found SuccessFully.");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return count;
	}




	public static void SelectOptionsByRemovingSpace(By locater,String expectedOption, String elementName) throws InterruptedException {

		WebElement we=myFindElement(locater,elementName);
		Select sel=new Select(we);
		List<WebElement> listOptions=sel.getOptions();
		for (WebElement webElement : listOptions) {
			String text=webElement.getText();
			if(text.trim().equalsIgnoreCase(expectedOption)==true) {
				sel.selectByVisibleText(expectedOption);
			}
		}
	}


	public static void  selectIfOptionVisiable(By locater,String expectedOption, String elementName) throws InterruptedException {

		WebElement we=myFindElement(locater,elementName);
		Select sel=new Select(we);
		List<WebElement> listOptions=sel.getOptions();
		for (WebElement webElement : listOptions) {		
			if(webElement.isDisplayed()==true) {
				sel.selectByVisibleText(expectedOption);
			}
		}	
	}


	public static ArrayList<String> getRowDataByRowNo(String xpath,int rowNo) {

		List<WebElement> list=driver.findElements(By.xpath(xpath+"//tr"));
		ArrayList<String> rowData=new ArrayList<String>();
		int count=0;
		for (int i = 0; i < list.size(); i++) {
			count=i+2;
			if(rowNo==count) {              
				List<WebElement> rowList=driver.findElements(By.xpath(xpath+"//tr[1]/following-sibling::tr["+rowNo+"]/td/following-sibling::td"));
				for (WebElement rowDataText : rowList) {
					rowData.add(rowDataText.getText());
				}
			}
		}
		return rowData;
	}


	public static ArrayList<String> getColumnDataByColumnNo(String tableXpath, int columnNo ) {

		List<WebElement> list=driver.findElements(By.xpath(tableXpath+"//a[@class='listFormHeaderLinks']"));
		ArrayList<String> rowData=new ArrayList<String>();
		int count=0;
		for (int i = 0; i <list.size(); i++) {
			count=i+1;
			if(columnNo==count) {              
				List<WebElement> rowList=driver.findElements(By.xpath(tableXpath+"//tr/following-sibling::tr//td["+(columnNo+1)+"]"));
				for (WebElement rowText : rowList) {
					rowData.add(rowText.getText());
				}
			}
		}
		return rowData;
	}


	public static ArrayList<String> getColumnDataByColumnName(String tableRowXpath,String columnDataxpath, String columnName) {

		List<WebElement> list=driver.findElements(By.xpath(tableRowXpath));
		ArrayList<String> rowData=new ArrayList<String>();
		for (WebElement text : list) {
			String columnData=text.getText();
			if(columnData.equalsIgnoreCase(columnName)==true) {              
				List<WebElement> rowList=driver.findElements(By.xpath(columnDataxpath+"/preceding-sibling::a/parent::td"));
				for (WebElement rowDataText : rowList) {
					rowData.add(rowDataText.getText());
				}
			}
		}
		return rowData;
	}


	public static int getRowNumberByRowId(String tableRowXpath,String rowId) {

		List<WebElement> list=driver.findElements(By.xpath(tableRowXpath+"/parent::td"));
		int count=0;
		for (int i = 0; i <list.size(); i++)  {
			String idText= list.get(i).getText();
			String rowText=idText.trim();
			if(rowText.equalsIgnoreCase(rowId)==true) {
				count=i+1;
				break;
			}
		}
		return count;
	}
	

	public static void getRow() {


		
		
		
		
		
	}
}

