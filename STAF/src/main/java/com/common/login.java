package com.common;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class login {

	public static void main(String[] args) throws InterruptedException {
		// set property to browser 
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		WebDriver obj= new ChromeDriver();
		// get the US AEM URL 
		obj.get("http://wchvqlcmsauth02:4502/libs/granite/core/content/login.html?resource=%2Fprojects.html&$$login$$=%24%24login%24%24&j_reason=unknown&j_reason_code=unknown");
		Thread.sleep(5000);
		// login with credentials 
		obj.findElement(By.xpath("//*[@id='username']")).sendKeys("C003207");
		obj.findElement(By.xpath("//*[@id='password']")).sendKeys("july-2017");
		
		obj.findElement(By.xpath("//*[@id='login']/button")).click();
		// maximize the browser.
		obj.manage().window().maximize();
		// click on adoboExperience manager icon
		obj.findElement(By.xpath("//*[@class='coral-Icon coral-Icon--sizeM coral-Icon--adobeExperienceManagerColor'][@icon='adobeExperienceManagerColor'][@aria-label='adobe experience manager color']")).click();
		Thread.sleep(3000);
		//click on sites 
		obj.findElement(By.xpath("//*[@id='globalnav-home-content']/coral-masonry-item[2]/div")).click();
		Thread.sleep(3000);
		//change the view of pages 
		obj.findElement(By.xpath("//*[@id='granite-shell-actionbar']/div[3]/div[3]/coral-cyclebutton/button")).click();
		Thread.sleep(2000);
		obj.findElement(By.xpath(".//*[@id='coral-id-3']/coral-selectlist-item[2]")).click();
		Thread.sleep(2000);
		
		//click on home page 
		obj.findElement(By.xpath("//*[@id='granite-shell-content']/div[2]/div/div[2]/coral-masonry/coral-masonry-item[4]/a/coral-card/coral-card-asset/img")).click();
		Thread.sleep(2000);
		//click on shopping online page 
		obj.findElement(By.xpath(".//*[@id='granite-shell-content']/div[2]/div/div[2]/coral-masonry/coral-masonry-item[1]/a/coral-card/coral-card-asset/img")).click();
		Thread.sleep(2000);
		// click on home page 
		obj.findElement(By.xpath("//*[@id='granite-shell-content']/div[2]/div/div[2]/coral-masonry/coral-masonry-item[6]/a/coral-card/coral-card-asset/img")).click();
		Thread.sleep(2000);
	// click on AEM REgression 
	obj.findElement(By.xpath("//*[@id='granite-shell-content']/div[2]/div/div[2]/coral-masonry/coral-masonry-item[1]/a/coral-card/coral-card-info")).click();
	Thread.sleep(2000);
	//click on create button in AEM regression page 
	
	obj.findElement(By.xpath("//*[@id='granite-shell-actionbar']/div[3]/div[2]/div/button")).click();
	Thread.sleep(2000);
// click on page to create page in drop down 
	obj.findElement(By.xpath("(.//*[text()='Page'])[1]")).click();		
	Thread.sleep(2000);
	// select the home page template 
	obj.findElement(By.xpath("html/body/form/div/coral-panelstack/coral-panel[1]/coral-panel-content/div/div/coral-masonry/coral-masonry-item[6]/coral-card/coral-card-asset")).click();
	Thread.sleep(2000);
//click on next button to create page 
	
	obj.findElement(By.xpath("html/body/form/div/div/coral-panelstack/coral-panel[1]/coral-panel-content/div/button")).click();
	Thread.sleep(2000);
	//give the page name in input box 
	obj.findElement(By.xpath(".//*[@id='coral-1']/div/section[1]/div[1]/div/input")).sendKeys("sampleautomation");
	Thread.sleep(2000);
	//click on create button to create page 
	
	obj.findElement(By.xpath("html/body/form/div/div/coral-panelstack/coral-panel[2]/coral-panel-content/div/button[2]")).click();
	Thread.sleep(2000);
	//click on open button 
	
	obj.findElement(By.xpath("html/body/coral-dialog/div[2]/coral-dialog-footer/button[2]")).click();
	Thread.sleep(2000);
	//click on done button 
	//obj.findElement(By.xpath("html/body/coral-dialog/div[2]/coral-dialog-footer/button[1]")).click();
	
	// click on menu button left side 
	obj.findElement(By.xpath("//*[@id='Content']/div[1]/nav/div[1]/button[1]")).click();
	Thread.sleep(2000);
	// click on assets in created page 
	
	//obj.findElement(By.xpath("//*[@id='SidePanel']/div[2]/div[1]/nav/a[1]/i")).click();
	// click on component button in page 
	obj.findElement(By.xpath("//*[@id='SidePanel']/div[2]/div[1]/nav/a[2]/i")).click();
	Thread.sleep(2000);
	
	
	
	// search for lifestyle in search box of component 
	obj.findElement(By.xpath("//*[@id='assetsearch']")).sendKeys("life");
	// select the 
	
	
		
		

	}

}
