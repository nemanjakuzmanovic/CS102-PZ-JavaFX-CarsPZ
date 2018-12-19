/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static main.Baza.CONNECTION;

/**
 *
 * @author ryz
 */
public class AdminFunctions extends Baza {
    
    // Validacija admina pri loginovanju na sistem, admin odvojen od korisnika zbog bezbednosti
    public static boolean logIn(String username, String password) {
        boolean logged = false;
        try {
            CONNECTION = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "SELECT * FROM staff WHERE username=? AND password=?";
            try (PreparedStatement ps = CONNECTION.prepareStatement(query)) {
                ps.setString(1, username);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    logged = true;
                }
                ps.close();
                CONNECTION.close();
            }
            CONNECTION.close();
        } catch (SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
        return logged;
    }

}
