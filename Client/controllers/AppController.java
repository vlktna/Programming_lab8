package controllers;

import aboutWorker.*;
import collection.WorkerCollection;
import commands.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import communication.ClientApp;
import communication.ServerCommunication;
import userAuthentication.SignIn;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class AppController {

    @FXML
    private Button helpButton;

    @FXML
    private Button infoButton;

    @FXML
    private Button showButton;

    @FXML
    private Button removeByIdButton;

    @FXML
    private Button addButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button clearButton;

    @FXML
    private Button scriptButton;

    @FXML
    private Button exitButton;

    @FXML
    private Button addIfMaxButton;

    @FXML
    private Button removeLowerButton;

    @FXML
    private Button historyButton;

    @FXML
    private Button filterByPositionButton;

    @FXML
    private Button printStartDateButton;

    @FXML
    private TextArea textField;

    @FXML
    private TextField userField;

    @FXML
    private Button ruButton;

    @FXML
    private Button enButton;

    @FXML
    private Button noButton;

    @FXML
    private Button plButton;

    @FXML
    private TableView<WorkerProperty> table;

    @FXML
    private TableColumn<WorkerProperty, Integer> idCol;

    @FXML
    private TableColumn<WorkerProperty, String> nameCol;

    @FXML
    private TableColumn<WorkerProperty, Integer> coordinateXCol;

    @FXML
    private TableColumn<WorkerProperty, Integer> coordinateYCol;

    @FXML
    private TableColumn<WorkerProperty, String> creationDateCol;

    @FXML
    private TableColumn<WorkerProperty, Long> salaryCol;

    @FXML
    private TableColumn<WorkerProperty, String> startDateCol;

    @FXML
    private TableColumn<WorkerProperty, String> endDateCol;

    @FXML
    private TableColumn<WorkerProperty, String> positionCol;

    @FXML
    private TableColumn<WorkerProperty, String> organizationTypeCol;

    @FXML
    private TableColumn<WorkerProperty, Integer> employeeCountCol;

    @FXML
    private TableColumn<WorkerProperty, String> ownerCol;

    @FXML
    private AnchorPane animationPane;


    private ResourceBundle bundle;
    private Locale locale;
    private static String language;

    public String getLanguage(){
        return language;
    }

    private ArrayList<String> historyCommand;
    private ObservableList<WorkerProperty> workers;


    @FXML
    public void initialize() {
        loadLang("en");
        language = "en";
        historyCommand = new ArrayList<>();

        loadTable();
        updateTable();
        NewStage stage = new NewStage();

        AddCommandController addCommand = new AddCommandController();
        IdController idController = new IdController();
        PositionController positionController = new PositionController();

        Animation animation = new Animation(animationPane, textField);
        animation.createAnimation();

        try {
            ClientApp clientApp = new ClientApp(new ServerCommunication());

            helpButton.setOnAction(event -> {
                textField.setText(clientApp.execute(new Help(), language));
                historyCommand.add(bundle.getString("historyCommand"));
            });

            infoButton.setOnAction(event -> {
                textField.setText(clientApp.execute(new Info(), language));
                historyCommand.add(bundle.getString("infoCommand"));
            });

            showButton.setOnAction(event -> {
                textField.setText(clientApp.execute(new Show(), language));
                historyCommand.add(bundle.getString("showCommand"));
            });

            removeByIdButton.setOnAction(event -> {
                stage.loadStage("/FXML/id.fxml");
                int id = idController.getId();
                idController.deleteId();

                if (id != 0) {
                    RemoveById removeById = new RemoveById(id);
                    textField.setText(clientApp.execute(removeById, language));
                    historyCommand.add(bundle.getString("removeByIdCommand"));
                    updateTable();
                    animation.createAnimation();
                }
            });

            addButton.setOnAction(event -> {
                stage.loadStage("/FXML/addCommand.fxml");
                Worker worker = addCommand.getWorker();
                addCommand.deleteWorker();

                if (worker != null) {
                    textField.setText(clientApp.execute(new Add(worker), language));
                    historyCommand.add(bundle.getString("removeByIdCommand"));
                    updateTable();
                    animation.createAnimation();
                }
            });

            updateButton.setOnAction(event -> {
                stage.loadStage("/FXML/id.fxml");
                int id = idController.getId();
                if (id != 0) {
                    stage.loadStage("/FXML/addCommand.fxml");
                    Worker worker = addCommand.getWorker();
                    addCommand.deleteWorker();
                    idController.deleteId();

                    if (worker != null) {
                        Update update = new Update(worker, id);
                        textField.setText(clientApp.execute(update, language));
                        historyCommand.add(bundle.getString("updateCommand"));
                        updateTable();
                        animation.createAnimation();
                    }
                }
            });

            scriptButton.setOnAction(event -> {
                stage.loadStage("/FXML/scriptFile.fxml");
                ScriptFileController scriptFileController = new ScriptFileController();
                ArrayList<AbstractCommand> commands = scriptFileController.getCommands();

                if (commands != null) {
                    textField.setText(clientApp.execute(new ExecuteScript(commands), language));
                    historyCommand.add(bundle.getString("scriptCommand"));
                    updateTable();
                    animation.createAnimation();
                    scriptFileController.clearCommands();
                }
            });

            historyButton.setOnAction(event -> {
                if (this.historyCommand.size() > 10) {
                    for (int i = 0; i < this.historyCommand.size() - 11; i++) {
                        this.historyCommand.remove(i);
                    }
                }
                textField.setText(String.valueOf(historyCommand));
                historyCommand.add(bundle.getString("historyCommand"));
            });

            clearButton.setOnAction(event -> {
                textField.setText(clientApp.execute(new Clear(), language));
                historyCommand.add(bundle.getString("clearCommand"));
                updateTable();
                animation.createAnimation();

            });

            exitButton.setOnAction(event -> {
                Stage thisStage = (Stage) exitButton.getScene().getWindow();
                thisStage.close();
            });

            addIfMaxButton.setOnAction(event -> {
                stage.loadStage("/FXML/addCommand.fxml");
                Worker worker = addCommand.getWorker();
                addCommand.deleteWorker();

                if (worker != null) {
                    AddIfMax addIfMax = new AddIfMax(worker);
                    textField.setText(clientApp.execute(addIfMax, language));
                    historyCommand.add(bundle.getString("addIfMaxCommand"));
                    updateTable();
                    animation.createAnimation();
                }
            });

            removeLowerButton.setOnAction(event -> {
                stage.loadStage("/FXML/id.fxml");
                int id = idController.getId();
                idController.deleteId();

                if (id != 0) {
                    textField.setText(clientApp.execute(new RemoveLower(id), language));
                    historyCommand.add(bundle.getString("removeLowerCommand"));
                    updateTable();
                    animation.createAnimation();

                }
            });

            filterByPositionButton.setOnAction(event -> {
                stage.loadStage("/FXML/position.fxml");
                Position position = positionController.getPosition();
                positionController.deletePosition();

                if (position != null) {
                    textField.setText(clientApp.execute(new FilterByPosition(position), language));
                    historyCommand.add(bundle.getString("filterByPositionCommand"));
                }
            });

            printStartDateButton.setOnAction(event -> {
                textField.setText(clientApp.execute(new PrintFieldDescendingStartDate(), language));
                historyCommand.add(bundle.getString("printStartDateCommand"));
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void btnRU(ActionEvent event){
        loadLang("ru");
        language = "ru";
    }

    @FXML
    private void btnEN(ActionEvent event){
        loadLang("en");
        language = "en";
    }

    @FXML
    private void btnNO(ActionEvent event){
        loadLang("no");
        language = "no";
    }

    @FXML
    private void btnPL(ActionEvent event){
        loadLang("pl");
        language = "pl";
    }

    private void loadLang(String lang){
        locale = new Locale(lang);
        bundle = ResourceBundle.getBundle("app", locale);

        helpButton.setText(bundle.getString("helpCommand"));
        infoButton.setText(bundle.getString("infoCommand"));
        showButton.setText(bundle.getString("showCommand"));
        addButton.setText(bundle.getString("addCommand"));
        updateButton.setText(bundle.getString("updateCommand"));
        clearButton.setText(bundle.getString("clearCommand"));
        removeByIdButton.setText(bundle.getString("removeByIdCommand"));
        scriptButton.setText(bundle.getString("scriptCommand"));
        exitButton.setText(bundle.getString("exitCommand"));
        addIfMaxButton.setText(bundle.getString("addIfMaxCommand"));
        removeLowerButton.setText(bundle.getString("removeLowerCommand"));
        historyButton.setText(bundle.getString("historyCommand"));
        filterByPositionButton.setText(bundle.getString("filterByPositionCommand"));
        printStartDateButton.setText(bundle.getString("printStartDateCommand"));

        userField.setText(bundle.getString("activeUser") + new SignIn().getUser().getUserName());

    }

    private void loadTable(){
        idCol.setCellValueFactory(cellData -> cellData.getValue().getIdProperty().asObject());
        nameCol.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        coordinateXCol.setCellValueFactory(cellData -> cellData.getValue().getCoordinateXProperty().asObject());
        coordinateYCol.setCellValueFactory(cellData -> cellData.getValue().getCoordinateYProperty().asObject());
        creationDateCol.setCellValueFactory(cellData -> cellData.getValue().getCreationDateProperty());
        salaryCol.setCellValueFactory(cellData -> cellData.getValue().getSalaryProperty().asObject());
        startDateCol.setCellValueFactory(cellData -> cellData.getValue().getStartDateProperty());
        endDateCol.setCellValueFactory(cellData -> cellData.getValue().getEndDateProperty());
        positionCol.setCellValueFactory(cellData -> cellData.getValue().getPositionProperty());
        organizationTypeCol.setCellValueFactory(cellData -> cellData.getValue().getOrganizationTypeProperty());
        employeeCountCol.setCellValueFactory(cellData -> cellData.getValue().getEmployeesCountProperty().asObject());
        ownerCol.setCellValueFactory(cellData -> cellData.getValue().getOwnerProperty());
    }

    private void updateTable(){
        WorkerCollection workerCollection = new WorkerCollection();
        this.workers = workerCollection.createObservableList();
        table.setItems(workers);
    }
}

