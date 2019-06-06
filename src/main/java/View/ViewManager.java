package View;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
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
        loadPrimaryStageWithGridPane(primaryStage);
    }

    private void loadPrimaryStageWithGridPane(Stage primaryStage) {
        try {
            Canvas canvas = new Canvas(width, height);
            Group group = new Group(canvas, knoppen);
            Scene scene = new Scene(group);
            primaryStage.setScene(scene);
            primaryStage.setTitle("WELCOME TO THE GAME");
            primaryStage.setX(windowAnchorX);
            primaryStage.setY(windowAnchorY);
            primaryStage.show();
            GraphicsContext gc = canvas.getGraphicsContext2D();
            gc.drawImage(achtergrond, 0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
            /*Bord_Controller b = new Bord_Controller(root);*/
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
