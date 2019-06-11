package View;

import Controller.Controller;
import Controller.Player_Controllers.Player_Controller;
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
import javafx.stage.Stage;

public class ViewManager extends Application{

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
    UitrustingView uitrustingview = new UitrustingView();   //maak uitrusting plaatsen
    WaterflesView waterflesView;      //maak waterfles stand
    SpeelbordView speelbordView = new SpeelbordView();


    private double windowWidth = 1600;
    private double windowHeight = 900;
    private double windowAnchorX = 50;
    private double windowAnchorY= 50;
    private boolean torf = true;


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
                System.out.println("rararara");
                group = firstBordload();
                torf = false;
            }else{
                group = makeGroup();
                System.out.println("lolololo");
            }

            BordView bordView = new BordView();  //maak achtergrond
            ActieKnoppenView actieknoppenview = new ActieKnoppenView();  //maak beweeg knoppen
            GraafKnoppenView graafknoppenview = new GraafKnoppenView();  //maak graaf knoppen
            EindigBeurtView eindigBeurtView = new EindigBeurtView();  //maak eindig beurt knop
            //LoadBordView loadbordview = new LoadBordView();  //geen idee wat deze doet
            OnderdeelView onderdeelview = new OnderdeelView();  //maak onderdelen
            SpeelbordView speelbordview = new SpeelbordView();  //maak speelbord tiles
            //    SpelerView spelerview = new SpelerView();           //maak speler poppetjes en zijkant informatie
            StormView stormview = new StormView();              //maak storm en stormmeter
            UitrustingView uitrustingview = new UitrustingView();   //maak uitrusting plaatsen
            WaterflesView waterflesView = new WaterflesView();      //maak waterfles stand
            SpeelbordView speelbordView = new SpeelbordView();
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



            ImageView achtergrond = bordView.maakAchtergrond(windowWidth, windowHeight);
            StackPane propellor = onderdeelview.loadPropeller("?", "?");
            StackPane beacon = onderdeelview.loadBeacon("?", "?");
            StackPane motor = onderdeelview.loadMotor("?", "?");
            StackPane zonnewijzer = onderdeelview.loadZonneWijzer("?", "?");
            GridPane knoppen = actieknoppenview.maakActieKnoppen();
            GridPane waterfles = waterflesView.createInitialGridPane();
            GridPane graafknoppen = graafknoppenview.maakGraafKnoppen();
            Button eindigbeurtKnop = eindigBeurtView.maakEindigbeurtKnop();
            GridPane spelbord = speelbordView.loadSpelBord();

            //Group group = new Group(knoppen, waterfles);
            Group group = new Group(achtergrond, knoppen, graafknoppen, eindigbeurtKnop, waterfles, propellor, beacon, motor, zonnewijzer, spelbord);
        Button burn = butje();

        //Group group = new Group(knoppen, waterfles);
        return  new Group(knoppen,
                burn,
                graafknoppen,
                eindigbeurtKnop,
                waterfles,
                propellor,
                beacon,
                motor,
                zonnewijzer,
                spelbord);

    }

    public Button butje(){
        Button burn = new Button("burn");
        burn.setPrefSize(152,57);
        burn.setLayoutX(1092);
        burn.setLayoutY(432);
        burn.setOnMouseClicked(e -> {
            Controller con = new Controller();
            con.verwijderZand();
            update();
        });
        return burn;

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
        System.out.println("help");
        Button burn = butje();


        Image backgroundImage = new Image("gamescreenempty.png");
        Canvas canvas = new Canvas(windowWidth, windowHeight);
        GridPane knoppen = actieknoppenview.getView();
        GridPane graafKnoppen = graafknoppenview.getView();
        Button eindigbeurt = eindigBeurtView.maakEindigbeurtKnop();
        GridPane waterfles = waterflesView.getView();

        StackPane propellor = onderdeelview.getPropellerView();
        StackPane beacon = onderdeelview.getBeaconView();
        StackPane motor = onderdeelview.getMotorView();
        StackPane zonnewijzer = onderdeelview.getZonnewijzerView();

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.drawImage(backgroundImage, 0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());



        Group group = new Group(canvas,burn, knoppen, graafKnoppen, eindigbeurt, waterfles, propellor,beacon,motor,zonnewijzer);
        return group;
    }

    public void update(){loadGameView();}
}
