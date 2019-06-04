package View;

import Controller.Bord_Controllers.LoadBord_Controller;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


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
    FirebaseService firebaseService = new FirebaseService();
    String tempString;

    public LoadBordView(Stage s, String roomId){
        primaryStage = s;
        this.roomId = roomId;
        //loadPrimaryStageWithGridPane(createInitialGridPane());
        loadPrimaryStageWithGridPane(createTestGridPane());
        loadBordController = loadBordController.getInstance();

        // PASS IT TO THE CONTROLLER WHO WILL PASS IT TO THE MODEL
        loadBordController.registerObserver( this);
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

    private GridPane createTestGridPane(){

        Object roomInfo = firebaseService.getSpel(roomId).getData();
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(400, 200);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);

        ArrayList<String> classes = ((ArrayList<String>) ((Map) roomInfo).get("Selectable_classes"));
        int count = 0;
        for(String s : classes){
            ImageView image = createImageView(s + ".png");
            tempString = s;
            image.setOnMouseClicked( e -> {myEvent(tempString);});
            gridPane.add(image, count, 0);
            count++;
       }



        return gridPane;
    }

    public void myEvent(String s){

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

        //gridPane.add(image, 0, 0);
        //gridPane.add(scoreText, 1, 0);


        return gridPane;
    }

    private GridPane createUpdatedGridPane(LoadBordObservable sb){
        return new GridPane();

    }




    @Override
    public void update(LoadBordObservable sb) {
        loadPrimaryStageWithGridPane(createUpdatedGridPane(sb));
    }


}
