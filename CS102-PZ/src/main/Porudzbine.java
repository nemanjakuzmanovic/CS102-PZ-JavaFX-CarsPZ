/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javax.swing.JOptionPane;

/**
 *
 * @author ryz
 */
public class Porudzbine {
    // Tip Vbox koji vraca vertikalno box koji ubacujemo u panel porudzbine
    public static VBox porudzbine(VBox vbX) throws FileNotFoundException, IOException {
        GridPane gridPaneX = new GridPane();
        gridPaneX.setAlignment(Pos.CENTER);
        gridPaneX.setHgap(10);
        gridPaneX.setVgap(10);
        gridPaneX.setPadding(new Insets(25, 25, 25, 25));

        Label porudzbine = new Label("Sve porudzbine: ");
        Label forma = new Label("Forma (Mozete definisati formu ugovora u fajlu forma.txt koja ce se prikazati ovde)/nije implementirano/");
        // Administrator cita sve zahteve iz fajla ugovori.txt koje posle ukoliko odobri ugovor stampa ukoliko ne, cuvaju se kao log zahteva korisnika
        BufferedReader br = new BufferedReader(new FileReader("ugovori.txt"));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            String everything = sb.toString();
            TextArea ta = new TextArea(everything);
            ta.setPrefSize(600, 250);
            gridPaneX.add(porudzbine, 0, 0);
            gridPaneX.add(ta, 0, 1);

            TextArea ta2 = new TextArea();
            ta2.setPrefSize(600, 250);
            gridPaneX.add(forma, 0, 2);
            gridPaneX.add(ta2, 0, 3);

            Button print = new Button("print");
            gridPaneX.add(print, 0, 4);

            print.setOnAction(e -> {
                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy^HH.mm");
                Date date = new Date();

                // Nazivamo fajl po trenutnom vremenu, kako bi se lakse snasli i radnici i administrator
                try (FileWriter fw = new FileWriter("ugovor/"+dateFormat.format(date)+".txt", true);
                        BufferedWriter bw = new BufferedWriter(fw);
                        PrintWriter out = new PrintWriter(bw)) {
                    out.println(ta2.getText());

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Uspesno!");
                    alert.setHeaderText("Uspesno ste istampali ugovor. Otvorite ga pomocu notepad++ ili nekog boljeg tekst editora.");
                    alert.setContentText("Ugovor se nalazi u folderu UGOVOR. Pod nazivom trenutnog vremena!");

                    alert.showAndWait();

                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "FATAL ERROR: " + ex);
                    // File writing/opening failed at some stage.
                }

            });

        } finally {
            br.close();
        }
        vbX.getChildren().add(gridPaneX);

        return vbX;
    }

}
