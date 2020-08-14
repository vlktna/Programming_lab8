package controllers;

import aboutWorker.*;
import exceptions.DateException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import communication.ClientApp;
import communication.ServerCommunication;
import userAuthentication.SignIn;

import java.io.IOException;
import java.sql.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class AddCommandController {

    @FXML
    private TextField coordinatesXField;

    @FXML
    private TextField salaryField;

    @FXML
    private TextField coordinatesYField;

    @FXML
    private TextField employeesCountField;

    @FXML
    private Button addButton;

    @FXML
    private TextField nameField;

    @FXML
    private TextField errorField;

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

    @FXML
    private MenuButton organizationTypeMenu;

    @FXML
    private MenuItem commercialButton;

    @FXML
    private MenuItem privateLimComButton;

    @FXML
    private MenuItem openJointStockComButton;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private DatePicker endDatePicker;

    private ResourceBundle bundle;
    private Locale locale;
    private Position position;
    private OrganizationType organizationType;
    private static Worker worker;

    public Worker getWorker(){
        return worker;
    }
    public void deleteWorker(){
        worker = null;
    }

    @FXML
    public void initialize() {
        errorField.setEditable(false);
        String language =  new AppController().getLanguage();
        loadLang(language);

        addButton.setOnAction(event -> {
            try {
                String name = nameField.getText();
                Integer coordinatesX = Integer.parseInt(coordinatesXField.getText());
                Integer coordinatesY = Integer.parseInt(coordinatesYField.getText());
                Long salary = Long.parseLong(salaryField.getText());
                Date startDate = Date.valueOf(startDatePicker.getValue());
                Integer employeesCount = Integer.valueOf(employeesCountField.getText());

                Date endDate;
                if (endDatePicker == null) {
                    endDate = null;
                } else {
                    endDate = Date.valueOf(endDatePicker.getValue());
                    if (startDate.compareTo(endDate) > 0) throw new DateException();
                }

                worker = new Worker(name, new Coordinates(coordinatesX, coordinatesY), salary, startDate, endDate,
                        this.position, new Organization(employeesCount, this.organizationType), new SignIn().getUser().getUserName());

                Stage stage = (Stage) addButton.getScene().getWindow();
                stage.close();

            } catch (NumberFormatException e) {
                errorField.setText(bundle.getString("addError"));
            } catch (DateException e) {
                errorField.setText(bundle.getString("dateError"));
            }
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

    public void btnCommercial(ActionEvent event){
        organizationTypeMenu.setStyle("-fx-background-color: #2a3950; -fx-border-color: #00e0c6; -fx-border-radius: 15; -fx-text-fill: #ffffff");
        organizationTypeMenu.setText(bundle.getString("commercialLabel"));
        organizationType = OrganizationType.COMMERCIAL;
    }

    public void btnPrivateLimCom(ActionEvent event){
        organizationTypeMenu.setStyle("-fx-background-color: #2a3950; -fx-border-color: #00e0c6; -fx-border-radius: 15; -fx-text-fill: #ffffff");
        organizationTypeMenu.setText(bundle.getString("privateLimComLabel"));
        organizationType = OrganizationType.PRIVATE_LIMITED_COMPANY;
    }

    public void btnOpenJointStockCom(ActionEvent event){
        organizationTypeMenu.setStyle("-fx-background-color: #2a3950; -fx-border-color: #00e0c6; -fx-border-radius: 15; -fx-text-fill: #ffffff");
        organizationTypeMenu.setText(bundle.getString("openJointStockComLabel"));
        organizationType = OrganizationType.OPEN_JOINT_STOCK_COMPANY;
    }

    private void loadLang(String lang) {
        locale = new Locale(lang);
        bundle = ResourceBundle.getBundle("app", locale);

        addButton.setText("OK");
        nameField.setPromptText(bundle.getString("nameLabel"));
        salaryField.setPromptText(bundle.getString("salaryLabel"));
        coordinatesXField.setPromptText(bundle.getString("coordinatesXLabel"));
        coordinatesYField.setPromptText(bundle.getString("coordinatesYLabel"));
        startDatePicker.setPromptText(bundle.getString("startDateLabel"));
        endDatePicker.setPromptText(bundle.getString("endDateLabel"));
        positionMenu.setText(bundle.getString("positionLabel"));
        organizationTypeMenu.setText(bundle.getString("organizationTypeLabel"));
        employeesCountField.setPromptText(bundle.getString("employeesCountLabel"));

        laborerButton.setText(bundle.getString("laborerLabel"));
        engineerButton.setText(bundle.getString("engineerLabel"));
        headOfDepartmentButton.setText(bundle.getString("headOfDepLabel"));
        developerButton.setText(bundle.getString("developerLabel"));
        bakerButton.setText(bundle.getString("bakerLabel"));

        commercialButton.setText(bundle.getString("commercialLabel"));
        privateLimComButton.setText(bundle.getString("privateLimComLabel"));
        openJointStockComButton.setText(bundle.getString("openJointStockComLabel"));
    }
}