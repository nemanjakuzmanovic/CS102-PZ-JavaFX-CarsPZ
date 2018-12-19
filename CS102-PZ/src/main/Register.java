/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javafx.application.Platform;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author ryz
 */
public class Register {
    // registracioni prozor
    public static void registerWindow(Stage primaryStage) {

        BorderPane bp = new BorderPane();

        HBox hb = new HBox();
        VBox vb = new VBox();

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
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

        Label email = new Label("E-mail: ");
        gridPane.add(email, 0, 3);

        TextField tf3 = new TextField();
        gridPane.add(tf3, 1, 3);

        Button btn = new Button("Register now");
        hb.getChildren().add(btn);

        Button btn2 = new Button("Login");
        vb.getChildren().add(btn2);

        hb.setPadding(new Insets(0, 0, 0, 150));
        vb.setPadding(new Insets(0, 0, 80, 180));

        btn.setStyle("-fx-font: 16 arial; -fx-base: #b6e7c9;");
        btn2.setStyle("-fx-font: 14 arial; -fx-base: #b6e2a5;");

        bp.setTop(gridPane);
        bp.setCenter(hb);
        bp.setBottom(vb);

        btn.setOnAction(e -> {
            //Validacija unetih polja korisnika pri registraciji na sistem
            if (tf1.getText().isEmpty() || tf2.getText().isEmpty() || tf3.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Molimo unesite sva polja!");
            } else if (tf1.getText().length() < 5) {
                JOptionPane.showMessageDialog(null, "Username mora imati minimum 5 karaktera");
            } else if (tf2.getText().length() < 5) {
                JOptionPane.showMessageDialog(null, "Password mora imati minimum 5 karaktera");
            } else if (tf3.getText().length() < 10) {
                JOptionPane.showMessageDialog(null, "Proverite email polje!");
            } else {
                if (Functions.checkIfUserExists(tf1.getText())) {
                    JOptionPane.showMessageDialog(null, "Korisnicko ime zauzeto");
                } else if (Functions.checkIfEmailExists(tf3.getText())) {
                    JOptionPane.showMessageDialog(null, "Vec postoji nalog vezan za Email");
                } else {
                    if (Functions.addUser(tf1.getText(), tf2.getText(), tf3.getText())) {
                        Registration.registrationWindow(primaryStage);
                    } else {
                        JOptionPane.showMessageDialog(null, "Fatal error");
                        Platform.exit();
                    }
                }
            }
        });

        btn2.setOnAction(e -> {
            Login.loginWindow(primaryStage);
            primaryStage.setTitle("Login");

        });

        Scene scene = new Scene(bp, 400, 400);

        primaryStage.setTitle("Register");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
        primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);
    }

}
