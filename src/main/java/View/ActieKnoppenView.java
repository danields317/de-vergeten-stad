package View;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.awt.*;

public class ActieKnoppenView {

    public ActieKnoppenView(){

    }

    public GridPane maakActieKnoppen(){
        Button up = new Button("▲");
        Button down = new Button("▼");
        Button left = new Button("◄");
        Button right = new Button("►");
        Button TileActions = new Button("◙");
        up.setPrefSize(40, 40);
        down.setPrefSize(40, 40);
        left.setPrefSize(40, 40);
        right.setPrefSize(40, 40);
        TileActions.setPrefSize(40, 40);

        GridPane acties = new GridPane();

        acties.add(up, 1, 0);
        acties.add(down, 1, 2);
        acties.add(left, 0, 1);
        acties.add(right, 2, 1);
        acties.add(TileActions, 1, 1);
        return acties;
    }

}
