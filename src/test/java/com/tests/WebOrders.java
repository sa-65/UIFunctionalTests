package com.tests;

import com.github.javafaker.Faker;
import com.google.errorprone.annotations.RestrictedApi;
import com.utilities.BrowserUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;
import static com.utilities.BrowserUtils.*;


public class WebOrders extends  BrowserUtils{
    String productName;
    String fullName;
    String street;
    String city;
    String state;
    String zipcode;
    String cardNo;
    String cardType;
    String quantity;
    String expDay;
    String date;

    protected Faker faker;
    protected Select select;
    protected Random random;


    @BeforeClass
    public void setUp(){


        faker=new Faker();
        random=new Random();
        driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
    }

    public void login(){
        driver.findElement(By.xpath("//div[@class='login']//input[1]")).sendKeys("Tester");
        driver.findElement(By.xpath("//div[@class='login']//input[2]")).sendKeys("test");
        driver.findElement(By.xpath("//div[@class='login']//input[3]")).click();
    }



    /**1.Login to Web Ordersapplication using “Tester”and “test”
     * 2.Click on View all productslink
     * 3.Remember all the product names from the table
     * 4.Click on View all orderslink
     *   1.Verify that all the values in the Productscolumn match the names from step 4.*/

    @Test
    public void products(){
       login();
       driver.findElement(By.xpath("//ul[@id='ctl00_menu']/li[2]")).click();
        driver.findElement(By.linkText("View all orders")).click();
        assertEquals(driver.findElement(By.xpath(
                            "//table/tbody/tr/td[3]")).getText(),"ScreenSaver");

        assertEquals(driver.findElement(By.xpath(
                "(//table/tbody/tr[3])/td[3]")).getText(),"FamilyAlbum");

        assertEquals(driver.findElement(By.xpath(
                "(//table/tbody/tr[5])/td[3]")).getText(),"MyMoney");

    }


    /**
     * 1.Login to Web Ordersapplication using “Tester”and “test””
     * 2.Click on Orderlink
     * 3.Select a product (Select different product every time)
     * 4.Enter data to all the required fields(Enter different data every time)
     * 5.Click Proceed
     * 6.Click on link View all orders
     * */
    @Test
    public void createOrder() throws InterruptedException {
        login();
        driver.findElement(By.xpath("//ul[@id='ctl00_menu']/li[3]/a")).click();
        WebElement product= driver.findElement(By.id("ctl00_MainContent_fmwOrder_ddlProduct"));
        //productName=product.getText();

        select=new Select(product);
        int proNO=random.nextInt(2);
        System.out.println(proNO);
        select.selectByIndex(proNO);
        proNO++;
        productName=driver.findElement(By.xpath(
                "//select[@id='ctl00_MainContent_fmwOrder_ddlProduct']/option["+proNO+"]")).getAttribute("value");
        quantity=proNO+"";
        System.out.println(productName);



        driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtQuantity")).sendKeys(quantity);
        fullName=faker.name().fullName();
        driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtName")).sendKeys(fullName);
        street=faker.address().streetAddress();
        driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox2")).sendKeys(street);
        Thread.sleep(2000);
        city=faker.address().city();
        driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox3")).sendKeys(city);
        state=faker.address().state();
        driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox4")).sendKeys(state);
        zipcode=faker.address().zipCode().replace("-","");
        driver.findElement(By.id(
                "ctl00_MainContent_fmwOrder_TextBox5")).sendKeys(zipcode);

        int creditCard=random.nextInt(3)+1;
       //card type
        WebElement card=driver.findElement(By.xpath(
                "//table[@id='ctl00_MainContent_fmwOrder_cardList']/tbody/tr/td["+creditCard+"]/input"));
        cardType=driver.findElement(By.xpath(
                "//table[@id='ctl00_MainContent_fmwOrder_cardList']/tbody/tr/td["+creditCard+"]/label")).getText();
        card.click();

        //card no
        cardNo=faker.finance().creditCard().replace("-","");
        driver.findElement(By.xpath("//input[@name='ctl00$MainContent$fmwOrder$TextBox6']")).sendKeys(cardNo);
        //expiration day
        expDay="12/24";
        driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox1")).sendKeys("12/24");
        Thread.sleep(2000);
        driver.findElement(By.id("ctl00_MainContent_fmwOrder_InsertButton")).click();
        driver.findElement(By.linkText("View all orders")).click();
        date=driver.findElement(By.xpath("//table[@id='ctl00_MainContent_orderGrid']/tbody/tr[2]/td[5]")).getText();

    }

