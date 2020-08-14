package userAuthentication;

import javafx.scene.control.TextField;

import java.util.Map;

public class SignUp extends AuthenticationCommand{

    private TextField errorField;

    public SignUp(TextField errorField){
        this.errorField = errorField;
    }

    @Override
    public void execute(String login, String password){
        UserHandler handler = new UserHandler();
        Map<String, String> users = handler.getUsersFromDB();

        if (users.containsValue(login)){
            errorField.setText("Login is already used");
        }else {
            handler.addUserToDB(login, password);
            errorField.setText("New account created successfully");
        }
    }
}
