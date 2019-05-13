package Controller.Player_Controllers;

import Model.player.Navigator;
import Model.player.Player;
import javafx.scene.paint.Color;

public class Player_controller {

    private Player player;

    public Player_controller(String nickname, String className, String description, int maxWater, Color color, String imagePath){
        this.player = new Player(nickname,className, description, maxWater, color, imagePath);
    }

    public void move(){

    }

    public void zandWegScheppen(){

    }

    public void Uitgraven(){

    }

    public void eenOnderdeelOppakken(){

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


    public Player getPlayer() {
        return player;
    }
}
