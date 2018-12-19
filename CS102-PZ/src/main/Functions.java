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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static main.Baza.CONNECTION;

/**
 *
 * @author ryz
 */
public class Functions extends Baza {

    // Provera da li korisnik vec postoji u bazi podataka
    public static boolean checkIfUserExists(String username) {
        boolean usernameExists = false;
        try {
            CONNECTION = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "SELECT * FROM users WHERE username=?";
            try (PreparedStatement ps = CONNECTION.prepareStatement(query)) {
                ps.setString(1, username);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    usernameExists = true;
                }
            }
            CONNECTION.close();

        } catch (SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }

        return usernameExists;
    }
    // Provera da li je email iskoriscen vec, i povezan za neki nalog. 1 nalog = 1 mail
    public static boolean checkIfEmailExists(String email) {
        boolean emailExists = false;
        try {
            CONNECTION = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "SELECT * FROM users WHERE email=?";
            try (PreparedStatement ps = CONNECTION.prepareStatement(query)) {
                ps.setString(1, email);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    emailExists = true;
                }
            }
            CONNECTION.close();

        } catch (SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }

        return emailExists;
    }

    // Dodavanje novog korisnika u bazu podataka
    public static boolean addUser(String username, String password, String email) {
        try {
            CONNECTION = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Users user = new Users();
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(email);
            String query = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
            try (PreparedStatement ps = CONNECTION.prepareStatement(query)) {
                ps.setString(1, user.getUsername());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getEmail());
                ps.execute();
            }
            CONNECTION.close();
        } catch (SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    // Loginovanje korisnika u aplikaciju
    public static boolean logIn(String username, String password) {
        boolean logged = false;
        try {
            CONNECTION = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "SELECT * FROM users WHERE username=? AND password=?";
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

    // Validacija korisnika kada iznajmi automobil - mora ukucati username i email, da bi potvrdio da je to stvarno on
    public static boolean logInAlt(String username, String email) {
        boolean logged = false;
        try {
            CONNECTION = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "SELECT * FROM users WHERE username=? AND email=?";
            try (PreparedStatement ps = CONNECTION.prepareStatement(query)) {
                ps.setString(1, username);
                ps.setString(2, email);
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

    // Kreiranje novog korisnika preko admin panela
    public static void create(Users user) {
        try {
            CONNECTION = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "INSERT INTO users (username,password,email) VALUES(?, ?, ?) ";
            try (PreparedStatement ps = CONNECTION.prepareStatement(query)) {
                ps.setString(1, user.getUsername());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getEmail());
                ps.execute();
                CONNECTION.close();
                System.out.println("Uspesan upis u bazu.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Functions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // Brisanje korisnika iz  baze podataka preko admin panela
    public static void delete(int id) {
        try {
            CONNECTION = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "DELETE FROM users WHERE id = ?";
            try (PreparedStatement ps = CONNECTION.prepareStatement(query)) {
                ps.setInt(1, id);
                ps.execute();
                System.out.println("Uspesno brisanje iz baze.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Functions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Uzimanje jednog korisnika iz baze podataka
    public static Users read(int id) {
        Users s = null;
        try {
            CONNECTION = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "SELECT * FROM users WHERE id = ?";
            try (PreparedStatement ps = CONNECTION.prepareStatement(query)) {
                ps.setInt(1, id);
                ResultSet result = ps.executeQuery();
                if (result.next()) {
                    s = new Users(result.getString("username"), result.getString("password"), result.getString("email"));
                }
                CONNECTION.close();
            }
        } catch (SQLException ex) {
            System.out.println("MySql Connection error...");
            Logger.getLogger(Functions.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return s;
    }

    // Azuriranje informacija o korisniku u bazi podataka
    public static void update(int id, Users ns) {
        try {
            CONNECTION = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "UPDATE users SET username = ?, password = ?, email = ? WHERE id = ?";
            try (PreparedStatement ps = CONNECTION.prepareStatement(query)) {
                ps.setString(1, ns.getUsername());
                ps.setString(2, ns.getPassword());
                ps.setString(3, ns.getEmail());
                ps.setInt(4, id);
                ps.execute();
                System.out.println("Uspesno azuriranje baze.");
            }
        } catch (SQLException ex) {
            System.out.println("MySql Connection error...");
            Logger.getLogger(Functions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Ispisivanje svih korisnika iz baze podataka sa svim informacijama
    public static List<Users> readAll() {
        List<Users> listaUsera = new ArrayList<>();
        try {
            CONNECTION = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "SELECT * FROM users WHERE 1";
            try (Statement st = (Statement) CONNECTION.createStatement()) {
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String email = rs.getString("email");
                    listaUsera.add(new Users(username, password, email));
                }
            }
            CONNECTION.close();
        } catch (SQLException ex) {
            System.out.println("MySql Connection error...");
            ex.printStackTrace();
        }
        return listaUsera;
    }
}
