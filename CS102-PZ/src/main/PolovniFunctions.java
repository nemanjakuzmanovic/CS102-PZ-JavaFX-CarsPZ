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
public class PolovniFunctions extends Baza {

    // Povlacenje svih imena automobila iz baze podataka
    public static List getAllNames() {
        List polovniautomobili = new ArrayList<>();
        try {
            CONNECTION = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "SELECT naziv FROM polovni";
            try (PreparedStatement ps = CONNECTION.prepareStatement(query)) {
                ResultSet result = ps.executeQuery();
                while (result.next()) {
                    String naziv = result.getString("naziv");
                    polovniautomobili.add(naziv);

                }

                ps.close();
                CONNECTION.close();
            }
            CONNECTION.close();
        } catch (SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
        return polovniautomobili;
    }

    // Povlacenje svih slika iz baze podataka
    public static List getAllImgs() {
        List polovniautomobili = new ArrayList<>();
        try {
            CONNECTION = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "SELECT imgUrl FROM polovni";
            try (PreparedStatement ps = CONNECTION.prepareStatement(query)) {
                ResultSet result = ps.executeQuery();
                while (result.next()) {
                    String imgUrl = result.getString("imgUrl");
                    polovniautomobili.add(imgUrl);
                }

                ps.close();
                CONNECTION.close();
            }
            CONNECTION.close();
        } catch (SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
        return polovniautomobili;
    }
    // povlacenje svih godista iz baze podataka
    public static List getAllYears() {
        List polovniautomobili = new ArrayList<>();
        try {
            CONNECTION = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "SELECT godiste FROM polovni";
            try (PreparedStatement ps = CONNECTION.prepareStatement(query)) {
                ResultSet result = ps.executeQuery();
                while (result.next()) {
                    int naziv = result.getInt("godiste");
                    polovniautomobili.add(naziv);

                }

                ps.close();
                CONNECTION.close();
            }
            CONNECTION.close();
        } catch (SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
        return polovniautomobili;
    }
    // povlacenje cena automobila iz baze podataka
    public static List getAllPrices() {
        List polovniautomobili = new ArrayList<>();
        try {
            CONNECTION = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "SELECT cena FROM polovni";
            try (PreparedStatement ps = CONNECTION.prepareStatement(query)) {
                ResultSet result = ps.executeQuery();
                while (result.next()) {
                    int naziv = result.getInt("cena");
                    polovniautomobili.add(naziv);

                }

                ps.close();
                CONNECTION.close();
            }
            CONNECTION.close();
        } catch (SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
        return polovniautomobili;
    }
    // povlacenje svih linkova ka automobilima koji se nalaze u bazi a i na polovniautomobili.com
    public static List getAllUrl() {
        List polovniautomobili = new ArrayList<>();
        try {
            CONNECTION = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "SELECT url FROM polovni";
            try (PreparedStatement ps = CONNECTION.prepareStatement(query)) {
                ResultSet result = ps.executeQuery();
                while (result.next()) {
                    String naziv = result.getString("url");
                    polovniautomobili.add(naziv);

                }

                ps.close();
                CONNECTION.close();
            }
            CONNECTION.close();
        } catch (SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
        return polovniautomobili;
    }
    
    // Kreiranje novog automobila
    public static void create(PolovniAutomobili pa) {
        try {
            CONNECTION = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "INSERT INTO polovni (imgUrl,naziv,godiste,cena,url) VALUES(?,?,?, ?, ?) ";
            try (PreparedStatement ps = CONNECTION.prepareStatement(query)) {
                ps.setString(1, pa.getImgUrl());
                ps.setString(2, pa.getNaziv());
                ps.setInt(3, pa.getGodiste());
                ps.setInt(4, pa.getCena());
                ps.setString(5, pa.getUrl());
                ps.execute();
                CONNECTION.close();
                System.out.println("Uspesan upis u bazu.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PolovniFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // brisanje postojeceg automobila iz baze podataka
    public static void delete(int id) {
        try {
            CONNECTION = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "DELETE FROM polovni WHERE id = ?";
            try (PreparedStatement ps = CONNECTION.prepareStatement(query)) {
                ps.setInt(1, id);
                ps.execute();
                System.out.println("Uspesno brisanje iz baze.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PolovniFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // citanje jednog automobila iz baze podataka
    public static PolovniAutomobili read(int id) {
        PolovniAutomobili s = null;
        try {
            CONNECTION = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "SELECT * FROM polovni WHERE id = ?";
            try (PreparedStatement ps = CONNECTION.prepareStatement(query)) {
                ps.setInt(1, id);
                ResultSet result = ps.executeQuery();
                if (result.next()) {
                    s = new PolovniAutomobili(result.getString("imgUrl"), result.getString("naziv"), result.getInt("godiste"), result.getInt("cena"), result.getString("url"));
                }
                CONNECTION.close();
            }
        } catch (SQLException ex) {
            System.out.println("MySql Connection error...");
            Logger.getLogger(PolovniFunctions.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return s;
    }
    //Azuriranje automobila u bazi podataka
    public static void update(int id, PolovniAutomobili ns) {
        try {
            CONNECTION = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "UPDATE users SET imgUrl = ?, naziv = ?, godiste = ?, cena = ?, url = ? WHERE id = ?";
            try (PreparedStatement ps = CONNECTION.prepareStatement(query)) {
                ps.setString(1, ns.getImgUrl());
                ps.setString(2, ns.getNaziv());
                ps.setInt(3, ns.getGodiste());
                ps.setInt(4, ns.getCena());
                ps.setString(6, ns.getUrl());
                ps.setInt(7, id);
                ps.execute();
                System.out.println("Uspesno azuriranje baze.");
            }
        } catch (SQLException ex) {
            System.out.println("MySql Connection error...");
            Logger.getLogger(PolovniFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // Citanje svih automobila koji se nalaze u bazi podataka
    public static List<PolovniAutomobili> readAll() {
        List<PolovniAutomobili> listaUsera = new ArrayList<>();
        try {
            CONNECTION = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "SELECT * FROM polovni WHERE 1";
            try (Statement st = (Statement) CONNECTION.createStatement()) {
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    String imgUrl = rs.getString("imgUrl");
                    String naziv = rs.getString("naziv");
                    int godiste = rs.getInt("godiste");
                    int cena = rs.getInt("cena");
                    String url = rs.getString("url");
                    listaUsera.add(new PolovniAutomobili(imgUrl, naziv, godiste,cena,url));
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
