package com.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class WOPostiveLoginTests {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();


    }

    @AfterMethod
    public void leanUp() throws InterruptedException {
        Thread.sleep(5000);
        //driver.close();
    }

    @Test

    public void positiveLoginTest(){
        driver.get("http://secure.smartbearsoftware.com/samples/testcomplete11/WebOrders/login.aspx");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        String title=driver.getTitle();
        if(title.equals("Web Orders Login")){
            System.out.println("Title verification PASSED");
        }else{
            System.out.println("Title verification FAILED");
        }


        WebElement userName=driver.findElement(By.id("ctl00_MainContent_username"));
        userName.sendKeys("Tester");
        WebElement password=driver.findElement(By.id("ctl00_MainContent_password"));
        password.sendKeys("test");
        WebElement loginBtn=driver.findElement(By.id("ctl00_MainContent_login_button"));
        loginBtn.click();

        if(driver.getTitle().equals("Web Orders")){
            System.out.println("Title verification PASSED");
        }else{
            System.out.println("Title verification FAILED");
        }

        String url=driver.getCurrentUrl();

        if(url.equals("http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/default.aspx")){
            System.out.println("Url verification PASSED");
        }else{
            System.out.println("Url verification FAILED");
        }

    }





}
