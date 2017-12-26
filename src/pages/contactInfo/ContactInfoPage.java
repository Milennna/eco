/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pages.contactInfo;

import domen.ContactInfo;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.basic.Page;

/**
 *
 * @author milena
 */
public class ContactInfoPage extends Page {
    
    private void clickOnAddButton(WebDriver driver) {
        clickOnElement(driver, By.className("glyphicon-plus"));
    }
    
    private String sendTextOnLocation(WebDriver driver) {
        return sendTextOnField(driver, By.id("location"));
    }
    
    private String sendTextOnAddress(WebDriver driver) {
        return sendTextOnField(driver, By.id("address"));
    }
    
    private int sendNumberOnAddressNumber(WebDriver driver) {
        return sendNumberOnField(driver, By.id("address_number"));
    }
    
    private String sendHours(WebDriver driver) {
        return sendHours(driver, By.id("hours"));
    }
    
    private String sendLatitude(WebDriver driver) {
        return sendLatitude(driver, By.id("latitude"));
    }
    
    private String sendLongitude(WebDriver driver) {
        return sendLatitude(driver, By.id("longitude"));
    }
    
    private int sendPhone(WebDriver driver) {
        return sendNumberOnField(driver, By.id("phone"));
    }
    
    private String sendEmail(WebDriver driver) {
        return getRandomEmail(driver, By.id("email"));
    }
    
    private int sendZoom(WebDriver driver) {
        return sendZoom(driver, By.id("zoom"));
    }
    
    private void clickOnSaveButton(WebDriver driver) {
        clickOnElement(driver, By.id("new_portfolio_submit"));
    }
    
    private ContactInfo commonSteps(WebDriver driver, String operation) {
        ContactInfo ci = new ContactInfo();
        if (operation.equals("new")) {
            clickOnAddButton(driver);
        } else {
            clickOnLastRow(driver, By.cssSelector("#rows-table > tbody"), By.className("glyphicon-pencil"));
            
        }
        ci.setLocation(sendTextOnLocation(driver));
        ci.setAddress(sendTextOnAddress(driver));
        ci.setAddressNumber(sendNumberOnAddressNumber(driver));
        ci.setHours(sendHours(driver));
        ci.setLatitude(sendLatitude(driver));
        ci.setLongitute(sendLongitude(driver));
        ci.setPhone(sendPhone(driver));
        ci.setEmail(sendEmail(driver));
        ci.setZoom(sendZoom(driver));
        
        clickOnSaveButton(driver);
        ci.setId(getIdFromLastRow(driver, By.cssSelector("#rows-table > tbody"), "data-contact-id"));
        return ci;
    }
    
    public ContactInfo createContact(WebDriver driver) {
        return commonSteps(driver, "new");
    }
    
    public ContactInfo editContact(WebDriver driver) {
        return commonSteps(driver, "edit");
    }
    
    public ContactInfo deleteContact(WebDriver driver) {
        ContactInfo ci = new ContactInfo();
        ci.setId(getIdFromLastRow(driver, By.cssSelector("#rows-table > tbody"), "data-contact-id"));
        clickOnLastRow(driver, By.cssSelector("#rows-table > tbody"), By.className("glyphicon-trash"));
        clickOnElement(driver, By.className("btn-danger"));
        return ci;
    }
    
    public void deleteUntil10(WebDriver driver) {
        deleteUntilFirst10(driver);
    }
}
