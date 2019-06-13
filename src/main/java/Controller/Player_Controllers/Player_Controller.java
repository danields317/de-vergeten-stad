package Controller.Player_Controllers;

import Controller.Tile_Controllers.TileController;
import Model.Tiles.StartTile;
import Model.Tiles.Tile;
import Model.data.StaticData;
import Model.player.Player;
import javafx.scene.paint.Color;
import observers.LoadBordObserver;
import observers.PlayerObserver;

import java.util.ArrayList;
import java.util.Map;

public class Player_Controller {


    static Player_Controller playercont;
    StaticData staticData = StaticData.getInstance();
    Player player;

    TileController tileController = TileController.getInstance();

    public Player_Controller(String className, int maxWater, int water, String imagePath){
        player = new Player(staticData.getUsername(),className, "b", maxWater, water, Color.BLUE, imagePath);
        spawnSpelers();
    }


    public Player_Controller(String n, String className, String b, int maxwater, Color color, String imagePath){
        player = new Player(staticData.getUsername(),className, "b", 4, Color.BLUE, imagePath);
    }
    // Singleton Pattern.
    // now we can call: SpelbordController.getInstance()  from everywhere
    // AND it guarantees there is only 1 instance.
    public static Player_Controller getInstance(boolean loadGame, Object classInfo) {
        if (playercont == null) {
            if( loadGame){


//                System.out.println(((Map)(StaticData.getInstance()).getRoomInfo()).get("Selectable_classes"));
//                System.out.println(classInfo);
                Map classIn =((Map)(classInfo));
//                System.out.println(((Long)(classIn.get("maxWater"))).intValue());
//                System.out.println(((Map)(StaticData.getInstance()).getRoomInfo()).get("archeoloog"));
                playercont = new Player_Controller(((String)(classIn.get("name"))),
                        ((Long)(classIn.get("maxWater"))).intValue(),
                        ((Long)(classIn.get("water"))).intValue(),
                        ((String)(classIn.get("name"))) +".png");


            }else{}
        }
        return playercont;
    }

    public static Player_Controller getInstance() {
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


    public void move(){

    }

    public void zandWegScheppen(){

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
