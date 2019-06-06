package View;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ViewManager extends Application{
    BordView bordAchtergrond = new BordView();  //maak achtergrond
    ActieKnoppenView AKV = new ActieKnoppenView();  //maak beweeg knoppen
    GraafKnoppenView GKV = new GraafKnoppenView();  //maak graaf knoppen
    //LoadBordView LBV = new LoadBordView();  //geen idee wat deze doet
    //LoginView login = new LoginView();  //maak login
    OnderdeelView onderdeel = new OnderdeelView();  //maak onderdelen
    SpeelbordView speelbord = new SpeelbordView();  //maak speelbord tiles
//    SpelerView speler = new SpelerView();           //maak speler poppetjes en zijkant informatie
    StormView storm = new StormView();              //maak storm en stormmeter
    UitrustingView uitrusting = new UitrustingView();   //maak uitrusting plaatsen
//    WaterflesView waterfles = new WaterflesView();      //maak waterfles stand

    static Stage primaryStage;
    String kaart = "/gamescreenempty.png";
    private double width = 1600;
    private double height = 900;
    private double windowAnchorX = 50;
    private double windowAnchorY= 50;

    Image achtergrond = bordAchtergrond.maakAchtergrond();
    GridPane knoppen = AKV.maakActieKnoppen();

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

            ImageView background = new ImageView(achtergrond);
            background.setX(0);
            background.setY(0);
            background.setFitWidth(width);
            background.setFitHeight(height);

            Group group = new Group(background, knoppen);
            Scene scene = new Scene(group);
            primaryStage.setScene(scene);
            primaryStage.setTitle("WELCOME TO THE GAME");
            primaryStage.setX(windowAnchorX);
            primaryStage.setY(windowAnchorY);
            primaryStage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
