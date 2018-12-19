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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

/**
 *
 * @author ryz
 */
public class KupiForm {

    // Forma koja se pojavi kada korisnik pritisne dugme KUPI
    public static void kupiForm(Stage stg) {
        Stage stage = new Stage();

        BorderPane bp = new BorderPane();

        VBox vb2 = new VBox();
        VBox vbE = new VBox();
        VBox vbX = new VBox();
        VBox vbY = new VBox();

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(100, 25, 40, 25));

        Label ime = new Label("Ime: ");
        gridPane.add(ime, 0, 1);

        TextField tf1 = new TextField();
        gridPane.add(tf1, 1, 1);

        Label prezime = new Label("Prezime: ");
        gridPane.add(prezime, 0, 2);

        TextField tf2 = new TextField();
        gridPane.add(tf2, 1, 2);

        Label grad = new Label("Grad: ");
        gridPane.add(grad, 0, 3);

        TextField tf3 = new TextField();
        gridPane.add(tf3, 1, 3);

        Label postanskiKod = new Label("Postanski Kod: ");
        gridPane.add(postanskiKod, 0, 4);

        TextField tf4 = new TextField();
        gridPane.add(tf4, 1, 4);

        Label adresa = new Label("Adresa: ");
        gridPane.add(adresa, 0, 5);

        TextField tf5 = new TextField();
        gridPane.add(tf5, 1, 5);

        Label brojTelefona = new Label("Broj tel. : ");
        gridPane.add(brojTelefona, 0, 6);

        TextField tf6 = new TextField();
        gridPane.add(tf6, 1, 6);

        Label jmbg = new Label("JMBG: ");
        gridPane.add(jmbg, 0, 7);

        TextField tf7 = new TextField();
        gridPane.add(tf7, 1, 7);

        Label brojLK = new Label("Broj LK: ");
        gridPane.add(brojLK, 0, 8);

        TextField tf8 = new TextField();
        gridPane.add(tf8, 1, 8);

        Button btn = new Button("Dalje");
        vbX.getChildren().add(btn);
        vbX.setAlignment(Pos.CENTER);
        vbX.setPadding(new Insets(0, 0, 30, 0));

        Label warning = new Label("Pritiskom na ovo dugme slazete se sa uslovima nase aplikacije. Svaka zloupotreba bice sankcionisana");

        btn.setStyle("-fx-font: 16 arial; -fx-base: #b6e7c9;");

        vbE.getChildren().addAll(vbX, vbY, warning);
        vbE.setAlignment(Pos.CENTER);
        vbE.setPadding(new Insets(-250, 0, 0, 0));

        bp.setTop(vb2);
        bp.setCenter(gridPane);
        bp.setBottom(vbE);

        btn.setOnAction(e -> {
            // Korisnik mora uneti validne podatke u aplikaciju - provera da li su popunjena sva polja
            if (tf1.getText().isEmpty() || tf2.getText().isEmpty() || tf3.getText().isEmpty() || tf4.getText().isEmpty() || tf5.getText().isEmpty() || tf6.getText().isEmpty() || tf7.getText().isEmpty() || tf8.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Warning");
                alert.setHeaderText("PAZNJA");
                alert.setContentText("MORATE uneti sva polja!");

                alert.showAndWait();
            } else {
                // Dodavanje informacija o korisniku u fajl
                try (FileWriter fw = new FileWriter("ugovori.txt", true);
                        BufferedWriter bw = new BufferedWriter(fw);
                        PrintWriter out = new PrintWriter(bw)) {
                    out.println("Ime: " + tf1.getText());
                    out.println("Prezime: " + tf2.getText());
                    out.println("Grad: " + tf3.getText());
                    out.println("Postanski Kod: " + tf4.getText());
                    out.println("Adresa: " + tf5.getText());
                    out.println("Broj tel. : " + tf6.getText());
                    out.println("JMBG: " + tf7.getText());
                    out.println("Broj LK: " + tf8.getText());
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "FATAL ERROR: " + ex);
                    // File writing/opening failed at some stage.
                }
                
                KupiValidate.kupiValidate(stg);
                stg.close();
                stage.close();

            }

        });

        Scene sc = new Scene(bp, 800, 600);
        stage.setTitle("Iznajmi auto");
        stage.setScene(sc);
        stage.show();

    }

}
