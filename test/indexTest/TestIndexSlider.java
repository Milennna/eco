/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indexTest;

import pages.index.IndexPage;
import db.DbConnection;
import domen.Index;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageUtil.PageUtilities;
import pages.basic.HomePage;
import pages.basic.LoginPage;
import setup.SeleniumProperties;

/**
 *
 * @author qa
 */
public class TestIndexSlider {

    public static WebDriver driver;
    public static WebDriverWait wait;
    public static LoginPage login;
    public static HomePage hp;
    public static IndexPage indexPage;

    @BeforeClass
    public static void login() {
        login = new LoginPage();
        SeleniumProperties.init();
        driver = PageUtilities.initWebDriver(driver);
        hp = login.login(driver);
        DbConnection.getConnection();

    }

    @AfterClass
    public static void tearDownClass() {
        driver.quit();
        DbConnection.close();
        
    }

    @Before
    public void openIndexSlider() {
        indexPage = hp.openIndexSlide(driver);
    }

    @After
    public void tearDown() {
        
    }

    @Test
    public void testCreateIndexSlide() {

        Index indexWeb = indexPage.createNewIndex(driver);
        Index indexDb = DbConnection.getIndex("SELECT * FROM `cms_index_slides`where id =" + indexWeb.getId());
        Assert.assertEquals(indexWeb.getId(), indexDb.getId());
        Assert.assertEquals(indexWeb.getTitle(), indexDb.getTitle());
        Assert.assertEquals(indexWeb.getDescription(), indexDb.getDescription());
        Assert.assertEquals(indexWeb.getLinkLabel(), indexDb.getLinkLabel());
        Assert.assertEquals(indexWeb.getLinkType(), indexDb.getLinkType());
        
        
    }

    @Test
    public void testEditIndexSlide() {
        Index indexWeb = indexPage.editIndex(driver);
        Index indexDb = DbConnection.getIndex("SELECT * FROM `cms_index_slides`where id =" + indexWeb.getId());
        Assert.assertEquals(indexWeb.getId(), indexDb.getId());
        Assert.assertEquals(indexWeb.getTitle(), indexDb.getTitle());

    }

    @Test
    public void testDeleteIndexSlide() {
        Index indexWeb = indexPage.deleteIndex(driver);
        Boolean isDeleted = DbConnection.isDeleted(("SELECT * FROM `cms_index_slides`where id =" + indexWeb.getId()));
        Assert.assertEquals(Boolean.TRUE, isDeleted);
    }
    
    @Test
    public void testDeleteUntil10(){
    
        
    }
}
