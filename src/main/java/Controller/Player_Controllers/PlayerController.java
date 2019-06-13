package Controller.Player_Controllers;

import Controller.Tile_Controllers.TileController;
import Model.Tiles.StartTile;
import Model.Tiles.Tile;
import Model.data.StaticData;
import Model.player.Player;
import Model.storm.StormEventBeweging;
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

    public void moveSpeler(StormEventBeweging.Richtingen stormRichting, StormEventBeweging.Stappen stappen, int stormX, int stormY){
        switch (stormRichting){
            case NOORD:
                moveSpelerZuid(stappen, stormX, stormY);
                break;
            case OOST:
                moveSpelerWest(stappen, stormX, stormY);
                break;
            case ZUID:
                moveSpelerNoord(stappen, stormX, stormY);;
                break;
            case WEST:
                moveSpelerOost(stappen, stormX, stormY);
                break;
            default:
                System.out.println("Dit hoort niet");
        }
    }

    private void moveSpelerNoord(StormEventBeweging.Stappen stappen, int stormX, int stormY){
        moveTile(stappen, stormX, stormY,0,1);
    }
    private void moveSpelerOost(StormEventBeweging.Stappen stappen, int stormX, int stormY){
        moveTile(stappen, stormX, stormY, -1, 0);
    }
    private void moveSpelerZuid(StormEventBeweging.Stappen stappen, int stormX, int stormY){
        moveTile(stappen, stormX, stormY, 0, -1);
    }
    private void moveSpelerWest(StormEventBeweging.Stappen stappen, int stormX, int stormY){
        moveTile(stappen, stormX, stormY, 1, 0);
    }

    private void moveTile(StormEventBeweging.Stappen stappen, int stormX, int stormY, int moveStormX, int moveStormY){
        for (int i = 0; i < stappen.getNumber(); i++){
            if (stormY+moveStormY <= 4 && stormX+moveStormX >= 0 && stormY+moveStormY >= 0 && stormX+moveStormX <= 4){

                player.setLocatie(stormX+moveStormX, stormY+moveStormY);

                tileController.getTileByLocation(player.getY(), player.getX()).notifyAllObservers();

                stormY = stormY + moveStormY;
                stormX = stormX + moveStormX;
            }
        }
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
