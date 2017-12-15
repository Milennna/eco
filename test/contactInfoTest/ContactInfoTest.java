/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contactInfoTest;

import db.DbConnection;
import domen.ContactInfo;
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
import pages.contactInfo.ContactInfoPage;
import setup.SeleniumProperties;

/**
 *
 * @author milena
 */
public class ContactInfoTest {

    public static WebDriver driver;
    public static WebDriverWait wait;
    public static LoginPage login;
    public static HomePage hp;
    public static ContactInfoPage cip;

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
    public void setUp() {
        cip = hp.openContactInfo(driver);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void createContact() {
        ContactInfo contactWeb = cip.createContact(driver);
        ContactInfo contactDb = DbConnection.getContactInfo("SELECT * FROM `cms_contact` WHERE id = " + contactWeb.getId());
        Assert.assertEquals(contactWeb.getId(), contactDb.getId());
        Assert.assertEquals(contactWeb.getLocation(), contactDb.getLocation());
        Assert.assertEquals(contactWeb.getAddress(), contactDb.getAddress());
        Assert.assertEquals(contactWeb.getAddressNumber(), contactDb.getAddressNumber());
        Assert.assertEquals(contactWeb.getEmail(), contactDb.getEmail());
        Assert.assertEquals(contactWeb.getHours(), contactDb.getHours());
        Assert.assertEquals(contactWeb.getLatitude(), contactDb.getLatitude());
        Assert.assertEquals(contactWeb.getLongitute(), contactDb.getLongitute());
        Assert.assertEquals(contactWeb.getPhone(), contactDb.getPhone());
        Assert.assertEquals(contactWeb.getZoom(), contactDb.getZoom());

    }

    @Test
    public void editContact() {
        ContactInfo contactWeb = cip.editContact(driver);
        ContactInfo contactDb = DbConnection.getContactInfo("SELECT * FROM `cms_contact` WHERE id = " + contactWeb.getId());
        Assert.assertEquals(contactWeb.getId(), contactDb.getId());
        Assert.assertEquals(contactWeb.getLocation(), contactDb.getLocation());
        Assert.assertEquals(contactWeb.getAddress(), contactDb.getAddress());
        Assert.assertEquals(contactWeb.getAddressNumber(), contactDb.getAddressNumber());
        Assert.assertEquals(contactWeb.getEmail(), contactDb.getEmail());
        Assert.assertEquals(contactWeb.getHours(), contactDb.getHours());
        Assert.assertEquals(contactWeb.getLatitude(), contactDb.getLatitude());
        Assert.assertEquals(contactWeb.getLongitute(), contactDb.getLongitute());
        Assert.assertEquals(contactWeb.getPhone(), contactDb.getPhone());
        Assert.assertEquals(contactWeb.getZoom(), contactDb.getZoom());
    }

    @Test
    public void deleteContact() {
        ContactInfo contactWeb = cip.deleteContact(driver);
        Boolean isDeleted = DbConnection.isDeleted("SELECT * FROM `cms_contact` WHERE id = " + contactWeb.getId());
        Assert.assertEquals(Boolean.TRUE, isDeleted);

    }

}
