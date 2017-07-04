package javatest;
/*
 * Things to do:
 * Java reflection for Driver instancing
 * Drivers should run regardless of the O.S.
 * 
 */

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterTest;


public class javatest {
	//set variable baseurl for ease of access
	public String baseUrl = "http://automationpractice.com/index.php";
	//Sets "driver" as a variable for WebDriver for ease of use
	public WebDriver driver;
	//dynamic Element for reusability
	public WebElement myDynamicElement;
	//dynamic Select for reusability
	public Select dropdown;

	//Preconditions to run before the Test
	@BeforeTest

 	//Parameters is used by the testing.xml to run parallel tests, identifies which browser is working on, so it can create the Driver
	//Receives "browser" variable from the .xml file
	
/*	
 * @Parameters("browser")
	public void setup(String browser) throws Exception{
		if(browser.equalsIgnoreCase("firefox")){
			//System.setProperty("webdriver.firefox.marionette", ".\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else if(browser.equalsIgnoreCase("chrome")){
			//System.setProperty("webdriver.chrome.driver",".\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("Edge")){
			//set path to Edge.exe
			//System.setProperty("webdriver.edge.driver",".\\MicrosoftWebDriver.exe");
			//create Edge instance
			driver = new EdgeDriver();
		}
else{
	//If no browser passed throw exception
	throw new Exception("Browser is not correct");
	//driver = new ChromeDriver();
}
driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
*/	

	//Method that sets the Driver for non-parallel runs
	public void beforeTest() {
		//Creates the Driver
		driver = new ChromeDriver();
		//driver = new FirefoxDriver();
		
		//Redirects to the baseUrl
		//driver.get(baseUrl);
		
		//maximizes the browser window
		//driver.manage().window().maximize();
	}
	
