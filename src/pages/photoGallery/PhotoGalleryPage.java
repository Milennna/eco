/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pages.photoGallery;

import domen.PhotoGallery;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.basic.Page;

/**
 *
 * @author milena
 */
public class PhotoGalleryPage extends Page {

    private void clickOnAddButton(WebDriver driver) {
        clickOnElement(driver, By.className("glyphicon-plus"));
    }

    private String sendTextOnTitleField(WebDriver driver) {
        return sendTextOnField(driver, By.id("title"));

    }

    private String sendTextOnTextField(WebDriver driver) {
        return sendTextOnField(driver, By.id("description"));
    }

    private PhotoGallery commonSteps(WebDriver driver, String operation) {
        PhotoGallery photo = new PhotoGallery();
        if (operation.equals("new")) {
            clickOnAddButton(driver);
        } else {
            clickOnAddButton(driver);
        }
        photo.setTitle(sendTextOnTitleField(driver));
        photo.setDescritpion(sendTextOnTextField(driver));
        uploadPhoto(driver, By.id("photo_gallery_leading_photo"), System.getProperty("user.dir")+ "\\Slike\\California.jpg");
        clickOnElement(driver, By.id("new_photoGallery_submit"));
        clickOnElement(driver, By.className("glyphicon-arrow-left"));
        photo.setId(getIdFromLastRow(driver, By.cssSelector("#rows-table > tbody"), "data-photo-gallery-id"));
        return photo;
    }

    public PhotoGallery createNewPhotoGallery(WebDriver driver) {
        return commonSteps(driver, "new");
    }

    public PhotoGallery editPhotoGallery(WebDriver driver) {
        return commonSteps(driver, "bbb");
    }

    public PhotoGallery deletePhotoGallery(WebDriver driver) {
        PhotoGallery photo = new PhotoGallery();
        photo.setId(getIdFromLastRow(driver, By.cssSelector("#rows-table > tbody"), "data-photo-gallery-id"));
        clickOnLastRow(driver, By.cssSelector("#rows-table > tbody"), By.className("glyphicon-trash"));
        clickOnElement(driver, By.className("btn-danger"));
        return photo;
    }

}
