/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package categoriesTest;

import db.DbConnection;
import domen.Category;
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
import pages.portfolios.CategoriesPage;
import setup.SeleniumProperties;

/**
 *
 * @author milena
 */
public class TestCategories {

    public static WebDriver driver;
    public static WebDriverWait wait;
    public static LoginPage login;
    public static HomePage hp;
    public static CategoriesPage cp;

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
    public void setUp() {
        cp = hp.openCategories(driver);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void createNewCategory() {
        Category catWeb = cp.createNewCategory(driver);
        Category catDb = DbConnection.getCategory("SELECT * FROM `cms_portfolios_categories`WHERE id = " + catWeb.getId());
        Assert.assertEquals(catWeb.getId(), catDb.getId());
        Assert.assertEquals(catWeb.getName(), catDb.getName());
        Assert.assertEquals(catWeb.getResume(), catDb.getResume());
    }

    @Test
    public void editCategory() {
        Category catWeb = cp.editCategory(driver);
        Category catDb = DbConnection.getCategory("SELECT * FROM `cms_portfolios_categories`WHERE id = " + catWeb.getId());
        Assert.assertEquals(catWeb.getId(), catDb.getId());
        Assert.assertEquals(catWeb.getName(), catDb.getName());
        Assert.assertEquals(catWeb.getResume(), catDb.getResume());
    }

    @Test
    public void deleteCategory() {
        Category catWeb = cp.deleteCategory(driver);
        Boolean isDeleted = DbConnection.isDeleted("SELECT * FROM `cms_portfolios_categories`WHERE id = " + catWeb.getId());
        Assert.assertEquals(Boolean.TRUE, isDeleted);
    }
    
    @Test 
    public void deleteUntil10(){
    Category catWeb = cp.deleteUntil10(driver);
    }
}

