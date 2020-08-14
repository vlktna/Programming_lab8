import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import communication.ServerCommunication;

import java.net.UnknownHostException;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{

        try {
//            Parameters params = getParameters();
//            List<String> parameters = params.getRaw();

//            if (parameters.size() != 2) throw new NoArgumentException();

            ServerCommunication serverCommunication = new ServerCommunication();
//
//            communication.setHostIP(parameters.get(0));
//            communication.setPort(parameters.get(1));
//
            serverCommunication.setHostIP("localhost");
            serverCommunication.setPort("8888");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/authorization.fxml"));
            Parent root = loader.load();
            primaryStage.setTitle("Client");
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.show();

//        }catch (NoArgumentException e){
//            System.out.println("Start over and enter <host> <port>");
//            System.exit(1);
        }catch (NumberFormatException e){
            System.out.println("Port is a number");
            System.exit(1);
        }catch (UnknownHostException e){
            System.out.println("Host not found");
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
