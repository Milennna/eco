/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package portfoliosTest;

import db.DbConnection;
import domen.Portfolios;
import java.util.Random;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageUtil.PageUtilities;
import pages.basic.HomePage;
import pages.basic.LoginPage;
import pages.portfolios.PortfoliosPage;
import setup.SeleniumProperties;

/**
 *
 * @author milena
 */
public class TestPortfolios {

    public static PortfoliosPage pp;
    public static HomePage hp;
    public static LoginPage lp;
    public static WebDriver driver;
    // public static Portfolios port;

    @BeforeClass
    public static void setUpClass() {
        SeleniumProperties.init();
        driver = PageUtilities.initWebDriver(driver);
        lp = new LoginPage();
        hp = lp.login(driver);
        DbConnection.getConnection();
    }

    @AfterClass
    public static void tearDownClass() {
        driver.quit();
        DbConnection.close();
    }

    @Before
    public void setUp() {
        pp = hp.openPortfolios(driver);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void addPortfolios() {
        Portfolios portWeb = pp.createNewPortfolios(driver);
        Portfolios portDb = DbConnection.getPortfolios("SELECT * FROM `cms_portfolios` WHERE id = " + portWeb.getId());
        Assert.assertEquals(portWeb.getId(), portDb.getId());
        Assert.assertEquals(portWeb.getTitle(), portDb.getTitle());
        Assert.assertEquals(portWeb.getDataCategories(), portDb.getDataCategories());
        Assert.assertEquals(portWeb.getCharacteristic1(), portDb.getCharacteristic1());
        Assert.assertEquals(portWeb.getCharacteristic2(), portDb.getCharacteristic2());
        Assert.assertEquals(portWeb.getDescription(), portDb.getDescription());

    }

    @Test
    public void editPortfolios() {
        Portfolios portWeb = pp.editPortfolios(driver);
        Portfolios portDb = DbConnection.getPortfolios("SELECT * FROM `cms_portfolios` WHERE id = " + portWeb.getId());
        Assert.assertEquals(portWeb.getId(), portDb.getId());
        Assert.assertEquals(portWeb.getTitle(), portDb.getTitle());
        Assert.assertEquals(portWeb.getDataCategories(), portDb.getDataCategories());
        Assert.assertEquals(portWeb.getCharacteristic1(), portDb.getCharacteristic1());
        Assert.assertEquals(portWeb.getCharacteristic2(), portDb.getCharacteristic2());
        Assert.assertEquals(portWeb.getDescription(), portDb.getDescription());
    }

    @Test
    public void deletePortfolios() {
        Portfolios portWeb = pp.deletePortfolios(driver);
        Boolean isDeleted = DbConnection.isDeleted(("SELECT * FROM `cms_portfolios` WHERE id = " + portWeb.getId()));
        org.junit.Assert.assertEquals(Boolean.TRUE, isDeleted);
    }
    
    @Test
    public void deleteUntil10(){
    Portfolios portWeb = pp.deleteUntil10(driver);
    }

    
}
