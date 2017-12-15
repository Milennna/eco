/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import domen.Category;
import domen.ContactInfo;
import domen.Index;
import domen.PhotoGallery;
import domen.Portfolios;
import domen.Users;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbConnection {
    
    private static Connection conn;
    
    public static void getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            
            conn = DriverManager.getConnection("jdbc:mysql://136.243.5.37:33063/eco_test", "root", "cubesqa");
        } catch (SQLException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void close() {
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Index getIndex(String query) {
        Index i = new Index();
        
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            while (rs.next()) {
                i.setId(rs.getInt("id"));
                i.setTitle(rs.getString(2));
                i.setDescription(rs.getString("description"));
                i.setLinkType(rs.getString("link_type"));
                i.setLinkLabel(rs.getString("link_label"));
            }            
        } catch (SQLException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
    }
    
    public static Boolean isDeleted(String query) {
        
        try {
            
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            System.out.println(query);
            
            if (rs.next()) {
                return false;
            } else {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static PhotoGallery getPhotoGallery(String query) {
        PhotoGallery photo = new PhotoGallery();
        
        try {
            
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            while (rs.next()) {
                photo.setId(rs.getInt("id"));
                photo.setTitle(rs.getString("title"));
                photo.setDescritpion(rs.getString("description"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return photo;
    }
    
    public static Users getUser(String query) {
        Users user = new Users();
        try {
            
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            while (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setStatus(rs.getInt("status"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    public static Portfolios getPortfolios(String query) {
        Portfolios port = new Portfolios();
        try {
            
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            while (rs.next()) {
                port.setId(rs.getInt("id"));
                port.setTitle(rs.getString("title"));
                port.setDataCategories(rs.getString("data_categories"));
                port.setCharacteristic1(rs.getString("characteristic1"));
                port.setCharacteristic2(rs.getString("characteristic2"));
                port.setDescription(rs.getString("description"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return port;
        
    }
    
    public static ContactInfo getContactInfo(String query) {
        ContactInfo ci = new ContactInfo();
        try {
            
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            while (rs.next()) {
                ci.setId(rs.getInt("id"));
                ci.setLocation(rs.getString("location"));
                ci.setAddress(rs.getString("address"));
                ci.setAddressNumber(rs.getInt("address_number"));
                ci.setHours(rs.getString("hours"));
                ci.setLatitude(rs.getString("latitude"));
                ci.setLongitute(rs.getString("longitude"));
                ci.setPhone(rs.getInt("phone"));
                ci.setEmail(rs.getString("email"));
                ci.setZoom(rs.getInt("zoom"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ci;
    }
    
    public static Category getCategory(String query) {
        Category cat = new Category();
        
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            while (rs.next()) {
                cat.setId(rs.getInt("id"));
                cat.setName(rs.getString("name"));
                cat.setResume(rs.getString("description"));
            }            
        } catch (SQLException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cat;
}
}
