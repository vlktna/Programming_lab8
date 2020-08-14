package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import userAuthentication.AuthenticationCommand;
import userAuthentication.SignIn;
import userAuthentication.SignUp;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AuthorizationController {

    @FXML
    private Button signInButton;

    @FXML
    private Button singUpButton;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField errorField;

    @FXML
    public void initialize(){
        errorField.setEditable(false);

        signInButton.setOnAction(event -> {
            fieldsCheck(new SignIn(signInButton, errorField));
        });

        singUpButton.setOnAction(event -> {
            fieldsCheck(new SignUp(errorField));
        });
    }

    private void fieldsCheck(AuthenticationCommand command){
        String login = loginField.getText().trim();
        String password = passwordField.getText().trim();

        if (login.equals("") & password.equals("")){
            errorField.setText("Login and password not entered");

        }else if (login.equals("")){
            errorField.setText("Login not entered");

        }else if (password.equals("")){
            errorField.setText("Password not entered");

        }else{
            command.execute(login, getHash(password));
        }
    }


    private static String getHash(String password){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] messageDigest = md.digest(password.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }catch (NoSuchAlgorithmException e){
            return "Ошибка при хешировании пароля";
        }
    }
}
