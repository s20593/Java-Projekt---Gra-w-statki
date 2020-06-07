package Statki;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainStatki extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Trudnosc.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();

        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
