/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author ryz
 */
public class PolovniAutomobili {

    private String imgUrl;
    private String naziv;
    private int godiste;
    private int cena;
    private String url;

    public PolovniAutomobili() {
    }

    public PolovniAutomobili(String imgUrl, String naziv, int godiste, int cena) {
        this.imgUrl = imgUrl;
        this.naziv = naziv;
        this.godiste = godiste;
        this.cena = cena;
    }
    
    public PolovniAutomobili(String imgUrl, String naziv, int godiste, int cena, String url){
        this.imgUrl = imgUrl;
        this.naziv = naziv;
        this.godiste = godiste;
        this.cena = cena;
        this.url = url;
    }
    
    public String getUrl(){
        return url;
    }
    
    public void setUrl(String url){
        this.url = url;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getGodiste() {
        return godiste;
    }

    public void setGodiste(int godiste) {
        this.godiste = godiste;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    @Override
    public String toString() {
        return "PolovniAutomobili{" + "imgUrl=" + imgUrl + ", naziv=" + naziv + ", godiste=" + godiste + ", cena=" + cena + '}';
    }

}
