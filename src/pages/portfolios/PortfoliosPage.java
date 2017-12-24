/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pages.portfolios;

import domen.Portfolios;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import pages.basic.Page;

/**
 *
 * @author milena
 */
public class PortfoliosPage extends Page {

    private void clickOnAddPorfolios(WebDriver driver) {
        clickOnElement(driver, By.className("glyphicon-plus"));
    }

    private String sendTextOnTitle(WebDriver driver) {
        return sendTextOnField(driver, By.id("title"));
    }

    private String chooseDataCategories(WebDriver driver) {
        WebElement combo = waitForVisibility(driver, By.id("data_categories"));
        Select data = new Select(combo);
        List<WebElement> items = data.getOptions();
        data.deselectAll();
        data.selectByIndex((int) (Math.random() * items.size()));
        data.selectByIndex((int) (Math.random() * items.size()));
        data.selectByIndex((int) (Math.random() * items.size()));
        data.selectByIndex((int) (Math.random() * items.size()));
        String values = "";
        List<WebElement> selectedItems = data.getAllSelectedOptions();
        for (int i = 0; i < selectedItems.size(); i++) {
            values = values + ", " + selectedItems.get(i).getAttribute("value");
            if (i == 0) {
                values = selectedItems.get(i).getAttribute("value");
            }

        }

//        WebElement selecetedItem = data.getFirstSelectedOption();
//        return selecetedItem.getAttribute("value");
//        List<WebElement> selectedItem = data.getAllSelectedOptions();
////        return selectedItem.
//      
//        for (WebElement element : selectedItem) {
//            String value = element.getAttribute("value");
//        }
//        return value;
        return values;
    }

    private String sendTextCharacteristic1(WebDriver driver) {
        return sendTextOnField(driver, By.id("characteristic1"));
    }

    private String sendTextCharacteristic2(WebDriver driver) {
        return sendTextOnField(driver, By.id("characteristic2"));
    }

    private String sendTextResume(WebDriver driver) {
        return sendTextOnField(driver, By.id("description"));
    }

    private void sendPhoto(WebDriver driver) {
        uploadPhoto(driver, By.id("portfolio_photo"), System.getProperty("user.dir") + "\\Slike\\Picture_1.jpg");

    }

    private void clickOnSaveButton(WebDriver driver) {
        clickOnElement(driver, By.id("new_portfolio_submit"));
    }

    private void clickOnEditButton(WebDriver driver) {
        clickOnLastRow(driver, By.cssSelector("#rows-table > tbody"), By.className("glyphicon-pencil"));
    }

    public Portfolios createNewPortfolios(WebDriver driver) {
        return commonSteps(driver, "new");

    }

    public Portfolios editPortfolios(WebDriver driver) {
        return commonSteps(driver, "edit");
    }

    public Portfolios deletePortfolios(WebDriver driver) {
        Portfolios port = new Portfolios();
        port.setId(getIdFromLastRow(driver, By.cssSelector("#rows-table > tbody"), "data-portfolio-id"));
        clickOnLastRow(driver, By.cssSelector("#rows-table > tbody"), By.className("glyphicon-trash"));
        clickOnElement(driver, By.className("btn-danger"));
        return port;
    }

    private Portfolios commonSteps(WebDriver driver, String operation) {
        Portfolios port = new Portfolios();
        if (operation.equals("new")) {
            clickOnAddPorfolios(driver);
        } else {
            clickOnEditButton(driver);
        }
        port.setTitle(sendTextOnTitle(driver));

        port.setDataCategories(chooseDataCategories(driver));
        port.setDataCategories(chooseDataCategories(driver));
        port.setCharacteristic1(sendTextCharacteristic1(driver));
        port.setCharacteristic2(sendTextCharacteristic2(driver));
        port.setDescription(sendTextResume(driver));
        sendPhoto(driver);
        clickOnSaveButton(driver);
        port.setId(getIdFromLastRow(driver, By.cssSelector("#rows-table > tbody"), "data-portfolio-id"));
        return port;
    }
}
