package View.bord_views;

import Controller.Bord_Controllers.Bord_Controller;
import View.bord_views.ActieKnoppenView;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import observers.*;
import Controller.Controller;

public class BordView {

    public ImageView maakAchtergrond(double windowWidth, double windowHeight) {
        Image achtergrondImage = new Image("/gamescreenempty.png");
        ImageView achtergrond = new ImageView(achtergrondImage);
        achtergrond.setX(0);
        achtergrond.setY(0);
        achtergrond.setFitWidth(windowWidth);
        achtergrond.setFitHeight(windowHeight);

        return achtergrond;
    }

}
