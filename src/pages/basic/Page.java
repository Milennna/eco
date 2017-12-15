/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pages.basic;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageUtil.PageUtilities;

/**
 *
 * @author qa
 */
public class Page {

    public void clickOnElement(WebDriver driver, By locator) {
        WebElement element = waitForClickability(driver, locator);
        element.click();
    }

    public String sendTextOnField(WebDriver driver, By locator) {
        WebElement field = waitForVisibility(driver, locator);
        field.clear();
        String text = PageUtilities.getRandomText();
        field.sendKeys(text);
        return text;
    }

    public void sendTextOnField(WebDriver driver, By locator, String text) {
        WebElement field = waitForVisibility(driver, locator);
        field.sendKeys(text);
    }

    public String getTextFromField(WebDriver driver, By locator, String text) {
        WebElement field = waitForVisibility(driver, locator);
        field.sendKeys(text);
        return field.getText();
    }

    public String getTextFromField(WebDriver driver, By locator, String text, String attName) {
        WebElement field = waitForVisibility(driver, locator);
        field.sendKeys(text);
        return field.getAttribute(attName);
    }

    public void sendUrlOnUrlField(WebDriver driver, By locator) {
        WebElement field = waitForVisibility(driver, locator);
        field.clear();
        field.sendKeys(PageUtilities.getRandomUrl());
    }

    public WebElement waitForVisibility(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return element;
    }

    public WebElement waitForClickability(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        return element;
    }

    public WebElement findLastRow(WebDriver driver, By tableLocator) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        WebElement table = waitForVisibility(driver, tableLocator);
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        WebElement lastRow = rows.get(rows.size() - 1);
        return lastRow;
    }

    public List<WebElement> findRows(WebDriver driver, By tableLocator) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        WebElement table = waitForVisibility(driver, tableLocator);
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        return rows;

    }

    public WebElement returnRowWithUsername(List<WebElement> rows, String username) {
        for (int i = 0; i < rows.size(); i++) {
            WebElement row = rows.get(i);
            WebElement td = row.findElement(By.cssSelector("td:nth-child(2)"));
            String tableUsername = td.getText();
            if (tableUsername.equals(username)) {
                return row;
            }
        }
        return null;
    }

    public void clickOnLastRow(WebDriver driver, By tableLocator, By buttonLocator) {
        WebElement lastRow = findLastRow(driver, tableLocator);
        WebElement button = lastRow.findElement(buttonLocator);
        button.click();
    }

    public int getIdFromLastRow(WebDriver driver, By tableLocator, String attributeName) {
        WebElement lastRow = findLastRow(driver, tableLocator);
        String id = lastRow.getAttribute(attributeName);
        return Integer.valueOf(id);
    }

    public void uploadPhoto(WebDriver driver, By locator, String photoPath) {
        WebElement photo = waitForVisibility(driver, locator);
        photo.sendKeys(photoPath);
    }

    public String getRandomEmail(WebDriver driver, By locator) {
        WebElement field = waitForVisibility(driver, locator);
        field.clear();
        String text = PageUtilities.getRandomEmail();
        field.sendKeys(text);
        return text;
    }

    public int sendNumberOnField(WebDriver driver, By locator) {
        WebElement field = waitForVisibility(driver, locator);
        field.clear();
//        String number = "" + PageUtilities.getRandomNumber();
//        field.sendKeys(number);
//        return Integer.valueOf(number);
        int number = PageUtilities.getRandomNumber();
        field.sendKeys(""+number);
        return number;
    }

    public String sendHours(WebDriver driver, By locator) {
        WebElement field = waitForVisibility(driver, locator);
        field.clear();
        String hours = PageUtilities.getRandomHour();
        field.sendKeys(hours);
        return hours;
    }

    public String sendLatitude(WebDriver driver, By locator) {
        WebElement field = waitForVisibility(driver, locator);
        field.clear();
        String latitude = PageUtilities.getRandomLatitude();
        field.sendKeys(latitude);
        return latitude;
    }
    public int sendZoom(WebDriver driver, By locator){
    WebElement field = waitForVisibility(driver, locator);
    field.clear();
    int number = PageUtilities.getRandomZoom();
    field.sendKeys(""+number);
    return number;
    }
    
}
