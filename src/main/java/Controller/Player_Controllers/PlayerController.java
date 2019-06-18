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


    public void moveNoord(){
        if(player.getY() > 0 && player.actiesOver()){
            Tile tileAbove = tileController.getTileByLocation((player.getY() - 1), player.getX());

            moveLogica(tileAbove, Player.Richingen.NOORD);

            tileController.getTileByLocation((player.getY() + 1), player.getX()).notifyAllObservers();
            tileController.getTileByLocation(player.getY(), player.getX()).notifyAllObservers();
        }
    }

    public void moveZuid(){
        if(player.getY() < 4 && player.actiesOver()){
            Tile tileBeneath = tileController.getTileByLocation((player.getY() + 1), player.getX());

            moveLogica(tileBeneath, Player.Richingen.ZUID);

            tileController.getTileByLocation((player.getY() - 1), player.getX()).notifyAllObservers();
            tileController.getTileByLocation(player.getY(), player.getX()).notifyAllObservers();
        }
    }

    public void moveOost(){
        if(player.getX() < 4 && player.actiesOver()){
            Tile tileRight = tileController.getTileByLocation(player.getY(), (player.getX() + 1));

            moveLogica(tileRight, Player.Richingen.OOST);

            tileController.getTileByLocation(player.getY(), (player.getX() - 1)).notifyAllObservers();
            tileController.getTileByLocation(player.getY(), player.getX()).notifyAllObservers();
        }
    }

    public void moveWest(){
        if(player.getX() > 0 && player.actiesOver()){
            Tile tileLeft = tileController.getTileByLocation(player.getY(), (player.getX() -  1));

            moveLogica(tileLeft, Player.Richingen.WEST);

            tileController.getTileByLocation(player.getY(), (player.getX() + 1)).notifyAllObservers();
            tileController.getTileByLocation(player.getY(), player.getX()).notifyAllObservers();
        }
    }

    private void moveLogica(Tile tile, Player.Richingen riching){
        if(tile.getZand() < 2 && !tile.getClass().equals(Storm.class)){
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

    public void digHere(){
        if (player.actiesOver()){
            Tile locatie = tileController.getTileByLocation(player.getY(), player.getX());
            digLogica(locatie);
        }
    }

    public void digNoord(){
        if(player.getY() > 0 && player.actiesOver()) {
            Tile locatie = tileController.getTileByLocation((player.getY() - 1), player.getX());
            digLogica(locatie);
        }
    }

    public void digZuid(){
        if(player.getY() < 4 && player.actiesOver()) {
            Tile locatie = tileController.getTileByLocation((player.getY() + 1), player.getX());
            digLogica(locatie);
        }
    }

    public void digOost(){
        if(player.getX() < 4 && player.actiesOver()) {
            Tile locatie = tileController.getTileByLocation(player.getY(), (player.getX() + 1));
            digLogica(locatie);
        }
    }

    public void digWest(){
        if(player.getX() > 0 && player.actiesOver()) {
            Tile locatie = tileController.getTileByLocation(player.getY(), (player.getX() - 1));
            digLogica(locatie);
        }
    }

    private void digLogica(Tile locatie){
        if (locatie.hasZand()){
            locatie.removeZandTegel();
            player.useAction();
        }
    }


    public void Uitgraven(){

    }

    public void eenOnderdeelOppakken(){

    }

    public void removeWater(){
        player.subtractWater(1);
    }


    public void giveWater(Player receiver, int amount){
        if(this.getPlayer().getWater() == 0){
            System.out.println("You dont have any water to give");
        } else if( receiver.getWater() >= receiver.getMaxWater()){
            System.out.println(receiver.getClassName() + " has already full water");
        }else{
            this.player.subtractWater(amount);
            receiver.addWater(amount);
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

    public void update(){
        player.notifyAllObservers();
    }
}