    /** 7.Verify that all the order details are correct*/
    @Test(dependsOnMethods = "createOrder")
    public void verifyOrderDetails(){
      WebElement row=driver.findElement(By.xpath("//input[@id='ctl00_MainContent_orderGrid_ctl02_OrderSelector']/../.."));
        System.out.println(row.getText());
      assertEquals(fullName+" "+productName+" "+quantity+" "+
              date+" "+ street+" "+city+" "+state+" "+zipcode+" "+cardType+" "+cardNo+" "+expDay,row.getText());

    }

    /**
     * 2.Login to Web Orders application using “Tester”and “test”
     * 3.Delete a random entry from the table
     * 4.Verify that table row count decreased by 1
     * 5.Verify Name column does not contain deleted person’s name*/

    @Test
    public void delete() throws InterruptedException {
        login();

        List<WebElement> rows=driver.findElements(By.xpath("//table[@id='ctl00_MainContent_orderGrid']/tbody/tr"));
        System.out.println(rows.size());
        //3
        int delete=random.nextInt(8)+2;
        driver.findElement(By.xpath("//table[@id='ctl00_MainContent_orderGrid']/tbody/tr["+delete+"]/td[1]")).click();
        String deletedName=driver.findElement(By.xpath(
                "//table[@id='ctl00_MainContent_orderGrid']/tbody/tr["+delete+"]/td[2]")).getText();
        System.out.println(deletedName);

        Thread.sleep(2000);
        driver.findElement(By.id("ctl00_MainContent_btnDelete")).click();
        Thread.sleep(2000);
        List<WebElement> rows2=driver.findElements(By.xpath("//table[@id='ctl00_MainContent_orderGrid']/tbody/tr"));
        System.out.println(rows2.size());

        //4
        assertTrue(rows.size()-rows2.size()==1);
        //5
        assertTrue(!(rows2.get(1).getText().contains(deletedName)));


    }

    /**1.Login to Web Ordersapplication using “Tester”and “test”
     * 2.Click edit button for any entry
     * 3.Change the quantity to a different amount
     * 4.Click Calculate
     * 5.Verify that new quantity is displayed
     * 6.Click Update
     * 7.Verify new quantity is displayed
     * 8.Verify that other information in that row did not change*/

    @Test
    public void edit() throws InterruptedException {
        login();
        Thread.sleep(2000);
        //2
        int edit=random.nextInt(8)+2;

        //8
        String rowBefore=driver.findElement(By.xpath("//table[@id='ctl00_MainContent_orderGrid']/tbody/tr["+edit+"]")).getText();
        String[] arr=rowBefore.split(" ");
        driver.findElement(By.xpath("//table[@id='ctl00_MainContent_orderGrid']/tbody/tr["+edit+"]/td[13]")).click();
        //3
        int proNO=random.nextInt(5)+4;
        quantity=proNO+"";
        WebElement changeQuantity=driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtQuantity"));
        Thread.sleep(3000);
        //4
        //driver.findElement(By.xpath("//input[@id='ctl00_MainContent_fmwOrder_txtTotal']/../input[2]")).click();
        changeQuantity.click();
        Thread.sleep(2000);
        changeQuantity.clear();
        Thread.sleep(2000);
        changeQuantity.sendKeys(quantity);
        //4
        driver.findElement(By.xpath("//input[@id='ctl00_MainContent_fmwOrder_txtTotal']/../input[2]")).click();
        //5
        assertTrue(driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtQuantity")).isDisplayed());
        //6
        driver.findElement(By.id("ctl00_MainContent_fmwOrder_UpdateButton")).click();
        //7
        driver.findElement(By.xpath("//table[@id='ctl00_MainContent_orderGrid']/tbody/tr["+edit+"]/td[4]")).isDisplayed();

        //8
        String  rowAfter=driver.findElement(By.xpath("//table[@id='ctl00_MainContent_orderGrid']/tbody/tr["+edit+"]")).getText();
        String[] arr2=rowAfter.split(" ");
        for (int i = 0; i < arr2.length; i++) {
            if (i == 3) {
                continue;
            }
            else {
                Assert.assertTrue(arr[i].equals(arr2[i]));
            }
        }
    }
}
