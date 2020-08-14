/**
 * @author Veronika Volokitina
 * @version 3
 * @since 2
 *
 * Класс для общения с клиентом
 */

package communication;

import commands.AbstractCommand;
import userAuthentication.User;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.logging.Logger;

public class ClientCommunication {
    static Logger logger = Logger.getLogger(ClientCommunication.class.getName());

    private DatagramSocket socket;
    private int port;
    private InetAddress clientAddress;
    private AbstractCommand command;

    public ClientCommunication(DatagramSocket socket){
        this.socket = socket;
    }

    /**
     * @return команда, которую отправил клиент
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public AbstractCommand receiveCommand(){
        try {
            byte[] incomingData = new byte[4096];
            DatagramPacket incomingPacket = new DatagramPacket(incomingData, incomingData.length);
            this.socket.receive(incomingPacket);
            this.clientAddress = incomingPacket.getAddress();
            this.port = incomingPacket.getPort();

            byte[] data = incomingPacket.getData();
            ByteArrayInputStream in = new ByteArrayInputStream(data);
            ObjectInputStream is = new ObjectInputStream(in);
            AbstractCommand command = (AbstractCommand) is.readObject();
            in.close();
            is.close();
            this.command = command;
            return command;

        } catch (IOException | ClassNotFoundException e) {
            logger.warning("Ошибка при получении команды от пользователя");
            return null;
        }
    }

    /**
     * @return Клиент
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public User receiveUser() {

        try {
            byte[] incomingData = new byte[4096];
            DatagramPacket incomingPacket = new DatagramPacket(incomingData, incomingData.length);
            this.socket.receive(incomingPacket);
            this.clientAddress = incomingPacket.getAddress();
            this.port = incomingPacket.getPort();

            byte[] data = incomingPacket.getData();
            ByteArrayInputStream in = new ByteArrayInputStream(data);
            ObjectInputStream is = new ObjectInputStream(in);
            User user = (User) is.readObject();
            in.close();
            is.close();
            return user;
        } catch (IOException | ClassNotFoundException e) {
            logger.warning("\n" + "Error getting data from user");
            return null;
        }
    }

    /**
     * @param msg - сообщение, которое отправляется клиенту
     * @throws IOException
     */
    public void sendMessage(String msg) {
        try {
            byte[] replyByte = msg.getBytes();
            DatagramPacket replyPacket = new DatagramPacket(replyByte, replyByte.length, this.clientAddress, this.port);
            this.socket.send(replyPacket);
        }catch (IOException e){
            logger.warning("\n" + "Error sending response to user");
        }
    }

}

