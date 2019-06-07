package View;

import Controller.Bord_Controllers.Bord_Controller;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import observers.*;
import Controller.Controller;

public class BordView {

    ActieKnoppenView AKV = new ActieKnoppenView();
    Controller controller = Controller.getInstance();


    String kaart = "/gamescreenempty.png";
    //String file = "C:\\Users\\mjboere\\workspace\\Hello FX World\\src\\wereldkaart.jpg";
    private double windowAnchorX = 50;
    private double windowAnchorY= 50;

    static Stage primaryStage;
    Bord_Controller bordController;
    TextField usernameField = new TextField();
    TextField passwordField = new TextField();
    Button zonBrand = new Button("Burn");


    /*public BordView(){
       maakAchtergrond();/

        // PASS IT TO THE CONTROLLER WHO WILL PASS IT TO THE MODEL
        bordController.registerObserver((BordObserver) this);
    }*/

    public ImageView maakAchtergrond(double w, double h){
        Image achtergrond = new Image("gamescreenempty.png");
        ImageView achtergrondView = new ImageView(achtergrond);
        achtergrondView.setX(0);
        achtergrondView.setY(0);
        achtergrondView.setFitWidth(w);
        achtergrondView.setFitHeight(h);
        return achtergrondView;
    }

}
