package Controller.Bord_Controllers;

import Controller.Tile_Controllers.Part_Controller;
import Model.Tiles.Tile;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.image.Image ;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class Field_Controller {

    private ArrayList<Tile> tiles = new ArrayList<Tile>();
    private int value;

    public Field_Controller(Group root) {
        GridPane gridPane = new GridPane();

        //Setting size for the pane
        gridPane.setMinSize(40, 40);

        //Setting the padding
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        //Setting the vertical and horizontal gaps between the columns
        gridPane.setVgap(5);
        gridPane.setHgap(5);


        for (int i = 0; i < 25; i++) {
            Part_Controller partController = new Part_Controller();
            tiles.add(partController.getTile());
        }
        double size = 125.0;

        for (int yAs = 0; yAs < (tiles.size() / 5 ); yAs++) {
            for (int xAs = 0; xAs < 5; xAs++) {

                if (tiles.get(xAs + (5 * yAs)).isDiscovered()) {
                    ImageView imv = new ImageView();
                    Image image = tiles.get(xAs + (5 * yAs)).getImage();


                    imv.setImage(image);





                    gridPane.add(imv, xAs, yAs);
                } else {
                    ImageView imv = new ImageView();
                    Image image = tiles.get(xAs + (5 * yAs)).getUndiscoveredImage();

                    value = (xAs + (5 * yAs));
                    imv.setImage(image);
                    imv.setFitWidth(size);
                    imv.setFitHeight(size);

                    imv.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

                        @Override
                        public void handle(MouseEvent event) {
                            System.out.println("hallo");
                            imv.setImage(tiles.get(value).getImage());
                        }
                    });

                    gridPane.add(imv, xAs, yAs);
                }

            }
        }
        root.getChildren().add(gridPane);

    }
}
