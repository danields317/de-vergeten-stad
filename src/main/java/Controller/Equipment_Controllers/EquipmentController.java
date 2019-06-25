package Controller.Equipment_Controllers;

import Controller.Player_Controllers.PlayerController;
import Controller.Tile_Controllers.TileController;
import Model.Tiles.Tile;
import Model.equipment.Equipment;
import Model.equipment.Zonneschild;
import Model.player.Player;
import View.AardekijkerPopup;
import View.bord_views.Acties_View;
import View.bord_views.SpeelbordView;
import javafx.scene.image.Image;
import observers.PlayerObserver;

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
        AardekijkerPopup aardekijkerPopup = new AardekijkerPopup(tile.getDiscoveredImage());
        playerController.getPlayer().removeEquipment(Equipment.EquipmentKaarten.AARDEKIJKER);
        setAardekijkerStatus();
    }

    public void gebruikDuinkanon(int x, int y){
        int pX = playerController.getPlayer().getX();
        int pY = playerController.getPlayer().getY();
        Tile tile = tileController.getTileByLocation(y, x);
        if ((x == pX && y >= pY-1 && y <= pY +1) || (y == pY && x >= pX-1 && x <= pX+1)) {
            tile.removeAllZand();
            setDuinkanonStatus();
            playerController.getPlayer().removeEquipment(Equipment.EquipmentKaarten.DUINKANON);
        }
    }

    public void gebruikJetpack(int x, int y){
        Tile currentTile = playerController.getTilelocation();
        Tile landTile = tileController.getTileByLocation(y, x);
        if(currentTile.getZand() < 2 && landTile.getZand() < 2){
            currentTile.removePlayer(playerController.getPlayer().getClassName());
            landTile.addPlayer(playerController.getPlayer().getClassName());
            playerController.getPlayer().setLocatie(landTile.getX(), landTile.getY());
            setJetpackStatus();
            playerController.getPlayer().removeEquipment(Equipment.EquipmentKaarten.JETPACK);

            currentTile.notifyAllObservers();
        }
    }

    public void gebruikTijdschakelaar(){
        playerController.getPlayer().getTweeActies();
        playerController.getPlayer().removeEquipment(Equipment.EquipmentKaarten.TIJDSCHAKELAAR);
    }

    public void gebruikWaterreserve(){
        int pX = playerController.getPlayer().getX();
        int pY = playerController.getPlayer().getY();
        Tile tile = tileController.getTileByLocation(pY, pX);
        for(Player speler : tile.getSpelers()){
            speler.addWater(2);
        }
        playerController.getPlayer().removeEquipment(Equipment.EquipmentKaarten.WATERRESERVE);
    }

    public void gebruikZonneschild(){
        Tile tile = tileController.getTileByLocation(playerController.getPlayer().getY(), playerController.getPlayer().getX());
        tile.setZonneSchild();
        playerController.getPlayer().removeEquipment(Equipment.EquipmentKaarten.ZONNESCHILD);
    }

    public void registerObserver(PlayerObserver ob){
        playerController.getPlayer().register(ob);
    }


}
