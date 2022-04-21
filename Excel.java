package Firstselenium;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class Excel {

	public static void main(String[] args) throws IOException, InterruptedException{
		// TODO Auto-generated method stub
		File file = new File("C:\\Users\\sa248q\\Documents\\Details.xlsx");
		FileInputStream inputStream = new FileInputStream(file);
		XSSFWorkbook wb=new XSSFWorkbook(inputStream);
		XSSFSheet sheet=wb.getSheet("Sheet1");
		System.setProperty("webdriver.edge.driver", "C:\\Users\\sa248q\\Downloads\\edgedriver_win64\\msedgedriver.exe");
		WebDriver driver=new EdgeDriver();
		XSSFRow row2=sheet.getRow(1);
		//link
		XSSFCell cell=row2.getCell(0);
		String link= cell.getStringCellValue();
		System.out.println(link);
		driver.get(link);
		driver.manage().window().maximize();
		
		//firstname
		XSSFCell f=row2.getCell(1);
		String fname= f.getStringCellValue();
		
		driver.findElement(By.id("firstName")).sendKeys(fname);
		//lastname
		XSSFCell l=row2.getCell(2);
		String lname= l.getStringCellValue();
		driver.findElement(By.id("lastName")).sendKeys(lname);
		
		//email
		XSSFCell e=row2.getCell(3);
		String email= e.getStringCellValue();
		driver.findElement(By.id("userEmail")).sendKeys(email);
		Thread.sleep(2000);
		
		//scrolling page
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		Thread.sleep(2000);
		
		//gender
		XSSFCell g=row2.getCell(4);
		String gender= g.getStringCellValue();
		System.out.println(gender);
		if (gender.equalsIgnoreCase("female"))
			driver.findElement(By.cssSelector("#genterWrapper > div.col-md-9.col-sm-12 > div:nth-child(2) > label")).click();
		else if (gender.equalsIgnoreCase("male"))
			driver.findElement(By.cssSelector("#genterWrapper > div.col-md-9.col-sm-12 > div:nth-child(1) > label")).click();
		else
			driver.findElement(By.cssSelector("#genterWrapper > div.col-md-9.col-sm-12 > div:nth-child(3) > label")).click();
		
		//mobilenumber
		XSSFCell n=row2.getCell(5);
		long no= (long) n.getNumericCellValue();
		System.out.println(no);
		String number=""+no;
		driver.findElement(By.id("userNumber")).sendKeys(number);
		
		//date of birth
		XSSFCell d=row2.getCell(6);
		String dob= d.getStringCellValue();
		//String date=""+dob;
		WebElement data=driver.findElement(By.id("dateOfBirthInput"));
		data.clear();
		data.getAttribute("value");
		data.sendKeys(dob);
		
		
	}

}
