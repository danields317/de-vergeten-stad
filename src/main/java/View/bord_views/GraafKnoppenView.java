package View.bord_views;

import Controller.Player_Controllers.PlayerController;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class GraafKnoppenView {

    static GridPane view = new GridPane();

    public GridPane maakGraafKnoppen(){
        Button digUp = new Button();
        Button digDown = new Button();
        Button digLeft = new Button();
        Button digRight = new Button();
        Button dig = new Button();
        digUp.setPrefSize(60, 60);
        digDown.setPrefSize(60, 60);
        digLeft.setPrefSize(60, 60);
        digRight.setPrefSize(60, 60);
        dig.setPrefSize(60, 60);

        dig.getStyleClass().add("buttonGraaf");
        digUp.getStyleClass().add("buttonMoveUp");
        digDown.getStyleClass().add("buttonMoveDown");
        digRight.getStyleClass().add("buttonMoveRight");
        digLeft.getStyleClass().add("buttonMoveLeft");

        GridPane acties = new GridPane();

        acties.add(digUp, 1, 0);
        acties.add(digDown, 1, 2);
        acties.add(digLeft, 0, 1);
        acties.add(digRight, 2, 1);
        acties.add(dig, 1, 1);

        acties.setLayoutX(525);
        acties.setLayoutY(685);

        PlayerController playerController = PlayerController.getInstance();
        boolean isArcheoloog = false;

        switch (playerController.getPlayer().getKlasse()){
            case ARCHEOLOOG:
                isArcheoloog = true;
                break;
        }

        final boolean finalArcheoloog = isArcheoloog;

        digUp.setOnMouseClicked(e -> {
            playerController.digNoord(finalArcheoloog);
        });

        digDown.setOnMouseClicked(e -> {
            playerController.digZuid(finalArcheoloog);
        });

        digRight.setOnMouseClicked(e -> {
            playerController.digOost(finalArcheoloog);
        });

        digLeft.setOnMouseClicked(e -> {
            playerController.digWest(finalArcheoloog);
        });

        dig.setOnMouseClicked(e -> {
            playerController.digHere(finalArcheoloog);
        });

        view = acties;
        return acties;
    }

    public static GridPane getView() {
        return view;
    }
}
