package View;

import Controller.Bord_Controllers.Player_Menu_Controller;
import Controller.Player_Controllers.FunctieController;
import Controller.Player_Controllers.PlayerController;
import Controller.Tile_Controllers.StormController;
import Controller.firebase_controllers.UpdateFirebaseController;
import Model.data.StaticData;
import View.bord_views.*;
import firebase.FirebaseService;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import observers.*;

import java.util.Map;

public class ViewManager extends Application implements PlayerObserver, StormObserver {

    private static ViewManager viewManager;
    public enum endConditions {VICTORYROYALE, STERFDOORSTORM, DEHYDRATION, SUFFOCATION};

    static Stage primaryStage;
    String kaart = "/gamescreenempty.png";
    ActieKnoppenView actieknoppenview = new ActieKnoppenView();  //maak beweeg knoppen
    GraafKnoppenView graafknoppenview = new GraafKnoppenView();  //maak graaf knoppen
    EindigBeurtView eindigBeurtView = new EindigBeurtView();  //maak eindig beurt knop
    OnderdeelView onderdeelview = new OnderdeelView();  //maak onderdelen
    SpeelbordView speelbordview = new SpeelbordView();  //maak speelbord tiles
    EquipmentView uitrustingview = new EquipmentView();   //maak uitrusting plaatsen
    WaterflesView waterflesView;      //maak waterfles stand
    StormMeterView stormMeterView = new StormMeterView(); //maak stormmetertekentje
    SpeelbordView speelbordView = SpeelbordView.getInstance();
    Acties_View acties_view;//maak actie tekens
    InstellingenView instellingenView = new InstellingenView();
    StaticData staticData = StaticData.getInstance();
    Player_Menu_View player_menu_view;


    private double windowWidth = 1600;
    private double windowHeight = 900;
    private double windowAnchorX = 50;
    private double windowAnchorY= 50;
    private boolean torf = true;

    public static ViewManager getInstance(){
        if (viewManager == null){
            viewManager = new ViewManager();
        }
        return viewManager;
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        loadLoginView();
    }

    public void loadLoginView() {

        new LoginView(primaryStage, this);

    }

