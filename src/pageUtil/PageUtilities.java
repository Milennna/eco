/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pageUtil;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author qa
 */
public class PageUtilities {

    public static String getRandomText() {
        return "Test" + (int) (Math.random() * 1000);
    }

    public static int getRandomNumber() {
        return (int) (Math.random() * 1000);
    }

    public static String getRandomUrl() {
        return "http://" + (getRandomText()) + (".te");
    }

    public static WebDriver initWebDriver() {
        WebDriver driver = new ChromeDriver();
        return driver;
    }
    
    public static String getRandomEmail() {
    return getRandomText() + "@gmail.com";
    }
    
    public static String getRandomHour(){
        int random = (int)(Math.random()*24);
        int random2 = (int)(Math.random()*24);
        
        String x = "0" + random;
        String y = "0" + random2;
        if(random<10 & random2<10){
            return x + "-" + y;
        }
        if(random<10 & random2>=10){
        return x + "-" + random2;
        }
        if(random>=10 & random2<10)
        return random + "-" +y;
        else {
        return random + "-" +random2;
        }
    }
    
    public static String getRandomLatitude(){
    return (int) (Math.random()*100) + "." + (int) (Math.random()*10000);
    }
    
    public static int getRandomZoom(){
    return (int) (Math.random()*20);
    }
}
