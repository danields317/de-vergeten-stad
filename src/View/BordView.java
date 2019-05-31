package View;

import Controller.Login_Controllers.Login_Controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import observers.SpelbordObservable;
import observers.SpelbordObserver;

public class BordView {

    String kaart = "/resources/gamescreenempty.png";
    //String file = "C:\\Users\\mjboere\\workspace\\Hello FX World\\src\\wereldkaart.jpg";
    private double width = 1600;
    private double height = 900;
    private double windowAnchorX = 50;
    private double windowAnchorY= 50;

    Stage primaryStage;
    Login_Controller loginController;
    TextField usernameField = new TextField();
    TextField passwordField = new TextField();

    public BordView(Stage s){
        primaryStage = s;
        loadPrimaryStageWithGridPane(createInitialGridPane());
        loginController = loginController.getInstance();

        // PASS IT TO THE CONTROLLER WHO WILL PASS IT TO THE MODEL
        loginController.registerObserver((SpelbordObserver) this);
    }

    private void loadPrimaryStageWithGridPane(GridPane gp) {
        try {


            GridPane root = gp;
            Image backgroundImage = new Image("resources/gamescreenempty.png");
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

    private GridPane createInitialGridPane(){
        Text scoreText = new Text("Player Score");

        Button submitButton = new Button("Submit");
        //submitButton.addEventFilter(MouseEvent.MOUSE_CLICKED, submitClicked);

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

    private GridPane createUpdatedGridPane(SpelbordObservable sb){
/*
        if(sb.isLoginCorrect()){
            return loginCorrect(sb);
        }else{
            return loginIncorrect(sb);
        }*/

        return new GridPane();

    }

    public GridPane loginCorrect(SpelbordObservable sb){
        Text scoreText = new Text("Player Score");
        Button startButton = new Button("Start Game");
        //startButton.addEventFilter(MouseEvent.MOUSE_CLICKED, startClicked);



        GridPane gridPane = new GridPane();
        gridPane.setMinSize(400, 200);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(startButton, 0,0);

        return gridPane;
    }





}
