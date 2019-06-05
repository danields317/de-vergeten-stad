package Controller.Player_Controllers;

import Controller.Equipment_Controllers.Equipment_Controller;
import Model.player.Player;
import javafx.scene.paint.Color;

public class Player_Controller {


    static Player_Controller playercont;
    Player player;

    public Player_Controller(String nickname, String className, String description, int maxWater, Color color, String imagePath){
        player = new Player(nickname,className, description, maxWater, color, imagePath);
    }

    // Singleton Pattern.
    // now we can call: SpelbordController.getInstance()  from everywhere
    // AND it guarantees there is only 1 instance.
    public static Player_Controller getInstance() {
        if (playercont == null) {
            playercont = new Player_Controller("a", "a", "a", 5, Color.BLUE, "Homescreenempty.png");
        }
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

    public void drinkWater(){
        player.subtractWater(1);
        System.out.println(player.getWater());
    }


    public void giveWater(Player receiver, int amount){
        if(this.getPlayer().getWater() == 0){
            System.out.println("You dont have any water to given");
        } else if( receiver.getWater() >= receiver.getMaxWater()){
            System.out.println(receiver.getClassName() + " has already full water");
        }else{
            this.player.subtractWater(amount);
            receiver.addWater(amount);
        }


    }

    public Equipment_Controller[] getInventory() { return player.getInventory(); }

    public Player getPlayer() {
        return player;
    }
}
