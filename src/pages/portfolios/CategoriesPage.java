/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pages.portfolios;

import domen.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.basic.Page;

/**
 *
 * @author milena
 */
public class CategoriesPage extends Page {

    private void clickOnAddCategory(WebDriver driver) {
        clickOnElement(driver, By.cssSelector("#page-wrapper > div > div:nth-child(3) > div > div > div.panel-heading.text-right > div > a"));

    }

    private String sendTextOnNameField(WebDriver driver) {
        return sendTextOnField(driver, By.id("name"));
    }

    private String sendTextOnResumeField(WebDriver driver) {
        return sendTextOnField(driver, By.id("description"));
    }

    private void clickOnSaveButton(WebDriver driver) {
        clickOnElement(driver, By.className("btn-success"));
    }

    private Category commonSteps(WebDriver driver, String operation) {
        Category cat = new Category();
        if (operation.equals("new")){
            clickOnAddCategory(driver);
        }else{
            clickOnLastRow(driver, By.cssSelector("#rows-table > tbody"), By.className("glyphicon-pencil"));
}
        cat.setName(sendTextOnNameField(driver));
        cat.setResume(sendTextOnResumeField(driver));
        clickOnSaveButton(driver);
        cat.setId(getIdFromLastRow(driver, By.cssSelector("#rows-table > tbody"), "data-portfolio-id"));
        return cat;
    }

    public Category createNewCategory(WebDriver driver) {
       return commonSteps(driver, "new");
    }
    public Category editCategory(WebDriver driver){
    return commonSteps(driver, "edit");
    }
    public Category deleteCategory(WebDriver driver){
        Category cat = new Category();
        cat.setId(getIdFromLastRow(driver, By.cssSelector("#rows-table > tbody") ,"data-portfolio-id"));
        clickOnLastRow(driver, By.cssSelector("#rows-table > tbody"), By.className("glyphicon-trash"));
        clickOnElement(driver, By.className("btn-danger"));
        return cat;
    }
}
