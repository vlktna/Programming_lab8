package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.util.Locale;
import java.util.ResourceBundle;

public class IdController {

    @FXML
    private TextField headField;

    @FXML
    private TextField inputField;

    @FXML
    private Button okButton;

    @FXML
    private TextField errorField;

    private ResourceBundle bundle;
    private Locale locale;
    private static Integer id;

    public int getId(){
        return id;
    }

    public void deleteId(){
        id = null;
    }

    @FXML
    public void initialize() {

        errorField.setEditable(false);
        String language =  new AppController().getLanguage();
        loadLang(language);

        okButton.setOnAction(event -> {
            try {
                id = Integer.parseInt(inputField.getText());
                Stage stage = (Stage) okButton.getScene().getWindow();
                stage.close();
            }catch (NumberFormatException e){
                errorField.setText(bundle.getString("enterIdError"));
            }
        });
    }

    private void loadLang(String lang) {
        locale = new Locale(lang);
        bundle = ResourceBundle.getBundle("app", locale);

        headField.setText(bundle.getString("enterIdLabel"));
    }
}
