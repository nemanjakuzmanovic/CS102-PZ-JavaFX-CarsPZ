/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author ryz
 */
public class Registration {
    // Prebacivanje korisnika ka login prozoru nakon uspesne registracije
    public static void registrationWindow(Stage primaryStage) {

        BorderPane bp = new BorderPane();

        Label tf = new Label("Uspesna registracija!");
        tf.setAlignment(Pos.CENTER);

        Button btn = new Button("Login");

        bp.setTop(tf);
        bp.setCenter(btn);

        btn.setOnAction(e -> {
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
