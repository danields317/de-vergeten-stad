package View;

import Controller.Bord_Controllers.LoadBord_Controller;
import Controller.Player_Controllers.Player_controller;
import Model.data.StaticData;
import firebase.FirebaseService;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import observers.LoadBordObservable;
import observers.LoadBordObserver;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.TimeUnit;


public class LoadBordView implements LoadBordObserver {

    String kaart = "Homescreenempty.png";
    //String file = "C:\\Users\\mjboere\\workspace\\Hello FX World\\src\\wereldkaart.jpg";
    private double width = 1600;
    private double height = 900;
    private double windowAnchorX = 50;
    private double windowAnchorY= 50;

    Stage primaryStage;
    LoadBord_Controller loadBordController;
    String roomId;

    public LoadBordView(Stage s, String roomId){
        primaryStage = s;
        this.roomId = roomId;
        //loadPrimaryStageWithGridPane(createInitialGridPane());
        loadPrimaryStageWithGridPane(createInitialGridPane());
        loadBordController = loadBordController.getInstance();

        // PASS IT TO THE CONTROLLER WHO WILL PASS IT TO THE MODEL
        loadBordController.registerObserver( this);

        loadBordController.updateView();
    }

    private void loadPrimaryStageWithGridPane(GridPane gp) {
        try {


            GridPane root = gp;
            Image backgroundImage = new Image("background.png");
            Canvas canvas = new Canvas(width, height);

            Group group = new Group(canvas, root);
            Scene scene = new Scene(group);
            primaryStage.setScene(scene);
            primaryStage.setTitle("WELCOME TO THE GAME");
            primaryStage.setX(windowAnchorX);
            primaryStage.setY(windowAnchorY);
            primaryStage.show();
            GraphicsContext gc = canvas.getGraphicsContext2D();
            gc.drawImage(backgroundImage, 0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
            /*Bord_Controller b = new Bord_Controller(root);*/
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private GridPane createUpdatedGridPane(LoadBordObservable sb){
        FirebaseService firebaseService = FirebaseService.getInstance();
        Object roomInfo = firebaseService.getSpel(roomId).getData();
        (StaticData.getInstance()).setRoomInfo(roomInfo);
        Object classes = ((Map) roomInfo).get("Selectable_classes");
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(400, 200);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);

        int count = 0;
        for(int i = 0; i < ((Map) classes).size(); i++){

            Object killMe = ((Map) classes).get(Integer.toString(i));
            final String tempString = ( ((Map) killMe).get("name")).toString();
            ImageView image = createImageView(tempString + ".png");
            image.setOnMouseClicked(e -> {
                Player_controller.getInstance(true, tempString);
                new BordView(primaryStage);
            });
            gridPane.add(image, count, 0);
            count++;
        }

        return gridPane;
    }

    public void myEvent(ImageView image){


    }



    public ImageView createImageView(String name){
       return new ImageView(new Image(name));
    }

    private GridPane createInitialGridPane(){

        GridPane gridPane = new GridPane();
        gridPane.setMinSize(400, 200);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);


        ImageView image = createImageView("giphy2.gif");
        gridPane.add(image, 0, 0);


        //gridPane.add(image, 0, 0);
        //gridPane.add(scoreText, 1, 0);


        return gridPane;
    }






    @Override
    public void update(LoadBordObservable sb) {
        loadPrimaryStageWithGridPane(createUpdatedGridPane(sb));
    }


}
