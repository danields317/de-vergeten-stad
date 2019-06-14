package Controller.Player_Controllers;

import Controller.Tile_Controllers.TileController;
import Model.Tiles.StartTile;
import Model.Tiles.Storm;
import Model.Tiles.Tile;
import Model.data.StaticData;
import Model.player.Player;
import javafx.scene.paint.Color;
import observers.PlayerObserver;

import java.util.ArrayList;
import java.util.Map;

public class PlayerController {


    static PlayerController playercont;
    StaticData staticData = StaticData.getInstance();
    Player player;

    TileController tileController = TileController.getInstance();

    public PlayerController(String className, int maxWater, int water, String imagePath){
        player = new Player(staticData.getUsername(),className, "b", maxWater, water, Color.BLUE, imagePath);
        spawnSpelers();
    }


    public PlayerController(String n, String className, String b, int maxwater, Color color, String imagePath){
        player = new Player(staticData.getUsername(),className, "b", 4, Color.BLUE, imagePath);
    }
    // Singleton Pattern.
    // now we can call: SpelbordController.getInstance()  from everywhere
    // AND it guarantees there is only 1 instance.
    public static PlayerController getInstance(boolean loadGame, Object classInfo) {
        if (playercont == null) {
            if( loadGame){


//                System.out.println(((Map)(StaticData.getInstance()).getRoomInfo()).get("Selectable_classes"));
//                System.out.println(classInfo);
                Map classIn =((Map)(classInfo));
//                System.out.println(((Long)(classIn.get("maxWater"))).intValue());
//                System.out.println(((Map)(StaticData.getInstance()).getRoomInfo()).get("archeoloog"));
                playercont = new PlayerController(((String)(classIn.get("name"))),
                        ((Long)(classIn.get("maxWater"))).intValue(),
                        ((Long)(classIn.get("water"))).intValue(),
                        ((String)(classIn.get("name"))) +".png");


            }else{}
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
        System.out.println(spawnTile.getX() + " " +  spawnTile.getY());
        System.out.println("set spawn playercontroller");
    }


    public void moveNoord(){
        if(player.getY() > 0){
            Tile tileAbove = tileController.getTileByLocation((player.getY() - 1), player.getX());
            if(tileAbove.getZand() < 2 && !tileAbove.getClass().equals(Storm.class)) {
                player.movePlayer(Player.Richingen.NOORD);
            }
        }
        tileController.getTileByLocation((player.getY() + 1), player.getX()).notifyAllObservers();
        tileController.getTileByLocation(player.getY(), player.getX()).notifyAllObservers();
    }

    public void moveZuid(){
        if(player.getY() < 4){
            Tile tileBeneath = tileController.getTileByLocation((player.getY() + 1), player.getX());
            if(tileBeneath.getZand() < 2 && !tileBeneath.getClass().equals(Storm.class)){
                player.movePlayer(Player.Richingen.ZUID);
            }
        }
        tileController.getTileByLocation((player.getY() - 1), player.getX()).notifyAllObservers();
        tileController.getTileByLocation(player.getY(), player.getX()).notifyAllObservers();
    }

    public void moveOost(){
        if(player.getX() < 4){
            Tile tileRight = tileController.getTileByLocation(player.getY(), (player.getX() + 1));
            if(tileRight.getZand() < 2 && !tileRight.getClass().equals(Storm.class)){
                player.movePlayer(Player.Richingen.OOST);
            }
        }
        tileController.getTileByLocation(player.getY(), (player.getX() - 1)).notifyAllObservers();
        tileController.getTileByLocation(player.getY(), player.getX()).notifyAllObservers();
    }

    public void moveWest(){
        if(player.getX() > 0){
            Tile tileLeft = tileController.getTileByLocation(player.getY(), (player.getX() -  1));
            if(tileLeft.getZand()  < 2 && !tileLeft.getClass().equals(Storm.class)){
                player.movePlayer(Player.Richingen.WEST);
            }
        }
        tileController.getTileByLocation(player.getY(), (player.getX() + 1)).notifyAllObservers();
        tileController.getTileByLocation(player.getY(), player.getX()).notifyAllObservers();
    }

    public void tileActies(){
        Tile locatie = tileController.getTileByLocation(player.getY(), player.getX());
        if (!locatie.isDiscovered()){
            locatie.discoverTile();
        }
        else {
            //oppakken onderdeel
        }
    }

    public void digHere(){
        Tile locatie = tileController.getTileByLocation(player.getY(), player.getX());
        locatie.removeZandTegel();
    }

    public void digNoord(){
        if(player.getY() > 0) {
            Tile locatie = tileController.getTileByLocation((player.getY() - 1), player.getX());
            locatie.removeZandTegel();
        }
    }

    public void digZuid(){
        if(player.getY() < 4) {
            Tile locatie = tileController.getTileByLocation((player.getY() + 1), player.getX());
            locatie.removeZandTegel();
        }
    }

    public void digOost(){
        if(player.getX() < 4) {
            Tile locatie = tileController.getTileByLocation(player.getY(), (player.getX() + 1));
            locatie.removeZandTegel();
        }
    }

    public void digWest(){
        if(player.getX() > 0) {
            Tile locatie = tileController.getTileByLocation(player.getY(), (player.getX() - 1));
            locatie.removeZandTegel();
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

    public void update(){
        player.notifyAllObservers();
    }
}
