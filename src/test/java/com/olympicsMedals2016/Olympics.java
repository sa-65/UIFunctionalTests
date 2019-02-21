package com.olympicsMedals2016;

import com.utilities.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;

public class Olympics extends BrowserUtils {
    List<WebElement> medalColumn;
    List<WebElement> countries;
    int greatest;
    int index;


    @BeforeClass
    public void set(){

        driver.get("https://en.wikipedia.org/wiki/2016_Summer_Olympics#Medal_table.");
    }

    /**2.Verify that by default the Medal table is sorted by rank.
     */
    @Test
    public void sortRank(){

        List<WebElement> tableRank=driver.findElements(By.xpath(
                "//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']//tbody/tr[position()<11]/td[1]"));

        for(int i=0;i<tableRank.size()-1;i++){
           int  current = Integer.parseInt(tableRank.get(i).getText());
            int  next =Integer.parseInt(tableRank.get(i+1).getText()) ;

            System.out.println("comparing: "+current+" with "+next);
            assertTrue(current<next);
        }

    }


    @Test
    public void sortCountries() throws InterruptedException{
      Thread.sleep(2000);
        //click on NOC
        driver.findElement(By.xpath(
                "//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']//thead/tr/th[2]")).click();


        List<WebElement> countries=driver.findElements(By.cssSelector(
                "table.wikitable.sortable.plainrowheaders.jquery-tablesorter>tbody>tr>th"));
        for (WebElement country:countries){
            System.out.println(country.getText());
        }

        //4
        for(int i=0;i<countries.size()-1;i++){
          String  current = countries.get(i).getText();
           String next =countries.get(i+1).getText() ;

            System.out.println("Comparing: "+current+" with "+next);
            Assert.assertTrue(current.compareTo(next)<=0);
        }

    }

    @Test
    public void sortRank2()throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.xpath(
                "//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']//thead/tr/th[2]")).click();
        List<WebElement> tableRank=driver.findElements(By.xpath(
                "//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']//tbody/tr[not(position()=8)]/td[1]"));

        for(int i=0;i<tableRank.size()-1;i++){
                int  current = Integer.parseInt(tableRank.get(i).getText());
                int  next =Integer.parseInt(tableRank.get(i+1).getText()) ;
                System.out.println("Comparing: "+current+" with "+next);
                Assert.assertTrue(!(current-next==1));
            }
        }


    public String  medals(int medalType) {


        // medal
        medalColumn = driver.findElements(By.xpath(
                "//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr[position()<11]/td["+medalType+"]"));

        greatest = Integer.parseInt(medalColumn.get(0).getText());

        for (int i = 0; i < medalColumn.size(); i++) {
            if (Integer.parseInt(medalColumn.get(i).getText()) > greatest) {
                greatest = Integer.parseInt(medalColumn.get(i).getText());
                index++;
            }
        }

        //countries
       countries = driver.findElements(By.xpath(
                " //table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr[position()<11]/th"));
        String country=countries.get(index).getText().trim();

        return country+": "+greatest;

    }

    /**1. Write a method that returns the name of the country with the greatest
     number of gold medals.*/
    @Test
    public void theMost1() {
        System.out.println("===========GOLD MEDALS=============");
        System.out.println(medals(2));

    }

    /**1. Write a method that returns the name of the country with the greatest
     number of silver medals.*/
    @Test
    public void theMost2(){
        System.out.println("===========SILVER MEDALS=============");
        System.out.println(medals(3));

    }

    /**3. Write a method that returns the name of the country with the greatest
     number of bronze medals*/
    @Test
    public void theMost3(){
        System.out.println("=============BRONZE MEDALS=================");
        System.out.println(medals(4));
    }

    /**4. Write a method that returns the name of the country with the greatest
     number of medals.*/
    @Test
    public void theMost4(){
        System.out.println("==========Greatest=========");
        System.out.println(medals(5));
    }


    public String  silvers(){
         medalColumn = driver.findElements(By.xpath(
                "//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr[position()<11]/td[3]"));
        countries = driver.findElements(By.xpath(
                " //table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr[position()<11]/th"));
    String str="";
      for(int i=0;i<countries.size();i++){
          str+="\n"+countries.get(i).getText()+": "+medalColumn.get(i).getText();
      }
      return str;

    }

    /**1. Write a method that returns a list of countries by their silver medal count.
     You decide the data type of the return.*/
    @Test
    public void countryByMedal(){
        System.out.println(silvers());

    }

    /**1. Write a method that takes country name and returns the row and column
     number. You decide the data type of the return.*/


    public String getIndex(String country){

        List<WebElement> list=driver.findElements(By.xpath("" +
                "//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']//tbody/tr[position()<11]"));

        String temp="";
        for(int i=0;i<list.size();i++){
            if(list.get(i).getText().contains(country)){
             temp=list.get(i).getText();
            }
        }

        String [] arr=temp.split(" ");
        //column
        List<WebElement> column=driver.findElements(By.xpath("" +
                "//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']//thead/tr/th"));
        int columnIndex=0;
        for(int i=0;i<column.size();i++){
            if(column.get(i).getText().equals("NOC")){
                columnIndex++;

            }
        }
       columnIndex+=1;
       return "Row number: "+arr[0]+", Column number: "+columnIndex;

    }

    @Test
    public void getIndex(){
        System.out.println(getIndex("Russia"));

    }


    /**1. Write a method that returns a list of two countries whose sum of bronze
     medals is 18.*/

    public String getSum(){

        medalColumn=driver.findElements(By.xpath(
                "//table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr[position()<11]/td[4]"));
        countries = driver.findElements(By.xpath(
                " //table[@class='wikitable sortable plainrowheaders jquery-tablesorter']/tbody/tr[position()<11]/th"));
        

        String str="";
        for(int i=0;i<medalColumn.size();i++){
            for(int j=0;j<medalColumn.size();j++){
                if(Integer.parseInt(medalColumn.get(i).getText())+Integer.parseInt(medalColumn.get(j).getText())==18&&i!=j){
                    str=countries.get(i).getText()+"\n"+countries.get(j).getText();
                }

            }
        }
        return str;

    }

    @Test
    public void getCountries(){
        System.out.println(getSum());
    }







}
