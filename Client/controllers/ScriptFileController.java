package controllers;

import aboutWorker.Position;
import aboutWorker.Worker;
import commands.*;
import communication.FileRead;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class ScriptFileController {

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
    private static ArrayList<AbstractCommand> commands;

    public ArrayList<AbstractCommand> getCommands(){
        return commands;
    }
    public void clearCommands(){
        commands.clear();
    }

    @FXML
    public void initialize() {

        errorField.setEditable(false);
        String language =  new AppController().getLanguage();
        loadLang(language);
        FileRead fileRead = new FileRead();
        commands = new ArrayList<>();

        okButton.setOnAction(event -> {
            try {
                String[] file = fileRead.getFile(inputField.getText().trim().toLowerCase()).split("\n");

                for (int i = 0; i < file.length; i++) {
                    commands.add(createObject(file[i]));
                }

                Stage stage = (Stage) okButton.getScene().getWindow();
                stage.close();
            }catch (FileNotFoundException e){
                errorField.setText(bundle.getString("fileNotFound"));
            } catch (IOException e) {
                e.printStackTrace();
            }catch (NullPointerException e){
                errorField.setText(bundle.getString("fileEmpty"));
            }
        });
    }

    private void loadLang(String lang) {
        locale = new Locale(lang);
        bundle = ResourceBundle.getBundle("app", locale);

        headField.setText(bundle.getString("enterFileName"));
    }

    private AbstractCommand createObject(String command){
        NewStage stage = new NewStage();
        AddCommandController addCommandController = new AddCommandController();
        IdController idController = new IdController();
        PositionController positionController =  new PositionController();

        switch (command){
            case "help":
                return new Help();

            case "info":
                return new Info();

            case "show":
                return new Show();

            case "add":
                stage.loadStage("/FXML/addCommand.fxml");
                Worker worker1 = addCommandController.getWorker();
                if (worker1 != null) {
                    addCommandController.deleteWorker();
                    return new Add(worker1);

                }else return new ErrorCommand("");


            case "update":
                stage.loadStage("/FXML/id.fxml");
                Integer id1 = idController.getId();
                if (id1 != null) {
                    stage.loadStage("/FXML/addCommand.fxml");
                    Worker worker2 = addCommandController.getWorker();
                    if (worker2 != null) {
                        idController.deleteId();
                        addCommandController.deleteWorker();
                        return new Update(worker2, id1);
                    }else return new ErrorCommand("");

                }else return new ErrorCommand("");


            case "remove_by_id":
                stage.loadStage("/FXML/id.fxml");
                Integer id2 = idController.getId();
                if (id2 != null) {
                    idController.deleteId();
                    return new RemoveById(id2);
                }else return new ErrorCommand("");

            case "clear":
                return new Clear();

            case "execute script":
                stage.loadStage("/FXML/scriptFile.fxml");
                ArrayList<AbstractCommand> commands = new ScriptFileController().getCommands();
                if (commands != null) {
                    return new ExecuteScript(commands);
                }else return new ErrorCommand("");

            case "add_if_max":
                stage.loadStage("/FXML/addCommand.fxml");
                Worker worker2 = addCommandController.getWorker();
                if (worker2 != null) {
                    addCommandController.deleteWorker();
                    return new AddIfMax(worker2);
                }else return new ErrorCommand("");

            case "remove_lower":
                stage.loadStage("/FXML/id.fxml");
                Integer id3 = idController.getId();
                if (id3 != 0) {
                    idController.deleteId();
                    return new RemoveLower(id3);
                }else return new ErrorCommand("");

            case "filter_by_position":
                stage.loadStage("/FXML/position.fxml");
                Position position = positionController.getPosition();
                if (position != null) {
                    positionController.deletePosition();
                    return new FilterByPosition(position);
                }else return new ErrorCommand("");

            case "print_field_descending_start_date":
                return new PrintFieldDescendingStartDate();

            default:
                return new ErrorCommand("Command not found");
        }
    }

}
