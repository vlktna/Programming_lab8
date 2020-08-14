package communication;

import commands.AbstractCommand;
import userAuthentication.SignIn;
import userAuthentication.User;

import java.io.IOException;

public class ClientApp{

    private ServerCommunication serverCommunication;


    public ClientApp(ServerCommunication serverCommunication){
        this.serverCommunication = serverCommunication;
    }

    public String execute(AbstractCommand command, String language){
        try {
            User user = new SignIn().getUser();
            user.setLanguage(language);
            serverCommunication.sendUser(user);
            serverCommunication.sendCommand(command);
            return serverCommunication.receiveMessage();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

}
