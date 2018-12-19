/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author ryz
 */
public class UserView {

    // Prozor koji korisnik vidi kada se loginuje na sistem tj. aplikaciju
    public static void userWindow(Stage primaryStage) {

        Stage ast = new Stage();

        BorderPane bp = new BorderPane();

        VBox imgC = new VBox();

        Image imageC = new Image("img/car.jpg");
        ImageView imgV = new ImageView(imageC);
        imgV.setFitWidth(700);
        imgV.setFitHeight(500);
        imgC.getChildren().add(imgV);
        bp.setCenter(imgV);

        BorderPane topPane = new BorderPane();

        HBox title = new HBox();

        Label titlelbl = new Label("Dobrodošli");
        titlelbl.setStyle(" -fx-font: 32px \"Serif\"; -fx-padding: 20; -fx-letter-spacing: 10px; -fx-background-color: #CCFF99; -fx-pref-width: 800; -fx-alignment: center;");
        title.getChildren().add(titlelbl);
        title.setAlignment(Pos.CENTER);

        topPane.setCenter(title);

        HBox titleMid = new HBox();
        titleMid.setPadding(new Insets(0, 0, 0, 100));
        Label slika = new Label("                   Slika                                       ");
        Label naziv = new Label("Naziv                        ");
        Label godiste = new Label("Godiste      ");
        Label cena = new Label("Cena");

        titleMid.getChildren().addAll(slika, naziv, godiste, cena);
        topPane.setBottom(titleMid);

        ScrollPane sp = new ScrollPane();
        ScrollPane sp2 = new ScrollPane();

        GridPane gp = new GridPane();
        GridPane gp2 = new GridPane();

        gp.setHgap(30);
        gp.setVgap(10);

        gp2.setHgap(10);
        gp2.setVgap(10);

        VBox hb = new VBox();
        VBox hb1 = new VBox();
        VBox hb11 = new VBox();
        VBox hb111 = new VBox();
        VBox vbo = new VBox();
        VBox vbo2 = new VBox();

        hb.setSpacing(10);

        VBox pro = new VBox();
        VBox pro1 = new VBox();
        VBox pro11 = new VBox();
        VBox pro111 = new VBox();
        VBox dom1 = new VBox();
        VBox dom2 = new VBox();
        pro.setSpacing(10);

        // Petlja koja korisniku iscrtava i ispisuje sve sto treba da vidi iz baze podataka
        for (int i = 0; i < PolovniFunctions.getAllImgs().size(); i++) {
            //Ispis svih slika iz baze
            Image im = new Image(PolovniFunctions.getAllImgs().get(i).toString());
            ImageView iv = new ImageView(im);
            // Ispisivanje svih naziva automobila
            Label la = new Label(PolovniFunctions.getAllNames().get(i).toString());

            //ispisvanje godista automobila iz baze
            Label la1 = new Label(PolovniFunctions.getAllYears().get(i).toString());

            //ispisivanje svih cena
            Label la2 = new Label(PolovniFunctions.getAllPrices().get(i).toString());

            String rn = PolovniFunctions.getAllNames().get(i).toString();
            String rn2 = PolovniFunctions.getAllUrl().get(i).toString();
            String rn3 = PolovniFunctions.getAllYears().get(i).toString();
            String rn4 = PolovniFunctions.getAllPrices().get(i).toString();

            // Browser preko koga korisnik moze saznati sve informacije o zeljenom automobilu
            final WebView browser = new WebView();
            final javafx.scene.web.WebEngine webEngine = browser.getEngine();
            webEngine.load(PolovniFunctions.getAllUrl().get(i).toString());

            hb.getChildren().addAll(iv);
            hb1.getChildren().addAll(la);
            hb11.getChildren().addAll(la1);
            hb111.getChildren().addAll(la2);
            Button link = new Button("Info");
            Button kupi = new Button("IZNAJMI");
            kupi.setPrefSize(100, 50);
            kupi.setOnAction(e -> {
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                // Upis u fajl informacija o automobilu kada korisnik izabere Iznajmi automobil
                try (FileWriter fw = new FileWriter("ugovori.txt", true);
                        BufferedWriter bw = new BufferedWriter(fw);
                        PrintWriter out = new PrintWriter(bw)) {
                    out.println("-------------------------");
                    out.println(dateFormat.format(date));
                    out.println("Automobil: " + rn);
                    out.println("Godiste: " + rn3);
                    out.println("Cena: " + rn4);
                    out.println("LINK: " + rn2);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "FATAL ERROR: " + ex);
                    // File writing/opening failed at some stage.
                }
                KupiForm.kupiForm(ast);
                primaryStage.close();

            });
            link.setPrefSize(50, 25);
            link.setOnAction(e -> {
                VBox vb = new VBox();
                Stage stg = new Stage();
                Scene sc = new Scene(vb, 800, 600);
                stg.setTitle("Info");
                vb.getChildren().addAll(browser);
                stg.setScene(sc);
                stg.show();

            });
            vbo2.getChildren().addAll(kupi);
            vbo.getChildren().addAll(link);
        }

        gp.add(hb, 0, 0);
        gp.add(hb1, 1, 0);
        gp.add(hb11, 2, 0);
        gp.add(hb111, 3, 0);
        gp.add(vbo, 4, 0);
        gp.add(vbo2, 5, 0);

        gp2.add(pro, 0, 0);
        gp2.add(pro1, 1, 0);
        gp2.add(pro11, 2, 0);
        gp2.add(pro111, 3, 0);

