package Controller.Player_Controllers;

import Model.data.StaticData;
import Model.player.Player;
import javafx.scene.paint.Color;
import observers.LoadBordObserver;
import observers.PlayerObserver;

import java.util.Map;

public class Player_Controller {


    static Player_Controller playercont;
    StaticData staticData = StaticData.getInstance();
    Player player;





    public Player_Controller(String className, int maxWater, int water, String imagePath){
        player = new Player(staticData.getUsername(),className, "b", maxWater, water, Color.BLUE, imagePath);
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


                System.out.println(((Map)(StaticData.getInstance()).getRoomInfo()).get("Selectable_classes"));
                System.out.println(classInfo);
                Map classIn =((Map)(classInfo));
                System.out.println(((Long)(classIn.get("maxWater"))).intValue());
                System.out.println(((Map)(StaticData.getInstance()).getRoomInfo()).get("archeoloog"));
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
        System.out.println(player.getWater());
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
}
