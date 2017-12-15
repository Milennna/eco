/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pages.basic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import setup.SeleniumProperties;

/**
 *
 * @author qa
 */
public class LoginPage extends Page {

    public HomePage login(WebDriver driver) {

        HomePage hp = new HomePage();
        driver.get(SeleniumProperties.url);
        driver.manage().window().maximize();
        SeleniumProperties.init();

        sendTextOnField(driver, By.name("username"), SeleniumProperties.username);

        sendTextOnField(driver, By.name("password"), SeleniumProperties.password);

        clickOnElement(driver, By.className("btn-block"));

        return hp;
    }

}