        pro1.setAlignment(Pos.CENTER);
        pro1.setSpacing(100);

        pro11.setAlignment(Pos.CENTER);
        pro11.setSpacing(100);

        pro111.setAlignment(Pos.CENTER);
        pro111.setSpacing(100);

        hb1.setAlignment(Pos.CENTER);
        hb1.setSpacing(100);

        hb11.setAlignment(Pos.CENTER);
        hb11.setSpacing(100);

        hb111.setAlignment(Pos.CENTER);
        hb111.setSpacing(100);

        vbo.setAlignment(Pos.CENTER);
        vbo.setSpacing(92);

        vbo2.setAlignment(Pos.CENTER);
        vbo2.setSpacing(67);

        sp.setStyle(" ");
        hb.setAlignment(Pos.CENTER);
        gp.setAlignment(Pos.CENTER);

        sp.setContent(gp);
        bp.setTop(topPane);

        sp2.setContent(gp2);

        VBox main = new VBox();
        VBox vb = new VBox();
        VBox vb2 = new VBox();

        Button btn = new Button("Iznajmi");
        Button btn2 = new Button("Kupi");
        Button logout = new Button("logout");

        btn.setOnAction(e -> {
            bp.setCenter(sp);

        });

        btn2.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Molimo sacekajte");
            alert.setHeaderText("U toku je obrada informacija (~30 sekundi)");
            alert.setContentText("Ne dirati nista dok se progres bar ne napuni!");
            ProgressBar pb = new ProgressBar(0.1);

            alert.setGraphic(pb);
            alert.show();

            /* Kada korisnik odabere dugme Novi automobili skidaju mu se automobili sa websajta polovniautomobili.com pomocu jsoupa
            // Proces traje oko 40 sekundi, posto server pravi timeout od 2 sekudne posle svakog skinutog linka.
             Ne iskljucivati aplikacju dok se ispisuju linkovi u konzoli, aplikacja radi sve vreme, potom ce prebaciti na taj prozor
             */
            Download don = new Download("https://polovniautomobili.com/");

            //Petlja koja prikazuje sve skinute informacije o automobilu sa sajta polovniautomobili.com preko jsoup biblioteke
            for (int i = 0; i < Download.getSlike().size(); i++) {

                Image im = new Image(Download.getSlike().get(i).toString());
                ImageView iv = new ImageView(im);
                pro.getChildren().add(iv);

                Label la = new Label(Download.getNaslovi().get(i).toString());
                Label la1 = new Label(Download.getGodine().get(i).toString());
                Label la2 = new Label(Download.getCene().get(i).toString());
                
                pb.setProgress(i);

                String rn = Download.getNaslovi().get(i).toString();
                String rn2 = Download.getGodine().get(i).toString();
                String rn3 = Download.getCene().get(i).toString();
                String rn4 = Download.getInfo().get(i).toString();

                final WebView browser = new WebView();
                final javafx.scene.web.WebEngine webEngine = browser.getEngine();
                webEngine.load(Download.getInfo().get(i).toString());
                System.out.println(Download.getInfo().get(i).toString());

                Button kupi2 = new Button("KUPI");
                kupi2.setPrefSize(100, 50);
                kupi2.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                        Date date = new Date();

                        try (FileWriter fw = new FileWriter("ugovori.txt", true);
                                BufferedWriter bw = new BufferedWriter(fw);
                                PrintWriter out = new PrintWriter(bw)) {
                            out.println("-------------------------");
                            out.println(dateFormat.format(date));
                            out.println("Automobil: " + rn);
                            out.println("Godiste: " + rn2);
                            out.println("Cena: " + rn3);
                            out.println("LINK: " + rn4);
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(null, "FATAL ERROR: " + ex);
                            // File writing/opening failed at some stage.
                        }
                        KupiForm.kupiForm(ast);
                        primaryStage.close();
                    }
                });

                Button link2 = new Button("Info");
                link2.setPrefSize(50, 25);
                link2.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        VBox vbb = new VBox();
                        Stage stg2 = new Stage();
                        Scene sc2 = new Scene(vbb, 800, 600);
                        stg2.setTitle("Info");
                        vbb.getChildren().addAll(browser);
                        stg2.setScene(sc2);
                        stg2.show();
                    }
                });

                pro1.getChildren().addAll(la);
                pro11.getChildren().addAll(la1);
                pro111.getChildren().addAll(la2);
                dom1.getChildren().addAll(link2);
                dom2.getChildren().addAll(kupi2);
            }

            bp.setCenter(sp2);
        });

        gp2.add(dom1, 4, 0);
        gp2.add(dom2, 5, 0);

        dom1.setAlignment(Pos.CENTER);
        dom1.setSpacing(92);

        dom2.setAlignment(Pos.CENTER);
        dom2.setSpacing(67);

        logout.setOnAction(e -> {
            Login.loginWindow(primaryStage);
        });

        btn.setPrefSize(100, 50);
        btn2.setPrefSize(100, 50);
        logout.setPrefSize(100, 50);

        main.getChildren().addAll(vb, vb2);

        vb2.setPadding(new Insets(550, 0, 0, 0));
        vb.getChildren().addAll(btn, btn2);
        vb2.getChildren().add(logout);

        bp.setLeft(main);

        Scene scene = new Scene(bp, 800, 800);

        primaryStage.setTitle("Welcome");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        // centriranje prozora na sredinu ekrana
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
        primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);

    }
}
