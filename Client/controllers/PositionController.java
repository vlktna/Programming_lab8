package controllers;

import aboutWorker.Position;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

public class PositionController {

    @FXML
    private TextField headField;

    @FXML
    private Button okButton;

    @FXML
    private MenuButton positionMenu;

    @FXML
    private MenuItem laborerButton;

    @FXML
    private MenuItem engineerButton;

    @FXML
    private MenuItem headOfDepartmentButton;

    @FXML
    private MenuItem developerButton;

    @FXML
    private MenuItem bakerButton;

    private ResourceBundle bundle;
    private Locale locale;

    private static Position position;

    public Position getPosition(){
        return position;
    }

    public void deletePosition(){
        position = null;
    }

    @FXML
    public void initialize() {
        String language =  new AppController().getLanguage();
        loadLang(language);

        okButton.setOnAction(event -> {
            Stage stage = (Stage) okButton.getScene().getWindow();
            stage.close();
        });
    }

    public void btnLaborer(ActionEvent event){
        positionMenu.setStyle("-fx-background-color: #2a3950; -fx-border-color: #00e0c6; -fx-border-radius: 15; -fx-text-fill: #ffffff");
        positionMenu.setText(bundle.getString("laborerLabel"));
        position = Position.LABORER;
    }

    public void btnEngineer(ActionEvent event){
        positionMenu.setStyle("-fx-background-color: #2a3950; -fx-border-color: #00e0c6; -fx-border-radius: 15; -fx-text-fill: #ffffff");
        positionMenu.setText(bundle.getString("engineerLabel"));
        position = Position.ENGINEER;
    }

    public void btnHeadOfDep(ActionEvent event){
        positionMenu.setStyle("-fx-background-color: #2a3950; -fx-border-color: #00e0c6; -fx-border-radius: 15; -fx-text-fill: #ffffff");
        positionMenu.setText(bundle.getString("headOfDepLabel"));
        position = Position.HEAD_OF_DEPARTMENT;
    }

    public void btnDeveloper(ActionEvent event){
        positionMenu.setStyle("-fx-background-color: #2a3950; -fx-border-color: #00e0c6; -fx-border-radius: 15; -fx-text-fill: #ffffff");
        positionMenu.setText(bundle.getString("developerLabel"));
        position = Position.DEVELOPER;
    }

    public void btnBaker(ActionEvent event){
        positionMenu.setStyle("-fx-background-color: #2a3950; -fx-border-color: #00e0c6; -fx-border-radius: 15; -fx-text-fill: #ffffff");
        positionMenu.setText(bundle.getString("bakerLabel"));
        position = Position.BAKER;
    }

    private void loadLang(String lang) {
        locale = new Locale(lang);
        bundle = ResourceBundle.getBundle("app", locale);

        headField.setText(bundle.getString("enterPositionLabel"));

        laborerButton.setText(bundle.getString("laborerLabel"));
        engineerButton.setText(bundle.getString("engineerLabel"));
        headOfDepartmentButton.setText(bundle.getString("headOfDepLabel"));
        developerButton.setText(bundle.getString("developerLabel"));
        bakerButton.setText(bundle.getString("bakerLabel"));

    }
}
