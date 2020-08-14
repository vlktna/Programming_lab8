/**
 * @author Veronika Volokitina
 * @version 3
 * @since 3
 *
 * Поток обработки полученных запросов
 */

package threads;

import collection.WorkerCollection;
import commands.AbstractCommand;
import communication.ClientCommunication;
import userAuthentication.User;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Logger;

public class Handler extends Thread{
    Logger logger = Logger.getLogger(Handler.class.getName());

    private ClientCommunication clientCommunication;
    private AbstractCommand command;
    private WorkerCollection workerCollection;
    private User user;

    public Handler(ClientCommunication clientCommunication, AbstractCommand command, WorkerCollection workerCollection, User user){
        this.clientCommunication = clientCommunication;
        this.command = command;
        this.workerCollection = workerCollection;
        this.user = user;
    }

    @Override
    public void run(){
        try {

            String data = this.command.execute(this.workerCollection, this.user);
            ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
            Sender sender = new Sender(this.clientCommunication, data, this.user);
            executor.execute(sender);

        } catch (IOException e) {
            logger.warning("Произошла ошибка при выполнении команды");
        }
    }
}