    public void loadGameView() {

        try {
            Group group;
            if(torf){
                group = firstBordload();
                torf = false;
            }
                group = makeGroup();


            Scene scene = new Scene(group, windowWidth, windowHeight);
            scene.getStylesheets().add("/css/game.css");
            primaryStage.setScene(scene);
            primaryStage.setTitle("De Vergeten Stad");
            primaryStage.setX(windowAnchorX);
            primaryStage.setY(windowAnchorY);
            primaryStage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void updateGameView() {
        try {
            Group group = makeGroup();

            Scene scene = new Scene(group, windowWidth, windowHeight);
            scene.getStylesheets().add("/css/game.css");
            primaryStage.setScene(scene);
            primaryStage.setTitle("De Vergeten Stad");
            primaryStage.setX(windowAnchorX);
            primaryStage.setY(windowAnchorY);
            primaryStage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public Group firstBordload(){
        (FunctieController.getInstance()).endLose();
        waterflesView = new WaterflesView();

        GridPane waterfles;

        waterfles = waterflesView.createInitialGridPane();
        GridPane stormTeken = stormMeterView.createInitialGridPane();
        acties_view = new Acties_View();
        player_menu_view = new Player_Menu_View();
        player_menu_view.maakView();


        StackPane propellor = onderdeelview.loadPropeller("?", "?");
        StackPane beacon = onderdeelview.loadBeacon("?", "?");
        StackPane motor = onderdeelview.loadMotor("?", "?");
        StackPane zonnewijzer = onderdeelview.loadZonneWijzer("?", "?");
        GridPane knoppen = actieknoppenview.maakActieKnoppen();
        GridPane graafknoppen = graafknoppenview.maakGraafKnoppen();
        GridPane spelbord = speelbordView.loadSpelBord();
        ImageView instelligen = instellingenView.InstellingenView();


        (PlayerController.getInstance()).update();
        (StormController.getInstance()).update();

        (PlayerController.getInstance()).registerObserver(this);
        (StormController.getInstance()).registerObserver(this);

        return new Group( new GridPane());
    }

    private void loadPrimaryStageWithGroup(Group group) {
        try {

            Image backgroundImage = new Image("background.png");
            Scene scene = new Scene(group, windowWidth, windowHeight);
            scene.getStylesheets().add("/css/game.css");

            primaryStage.setScene(scene);
            primaryStage.setTitle("De Vergeten Stad");
            primaryStage.setX(windowAnchorX);
            primaryStage.setY(windowAnchorY);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private Group makeGroup(){

        Image backgroundImage = new Image("gamescreenempty.png");
        Canvas canvas = new Canvas(windowWidth, windowHeight);
        GridPane knoppen = actieknoppenview.getView();
        GridPane graafKnoppen = graafknoppenview.getView();

        Button eindigBeurt;
        if(((String)((Map) staticData.getRoomInfo()).get("activePlayer")).equals(staticData.getClassName()) ) {
            eindigBeurt = eindigBeurtView.maakEindigbeurtKnop();
        }else{
            eindigBeurt = new Button( (String)((Map) staticData.getRoomInfo()).get("activePlayer") + "/n beurt");
            eindigBeurt.setPrefSize(152,57);
            eindigBeurt.setLayoutX(1392);
            eindigBeurt.setLayoutY(732);
        }
        GridPane waterfles = waterflesView.getView();


        StackPane propellor = onderdeelview.getPropellerView();
        StackPane beacon = onderdeelview.getBeaconView();
        StackPane motor = onderdeelview.getMotorView();
        StackPane zonnewijzer = onderdeelview.getZonnewijzerView();
        GridPane spelbord = speelbordView.getSpelbord();
        GridPane stormTeken =  stormMeterView.getView();
        GridPane acties = acties_view.getView();
        GridPane playermenu = player_menu_view.getView();
        GridPane playermenuclasses = player_menu_view.getView2();

        StackPane uitrusting = uitrustingview.getUitrusting();

        ImageView instelligen = instellingenView.InstellingenView();

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.drawImage(backgroundImage, 0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());

        Group group = new Group(canvas, stormTeken, knoppen, graafKnoppen, eindigBeurt, waterfles, propellor,beacon,motor,zonnewijzer, spelbord, uitrusting, acties, instelligen, playermenu, playermenuclasses);
        return group;
    }

    public void update(){
        loadGameView();
    }

    public void loadEndGame(endConditions endConiditon){
        Image image = new Image("/placeholder.png");

        switch(endConiditon){
            case VICTORYROYALE:
                image = new Image("/Endscreen/victoryroyale.png");
                break;
            case STERFDOORSTORM:
                image = new Image("/Endscreen/sterfdoorstorm.png");
                break;
            case DEHYDRATION:
                image = new Image("/Endscreen/dehydration.png");
                break;
            case SUFFOCATION:
                image = new Image("/Endscreen/suffocation.png");
                break;
        }

        ImageView pdb = new ImageView(image);

        pdb.setOnMouseClicked(e -> {
            loadLoginView();
        });

        FirebaseService.getInstance().stopRunning();

        Group group = new Group(pdb);
        //Scene endScene = new Scene(group, windowWidth, windowHeight);
        //primaryStage.setScene(endScene);
        //primaryStage.getScene().setRoot(group);
        primaryStage.setScene(new Scene(group));
        //primaryStage.setTitle("De Vergeten Stad");
        //primaryStage.setX(windowAnchorX);
        //primaryStage.setY(windowAnchorY);
        //primaryStage.show();
    }

//    public void enableButtons(){
//        for (Node i : primaryStage.getScene().getRoot().getChildrenUnmodifiable()){
//            i.setDisable(false);
//        }
//    }

    public void disableButtons(){
        for (Node i : primaryStage.getScene().getRoot().getChildrenUnmodifiable()){
            i.setDisable(true);
        }
    }

    @Override
    public void update(PlayerObservable sb) {
        loadGameView();
    }

    @Override
    public void update(StormObservable sb) {
        loadGameView();
    }
}
