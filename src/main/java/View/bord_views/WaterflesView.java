package View.bord_views;

import Controller.Bord_Controllers.LoadBord_Controller;
import Controller.Bord_Controllers.Water_Controller;
import Controller.Player_Controllers.Player_Controller;
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
import javafx.stage.Stage;
import observers.LoadBordObservable;
import observers.WaterObservable;
import observers.WaterObserver;

import java.util.Map;

public class WaterflesView implements WaterObserver {


    String kaart = "Homescreenempty.png";
    //String file = "C:\\Users\\mjboere\\workspace\\Hello FX World\\src\\wereldkaart.jpg";
    private double width = 1600;
    private double height = 900;
    private double windowAnchorX = 50;
    private double windowAnchorY= 50;

    Stage primaryStage;
    Water_Controller waterController;
    static GridPane view;


    public WaterflesView(){
        /*primaryStage = s;
        //loadPrimaryStageWithGridPane(createInitialGridPane());
        loadPrimaryStageWithGridPane(createInitialGridPane());*/
        waterController = waterController.getInstance();

    }

    private void loadPrimaryStageWithGridPane() {
        try {


            GridPane root = view;
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

    private void createUpdatedGridPane(WaterObservable sb){
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(400, 200);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);
        ImageView waterImage = new ImageView(new Image(sb.getImgWater()));

        gridPane.add(waterImage, 50,50);
        view = gridPane;
    }

    public void myEvent(ImageView image){


    }



    public ImageView createImageView(String name){
        return new ImageView(new Image(name));
    }

    public GridPane createInitialGridPane(){



        GridPane gridPane = new GridPane();
        gridPane.setMinSize(400, 200);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);


        ImageView waterImage = new ImageView(new Image("/Fles0_4.png"));
        waterImage.setFitWidth(60.0);
        waterImage.setFitHeight(60.0);
        gridPane.add(waterImage, 20, 20);



        //gridPane.add(image, 0, 0);
        //gridPane.add(scoreText, 1, 0);


        return gridPane;
    }


    public static GridPane getView() {
        return view;
    }

    @Override
    public void update(WaterObservable sb) {
        createUpdatedGridPane(sb);
        loadPrimaryStageWithGridPane();
    }


}
