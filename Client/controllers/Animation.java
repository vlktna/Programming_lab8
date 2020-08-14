package controllers;

import aboutWorker.Worker;
import collection.WorkerCollection;
import javafx.animation.PathTransition;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import java.util.*;

public class Animation{

    private final AnchorPane animationPane;
    private final TextArea textField;
    private final Map<String, String> userColor = new HashMap<String, String>();
    private final ArrayList<Button> buttonList =  new ArrayList<Button>();
    private LinkedList<Worker> workers;
    private final WorkerCollection workerCollection = new WorkerCollection();

    public Animation(AnchorPane animationPane, TextArea textField){
        this.animationPane = animationPane;
        this.textField = textField;
    }


    public void createAnimation(){
        workerCollection.getCollectionFromDB();
        deleteAnimation();
        this.workers = workerCollection.getCollection();
        for (Worker worker: workers){
            createCircle(worker);
        }
    }

    private void deleteAnimation(){
        for(Button button: buttonList){
            this.animationPane.getChildren().remove(button);
        }
    }

    private void createCircle(Worker worker){

        Button button = new Button();
        String color = null;

        if (this.userColor.containsKey(worker.getOwner())){
            color = userColor.get(worker.getOwner());
        }else{
            color = createColor();
            userColor.put(worker.getOwner(), color);
        }

        button.setStyle("-fx-border-radius: " + 40 + "; -fx-background-radius: " + 40 + ";-fx-background-color: "
                + color + "; -fx-pref-height: " + 40 + "; -fx-pref-width: " + 40);

        button.setLayoutX(Math.random()*200 + 100);
        button.setLayoutY(Math.random()*150 + 100);
        button.setText(String.valueOf(worker.getId()));

        button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                textField.setText(worker.toString());
            }
        });

        animationPane.getChildren().add(button);

        PathTransition transition = new PathTransition();
        transition.setDuration(Duration.seconds(Math.random()*10 + 2));
        transition.setNode(button);
        Circle circle = new Circle((int) (Math.random()*100));
        transition.setPath(circle);
        transition.setAutoReverse(true);
        transition.setCycleCount(TranslateTransition.INDEFINITE);

        transition.play();

        buttonList.add(button);
    }

    private String createColor(){
        String green = Integer.toHexString((int) (Math.random()*105 + 150)).toUpperCase();
        String blue = Integer.toHexString((int) (Math.random()*105 + 150)).toUpperCase();
        String color = "#00" + green + blue;
        return color;
    }

}
