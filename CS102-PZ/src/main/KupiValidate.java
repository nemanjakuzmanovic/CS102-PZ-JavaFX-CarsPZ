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
import javafx.scene.control.Alert.AlertType;
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

// Validacija korisnika kada pritisne na dugme Iznajmi auto - Mora  uneti username i email da bi potvrdio da je to zaista on
public class KupiValidate {

    public static void kupiValidate(Stage stg) {
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

        Label username = new Label("Username: ");
        gridPane.add(username, 0, 1);

        TextField tf1 = new TextField();
        gridPane.add(tf1, 1, 1);

        Label password = new Label("E-mail: ");
        gridPane.add(password, 0, 2);

        TextField tf2 = new TextField();
        gridPane.add(tf2, 1, 2);

        Button btn = new Button("Validate");
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
            //Validacija da su uneta sva polja
            if (tf1.getText().isEmpty() || tf2.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.initStyle(StageStyle.UTILITY);
                alert.setTitle("Warning");
                alert.setHeaderText("PAZNJA");
                alert.setContentText("MORATE uneti sva polja!");
                alert.showAndWait();
            } else {
                // Korisnik mora uneti validne podatke, proveravaju se iz baze podataka da li zaista postoje
                if (Functions.logInAlt(tf1.getText(), tf2.getText())) {
                    // Kada se izvrsi validacija korisnikovi podatci kao i podatci o kolima koje iznjamljuje se smestaju zajednu u ovaj fajl
                    try (FileWriter fw = new FileWriter("ugovori.txt", true);
                            BufferedWriter bw = new BufferedWriter(fw);
                            PrintWriter out = new PrintWriter(bw)) {
                        out.println("Username: " + tf1.getText());
                        out.println("E-mail: " + tf2.getText());
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "FATAL ERROR: " + ex);
                        // File writing/opening failed at some stage.
                    }
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Uspesno!");
                    alert.setHeaderText(null);
                    alert.setContentText("Vas zahtev je prosledjen administratoru na odobrenje!");

                    alert.showAndWait();
                    UserView.userWindow(stage);
                    stg.close();

                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.initStyle(StageStyle.UTILITY);
                    alert.setTitle("Warning");
                    alert.setHeaderText("PAZNJA");
                    alert.setContentText("Korisnik ne postoji ili ste pogresili sifru");
                    alert.showAndWait();
                }
            }
        });

        Scene sc = new Scene(bp, 800, 600);
        stage.setTitle("Info");
        stage.setScene(sc);
        stage.show();

    }
}
