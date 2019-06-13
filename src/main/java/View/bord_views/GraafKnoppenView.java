package View.bord_views;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class GraafKnoppenView {

    static GraafKnoppenView graafKnoppenView;

    public static GraafKnoppenView getInstance(){
        if (graafKnoppenView == null){
            graafKnoppenView = new GraafKnoppenView();
        }
        return graafKnoppenView;
    }

    static GridPane view = new GridPane();

    public GridPane maakGraafKnoppen(){
        Button up = new Button("▲");
        Button down = new Button("▼");
        Button left = new Button("◄");
        Button right = new Button("►");
        Button TileActions = new Button("Graaf");
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

        acties.setLayoutX(525);
        acties.setLayoutY(685);

        view = acties;
        return acties;
    }

    public static GridPane getView() {
        return view;
    }
}