	//Reusable explicitly wait, using Element ID
	public void explicitIdWait (String elementToWait){
		myDynamicElement = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id(elementToWait)));
	}
	//Reusable explicitly wait, using Partial Text
	public void explicitPartialTextWait (String elementToWait){
		myDynamicElement = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(elementToWait)));
	}
	public void explicitXpathWait (String elementToWait){
		myDynamicElement = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementToWait)));
	}
	public void explicitCssWait (String elementToWait){
		myDynamicElement = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(elementToWait)));
	}
	
	//Sets the method as a test for the Driver to run. 
	//Priority is set, so the Driver runs the test in the specified priority-based order
	
	
	//@Test(priority=1)
	//This test will Register a new user to the application
	public void signIn() {
		driver.get(baseUrl);
		String os = System.getProperty("os.name").toLowerCase();
		System.out.printf(os);
		
		//Mac does not allow for an automated window to gain focus. This instruction helps get the window focus if the OS is Mac
		if(os.equals("mac os x")){
			//Sets the current window handle
			String currentWindowHandle = this.driver.getWindowHandle();
			//run your javascript and alert code
			((JavascriptExecutor)this.driver).executeScript("alert('Test')"); 
			this.driver.switchTo().alert().accept();
			//Switch back to to the window using the handle saved earlier
			this.driver.switchTo().window(currentWindowHandle);
		}
		driver.findElement(By.className("login")).click();
		driver.findElement(By.id("email_create")).sendKeys("jose.q@4.com");
		driver.findElement(By.name("SubmitCreate")).click();
		//Waits for presence of "id_gender1" element
		explicitIdWait("id_gender1");
		driver.findElement(By.id("id_gender1")).click();
		driver.findElement(By.id("customer_firstname")).sendKeys("Jose");
		driver.findElement(By.id("customer_lastname")).sendKeys("Quesada");
		myDynamicElement = driver.findElement(By.id("email"));
		//Waits for the email to be present as it is auto-populated by the application.
		if (myDynamicElement.getText() == ""){
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
		//Prints the email value. For debug purposes
		System.out.printf(myDynamicElement.getAttribute("value"));
		//If the auto-populated email is different from the user entered email. Replaces the wrong email with the user-entered one
		//Unnecessary, used for testing purposes
		if(myDynamicElement.getAttribute("value").equals("jose.q@4.com") != true){
			myDynamicElement.clear();
			myDynamicElement.sendKeys("jose.q@4.com");
		}
		
		driver.findElement(By.id("passwd")).sendKeys("123456");
		dropdown = new Select(driver.findElement(By.id("days")));
		//Dropdown select using Value
		dropdown.selectByValue("24");
		dropdown = new Select(driver.findElement(By.id("months")));
		dropdown.selectByValue("11");
		dropdown = new Select(driver.findElement(By.id("years")));
		dropdown.selectByValue("1988");
		driver.findElement(By.id("newsletter")).click();
		driver.findElement(By.id("optin")).click();
		//First and Last name are autopopulated
		//driver.findElement(By.id("firstname")).sendKeys("Jose");
		//driver.findElement(By.id("lastname")).sendKeys("Quesada");
		driver.findElement(By.id("company")).sendKeys("Stateside");
		driver.findElement(By.id("address1")).sendKeys("Barrio Tournon");
		driver.findElement(By.id("address2")).sendKeys("150mts norte El Pueblo");
		driver.findElement(By.id("city")).sendKeys("Goicoechea");
		dropdown = new Select(driver.findElement(By.id("id_state")));
		//Dropdown select using Visible Text
		dropdown.selectByVisibleText("Florida");
		driver.findElement(By.id("postcode")).sendKeys("12345");
		dropdown = new Select(driver.findElement(By.id("id_country")));
		//Dropdown select using Index
		dropdown.selectByIndex(1);
		driver.findElement(By.id("other")).sendKeys("This is a really long form \nAnd this is a breakpoint");
		driver.findElement(By.id("phone")).sendKeys("321654987");
		driver.findElement(By.id("phone_mobile")).sendKeys("123456789");
		driver.findElement(By.id("alias")).clear();
		driver.findElement(By.id("alias")).sendKeys("ST address");
		//driver.findElement(By.id("submitAccount")).click();
		//driver.findElement(By.className("logout")).click();
	}
	
	//@Test(priority=2)
	//This test will log in to the application using an existing account
	public void login(){
		driver.get(baseUrl);
		//Waits for the page to load
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.className("login")).click();
		driver.findElement(By.id("email")).sendKeys("joseeqr@gmail.com");
		driver.findElement(By.id("passwd")).sendKeys("123456");
		driver.findElement(By.id("SubmitLogin")).click();
		driver.findElement(By.className("logout")).click();
	}
	
	@Test(priority=3)
	//This test will test the shopping cart functionality
	public void addCart(){
		driver.get(baseUrl);
		//Gets the list of Featured products in order to access them
		myDynamicElement = driver.findElement(By.id("homefeatured"));
		List<WebElement> links = myDynamicElement.findElements(By.tagName("li"));
		//By clicking on a specific product, app will redirect to the product's page
		//links.get(1).click();
		
		//Actions sets focus on the featured products, so that the "Add to Cart" CTAs show.
		Actions actions = new Actions(driver);
		//Sets focus to the second item in the list
		//WebElement mainMenu = links.get(1);
		actions.moveToElement(links.get(1));
		actions.moveToElement(driver.findElement(By.linkText("Add to cart"))).click().perform();
		//Adds item to cart
		//driver.findElement(By.linkText("Add to cart")).click();

		//explicitXpathWait("//div[@id='layer_cart']");
		/*
		mainMenu = driver.findElement(By.id("layer_cart"));
		actions.moveToElement(mainMenu).perform();
		actions.moveToElement(driver.findElement(By.cssSelector("span.cross"))).click().perform();
		*/
		explicitCssWait("span.cross");
		
		driver.findElement(By.cssSelector("span.cross")).click();
		driver.findElement(By.cssSelector("a[title=\"View my shopping cart\"]")).click();
		
		//explicitPartialTextWait("Proceed to checkout");
		//driver.findElement(By.partialLinkText("Proceed to checkout")).click();
		//driver.findElement(By.partialLinkText("Proceed to checkout")).click();
	}
	
	@AfterTest
	public void close() {
		//Closes the browser
		//driver.quit();
	}
	
	public void stupidShit(){
		
	}

}
