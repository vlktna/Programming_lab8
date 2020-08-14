/**
 * @author Veronika Volokitina
 * @version 3
 * @since 3
 *
 * Поток чтения полученных запросов
 */

package threads;

import collection.WorkerCollection;
import commands.AbstractCommand;
import communication.ClientCommunication;
import userAuthentication.User;
import java.net.DatagramSocket;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Logger;

public class Receiver extends Thread {
    Logger logger = Logger.getLogger(Receiver.class.getName());
    private WorkerCollection workerCollection;
    private DatagramSocket socket;

    public Receiver(WorkerCollection workerCollection, DatagramSocket socket) {
        this.workerCollection = workerCollection;
        this.socket = socket;
    }

    @Override
    public void run() {
        while (true) {
            ClientCommunication clientCommunication = new ClientCommunication(socket);
            User user = clientCommunication.receiveUser();
            AbstractCommand command = clientCommunication.receiveCommand();

            logger.info("Receive command " + command + " from user " + user.getUserName());

            ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
            Handler handler = new Handler(clientCommunication, command, this.workerCollection, user);
            executor.execute(handler);
        }
    }
}


