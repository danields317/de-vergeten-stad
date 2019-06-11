package View.bord_views;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.awt.*;

public class ActieKnoppenView {

    public GridPane maakActieKnoppen(){
        Button up = new Button("▲");
        Button down = new Button("▼");
        Button left = new Button("◄");
        Button right = new Button("►");
        Button TileActions = new Button("Actie");
        up.setPrefSize(60, 60);
        down.setPrefSize(60, 60);
        left.setPrefSize(60, 60);
        right.setPrefSize(60, 60);
        TileActions.setPrefSize(60, 60);

        GridPane acties = new GridPane();

        acties.add(up, 1, 0);
        acties.add(down, 1, 2);
        acties.add(left, 0, 1);
        acties.add(right, 2, 1);
        acties.add(TileActions, 1, 1);

        acties.setLayoutX(319);
        acties.setLayoutY(685);


        return acties;
    }

}
