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

/**
 * Deze klasse handelt het gebruik van equipment door een speler af
 *
 * @author Daniel
 */

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

    /**
     * Zet de aardekijker equipment kaart op actief om deze te gaan gebruiken.
     * Een andere kaart die actief stond wordt gedeactiveerd.
     */

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

    /**
     * Zet de duinkanon equipment kaart op actief om deze te gaan gebruiken.
     * Een andere kaart die actief stond wordt gedeactiveerd.
     */

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

    /**
     * Zet de jetpack equipment kaart op actief om deze te gaan gebruiken.
     * Een andere kaart die actief stond wordt gedeactiveerd.
     */

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

    /**
     * Gebruikt de aardekijker om de ontdekte kant van een onontdekte tile te bekijken.
     * Verwijdert hierna de aardekijker uit de inventory en zet de kaart weer inactief.
     * @param x-as van de tile waar de speler op klikte
     * @param y-as van de tile waar de speler op klikte
     */

    public void gebruikAardekijker(int x, int y){
        Tile tile = tileController.getTileByLocation(y , x);
        AardekijkerPopup aardekijkerPopup = new AardekijkerPopup(tile.getDiscoveredImage());
        playerController.getPlayer().removeEquipment(Equipment.EquipmentKaarten.AARDEKIJKER);
        setAardekijkerStatus();
    }

    /**
     * Gebuikt de aardekijker op een tile om al het zand te verwijderen.
     * Verwijdert hierna de aardekijker uit de inventory en zet de kaart weer inactief.
     * @param x-as van de tile waar de speler op klikte
     * @param y-as van de tile waar de speler op klikte
     */

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

    /**
     * Gebuikt de Jetpack om de speler naar een andere tile te verplaatsen.
     * Verwijdert hierna de aardekijker uit de inventory en zet de kaart weer inactief.
     * @param x-as van de tile waar de speler op klikte
     * @param y-as van de tile waar de speler op klikte
     */

    public void gebruikJetpack(int x, int y){
        Tile currentTile = tileController.getTileByLocation(playerController.getPlayer().getY(), playerController.getPlayer().getX());
        Tile landTile = tileController.getTileByLocation(y, x);
        if(currentTile.getZand() < 2 && landTile.getZand() < 2){
            currentTile.removeSpeler(playerController.getPlayer());
            landTile.addSpeler(playerController.getPlayer());
            playerController.getPlayer().setLocatie(landTile.getX(), landTile.getY());
            setJetpackStatus();
            playerController.getPlayer().removeEquipment(Equipment.EquipmentKaarten.JETPACK);
        }
    }

    /**
     * Geeft de speler twee extra acties.
     * Verwijdert hierna de aardekijker uit de inventory en zet de kaart weer inactief.
     */

    public void gebruikTijdschakelaar(){
        playerController.getPlayer().getTweeActies();
        playerController.getPlayer().removeEquipment(Equipment.EquipmentKaarten.TIJDSCHAKELAAR);
    }

    /**
     * Geeft alle speler op een tile 2 extra water.
     * Verwijdert hierna de aardekijker uit de inventory en zet de kaart weer inactief.
     */

    public void gebruikWaterreserve(){
        int pX = playerController.getPlayer().getX();
        int pY = playerController.getPlayer().getY();
        Tile tile = tileController.getTileByLocation(pY, pX);
        for(Player speler : tile.getSpelers()){
            speler.addWater(2);
        }
        playerController.getPlayer().removeEquipment(Equipment.EquipmentKaarten.WATERRESERVE);
    }

    /**
     * Zet een zonneschild op een tile om niet verbrand te worden.
     */

    public void gebruikZonneschild(){
        Tile tile = tileController.getTileByLocation(playerController.getPlayer().getY(), playerController.getPlayer().getX());
        tile.setZonneSchild();
        playerController.getPlayer().removeEquipment(Equipment.EquipmentKaarten.ZONNESCHILD);
    }

    public void registerObserver(PlayerObserver ob){
        playerController.getPlayer().register(ob);
    }


}
