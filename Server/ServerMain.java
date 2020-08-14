/**
 * @author Veronika Volokitina
 * @version 2
 * @since 2
 */

import collection.WorkerCollection;
import threads.Receiver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Logger;

public class ServerMain {
    static int port = 8888;

    public static void main(String[] args) {
        Logger logger = Logger.getLogger(ServerMain.class.getName());


        try {
//            int port = Integer.parseInt(args[0]);

            DatagramSocket socket = new DatagramSocket(port);

            WorkerCollection workerCollection = new WorkerCollection();
            workerCollection.getCollectionFromDB();

            ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
            Receiver receiver = new Receiver(workerCollection, socket);
            executor.execute(receiver);

        }catch(NumberFormatException e){
            logger.warning("Wrong port");
        }catch(ArrayIndexOutOfBoundsException | NullPointerException e){
            logger.warning("Port not specified");
        }catch (SocketException e) {
            logger.warning("Error creating socket");
        }catch (IOException e){
            logger.warning("Something went wrong");
        }
    }
}




