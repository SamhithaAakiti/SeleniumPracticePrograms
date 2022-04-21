package Firstselenium;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;

public class AmazonExcel {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		File file = new File("C:\\Users\\sa248q\\Documents\\amazon.xlsx");
		FileInputStream inputStream = new FileInputStream(file);
		XSSFWorkbook wb=new XSSFWorkbook(inputStream);
		XSSFSheet sheet=wb.getSheet("Sheet1");
		int n=sheet.getLastRowNum()-sheet.getFirstRowNum();
		
		for(int i=1;i<=n;i++)
		{
			//launching browser
			System.setProperty("webdriver.edge.driver", "C:\\Users\\sa248q\\Downloads\\edgedriver_win64\\msedgedriver.exe");
			WebDriver driver=new EdgeDriver();
			
			//link
			driver.navigate().to(sheet.getRow(1).getCell(0).getStringCellValue());
			driver.manage().window().maximize();
			WebElement s=driver.findElement(By.xpath("//*[@id=\'nav-link-accountList\']/span"));
			Actions a=new Actions(driver);
			a.moveToElement(s).perform();
			driver.findElement(By.xpath("//*[@id=\'nav-flyout-ya-signin\']/a/span")).click();
			//signin
			long m=(long) sheet.getRow(i).getCell(1).getNumericCellValue();
			String p=""+m;
			driver.findElement(By.id("ap_email")).sendKeys(p);
			driver.findElement(By.xpath("//*[@id=\'continue\']")).click();
			driver.findElement(By.id("ap_password")).sendKeys(sheet.getRow(i).getCell(2).getStringCellValue());
			driver.findElement(By.xpath("//*[@id=\'signInSubmit\']")).click();
			Thread.sleep(2000);
			String u=driver.getCurrentUrl();
			System.out.println(u);
			XSSFCell c=sheet.getRow(i).createCell(3);
			if(u.equals("https://www.amazon.in/?ref_=nav_signin&"))
			{
				c.setCellValue("Login Successful");
				//signout
				WebElement s1=driver.findElement(By.xpath("//*[@id=\'nav-link-accountList\']/span"));
				Actions a1=new Actions(driver);
				a1.moveToElement(s1).perform();
				driver.findElement(By.cssSelector("#nav-item-signout > span")).click();
			}
			else
				c.setCellValue("Login Failed");
			
			FileOutputStream out=new FileOutputStream("C:\\Users\\sa248q\\Documents\\amazon.xlsx");
			wb.write(out);
			driver.close();
		}

	}
}
