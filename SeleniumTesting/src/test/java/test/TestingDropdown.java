package test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
//import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestingDropdown {
	public static WebDriver driver;
	
	public static void main(String[] args) {
		
	TestingDropdown test=new TestingDropdown();
	
	test.login();
	
	test.showItemsDropdown();
	
	test.tableInfo();
	
	test.logout();
	
	
	}
	public void login() {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
		driver.manage().window().maximize();
		WebElement userName=driver.findElement(By.xpath("//*[@id=\"txtUsername\"]"));
		userName.sendKeys("Admin");
		WebElement passWord=driver.findElement(By.xpath("//*[@id=\"txtPassword\"]"));
		passWord.sendKeys("admin123");
		WebElement loginBtn=driver.findElement(By.xpath("//*[@id=\"btnLogin\"]"));
		loginBtn.click();
		
	}
	public void logout() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id=\"welcome\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"welcome-menu\"]/ul/li[2]/a")).click();

	}
	public void showItemsDropdown() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement adminTab=driver.findElement(By.xpath("//*[@id=\"menu_admin_viewAdminModule\"]/b"));
		adminTab.click();
		
		Select userNameDropdown=new Select(driver.findElement(By.xpath("//*[@id=\"searchSystemUser_userType\"]")));
		userNameDropdown.selectByValue("2");
		
		
		List<WebElement> dropdownList=userNameDropdown.getOptions();
		int size=dropdownList.size();
		System.out.println("Number of Items in DropDownList is: "+size);
		int i=1;
			for(WebElement e:dropdownList) {
				String nameOfItems=e.getText();
				System.out.println(i+".Item is: "+nameOfItems);
				i++;
			}
		WebElement searchBtn=driver.findElement(By.xpath("//*[@id=\"searchBtn\"]"));
		searchBtn.click();
	}
	public void tableInfo() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		List<WebElement>rows=driver.findElements(By.xpath("//*[@id=\"resultTable\"]/tbody/tr"));
		int rowsNo=rows.size();
		System.out.println("List of Employees Data of User Role - ESS ");
		System.out.println("Rows: "+rowsNo);
		
		WebElement toGetColumns=driver.findElement(By.xpath("//*[@id=\"resultTable\"]/tbody/tr"));
		List<WebElement>columns=toGetColumns.findElements(By.tagName("td"));
		int colNo=columns.size()-1;
		
			for(int i=1;i<=rowsNo;i++) {
				for(int j=1;j<=colNo;j++) {
					String name=driver.findElement(By.xpath("//*[@id=\"resultTable\"]/tbody/tr["+i+"]/td["+j+"]")).getText()+"\t"; 
					
					System.out.print(name);
				}
				System.out.println();
			}
		
	}
}
