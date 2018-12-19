/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author ryz
 */
public class Download extends SkidanjeJsoup {

    //Povlacenje svih slika sa polovniautomobili.com pocetne strane - 20 komada
    public static List getSlike() {
        List lista = new ArrayList();
        Document doc;

        try {
            doc = (Document) Jsoup.connect(url).get(); 
            // Element img koji se povlaci pomocu jsoup-a
            Elements elementi = doc.select("img[src$=.jpg]");
            Iterator<Element> iterator = elementi.listIterator();
            while (iterator.hasNext()) {
                Element element = iterator.next();
                String img = element.attr("src");
                lista.add(img);
            }
        } catch (IOException ex) {
            Logger.getLogger(Download.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    // Povlacenje svih naziva preko jsouop-a sa pocetne strane polovniautomobili.com - 20 komada
    public static List getNaslovi() {
        List lista = new ArrayList();
        Document doc;

        try {
            doc = (Document) Jsoup.connect(url).get();
            //Povlacenje naziva automobila sa sajta polovniautomobili.com
            Elements elementi2 = doc.select(".title");//naziv
            Iterator<Element> iterator2 = elementi2.listIterator();
            while (iterator2.hasNext()) {
                Element element2 = iterator2.next();
                String img = element2.text();
                lista.add(img);
            }
        } catch (IOException ex) {
            Logger.getLogger(Download.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
    // Povlacenje godista automobila sa polovniautomobili.com sajta - pocetne strane
    public static List getGodine() {
        List lista = new ArrayList();
        Document doc;
        try {
            doc = (Document) Jsoup.connect(url).get();
            // Godiste automobila
            Elements elementi3 = doc.select(".description");//opis godiste
            Iterator<Element> iterator3 = elementi3.listIterator();
            while (iterator3.hasNext()) {
                Element element3 = iterator3.next();
                // Brisanje praznog prostora, kao i karaktera
                String img = element3.text().trim().replaceAll("\u00a0", "").replaceAll("god", "");
                lista.add(img);
            }
        } catch (IOException ex) {
            Logger.getLogger(Download.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    // Povlacenje svih cena sa pocetne strane polovniautomobili.com
    public static List getCene() {

        List lista = new ArrayList();
        Document doc;
        try {
            doc = (Document) Jsoup.connect(url).get();
            //Povlacenje cene
            Elements elementi4 = doc.select(".price");//cena
            Iterator<Element> iterator4 = elementi4.listIterator();
            while (iterator4.hasNext()) {
                Element element4 = iterator4.next();
                // Brisanje tacke, kao i znaka E (euro) sa cene, kako bi lepse prikazali, i kako bi se kasnije int vrednost unela u bazu
                String img = element4.text().trim().replaceAll("\u00a0", "").replaceAll("god", "").replaceAll("\\.", "").replaceAll("\u20ac", "");
                lista.add(img);
            }

        } catch (IOException ex) {
            Logger.getLogger(Download.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    // Povlacenje URL svakog automobila koji se skine sa pocetne strane polovniautomobili.com
    public static List getInfo() {

        List lista = new ArrayList();
        Document doc;
        try {
            doc = (Document) Jsoup.connect(url).get();
            // Povlacenje linka ka automobilu
            Elements elementi4 = doc.select("ul.ads > li > a");//url
            Iterator<Element> iterator4 = elementi4.listIterator();
            while (iterator4.hasNext()) {
                Element element4 = iterator4.next();
                String img = element4.attr("href");
                lista.add("https://www.polovniautomobili.com" + img);
            }

        } catch (IOException ex) {
            Logger.getLogger(Download.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public Download(String url) {
        super(url);
    }

}
