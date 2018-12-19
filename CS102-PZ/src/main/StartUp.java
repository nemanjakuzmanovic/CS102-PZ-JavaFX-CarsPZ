/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.File;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author ryz
 */
public class StartUp extends Application {
    // Main aplikacije iz koga se pokrece program
    @Override
    public void start(Stage primaryStage) {

        BorderPane bp = new BorderPane();

        Button btn1 = new Button();
        Button btn2 = new Button();

        ImageView iv = new ImageView();

        Image img = new Image("img/welcome.jpg");

        iv.setImage(img);

        btn1.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
        btn2.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");

        btn1.setText("Register");
        btn2.setText("Login");

        VBox vb = new VBox();
        VBox vb1 = new VBox();
        bp.setCenter(vb);
        bp.setTop(vb1);
        vb1.getChildren().add(iv);
        vb.getChildren().add(btn1);
        vb.getChildren().add(btn2);
        vb.setSpacing(30);
        vb.setPadding(new Insets(20, 0, 0, 120));
        vb1.setPadding(new Insets(20, 0, 0, 85));
        btn1.setPrefWidth(150);
        btn2.setPrefWidth(150);

        btn1.setOnAction(e -> {
            Register.registerWindow(primaryStage);
        });

        btn2.setOnAction(e -> {
            Login.loginWindow(primaryStage);
        });

        Scene scene = new Scene(bp, 400, 400);

        primaryStage.setTitle("Welcome!");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(false);
        primaryStage.setResizable(false);
        primaryStage.show();

        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
        primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
