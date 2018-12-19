/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author ryz
 */
public class CrudCars {

    //metoda VBox tipa koja vraca vertikalni box koji koristimo u userView i adminView
    public static VBox crudCars(VBox vbX) {
        GridPane gridPaneX = new GridPane();
        gridPaneX.setAlignment(Pos.CENTER);
        gridPaneX.setHgap(10);
        gridPaneX.setVgap(10);
        gridPaneX.setPadding(new Insets(25, 25, 25, 25));

        ToggleGroup group = new ToggleGroup();
        RadioButton rb1 = new RadioButton("Upisi");
        RadioButton rb2 = new RadioButton("Ukloni");
        RadioButton rb3 = new RadioButton("Izmeni");
        RadioButton rb4 = new RadioButton("Prikazi");
        rb1.setToggleGroup(group);
        rb2.setToggleGroup(group);
        rb3.setToggleGroup(group);
        rb4.setToggleGroup(group);
        HBox radiob = new HBox(25);
        radiob.getChildren().add(rb1);
        radiob.getChildren().add(rb2);
        radiob.getChildren().add(rb3);
        radiob.getChildren().add(rb4);
        radiob.setAlignment(Pos.CENTER);

        Label imgUrl = new Label("imgUrl: ");
        gridPaneX.add(imgUrl, 0, 1);

        TextField tf1 = new TextField();
        gridPaneX.add(tf1, 1, 1);

        Label naziv = new Label("Naziv: ");
        gridPaneX.add(naziv, 0, 2);

        TextField tf2 = new TextField();
        gridPaneX.add(tf2, 1, 2);

        Label godiste = new Label("Godiste: ");
        gridPaneX.add(godiste, 0, 3);

        TextField tf3 = new TextField();
        gridPaneX.add(tf3, 1, 3);

        Label cena = new Label("cena: ");
        gridPaneX.add(cena, 0, 4);

        TextField tf4 = new TextField();
        gridPaneX.add(tf4, 1, 4);

        Label url = new Label("url: ");
        gridPaneX.add(url, 0, 5);

        TextField tf5 = new TextField();
        gridPaneX.add(tf5, 1, 5);

        Label id = new Label("ID: ");
        gridPaneX.add(id, 0, 6);

        TextField tf6 = new TextField();
        gridPaneX.add(tf6, 1, 6);

        rb1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (rb1.isSelected()) {
                    tf1.setDisable(false);
                    tf2.setDisable(false);
                    tf3.setDisable(false);
                    tf4.setDisable(false);
                    tf5.setDisable(false);
                    tf6.setDisable(true);
                }
            }
        });

        rb2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (rb2.isSelected()) {
                    tf1.setDisable(true);
                    tf2.setDisable(true);
                    tf3.setDisable(true);
                    tf4.setDisable(true);
                    tf5.setDisable(true);
                    tf6.setDisable(false);
                }
            }
        });

        rb3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (rb3.isSelected()) {
                    tf1.setDisable(false);
                    tf2.setDisable(false);
                    tf3.setDisable(false);
                    tf4.setDisable(false);
                    tf5.setDisable(false);
                    tf6.setDisable(false);
                }
            }
        });

        rb4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (rb4.isSelected()) {

                    tf1.setDisable(true);
                    tf2.setDisable(true);
                    tf3.setDisable(true);
                    tf4.setDisable(true);
                    tf5.setDisable(true);
                    tf6.setDisable(true);
                }
            }
        });

        Button btnX = new Button("Send");
        gridPaneX.add(btnX, 0, 7);

        vbX.getChildren().add(radiob);
        vbX.getChildren().add(gridPaneX);

        btnX.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event
            ) {
                if (rb1.isSelected()) {
                    String imgUrl = tf1.getText();
                    String naziv = tf2.getText();
                    int cena = Integer.parseInt(tf3.getText());
                    int godiste = Integer.parseInt(tf4.getText());
                    String url = tf5.getText();
                    //Kreiranje objekta PolovniAutomobil - zapravo kreira automobil
                    PolovniFunctions.create(new PolovniAutomobili(imgUrl, naziv, cena, godiste, url));
                } else if (rb2.isSelected()) {
                    int iD = Integer.parseInt(tf6.getText());
                    //Brisanje automobila iz baze
                    PolovniFunctions.delete(iD);
                } else if (rb3.isSelected()) {
                    String imgUrl = tf1.getText();
                    String naziv = tf2.getText();
                    int cena = Integer.parseInt(tf3.getText());
                    int godiste = Integer.parseInt(tf4.getText());
                    String url = tf5.getText();
                    int iD = Integer.parseInt(tf6.getText());
                    //azuriranje automobila iz baze
                    PolovniFunctions.update(iD, new PolovniAutomobili(imgUrl, naziv, cena, godiste, url));
                } else if (rb4.isSelected()) {
                    Stage st = new Stage();
                    FlowPane fp = new FlowPane();
                    TextArea lebe = new TextArea(PolovniFunctions.readAll().toString().replaceAll("\\{", "\n").replaceAll("\\}", "\n"));
                    lebe.setPrefSize(1000, 800);
                    fp.getChildren().add(lebe);
                    Scene scene23 = new Scene(fp, 1000, 800);
                    st.setTitle("Users");
                    st.setScene(scene23);
                    st.show();
                }
            }
        }
        );
        return vbX;
    }

}
