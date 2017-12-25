/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usersTest;

import db.DbConnection;
import domen.Users;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageUtil.PageUtilities;
import pages.basic.HomePage;
import pages.basic.LoginPage;
import pages.users.UsersPage;
import setup.SeleniumProperties;

/**
 *
 * @author milena
 */
public class TestUsers {
    public static WebDriver driver;
    public static LoginPage lp;
    public static HomePage hp;
    public static UsersPage up;
    public static Users user;
    
    
    
    @BeforeClass
    public static void setUpClass() {
        driver = PageUtilities.initWebDriver(driver);
        SeleniumProperties.init();
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
        up = hp.openUsers(driver);
    }
    
    @After
    public void tearDown() {
    }
@Test
public void addUser(){
Users userWeb = up.createUser(driver);
Users userDb = DbConnection.getUser("SELECT * FROM `cms_users` WHERE id = "+ userWeb.getId());
Assert.assertEquals(userWeb.getId(), userDb.getId());
Assert.assertEquals(userWeb.getUsername(), userDb.getUsername());
Assert.assertEquals(userWeb.getFirstName(), userDb.getFirstName());
Assert.assertEquals(userWeb.getLastName(), userDb.getLastName());
Assert.assertEquals(userWeb.getEmail(), userDb.getEmail());
//Assert.assertEquals(userWeb.getStatus(), userDb.getStatus());


}
@Test
public void editUser(){
Users userWeb = up.editUsers(driver);
Users userDb = DbConnection.getUser("SELECT * FROM `cms_users` WHERE id = "+ userWeb.getId());
Assert.assertEquals(userWeb.getId(), userDb.getId());
Assert.assertEquals(userWeb.getUsername(), userDb.getUsername());
Assert.assertEquals(userWeb.getFirstName(), userDb.getFirstName());
Assert.assertEquals(userWeb.getLastName(), userDb.getLastName());
Assert.assertEquals(userWeb.getEmail(), userDb.getEmail());
//Assert.assertEquals(userWeb.getStatus(), userDb.getStatus());

}
@Test 
public void deleteUser(){
    Users userWeb = up.deleteUsers(driver);
    Boolean isDeleted = DbConnection.isDeleted("SELECT * FROM `cms_users` WHERE id = "+ userWeb.getId());
   org.junit.Assert.assertEquals(Boolean.TRUE, isDeleted);
}

@Test 
public void deleteUntil10(){
Users userWeb = up.deleteUntil10(driver);
}

}
