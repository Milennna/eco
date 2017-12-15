/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pages.index;

import pages.basic.Page;
import domen.Index;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import pageUtil.PageUtilities;

/**
 *
 * @author qa
 */
public class IndexPage extends Page {

    private void clickOnAddButton(WebDriver driver) {
        clickOnElement(driver, By.className("glyphicon-plus"));
    }

    private String sendTextOnTitleField(WebDriver driver) {
        return sendTextOnField(driver, By.id("title"));

    }

    private String sendTextOnTextField(WebDriver driver) {
        return sendTextOnField(driver, By.id("description"));
    }

    private String chooseComboCreate(WebDriver driver, String linkType) {
        WebElement combo = waitForVisibility(driver, By.id("link_type"));
        Select option = new Select(combo);
        option.selectByValue("InternalLink");
//        return linkType;

        WebElement selecetedItem = option.getFirstSelectedOption();
        return selecetedItem.getAttribute("value");

    }

    private String chooseComboEdit(WebDriver driver, String linkType) {
        WebElement combo = waitForVisibility(driver, By.id("link_type"));
        Select option = new Select(combo);
        option.selectByValue("ExternalLink");
        WebElement selectedItem = option.getFirstSelectedOption();
        return selectedItem.getAttribute("value");

    }

    private String sendTextOnLinkLabel(WebDriver driver) {
        return getTextFromField(driver, By.id("link_label"), PageUtilities.getRandomText(), "value");
    }

    public Index createNewIndex(WebDriver driver) {
        return commonSteps(driver, "new");

    }

    public Index editIndex(WebDriver driver) {
        return commonSteps(driver, "bbb");
    }

    private Index commonSteps(WebDriver driver, String operation) {
        Index i = new Index();
        
        if (operation.equals("new")) {
            clickOnAddButton(driver);
        } else {
            clickOnLastRow(driver, By.cssSelector("#rows-table > tbody"), By.className("glyphicon-pencil"));
        }
        i.setTitle(sendTextOnTitleField(driver));
        i.setDescription(sendTextOnTextField(driver));
        
        if (operation.equals("new")) {
            i.setLinkType(chooseComboCreate(driver, "InternalLink"));
        } else {
            i.setLinkType(chooseComboEdit(driver, "ExternalLink"));
        }
        i.setLinkLabel(sendTextOnLinkLabel(driver));
        if(operation.equals("new")){
            sendTextOnField(driver, By.id("internal_link_url"), PageUtilities.getRandomUrl());
        }else {
            sendTextOnField(driver, By.id("external_link_url"), PageUtilities.getRandomUrl());
        }
        
        uploadPhoto(driver, By.id("index_slide_photo"), System.getProperty("user.dir")+ "\\Slike\\California.jpg");
        clickOnElement(driver, By.id("new_indexSlide_submit"));
        i.setId(getIdFromLastRow(driver, By.cssSelector("#rows-table > tbody"), "data-index-slide-id"));
        return i;

    }

    public Index deleteIndex(WebDriver driver) {
        Index i = new Index();
        i.setId(getIdFromLastRow(driver, By.cssSelector("#rows-table > tbody"), "data-index-slide-id"));
        clickOnLastRow(driver, By.cssSelector("#rows-table > tbody"), By.className("glyphicon-trash"));
        clickOnElement(driver, By.className("btn-danger"));
        return i;

    }
}
