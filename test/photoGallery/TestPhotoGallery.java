/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package photoGallery;

import db.DbConnection;
import domen.PhotoGallery;
import static indexTest.TestIndexSlider.driver;
import static indexTest.TestIndexSlider.hp;
import static indexTest.TestIndexSlider.login;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageUtil.PageUtilities;
import pages.basic.HomePage;
import pages.basic.LoginPage;
import pages.photoGallery.PhotoGalleryPage;
import setup.SeleniumProperties;

/**
 *
 * @author milena
 */
public class TestPhotoGallery {

    public static WebDriver driver;
    public static WebDriverWait wait;
    public static LoginPage login;
    public static HomePage hp;
    public static PhotoGalleryPage php;

    @BeforeClass
    public static void login() {
        login = new LoginPage();
        SeleniumProperties.init();
        driver = PageUtilities.initWebDriver();
        hp = login.login(driver);
        DbConnection.getConnection();
    }

    @AfterClass
    public static void tearDownClass() {
        driver.quit();
        DbConnection.close();
    }

    @Before
    public void openPhotoGalery() {
        php = hp.openPhotoGallery(driver);

    }

    @After
    public void tearDown() {

        
    }

    @Test
    public void createNewGallery() {
        PhotoGallery photoWeb = php.createNewPhotoGallery(driver);
        PhotoGallery photoDb = DbConnection.getPhotoGallery("SELECT * FROM `cms_photo_galleries`WHERE id = " + photoWeb.getId());
        Assert.assertEquals(photoWeb.getId(), photoDb.getId());
        Assert.assertEquals(photoWeb.getTitle(), photoDb.getTitle());
        Assert.assertEquals(photoWeb.getDescritpion(), photoDb.getDescritpion());
    }

    @Test
    public void editPhotoGallery() {
        PhotoGallery photoWeb = php.editPhotoGallery(driver);
        PhotoGallery photoDb = DbConnection.getPhotoGallery("SELECT * FROM `cms_photo_galleries`WHERE id = " + photoWeb.getId());
        Assert.assertEquals(photoWeb.getId(), photoDb.getId());
        Assert.assertEquals(photoWeb.getDescritpion(), photoDb.getDescritpion());
        
    }

    @Test
    public void deletePhotoGallery() {
        PhotoGallery photoWeb = php.deletePhotoGallery(driver);
        Boolean isDeleted = DbConnection.isDeleted("SELECT * FROM `cms_photo_galleries`WHERE id = " + photoWeb.getId());
        Assert.assertEquals(Boolean.TRUE, isDeleted);
    }
}
