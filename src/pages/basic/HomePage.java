/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pages.basic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.categories.CategoriesPage;
import pages.contactInfo.ContactInfoPage;
import pages.index.IndexPage;
import pages.photoGallery.PhotoGalleryPage;
import pages.portfolios.PortfoliosPage;
import pages.users.UsersPage;

/**
 *
 * @author qa
 */
public class HomePage extends Page {

    public IndexPage openIndexSlide(WebDriver driver) {
        clickOnElement(driver, By.cssSelector("#side-menu > li:nth-child(2) > a"));
        IndexPage i = new IndexPage();
        return i;

    }

    public PhotoGalleryPage openPhotoGallery(WebDriver driver) {
        clickOnElement(driver, By.cssSelector("#side-menu > li:nth-child(3) > a"));
        PhotoGalleryPage php = new PhotoGalleryPage();
        return php;
    }

    public UsersPage openUsers(WebDriver driver) {
//    clickOnElement(driver, By.cssSelector("#side-menu > li.active > ul > li:nth-child(1) > a"));
//    UsersPage up = new UsersPage();
//    return up;

        WebElement element = driver.findElement(By.cssSelector("#side-menu > li:nth-child(6) > ul > li:nth-child(1) > a"));
        WebElement element2 = driver.findElement(By.cssSelector("#side-menu > li:nth-child(6) > a"));

        if (element.isDisplayed()) {
            element.click();

        } else {
            element2.click();
            element.click();
        }
        UsersPage up = new UsersPage();
        return up;

    }

    public PortfoliosPage openPortfolios(WebDriver driver) {
        WebElement element = driver.findElement(By.cssSelector("#side-menu > li:nth-child(7) > ul > li:nth-child(1) > a"));
        WebElement element2 = driver.findElement(By.cssSelector("#side-menu > li:nth-child(7) > a"));

        if (element.isDisplayed()) {
            element.click();

        } else {
            element2.click();
            element.click();
        }
        PortfoliosPage pp = new PortfoliosPage();
        return pp;
    }
    public ContactInfoPage openContactInfo(WebDriver driver){
    ContactInfoPage cip = new ContactInfoPage();
        clickOnElement(driver, By.cssSelector("#side-menu > li:nth-child(8) > a"));
    return cip;
    }
    
    public CategoriesPage openCategories(WebDriver driver) {
        WebElement element = driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[7]/ul/li[3]/a"));
        WebElement element2 = driver.findElement(By.cssSelector("#side-menu > li:nth-child(7) > a"));

        if (element.isDisplayed()) {
            element.click();
            
        } else {
            element2.click();
            element.click();
        }
//clickOnElement(driver, By.xpath("//*[@id=\"side-menu\"]/li[7]/ul/li[3]/a"));
        CategoriesPage cp = new CategoriesPage();
        return cp;
    }
    
    
}
