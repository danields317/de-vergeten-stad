package Controller.Player_Controllers;

import Controller.Bord_Controllers.Water_Controller;
import Controller.Tile_Controllers.TileController;
import Model.Bord.Onderdeel;
import Model.Tiles.PartTile;
import Model.Tiles.StartTile;
import Model.Tiles.Storm;
import Model.Tiles.Tile;
import Model.data.StaticData;
import Model.player.Player;
import javafx.scene.paint.Color;
import observers.PlayerObserver;
import observers.WaterObserver;

import java.util.ArrayList;
import java.util.Map;

public class PlayerController {


    static PlayerController playercont;
    StaticData staticData = StaticData.getInstance();
    Player player;

     Player archeoloog;
     Player klimmer;
     Player verkenner;
     Player waterdrager;

    TileController tileController = TileController.getInstance();

    public PlayerController(String className, int maxWater, int water, String imagePath, Player.SpelerKlassen klasse){
        player = new Player(staticData.getUsername(),className, "b", maxWater, water, Color.BLUE, imagePath, klasse);
        spawnSpelers();
    }


    public PlayerController(String n, String className, String b, int maxwater, Color color, String imagePath, Player.SpelerKlassen klasse){
        player = new Player(staticData.getUsername(),className, "b", maxwater, Color.BLUE, imagePath, klasse);
    }

    // Singleton Pattern.
    // now we can call: SpelbordController.getInstance()  from everywhere
    // AND it guarantees there is only 1 instance.
    public static PlayerController getInstance(boolean loadGame, Object classInfo) {
        if (playercont == null) {
            if(loadGame){
                Map classIn =((Map)(classInfo));
                playercont = new PlayerController( ((String)(classIn.get("name"))),
                        ((Long)(classIn.get("maxWater"))).intValue(),
                        ((Long)(classIn.get("water"))).intValue(),
                        ((String)(classIn.get("name"))) +".png",
                        getKlasse(classIn.get("name").toString().toLowerCase()));
            }
        }
        return playercont;
    }

    public static PlayerController getInstance() {
        return playercont;
    }

    public void spawnSpelers(){
        ArrayList<Tile> tiles = tileController.getTiles();
        Tile spawnTile = null;
        for(Tile tile:tiles){
            if (tile.getClass().equals(StartTile.class)){
                spawnTile = tile;
                break;
            }
        }
        player.setLocatie(spawnTile.getX(), spawnTile.getY());
        spawnTile.addSpeler(player);
    }


    public void moveNoord(boolean isKlimmer){
        if(player.getY() > 0 && player.actiesOver()){
            Tile tileAbove = tileController.getTileByLocation((player.getY() - 1), player.getX());

            moveLogica(tileAbove, Player.Richingen.NOORD, isKlimmer);
        }
    }

    public void moveZuid(boolean isKlimmer){
        if(player.getY() < 4 && player.actiesOver()){
            Tile tileBeneath = tileController.getTileByLocation((player.getY() + 1), player.getX());

            moveLogica(tileBeneath, Player.Richingen.ZUID, isKlimmer);
        }
    }

    public void moveOost(boolean isKlimmer){
        if(player.getX() < 4 && player.actiesOver()){
            Tile tileRight = tileController.getTileByLocation(player.getY(), (player.getX() + 1));

            moveLogica(tileRight, Player.Richingen.OOST, isKlimmer);
        }
    }

    public void moveWest(boolean isKlimmer){
        if(player.getX() > 0 && player.actiesOver()){
            Tile tileLeft = tileController.getTileByLocation(player.getY(), (player.getX() -  1));

            moveLogica(tileLeft, Player.Richingen.WEST, isKlimmer);
        }
    }

    public void moveNoordOost(){
        if(player.getX() < 4 && player.getY() > 0 && player.actiesOver()) {
            Tile destTile = tileController.getTileByLocation((player.getY() - 1), (player.getX() + 1));
            moveSchuinLogica(destTile, Player.RichtingenSchuin.NOORDOOST);
        }
    }

    public void moveZuidOost(){
        if(player.getX() < 4 && player.getY() < 4 && player.actiesOver()) {
            Tile destTile = tileController.getTileByLocation((player.getY() + 1), (player.getX() + 1));
            moveSchuinLogica(destTile, Player.RichtingenSchuin.ZUIDOOST);
        }
    }

    public void moveZuidWest(){
        if(player.getX() > 0 && player.getY() < 4 && player.actiesOver()) {
            Tile destTile = tileController.getTileByLocation((player.getY() + 1), (player.getX() - 1));
            moveSchuinLogica(destTile, Player.RichtingenSchuin.ZUIDWEST);
        }
    }

    public void moveNoordWest(){
        if(player.getX() > 0 && player.getY() > 0 && player.actiesOver()) {
            Tile destTile = tileController.getTileByLocation((player.getY() - 1), (player.getX() - 1));
            moveSchuinLogica(destTile, Player.RichtingenSchuin.NOORDWEST);
        }
    }

    private void moveSchuinLogica(Tile tile, Player.RichtingenSchuin richting){
        if(tile.getZand() < 2 && !tile.getClass().equals(Storm.class) && tileController.getTileByLocation(player.getY(), player.getX()).getZand() < 2){
            tileController.getTileByLocation(player.getY(), player.getX()).removeSpeler(player);
            player.movePlayerSchuin(richting);
            player.useAction();
            tile.addSpeler(player);
        }
    }

    private void moveLogica(Tile tile, Player.Richingen riching, boolean isKlimmer){
        if((tile.getZand() < 2 || isKlimmer) && !tile.getClass().equals(Storm.class) && (tileController.getTileByLocation(player.getY(), player.getX()).getZand() < 2 || isKlimmer)){
            tileController.getTileByLocation(player.getY(), player.getX()).removeSpeler(player);
            player.movePlayer(riching);
            player.useAction();
            tile.addSpeler(player);
        }
    }

