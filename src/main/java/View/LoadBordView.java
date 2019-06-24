package View;

import Controller.Bord_Controllers.LoadBord_Controller;
import Controller.Player_Controllers.PlayerController;
import Model.data.StaticData;
import firebase.FirebaseService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import observers.LoadBordObservable;
import observers.LoadBordObserver;

import java.util.*;


public class LoadBordView implements LoadBordObserver {

    String kaart = "Homescreenempty.png";
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
            scene.getStylesheets().add("/css/spelerklasse.css");
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
        firebaseService.listen(roomId);
        Object roomInfo = firebaseService.getSpel(roomId).getData();
        (StaticData.getInstance()).setRoomInfo(roomInfo);
        (StaticData.getInstance()).setRoomName(roomId);
        Object classes = ((Map) roomInfo).get("Selectable_classes");
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setMinSize(width, (height / 1.5));
        gridPane.setVgap(50);
        gridPane.setHgap(50);
        gridPane.setAlignment(Pos.CENTER);

        int count = 0;
        for(int i = 0; i < ((Map) classes).size(); i++){

            GridPane gp = new GridPane();
            gp.setPadding(new Insets(50, 25, 50, 25));
            GridPane gp2 = new GridPane();
            GridPane gp3 = new GridPane();


            Object killMe = ((Map) classes).get(Integer.toString(i));
            final String tempString = ( ((Map) killMe).get("name")).toString();
            Text className = new Text(tempString);
            ImageView image = createImageView(tempString + ".png");
            gp.setOnMouseClicked(e -> {
                PlayerController player = PlayerController.getInstance(true, killMe);
                player.update();
                (StaticData.getInstance()).setClassName(tempString);
                ViewManager view = new ViewManager();
                view.loadGameView();
            });
            image.setFitHeight(250.0);
            image.setFitWidth(250.0);
            Text water =  new Text(( ((Map) killMe).get("water")).toString());
            Text splitter =  new Text(" / ");
            Text maxWater =  new Text(( ((Map) killMe).get("maxWater")).toString());


            //Styling
            water.setFill(Color.BLACK);
            water.setFont(Font.font(null, FontWeight.BOLD, 30));
            splitter.setFill(Color.BLACK);
            splitter.setFont(Font.font(null, FontWeight.BOLD, 30));
            maxWater.setFill(Color.BLACK);
            maxWater.setFont(Font.font(null,FontWeight.BOLD, 30));
            className.setFill(Color.BLACK);
            className.setFont(Font.font(null,FontWeight.BOLD, 30));
            className.setTextAlignment(TextAlignment.CENTER);
            gp.setStyle("-fx-background-color: #FFFFFF;");
            gp.setAlignment(Pos.CENTER);
            gp.getStyleClass().add("spelerklasse");
            gp2.setAlignment(Pos.CENTER);
            gp2.setMinWidth(250.0);
            gp2.setPrefWidth(250.0);
            gp2.setMaxWidth(250.0);
            gp3.setAlignment(Pos.CENTER);


            //Set up gridpanes
            gp3.add(water, 0, 1);
            gp3.add(splitter, 1, 1);
            gp3.add(maxWater, 2, 1);

            gp2.add(className, 0, 0);
            gp2.add(gp3, 0, 1);

            gp.add(image, 0, 0);
            gp.add(gp2, 0, 1);
            gridPane.add(gp, count, 0);
            count++;
        }

        return gridPane;
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
        return gridPane;
    }

    @Override
    public void update(LoadBordObservable sb) {
        loadPrimaryStageWithGridPane(createUpdatedGridPane(sb));
    }


}
