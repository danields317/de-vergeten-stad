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

/**
 *
 * @author Jason
 * @author ryan
 * @author daniël
 * @version 1.0.01
 * @since 25-06-2019
 */

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
    public static PlayerController getInstance  (boolean loadGame, Object classInfo) {
        if (playercont == null) {
            if(loadGame){
//                System.out.println(((Map)(StaticData.getInstance()).getRoomInfo()).get("Selectable_classes"));
//                System.out.println(classInfo);
                Map classIn =((Map)(classInfo));
//                System.out.println(((Long)(classIn.get("maxWater"))).intValue());
//                System.out.println(((Map)(StaticData.getInstance()).getRoomInfo()).get("archeoloog"));
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
            if((tileAbove.getZand() < 2 || isKlimmer) && !tileAbove.getClass().equals(Storm.class) && (tileController.getTileByLocation(player.getY(), player.getX()).getZand() < 2 || isKlimmer)){
                Tile tile = getTilelocation();
                tile.removePlayer(this.player.getClassName());

                for(Tile atile : tileController.getTiles()){
                    if(atile.getY() == (tile.getY() - 1) && atile.getX() == tile.getX() ){
                        atile.addPlayer(player.getClassName());
                    }
                }

                moveLogica(tileAbove, Player.Richingen.NOORD, isKlimmer);

                tileController.getTileByLocation((player.getY() + 1), player.getX()).notifyAllObservers();
                tileController.getTileByLocation(player.getY(), player.getX()).notifyAllObservers();
            }
        }
    }

    public void moveZuid(boolean isKlimmer){
        if(player.getY() < 4 && player.actiesOver()){
            Tile tileBeneath = tileController.getTileByLocation((player.getY() + 1), player.getX());
            if((tileBeneath.getZand() < 2 || isKlimmer) && !tileBeneath.getClass().equals(Storm.class) && (tileController.getTileByLocation(player.getY(), player.getX()).getZand() < 2 || isKlimmer)) {
                Tile tile = getTilelocation();
                tile.removePlayer(this.player.getClassName());

                for(Tile atile : tileController.getTiles()){
                    if(atile.getY() == (tile.getY() + 1) && atile.getX() == tile.getX() ){
                        atile.addPlayer(player.getClassName());
                    }
                }

                moveLogica(tileBeneath, Player.Richingen.ZUID, isKlimmer);

                tileController.getTileByLocation((player.getY() - 1), player.getX()).notifyAllObservers();
                tileController.getTileByLocation(player.getY(), player.getX()).notifyAllObservers();
            }
        }
    }

    public void moveOost(boolean isKlimmer){
        if(player.getX() < 4 && player.actiesOver()){
            Tile tileRight = tileController.getTileByLocation(player.getY(), (player.getX() + 1));
            if((tileRight.getZand() < 2 || isKlimmer) && !tileRight.getClass().equals(Storm.class) && (tileController.getTileByLocation(player.getY(), player.getX()).getZand() < 2 || isKlimmer)) {
                Tile tile = getTilelocation();
                tile.removePlayer(this.player.getClassName());

                for(Tile atile : tileController.getTiles()){
                    if(atile.getY() == tile.getY() && atile.getX() == (tile.getX() + 1) ){
                        atile.addPlayer(player.getClassName());
                    }
                }
                moveLogica(tileRight, Player.Richingen.OOST, isKlimmer);

                tileController.getTileByLocation(player.getY(), (player.getX() - 1)).notifyAllObservers();
                tileController.getTileByLocation(player.getY(), player.getX()).notifyAllObservers();
            }
        }
    }

    public void moveWest(boolean isKlimmer){
        if(player.getX() > 0 && player.actiesOver()){
            Tile tileLeft = tileController.getTileByLocation(player.getY(), (player.getX() -  1));
            if((tileLeft.getZand() < 2 || isKlimmer) && !tileLeft.getClass().equals(Storm.class) && (tileController.getTileByLocation(player.getY(), player.getX()).getZand() < 2 || isKlimmer)) {
                Tile tile = getTilelocation();
                tile.removePlayer(this.player.getClassName());

                for(Tile atile : tileController.getTiles()){
                    if(atile.getY() == tile.getY() && atile.getX() == (tile.getX() - 1) ){
                        atile.addPlayer(player.getClassName());
                    }
                }
                moveLogica(tileLeft, Player.Richingen.WEST, isKlimmer);

                tileController.getTileByLocation(player.getY(), (player.getX() + 1)).notifyAllObservers();
                tileController.getTileByLocation(player.getY(), player.getX()).notifyAllObservers();
            }
        }
    }

    public void moveNoordOost(){
        if(player.getX() < 4 && player.getY() > 0 && player.actiesOver()){
            Tile destTile = tileController.getTileByLocation((player.getY() - 1), (player.getX() + 1));
            if(destTile.getZand() < 2 && !destTile.getClass().equals(Storm.class) && tileController.getTileByLocation(player.getY(), player.getX()).getZand() < 2){
                Tile tile = getTilelocation();
                tile.removePlayer(this.player.getClassName());

                for(Tile atile : tileController.getTiles()){
                    if(atile.getY() == (tile.getY() - 1) && atile.getX() == (tile.getX() + 1) ){
                        atile.addPlayer(player.getClassName());
                    }
                }
                moveSchuinLogica(destTile, Player.RichtingenSchuin.NOORDOOST);

                tileController.getTileByLocation((player.getY() + 1), (player.getX() - 1)).notifyAllObservers();
                tileController.getTileByLocation(player.getY(), player.getX()).notifyAllObservers();
            }
        }
    }

    public void moveZuidOost(){
        if(player.getX() < 4 && player.getY() < 4 && player.actiesOver()){
            Tile destTile = tileController.getTileByLocation((player.getY() + 1), (player.getX() + 1));
            if(destTile.getZand() < 2 && !destTile.getClass().equals(Storm.class) && tileController.getTileByLocation(player.getY(), player.getX()).getZand() < 2){
                Tile tile = getTilelocation();
                tile.removePlayer(this.player.getClassName());

                for(Tile atile : tileController.getTiles()){
                    if(atile.getY() == (tile.getY() + 1) && atile.getX() == (tile.getX() + 1) ){
                        atile.addPlayer(player.getClassName());
                    }
                }
                moveSchuinLogica(destTile, Player.RichtingenSchuin.ZUIDOOST);

                tileController.getTileByLocation((player.getY() - 1), (player.getX() - 1)).notifyAllObservers();
                tileController.getTileByLocation(player.getY(), player.getX()).notifyAllObservers();
            }
        }
    }

    public void moveZuidWest(){
        if(player.getX() > 0 && player.getY() < 4 && player.actiesOver()){
            Tile destTile = tileController.getTileByLocation((player.getY() + 1), (player.getX() - 1));
            if(destTile.getZand() < 2 && !destTile.getClass().equals(Storm.class) && tileController.getTileByLocation(player.getY(), player.getX()).getZand() < 2){
                Tile tile = getTilelocation();
                tile.removePlayer(this.player.getClassName());

                for(Tile atile : tileController.getTiles()){
                    if(atile.getY() == (tile.getY() + 1) && atile.getX() == (tile.getX() - 1) ){
                        atile.addPlayer(player.getClassName());
                    }
                }
                moveSchuinLogica(destTile, Player.RichtingenSchuin.ZUIDWEST);

                tileController.getTileByLocation((player.getY() - 1), (player.getX() + 1)).notifyAllObservers();
                tileController.getTileByLocation(player.getY(), player.getX()).notifyAllObservers();
            }
        }
    }

    public void moveNoordWest(){
        if(player.getX() > 0 && player.getY() > 0 && player.actiesOver()) {
            Tile destTile = tileController.getTileByLocation((player.getY() - 1), (player.getX() - 1));
            if(destTile.getZand() < 2 && !destTile.getClass().equals(Storm.class) && tileController.getTileByLocation(player.getY(), player.getX()).getZand() < 2){
                Tile tile = getTilelocation();
                tile.removePlayer(this.player.getClassName());

                for(Tile atile : tileController.getTiles()){
                    if(atile.getY() == (tile.getY() - 1) && atile.getX() == (tile.getX() - 1) ){
                        atile.addPlayer(player.getClassName());
                    }
                }
                moveSchuinLogica(destTile, Player.RichtingenSchuin.NOORDWEST);

                tileController.getTileByLocation((player.getY() + 1), (player.getX() + 1)).notifyAllObservers();
                tileController.getTileByLocation(player.getY(), player.getX()).notifyAllObservers();
            }
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
        Tile locatie = getTilelocation();
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
            Tile tile = getTilelocation();
            digLogica(tile, isArcheoloog);
        }
    }

    public void digNoord(boolean isArcheoloog){
        if(player.getY() > 0 && player.actiesOver()) {
            Tile tile = getTilelocation();
            for(Tile atile : tileController.getTiles()){
                if(atile.getY() == (tile.getY()-1) && atile.getX() == tile.getX() ){
                    digLogica(atile, isArcheoloog);
                }
            }
        }
    }

    public void digZuid(boolean isArcheoloog){
        if(player.getY() < 4 && player.actiesOver()) {
            Tile tile = getTilelocation();
            for(Tile atile : tileController.getTiles()){
                if(atile.getY() == (tile.getY() + 1) && atile.getX() == tile.getX() ){
                    digLogica(atile, isArcheoloog);
                }
            }
        }
    }

    public void digOost(boolean isArcheoloog){
        if(player.getX() < 4 && player.actiesOver()) {
            Tile tile = getTilelocation();
            for(Tile atile : tileController.getTiles()){
                if(atile.getY() == tile.getY() && atile.getX() == (tile.getX()+ 1) ){
                    digLogica(atile, isArcheoloog);
                }
            }
        }
    }

    public void digWest(boolean isArcheoloog){
        if(player.getX() > 0 && player.actiesOver()) {
            Tile tile = getTilelocation();
            for(Tile atile : tileController.getTiles()){
                if(atile.getY() == tile.getY() && atile.getX() == (tile.getX() - 1) ){
                    digLogica(atile, isArcheoloog);
                }
            }
        }
    }

    public void digNoordOost(){
        if(player.getX() < 4 && player.getY() > 0 && player.actiesOver()){
            Tile tile = getTilelocation();
            for(Tile atile : tileController.getTiles()){
                if(atile.getY() == (tile.getY()-1) && atile.getX() == (tile.getX() + 1 )){
                    digLogica(atile, false);
                }
            }
        }
    }

    public void digZuidOost(){
        if(player.getX() < 4 && player.getY() < 4 && player.actiesOver()) {
            Tile tile = getTilelocation();
            for(Tile atile : tileController.getTiles()){
                if(atile.getY() == (tile.getY()+1) && atile.getX() == (tile.getX() + 1 )){
                    digLogica(atile, false);
                }
            }
        }
    }

    public void digZuidWest(){
        if(player.getX() > 0 && player.getY() < 4 && player.actiesOver()) {
            Tile tile = getTilelocation();
            for(Tile atile : tileController.getTiles()){
                if(atile.getY() == (tile.getY()+1) && atile.getX() == (tile.getX() - 1 )){
                    digLogica(atile, false);
                }
            }

        }
    }

    public void digNoordWest(){
        if(player.getX() > 0 && player.getY() > 0 && player.actiesOver()) {
            Tile tile = getTilelocation();
            for(Tile atile : tileController.getTiles()){
                if(atile.getY() == (tile.getY()-1) && atile.getX() == (tile.getX() - 1 )){
                    digLogica(atile, false);
                }
            }
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



    public void Uitgraven(){

    }

    public void eenOnderdeelOppakken(){

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
        player.register(sbv);
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

    public Tile getTilelocation(){
        Tile selectedTile = new Tile();
        for(Tile tile : tileController.getTiles()){

            ArrayList<String>players = tile.getPlayers();
            for(int i = 0; i < players.size(); i++){
                if(this.player.getClassName().equals(players.get(i))){
                    selectedTile = tile;
                }
            }

        }
        return selectedTile;
    }

    public boolean checkPlayerWater(){
        StaticData staticData = StaticData.getInstance();
        Map<String, Object> gebruikers = (Map)((Map)staticData.getRoomInfo()).get("Selectable_classes");
        for (int i = 0; i < 4; i++){
            if (Integer.valueOf(((Map)(gebruikers.get(Integer.toString(i)))).get("water").toString()) < 0 ){
                return true;
            }
        }
        return false;
    }

    public void update(){
        player.notifyAllObservers();
    }
}
