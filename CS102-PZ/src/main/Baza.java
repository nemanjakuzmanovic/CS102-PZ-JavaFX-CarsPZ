/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.sql.Connection;

/**
 *
 * @author ryz
 */
public class Baza {

    /*
     Definisanje baze podataka, i osnovnih informacija pomocu kojih se kacimo na istu
     */
    protected static java.sql.Connection CONNECTION = null;
    protected static final String URL = "jdbc:mysql://localhost/cs102pz";
    protected static final String USERNAME = "root";
    protected static final String PASSWORD = "";

    public Baza() {
    }

    public static Connection getCONNECTION() {
        return CONNECTION;
    }

    public static String getURL() {
        return URL;
    }

    public static String getUSERNAME() {
        return USERNAME;
    }

    public static String getPASSWORD() {
        return PASSWORD;
    }

    @Override
    public String toString() {
        return "Baza{" + '}';
    }

}
