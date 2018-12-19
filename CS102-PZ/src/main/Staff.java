/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author ryz
 */
public class Staff {
    // administratorski prozor
    public static void staffWindow(Stage primaryStage) {

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

        Label password = new Label("Password: ");
        gridPane.add(password, 0, 2);

        PasswordField tf2 = new PasswordField();
        gridPane.add(tf2, 1, 2);

        Button btn = new Button("Login now");
        vbX.getChildren().add(btn);
        vbX.setAlignment(Pos.CENTER);
        vbX.setPadding(new Insets(0, 0, 30, 0));

        Button btn2 = new Button("MAIN PAGE");
        vbY.getChildren().add(btn2);
        vbY.setAlignment(Pos.CENTER);

        btn.setStyle("-fx-font: 16 arial; -fx-base: #b6e7c9;");
        btn2.setStyle("-fx-font: 14 arial; -fx-base: #b6e2a5;");

        vbE.getChildren().addAll(vbX, vbY);
        vbE.setAlignment(Pos.CENTER);
        vbE.setPadding(new Insets(-250, 0, 0, 0));

        bp.setTop(vb2);
        bp.setCenter(gridPane);
        bp.setBottom(vbE);

        btn.setOnAction(e -> {
            // Forma za administratora koji se posebno loguje od korisnika
            if (tf1.getText().isEmpty() || tf2.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Molimo unesite sva polja!");
            } else {
                if (AdminFunctions.logIn(tf1.getText(), tf2.getText())) {
                    AdminView.adminWindow(primaryStage);
                } else {
                    JOptionPane.showMessageDialog(null, "Super korisnik ne postoji ili je sifra pogresna");
                }
            }

        });

        btn2.setOnAction(e -> {
            Login.loginWindow(primaryStage);
        });

        Scene scene = new Scene(bp, 400, 400);

        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
        primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);
    }

}
