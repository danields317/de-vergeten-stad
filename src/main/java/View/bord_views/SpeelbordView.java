package View.bord_views;

import Controller.Equipment_Controllers.EquipmentController;
import Controller.Player_Controllers.PlayerController;
import Controller.Tile_Controllers.TileController;
import Model.Tiles.EquipmentTile;
import Model.Tiles.Tile;
import Model.Tiles.Tunnel;
import Model.player.Player;
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
    private PlayerController playerController;
    private EquipmentController equipmentController;

    public GridPane spelbord;

    public boolean aardekijkerSelected = false;
    public boolean duinkanonSelected = false;
    public boolean jetpackSelected = false;

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
        playerController = PlayerController.getInstance();
        equipmentController = EquipmentController.getInstance();
        ArrayList<Tile> tiles = tileController.getTiles();
        for(int i = 0; i < tiles.size(); i ++){

            Tile tile = tiles.get(i);
            TileView tileView = new TileView(tile.getImage());
            tileViews.add(tileView);
            tileView.update(tile);
            StackPane tilePane = tileView.maakTile();
            spelbord.setMargin(tilePane, new Insets(5,5,5,5));

            tilePane.setOnMouseClicked(e -> {
                handleTileClick(spelbord.getColumnIndex(tilePane), spelbord.getRowIndex(tilePane) );
            } );

            spelbord.add(tilePane, tile.getX() , tile.getY());

        }
        return spelbord;
    }

    public void handleTileClick(int x, int y){
        Player speler = playerController.getPlayer();
        Tile playerTile = tileController.getTileByLocation(speler.getY(), speler.getX());
        Tile clickedTile = tileController.getTileByLocation(y, x);
        if (speler.actiesOver() && clickedTile.getVariant() == Tile.Varianten.TUNNEL && playerTile.getVariant() == Tile.Varianten.TUNNEL && clickedTile.isDiscovered() && playerTile.isDiscovered() && playerTile.getZand() < 2 && clickedTile.getZand() < 2){
            speler.setLocatie(x, y);
            playerTile.removeSpeler(speler);
            clickedTile.addSpeler(speler);
            speler.useAction();
        }
        else if(aardekijkerSelected){
            equipmentController.gebruikAardekijker(x , y);
        }
        else if(duinkanonSelected){
            equipmentController.gebruikDuinkanon(x, y);
        }
        else if (jetpackSelected){
            equipmentController.gebruikJetpack(x, y);
        }
        else{
            tileController.tileClicked(x, y);
        }
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
