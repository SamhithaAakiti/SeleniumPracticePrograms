package Firstselenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;

public class basicprogram {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.edge.driver", "C:\\Users\\sa248q\\Downloads\\edgedriver_win64\\msedgedriver.exe");
		WebDriver driver=new EdgeDriver(); //launching edge
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		
		//opening web app
		driver.navigate().to("https://amazon.in");
		driver.manage().window().maximize();
		String t=driver.getTitle();
		if(t.equalsIgnoreCase("Amazon.in"))
			System.out.println("Title matches");
		else
			System.out.println(t);
		
		//signin
		WebElement s=driver.findElement(By.xpath("//*[@id=\'nav-link-accountList\']/span"));
		Actions a=new Actions(driver);
		a.moveToElement(s).perform();
		driver.findElement(By.xpath("//*[@id=\'nav-flyout-ya-signin\']/a/span")).click();
		driver.findElement(By.id("ap_email")).sendKeys("9381455006");
		driver.findElement(By.xpath("//*[@id=\'continue\']")).click();
		driver.findElement(By.id("ap_password")).sendKeys("S@mh1th@");
		driver.findElement(By.xpath("//*[@id=\'signInSubmit\']")).click();
		
		//locating a web element
		/*String tag=" ";
		tag=driver.findElement(By.cssSelector("#nav-hamburger-menu > i")).getText();
		System.out.println(tag);*/
		
		driver.findElement(By.cssSelector("#nav-hamburger-menu > i")).click();
		driver.findElement(By.xpath("//*[@id=\'hmenu-content\']/ul[1]/li[2]/a")).click();
		driver.findElement(By.linkText("Health & Personal Care")).click();
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("softtouch");
		driver.findElement(By.id("nav-search-submit-button")).click();
	}

}
