package fpc;/**
 * Created with IntelliJ IDEA.
 * User: Fryslan
 * Date: 16-12-13
 * Time: 21:30
 */

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UI extends Application {

    public static Parent r;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setController(Controller.class);
        Parent root = loader.load(this.getClass().getResource("ui.fxml"));
        System.out.println(loader.getController().toString());
        r= root;

        stage.setScene(new Scene(root));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.show();

    }
}
