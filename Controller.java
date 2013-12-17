package fpc;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created with IntelliJ IDEA.
 * User: Fryslan
 * Date: 16-12-13
 * Time: 22:16
 */
public class Controller implements Initializable {

    @FXML ComboBox TreeCombobox;
    @FXML
    Rectangle CloseRectangle;
    @FXML
    Rectangle TakeNestRectangle;
    @FXML
    Label StartLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        StartLabel.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                FryslanPowerChopper.treeName = TreeCombobox.getSelectionModel().getSelectedItem().toString();
                FryslanPowerChopper.loaded = true;
                Stage stage = (Stage) UI.r.getScene().getWindow();
                stage.close();
            }
        });

        CloseRectangle.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                Stage stage = (Stage) UI.r.getScene().getWindow();
                stage.close();
            }
        });
    }
}
