package View;

import Controller.Bord_Controllers.Bord_Controller;
import Controller.Login_Controllers.Login_Controller;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import observers.*;

public class LoginView implements LoginObserver {

    String kaart = "/resources/Homescreenempty.png";
    //String file = "C:\\Users\\mjboere\\workspace\\Hello FX World\\src\\wereldkaart.jpg";
    private double width = 1600;
    private double height = 900;
    private double windowAnchorX = 50;
    private double windowAnchorY= 50;

    Stage primaryStage;
    Login_Controller loginController;
    TextField usernameField = new TextField();
    TextField passwordField = new TextField();

    public LoginView(Stage s){
        primaryStage = s;
        loadPrimaryStageWithGridPane(createInitialGridPane());
        loginController = loginController.getInstance();

        // PASS IT TO THE CONTROLLER WHO WILL PASS IT TO THE MODEL
        loginController.registerObserver((LoginObserver) this);
    }

    private void loadPrimaryStageWithGridPane(GridPane gp) {
        try {


            GridPane root = gp;
            Image backgroundImage = new Image("resources/Homescreenempty.png");
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
        submitButton.addEventFilter(MouseEvent.MOUSE_CLICKED, submitClicked);

        GridPane gridPane = new GridPane();
        gridPane.setMinSize(400, 200);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);

        //gridPane.add(image, 0, 0);
        //gridPane.add(scoreText, 1, 0);

        gridPane.add(usernameField, 0, 1);
        gridPane.add(passwordField, 0,2);
        gridPane.add(submitButton, 0,3);

        return gridPane;
    }

    private GridPane createUpdatedGridPane(LoginObservable sb){

        if(sb.isLoginCorrect()){
            return loginCorrect(sb);
        }else{
            return loginIncorrect(sb);
        }

    }

    public GridPane loginCorrect(LoginObservable sb){
        Text scoreText = new Text("Player Score");
        Button startButton = new Button("Start Game");
        startButton.addEventFilter(MouseEvent.MOUSE_CLICKED, startClicked);



        GridPane gridPane = new GridPane();
        gridPane.setMinSize(400, 200);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(startButton, 0,0);

        return gridPane;
    }

    public GridPane loginIncorrect(LoginObservable sb){
        Text error = new Text("Username or password is not correct");
        error.setFill(Color.RED);
        Button submitButton = new Button("Submit");
        submitButton.addEventFilter(MouseEvent.MOUSE_CLICKED, submitClicked);
        usernameField.setText(sb.getGivenUsername());
        passwordField.setText(sb.getGivenPassword());


        GridPane gridPane = new GridPane();
        gridPane.setMinSize(400, 200);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);

//        gridPane.add(image, 0, 0);
//        gridPane.add(scoreText, 1, 0);
//        gridPane.add(usernameField, 1, 1);
        gridPane.add(error, 0 ,0);
        gridPane.add(usernameField, 0, 1);
        gridPane.add(passwordField, 0,2);
        gridPane.add(submitButton, 0,3);

        return gridPane;
    }

    private ImageView loadWorldImage() {
        ImageView imageView = null;
        try {
            Image image = new Image(getClass().getResource(kaart).toURI().toString());
            //Image image = new Image(new FileInputStream(file));
            imageView = new ImageView(image);
            return imageView;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imageView;
    }


    EventHandler<MouseEvent> startClicked = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent e) {
            new BordView(primaryStage);

        }
    };
    EventHandler<MouseEvent> submitClicked = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent e) {
            loginController.checkLogin(usernameField.getText(), passwordField.getText());
        }
    };

    @Override
    public void update(LoginObservable sb) {
        loadPrimaryStageWithGridPane(createUpdatedGridPane(sb));
    }



}
