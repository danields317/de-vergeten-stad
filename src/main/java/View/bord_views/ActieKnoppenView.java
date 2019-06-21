package View.bord_views;

import Controller.Player_Controllers.PlayerController;
import javafx.scene.control.Button;
import javafx.scene.layout.*;

public class ActieKnoppenView {

    static GridPane view = new GridPane();
    GridPane acties;
    PlayerController playerController;

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

        acties = new GridPane();

        acties.add(up, 1, 0);
        acties.add(down, 1, 2);
        acties.add(left, 0, 1);
        acties.add(right, 2, 1);
        acties.add(tileActions, 1, 1);

        acties.setLayoutX(319);
        acties.setLayoutY(685);

        playerController = PlayerController.getInstance();
        boolean isKlimmer = false;

        switch (playerController.getPlayer().getKlasse()){
            case WATERDRAGER:
                setWaterdragerKnoppen();
                break;
            case VERKENNER:
                setVerkennerKnoppen();
                break;
            case KLIMMER:
                isKlimmer = true;
                break;
        }

        final boolean finalKlimmer = isKlimmer;

        up.setOnMouseClicked(e -> {
            playerController.moveNoord(finalKlimmer);
        });

        down.setOnMouseClicked(e -> {
            playerController.moveZuid(finalKlimmer);
        });

        right.setOnMouseClicked(e -> {
            playerController.moveOost(finalKlimmer);
        });

        left.setOnMouseClicked(e -> {
            playerController.moveWest(finalKlimmer);
        });

        tileActions.setOnMouseClicked(e -> {
            playerController.tileActies();
        });

        view = acties;
        return acties;
    }

    public void setWaterdragerKnoppen(){
        Button wS = new Button("wS");
        acties.add(wS, 2, 2);
        wS.setOnMouseClicked(e -> {
            playerController.schepWater();
        });
    }

    public void setVerkennerKnoppen(){
        Button rB = new Button("rB");
        Button rO = new Button("rO");
        Button lB = new Button("lB");
        Button lO = new Button("lO");

        rB.setOnMouseClicked(e -> playerController.moveNoordOost());
        rO.setOnMouseClicked(e -> playerController.moveZuidOost());
        lB.setOnMouseClicked(e -> playerController.moveNoordWest());
        lO.setOnMouseClicked(e -> playerController.moveZuidWest());

        acties.add(lB, 0, 0);
        acties.add(lO, 0, 2);
        acties.add(rB, 2, 0);
        acties.add(rO, 2, 2);
    }

    public static GridPane getView() {
        return view;
    }
}
