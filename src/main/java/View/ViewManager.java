package View;

import Controller.Player_Controllers.PlayerController;
import Controller.Tile_Controllers.StormController;
import Controller.Tile_Controllers.TileController;
import Controller.firebase_controllers.UpdateFirebaseController;
import View.bord_views.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import observers.*;

public class ViewManager extends Application implements PlayerObserver, StormObserver {

    private static ViewManager viewManager;

    static Stage primaryStage;
    String kaart = "/gamescreenempty.png";
    //BordView bordView = new BordView();  //maak achtergrond
    ActieKnoppenView actieknoppenview = new ActieKnoppenView();  //maak beweeg knoppen
    GraafKnoppenView graafknoppenview = new GraafKnoppenView();  //maak graaf knoppen
    EindigBeurtView eindigBeurtView = new EindigBeurtView();  //maak eindig beurt knop
    //LoadBordView loadbordview = new LoadBordView();  //geen idee wat deze doet
    OnderdeelView onderdeelview = new OnderdeelView();  //maak onderdelen
    SpeelbordView speelbordview = new SpeelbordView();  //maak speelbord tiles
    //    SpelerView spelerview = new SpelerView();           //maak speler poppetjes en zijkant informatie
    //StormView stormview = new StormView();              //maak storm en stormmeter
    EquipmentView uitrustingview = new EquipmentView();   //maak uitrusting plaatsen
    WaterflesView waterflesView;      //maak waterfles stand
    StormMeterView stormMeterView = new StormMeterView(); //maak stormmetertekentje
    SpeelbordView speelbordView = SpeelbordView.getInstance();
    Acties_View acties_view;//maak actie tekens


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
            scene.getStylesheets().add("/styles.css");
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
        waterflesView = new WaterflesView();

        GridPane waterfles;

        waterfles = waterflesView.createInitialGridPane();
        GridPane stormTeken = stormMeterView.createInitialGridPane();
        acties_view = new Acties_View();

        StackPane propellor = onderdeelview.loadPropeller("?", "?");
        StackPane beacon = onderdeelview.loadBeacon("?", "?");
        StackPane motor = onderdeelview.loadMotor("?", "?");
        StackPane zonnewijzer = onderdeelview.loadZonneWijzer("?", "?");
        GridPane knoppen = actieknoppenview.maakActieKnoppen();
        GridPane graafknoppen = graafknoppenview.maakGraafKnoppen();
        GridPane spelbord = speelbordView.loadSpelBord();
//        GridPane acties_view =
//        Image backgroundImage = new Image("gamescreenempty.png");
//        Canvas canvas = new Canvas(windowWidth, windowHeight);
//
//
//        Button eindigbeurtKnop = eindigBeurtView.maakEindigbeurtKnop();
//        Button eindigBeurt = eindigBeurtKnop(eindigbeurtKnop);
//
//        Group uitrusting = uitrustingview.createEquipmentView();
//
//        GraphicsContext gc = canvas.getGraphicsContext2D();
//        gc.drawImage(backgroundImage, 0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
//
//        Group group = new Group(canvas, stormTeken, knoppen, graafknoppen, eindigbeurtKnop, waterfles, propellor, beacon, motor, zonnewijzer, spelbord, uitrusting);
//        return  group;


        (PlayerController.getInstance()).update();
        (StormController.getInstance()).update();

        (PlayerController.getInstance()).registerObserver(this);
        (StormController.getInstance()).registerObserver(this);

        return new Group( new GridPane());
    }

    public Button eindigBeurtKnop(Button eindigBeurt){
        eindigBeurt.setOnMouseClicked(e -> {
            StormController stormController = StormController.getInstance();
            stormController.voerStormEventsUit();
            PlayerController playerController = PlayerController.getInstance();
            playerController.getPlayer().refillActions();
            (PlayerController.getInstance()).getPlayer().subtractWater(1);
            (UpdateFirebaseController.getInstance()).updateFirebase();
            //update();
        });
        return eindigBeurt;
    }

    private void loadPrimaryStageWithGroup(Group group) {
        try {

            Image backgroundImage = new Image("background.png");
            Scene scene = new Scene(group, windowWidth, windowHeight);
            scene.getStylesheets().add("/styles.css");

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
        Button eindigbeurtKnop = eindigBeurtView.maakEindigbeurtKnop();
        Button eindigBeurt = eindigBeurtKnop(eindigbeurtKnop);
        GridPane waterfles = waterflesView.getView();


        StackPane propellor = onderdeelview.getPropellerView();
        StackPane beacon = onderdeelview.getBeaconView();
        StackPane motor = onderdeelview.getMotorView();
        StackPane zonnewijzer = onderdeelview.getZonnewijzerView();
        GridPane spelbord = speelbordView.getSpelbord();
        GridPane stormTeken =  stormMeterView.getView();
        System.out.println("Ik ben boven dat ding");
        GridPane acties = acties_view.getView();

        StackPane uitrusting = uitrustingview.getUitrusting();

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.drawImage(backgroundImage, 0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());

        Group group = new Group(canvas, stormTeken, knoppen, graafKnoppen, eindigBeurt, waterfles, propellor,beacon,motor,zonnewijzer, spelbord, uitrusting, acties);
        return group;
    }

    public void update(){loadGameView();}


    @Override
    public void update(PlayerObservable sb) {
        loadGameView();
    }

    @Override
    public void update(StormObservable sb) {
        loadGameView();
    }
}
