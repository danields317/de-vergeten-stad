package View.bord_views;

import Controller.Tile_Controllers.TileController;
import Model.Tiles.EquipmentTile;
import Model.Tiles.Tile;
import Model.Tiles.Tunnel;
import View.TileView;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;

public class SpeelbordView{
    //een gridpane met per pane een tile
    //foto's hier opslaan en alleen de coordianten ophalen
    //bij update juiste coordinaat bij juiste image zetten.
    private TileController tileController;
    public GridPane spelbord;

    private ArrayList<TileView> tileViews = new ArrayList<>();

    static SpeelbordView speelbordView;

    public static SpeelbordView getInstance(){
        if (speelbordView == null){
            speelbordView = new SpeelbordView();
        }
        return speelbordView;
    }


    public GridPane loadSpelBord(){
        spelbord = new GridPane();
        spelbord.setLayoutX(410);
        spelbord.setLayoutY(75);
        tileController = TileController.getInstance();
        ArrayList<Tile> tiles = tileController.getTiles();
        System.out.println(tiles.size());
        for(int i = 0; i < tiles.size(); i ++){

            Tile tile = tiles.get(i);
            TileView tileView = new TileView(tile.getImage());
            tileViews.add(tileView);
            tileView.update(tile);

            //ImageView tile = new ImageView(tiles[i][j].getImage());
            //tile.setFitHeight(115);
            //tile.setFitWidth(115)
            StackPane tilePane = tileView.maakTile();
            spelbord.setMargin(tilePane, new Insets(5,5,5,5));

            tilePane.setOnMouseClicked(e -> {
                tileController.tileClicked( spelbord.getColumnIndex(tilePane), spelbord.getRowIndex(tilePane) );
            } );

            spelbord.add(tilePane, tile.getX() , tile.getY());

        }
        return spelbord;
    }

    public void updateSpelBord(Tile tile, Tile tile2){
        tileController = TileController.getInstance();
        ArrayList<Tile> tiles = tileController.getTiles();

        spelbord.getChildren().remove(tileViews.get(tiles.indexOf(tile)).maakTile());
        spelbord.getChildren().remove(tileViews.get(tiles.indexOf(tile2)).maakTile());

        spelbord.add(tileViews.get(tiles.indexOf(tile2)).maakTile(), tile2.getX(), tile2.getY());
        spelbord.add(tileViews.get(tiles.indexOf(tile)).maakTile(), tile.getX(), tile.getY());
    }

    public GridPane getSpelbord() {
        return spelbord;
    }
}