    public void tileActies(){
        Tile locatie = tileController.getTileByLocation(player.getY(), player.getX());
        if (!locatie.isDiscovered() && player.actiesOver() && !locatie.hasZand()){
            locatie.discoverTile();
            tileController.useTileDiscoveredAction(player.getX(), player.getY());
            player.useAction();
        }
        else if (player.actiesOver() && locatie.getZand() < 2 && locatie.hasOnderdeel()){
            locatie.getOnderdelen().get(0).pakOp();
            locatie.removeOnderdeel();
            player.useAction();
        }
    }

    public void digHere(boolean isArcheoloog){
        if (player.actiesOver()){
            Tile locatie = tileController.getTileByLocation(player.getY(), player.getX());
            digLogica(locatie, isArcheoloog);
        }
    }

    public void digNoord(boolean isArcheoloog){
        if(player.getY() > 0 && player.actiesOver()) {
            Tile locatie = tileController.getTileByLocation((player.getY() - 1), player.getX());
            digLogica(locatie, isArcheoloog);
        }
    }

    public void digZuid(boolean isArcheoloog){
        if(player.getY() < 4 && player.actiesOver()) {
            Tile locatie = tileController.getTileByLocation((player.getY() + 1), player.getX());
            digLogica(locatie, isArcheoloog);
        }
    }

    public void digOost(boolean isArcheoloog){
        if(player.getX() < 4 && player.actiesOver()) {
            Tile locatie = tileController.getTileByLocation(player.getY(), (player.getX() + 1));
            digLogica(locatie, isArcheoloog);
        }
    }

    public void digWest(boolean isArcheoloog){
        if(player.getX() > 0 && player.actiesOver()) {
            Tile locatie = tileController.getTileByLocation(player.getY(), (player.getX() - 1));
            digLogica(locatie, isArcheoloog);
        }
    }

    public void digNoordOost(){
        if(player.getX() < 4 && player.getY() > 0 && player.actiesOver()){
            Tile locatie = tileController.getTileByLocation((player.getY() - 1), (player.getX() +1));
            digLogica(locatie, false);
        }
    }

    public void digZuidOost(){
        if(player.getX() < 4 && player.getY() < 4 && player.actiesOver()) {
            Tile locatie = tileController.getTileByLocation((player.getY() + 1), (player.getX() + 1));
            digLogica(locatie, false);
        }
    }

    public void digZuidWest(){
        if(player.getX() > 0 && player.getY() < 4 && player.actiesOver()) {
            Tile locatie = tileController.getTileByLocation((player.getY() + 1), (player.getX() - 1));
            digLogica(locatie, false);
        }
    }

    public void digNoordWest(){
        if(player.getX() > 0 && player.getY() > 0 && player.actiesOver()) {
            Tile locatie = tileController.getTileByLocation((player.getY() - 1), (player.getX() - 1));
            digLogica(locatie, false);
        }
    }

    private void digLogica(Tile locatie, boolean isArcheoloog){
        if (locatie.hasZand()){
            locatie.removeZandTegel();
            player.useAction();
        }
        if(locatie.hasZand() && isArcheoloog){
            locatie.removeZandTegel();
        }
    }

    public void schepWater(){
        Tile tile = tileController.getTileByLocation(player.getY(), player.getX());
        if(tile.getVariant() == Tile.Varianten.WATERPUT){
            player.addWater(2);
            player.useAction();
        }
    }

    public void removeWater(){
        Tile tile = tileController.getTileByLocation(player.getY(), player.getX());
        if(!tile.hasZonneSchild() && tile.getVariant() != Tile.Varianten.TUNNEL ) {
            player.subtractWater(1);
        }
    }


    public void giveWater(Player.SpelerKlassen receiver, int amount){

        Player receiverPlayer = player;

        switch (receiver){
            case ARCHEOLOOG:
                receiverPlayer = archeoloog;
                break;
            case KLIMMER:
                receiverPlayer = klimmer;
                break;
            case VERKENNER:
                receiverPlayer = verkenner;
                break;
            case WATERDRAGER:
                receiverPlayer = waterdrager;
                break;
        }

        if(this.getPlayer().getWater() == 0){
            System.out.println("You dont have any water to give");
        } else if( receiverPlayer.getWater() >= receiverPlayer.getMaxWater()){
            System.out.println(receiverPlayer.getClassName() + " has already full water");
        }else{
            this.player.subtractWater(amount);
            receiverPlayer.addWater(amount);
        }


    }

    public void registerObserver(PlayerObserver sbv) {
        System.out.println(sbv);player.register(sbv);
    }

    public Player getPlayer() {
        return player;
    }

    public void updateData(){
        player.updateData();
    }

    private static Player.SpelerKlassen getKlasse(String klasse){
        if (klasse.equals(Player.SpelerKlassen.ARCHEOLOOG.toString().toLowerCase())){
            return Player.SpelerKlassen.ARCHEOLOOG;
        } else if (klasse.equals(Player.SpelerKlassen.VERKENNER.toString().toLowerCase())){
            return Player.SpelerKlassen.VERKENNER;
        } else if (klasse.equals(Player.SpelerKlassen.WATERDRAGER.toString().toLowerCase())){
            return Player.SpelerKlassen.WATERDRAGER;
        } else if (klasse.equals(Player.SpelerKlassen.KLIMMER.toString().toLowerCase())){
            return Player.SpelerKlassen.KLIMMER;
        }
        return null;
    }

    public void update(){
        player.notifyAllObservers();
    }
}
