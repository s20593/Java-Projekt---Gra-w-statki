package Statki;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class StatkiController{

    @FXML
    private ComboBox poziom;

    @FXML
    private Button submit;

    @FXML
    void poziomSubmit(MouseEvent event) throws IOException {
        String poz = String.valueOf(poziom.getValue());

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Plansza.fxml"));
        Parent root = loader.load();
        StatkiController2 scene2Controller = loader.getController();
        scene2Controller.setPoziom(poz);

        Scene scene = submit.getScene();
        scene.setRoot(root);
    }

}
