/**
 * @author Veronika Volokitina
 * @version 3
 * @since 3
 *
 * Поток отправки ответа
 */

package threads;

import communication.ClientCommunication;
import userAuthentication.User;
import java.util.logging.Logger;

public class Sender extends Thread{
    Logger logger = Logger.getLogger(Sender.class.getName());

    private String response;
    private ClientCommunication clientCommunication;
    private User user;

    public Sender(ClientCommunication clientCommunication, String response, User user){
        this.clientCommunication = clientCommunication;
        this.response = response;
        this.user = user;
    }

    @Override
    public void run(){
        this.clientCommunication.sendMessage(this.response);
        logger.info("Reply send to user " + this.user.getUserName());
    }
}
