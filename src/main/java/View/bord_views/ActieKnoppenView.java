package View.bord_views;

import Controller.Player_Controllers.PlayerController;
import Model.Tiles.Tile;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.awt.*;

public class ActieKnoppenView {

    static GridPane view = new GridPane();

    public GridPane maakActieKnoppen(){
        Button up = new Button();
        Button down = new Button();
        Button left = new Button();
        Button right = new Button();
        Button tileActions = new Button();
        up.setPrefSize(60, 60);
        down.setPrefSize(60, 60);
        left.setPrefSize(60, 60);
        right.setPrefSize(60, 60);
        tileActions.setPrefSize(60, 60);


        tileActions.getStyleClass().add("buttonActie");
        up.getStyleClass().add("buttonMoveUp");
        down.getStyleClass().add("buttonMoveDown");
        right.getStyleClass().add("buttonMoveRight");
        left.getStyleClass().add("buttonMoveLeft");

        GridPane acties = new GridPane();

        acties.add(up, 1, 0);
        acties.add(down, 1, 2);
        acties.add(left, 0, 1);
        acties.add(right, 2, 1);
        acties.add(tileActions, 1, 1);

        acties.setLayoutX(319);
        acties.setLayoutY(685);

        up.setOnMouseClicked(e -> {
            PlayerController playerController = PlayerController.getInstance();
            playerController.moveNoord();
        });

        down.setOnMouseClicked(e -> {
            PlayerController playerController = PlayerController.getInstance();
            playerController.moveZuid();
        });

        right.setOnMouseClicked(e -> {
            PlayerController playerController = PlayerController.getInstance();
            playerController.moveOost();
        });

        left.setOnMouseClicked(e -> {
            PlayerController playerController = PlayerController.getInstance();
            playerController.moveWest();
        });

        tileActions.setOnMouseClicked(e -> {
            PlayerController playerController = PlayerController.getInstance();
            playerController.tileActies();
        });

        view = acties;
        return acties;
    }



    public static GridPane getView() {
        return view;
    }
}
