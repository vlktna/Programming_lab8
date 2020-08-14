/**
 * @author Veronika Volokitina
 * @version 3
 * @since 2
 * Класс для общения с сервером
 */
package communication;


import commands.AbstractCommand;
import userAuthentication.User;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ServerCommunication {

    private static InetAddress hostIP;
    private static int port;
    private static DatagramSocket socket;


    public ServerCommunication() throws IOException {
        socket = new DatagramSocket();
        socket.setSoTimeout(1000);
    }

    /**
     * @param command - команда, которая отправляется серверу
     * @throws IOException
     */
    public void sendCommand(AbstractCommand command) throws IOException {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(outputStream);
        os.writeObject(command);

        byte[] data = outputStream.toByteArray();
        DatagramPacket sendPacket = new DatagramPacket(data, data.length, hostIP, port);
        socket.send(sendPacket);

        outputStream.close();
        os.close();

    }

    /**
     * @param user - клиент
     * @throws IOException
     */
    public void sendUser(User user) throws IOException {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(outputStream);
        os.writeObject(user);

        byte[] data = outputStream.toByteArray();
        DatagramPacket sendPacket = new DatagramPacket(data, data.length, hostIP, port);
        socket.send(sendPacket);

        outputStream.close();
        os.close();
    }

    /**
     * @throws IOException
     */
    public String receiveMessage() throws IOException {
        byte[] incomingData = new byte[4096];
        DatagramPacket incomingPacket = new DatagramPacket(incomingData, incomingData.length);
        this.socket.receive(incomingPacket);
        return new String(incomingPacket.getData());
    }

    public User receiveUser() {
        try {
            byte[] incomingData = new byte[1024];
            DatagramPacket incomingPacket = new DatagramPacket(incomingData, incomingData.length);

            byte[] data = incomingPacket.getData();
            ByteArrayInputStream in = new ByteArrayInputStream(data);
            ObjectInputStream is = new ObjectInputStream(in);
            User user = (User) is.readObject();

            in.close();
            is.close();
            return user;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setHostIP(String host) throws UnknownHostException {
        hostIP = InetAddress.getByName(host);
    }

    public void setPort(String port){
        ServerCommunication.port = Integer.parseInt(port);
    }
}

