package View.bord_views;

import Controller.Tile_Controllers.TileController;
import Model.Tiles.Tile;
import View.TileView;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class SpeelbordView {
    //een gridpane met per pane een tile
    //foto's hier opslaan en alleen de coordianten ophalen
    //bij update juiste coordinaat bij juiste image zetten.
    private TileController tileController;
    public GridPane spelbord;


    public GridPane loadSpelBord(){
        spelbord = new GridPane();
        spelbord.setLayoutX(410);
        spelbord.setLayoutY(75);
        tileController = TileController.getInstance();
        Tile[][] tiles = tileController.getTiles();
        for(int i = 0; i < tiles.length; i ++){
            for(int j = 0; j < tiles[i].length; j ++){

                if (tiles[i][j] != null){
                    TileView tileView = new TileView(tiles[i][j].getImage());

                    //ImageView tile = new ImageView(tiles[i][j].getImage());
                    //tile.setFitHeight(115);
                    //tile.setFitWidth(115)
                    ImageView tile = tileView.maakTileImage();
                    spelbord.setMargin(tile, new Insets(5,5,5,5));

                    spelbord.add(tile, i , j);;
                    System.out.println(i + " " + j);
                }

            }
        }
        return spelbord;
    }
}
