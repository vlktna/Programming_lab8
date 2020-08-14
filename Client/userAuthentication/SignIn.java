package userAuthentication;

import controllers.NewStage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.util.Map;

public class SignIn extends AuthenticationCommand{

    private Button signInButton;
    private TextField errorField;
    private static User user;

    public SignIn(Button signInButton, TextField errorField){
        this.signInButton = signInButton;
        this.errorField = errorField;
    }

    public SignIn(){
    }

    public User getUser(){
        return user;
    }

    @Override
    public void execute(String login, String password){
        UserHandler handler = new UserHandler();
        Map<String, String> users = handler.getUsersFromDB();

        if (users.containsValue(login)){
            if (users.containsKey(password)){
                user = new User(login);
                this.signInButton.getScene().getWindow().hide();
                NewStage stage = new NewStage();
                stage.loadStage("/FXML/app.fxml");

            }else{
                errorField.setText("Wrong password");
            }
        }else{
            errorField.setText("Wrong login");
        }
    }
}
