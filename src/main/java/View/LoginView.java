package View;

import Controller.Login_Controllers.Login_Controller;
import Controller.firebase_controllers.UpdateFirebaseController;
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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import observers.*;


public class LoginView implements LoginObserver {

    String kaart = "Homescreenempty.png";
    private double width = 1600;
    private double height = 900;
    private double windowAnchorX = 50;
    private double windowAnchorY= 50;

    private ViewManager viewManager;

    Stage primaryStage;
    Login_Controller loginController;
    TextField usernameField = new TextField();
    TextField passwordField = new TextField();
    TextField roomId = new TextField("test");

    public LoginView(Stage s, ViewManager viewManager){
        this.viewManager = viewManager;
        primaryStage = s;
        loadPrimaryStageWithGridPane(createInitialGridPane());
        loginController = loginController.getInstance();

        // PASS IT TO THE CONTROLLER WHO WILL PASS IT TO THE MODEL
        loginController.registerObserver((LoginObserver) this);
    }

    private void loadPrimaryStageWithGridPane(GridPane gp) {
        try {


            GridPane root = gp;
            Image backgroundImage = new Image("Homescreenempty.png");
            Canvas canvas = new Canvas(width, height);

            Group group = new Group(canvas, root);
            Scene scene = new Scene(group);
            scene.getStylesheets().add("/css/login.css");
            primaryStage.setScene(scene);
            primaryStage.setTitle("WELCOME TO THE GAME");
            primaryStage.setX(windowAnchorX);
            primaryStage.setY(windowAnchorY);
            primaryStage.show();
            GraphicsContext gc = canvas.getGraphicsContext2D();
            gc.drawImage(backgroundImage, 0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private GridPane createInitialGridPane(){

        Text text1 = new Text("Username:");
        Text text2 = new Text("Password:");

        //Set style
        text1.setFill(Color.WHITE);
        text1.setFont(Font.font(null,FontWeight.BOLD, 18));
        text2.setFill(Color.WHITE);
        text2.setFont(Font.font(null,FontWeight.BOLD, 18));

        Button submitButton = new Button("Submit");
        submitButton.addEventFilter(MouseEvent.MOUSE_CLICKED, submitClicked);

        GridPane gridPane = new GridPane();
        gridPane.setMinSize(400, 200);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(text1, 140, 70);
        gridPane.add(usernameField, 140, 71);
        gridPane.add(text2, 140,72);
        gridPane.add(passwordField, 140,73);
        gridPane.add(submitButton, 140,74);

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
        Text error = new Text(sb.getError());
        error.setFill(Color.RED);
        Button startButton = new Button("Start Game");
        Button loadButton = new Button("Load Game");
        startButton.addEventFilter(MouseEvent.MOUSE_CLICKED, startClicked);
        loadButton.addEventFilter(MouseEvent.MOUSE_CLICKED, loadClicked);


        GridPane gridPane = new GridPane();
        gridPane.setMinSize(400, 200);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);
        if(!sb.getError().isEmpty()){
            gridPane.add(error, 0 ,0);
        }
        gridPane.add(roomId, 140,70);
        gridPane.add(startButton, 140,71);
        gridPane.add(loadButton, 140 ,72);

        return gridPane;
    }

    public GridPane loginIncorrect(LoginObservable sb){
        Text error = new Text(sb.getError());
        Text text1 = new Text("Username:");
        Text text2 = new Text("Password:");

        //Set style
        text1.setFill(Color.WHITE);
        text1.setFont(Font.font(null,FontWeight.BOLD, 18));
        text2.setFill(Color.WHITE);
        text2.setFont(Font.font(null,FontWeight.BOLD, 18));
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
        gridPane.add(error, 140 ,69);
        gridPane.add(text1, 140, 70);
        gridPane.add(usernameField, 140, 71);
        gridPane.add(text2, 140,72);
        gridPane.add(passwordField, 140,73);
        gridPane.add(submitButton, 140,74);

        return gridPane;
    }

    private ImageView loadWorldImage() {
        ImageView imageView = null;
        try {
            Image image = new Image(getClass().getResource(kaart).toURI().toString());
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
            (UpdateFirebaseController.getInstance()).makeFirebase(roomId.getText());
            //

        }
    };
    EventHandler<MouseEvent> loadClicked = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent e ) {
            loginController.loadGame(roomId.getText(), primaryStage);

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
