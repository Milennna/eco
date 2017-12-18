/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pages.users;

import domen.Users;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.basic.Page;

/**
 *
 * @author milena
 */
public class UsersPage extends Page {

    String username;

    private void clickOnAddUser(WebDriver driver) {
        clickOnElement(driver, By.className("btn-default"));
    }

    private String sendtextOnUsername(WebDriver driver) {
        return sendTextOnField(driver, By.id("username"));

    }

    private String sendtextOnFirstName(WebDriver driver) {
        return sendTextOnField(driver, By.id("first_name"));
    }

    private String sendTextOnLastName(WebDriver driver) {
        return sendTextOnField(driver, By.id("last_name"));
    }

    private String sendTextOnEmail(WebDriver driver) {
        return getRandomEmail(driver, By.id("email"));
    }

    private void clickOnSave(WebDriver driver) {
        clickOnElement(driver, By.id("new_user_submit"));
    }

    private void clickOnEdit(WebDriver driver) {
        clickOnLastRow(driver, By.cssSelector("#rows-table > tbody"), By.className("glyphicon-pencil"));
    }

    private Users commonSteps(WebDriver driver, String operation) {
        Users user = new Users();
        if (operation.equals("new")) {
            clickOnAddUser(driver);
        } else {
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            clickOnEdit(driver);
        }
        username = sendtextOnUsername(driver);
        user.setUsername(username);
        user.setFirstName(sendtextOnFirstName(driver));
        user.setLastName(sendTextOnLastName(driver));
        user.setEmail(sendTextOnEmail(driver));

        //user.setStatus(1);
        clickOnSave(driver);
//        String username = user.getUsername();
//        List<WebElement> rows = findRows(driver, By.cssSelector("#rows-table > tbody"));
//        WebElement row = returnRowWithUsername(rows, username);
//        String id = row.getAttribute("data-user-id");
//        user.setId(Integer.valueOf(id));
        WebElement searchField = waitForVisibility(driver, By.className("input-sm"));
        searchField.sendKeys(username);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        user.setId(getIdFromLastRow(driver, By.xpath("//*[@id=\"rows-table\"]/tbody"), "data-user-id"));
        return user;
    }

    public Users createUser(WebDriver driver) {
        return commonSteps(driver, "new");
    }

    public Users editUsers(WebDriver driver) {
        return commonSteps(driver, "edit");
    }

    public Users deleteUsers(WebDriver driver) {
        Users user = new Users();
        WebElement searchField = waitForVisibility(driver, By.className("input-sm"));
        searchField.sendKeys(username);
        user.setId(getIdFromLastRow(driver, By.cssSelector("#rows-table > tbody"), "data-user-id"));
        clickOnLastRow(driver, By.cssSelector("#rows-table > tbody"), By.className("glyphicon-trash"));
        clickOnElement(driver, By.className("btn-danger"));
        return user;
    }

}
