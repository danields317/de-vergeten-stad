package Controller.Equipment_Controllers;

import Controller.Player_Controllers.PlayerController;
import Controller.Tile_Controllers.TileController;
import Model.Tiles.Tile;
import Model.equipment.Equipment;
import Model.equipment.Zonneschild;
import Model.player.Player;
import View.bord_views.SpeelbordView;
import javafx.scene.image.Image;

public class EquipmentController {

    private static EquipmentController equipmentController;
    PlayerController playerController;
    TileController tileController;
    SpeelbordView speelbordView;

    public EquipmentController(){
        playerController = PlayerController.getInstance();
        tileController = TileController.getInstance();
        speelbordView = SpeelbordView.getInstance();
    }

    public static EquipmentController getInstance(){
        if (equipmentController == null){
            equipmentController = new EquipmentController();
        }
        return equipmentController;
    }

    public void setAardekijkerStatus(){
        speelbordView.duinkanonSelected = false;
        speelbordView.jetpackSelected = false;
        if(!speelbordView.aardekijkerSelected){
            speelbordView.aardekijkerSelected = true;
        }
        else{
            speelbordView.aardekijkerSelected = false;
        }
    }

    public void setDuinkanonStatus(){
        speelbordView.aardekijkerSelected = false;
        speelbordView.jetpackSelected = false;
        if(!speelbordView.duinkanonSelected){
            speelbordView.duinkanonSelected = true;
        }
        else{
            speelbordView.duinkanonSelected = false;
        }
    }

    public void setJetpackStatus(){
        speelbordView.aardekijkerSelected = false;
        speelbordView.duinkanonSelected = false;
        if(!speelbordView.jetpackSelected){
            speelbordView.jetpackSelected = true;
        }
        else{
            speelbordView.jetpackSelected = false;
        }
    }

    public void gebruikAardekijker(int x, int y){
        Tile tile = tileController.getTileByLocation(y , x);
        System.out.println(tile.getVariant());
    }

    public void gebruikDuinkanon(int x, int y){
        int pX = playerController.getPlayer().getX();
        int pY = playerController.getPlayer().getY();
        Tile tile = tileController.getTileByLocation(y, x);
        if ((x == pX && y >= pY-1 && y <= pY +1) || (y == pY && x >= pX-1 && x <= pX+1)) {
            tile.removeAllZand();
        }
    }

    public void gebruikJetpack(int x, int y){
        Tile currentTile = tileController.getTileByLocation(playerController.getPlayer().getY(), playerController.getPlayer().getX());
        Tile landTile = tileController.getTileByLocation(y, x);
        if(currentTile.getZand() < 2 && landTile.getZand() < 2){
            currentTile.removeSpeler(playerController.getPlayer());
            landTile.addSpeler(playerController.getPlayer());
            playerController.getPlayer().setLocatie(landTile.getX(), landTile.getY());
        }
    }

    public void gebruikTijdschakelaar(){
        playerController.getPlayer().getTweeActies();
    }

    public void gebruikWaterreserve(){
        int pX = playerController.getPlayer().getX();
        int pY = playerController.getPlayer().getY();
        Tile tile = tileController.getTileByLocation(pY, pX);
        for(Player speler : tile.getSpelers()){
            speler.addWater(2);
        }
    }

    public void gebruikZonneschild(){
        Tile tile = tileController.getTileByLocation(playerController.getPlayer().getY(), playerController.getPlayer().getX());
        tile.setZonneSchild();
    }


}
