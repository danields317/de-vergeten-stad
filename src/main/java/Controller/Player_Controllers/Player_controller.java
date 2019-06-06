package Controller.Player_Controllers;

import Model.player.Navigator;
import Model.player.Player;
import javafx.scene.paint.Color;

public class Player_controller {


    static Player_controller playercont;
    Player player;




    public Player_controller(String nickname, String className, String description, int maxWater, Color color, String imagePath){
//        player = new Player(nickname,className, description, maxWater, color, imagePath);
        player = new Player();
    }

    // Singleton Pattern.
    // now we can call: SpelbordController.getInstance()  from everywhere
    // AND it guarantees there is only 1 instance.
    public static Player_controller getInstance() {
        if (playercont == null) {
            playercont = new Player_controller("a", "a", "a", 5, Color.BLUE, "Homescreenempty.png");
        }
        return playercont;
    }

    public void beweegSpelerNoord(){
        player.beweegNoord();
    }

    public void beweegSpelerZuid(){
        player.beweegZuid();
    }

    public void beweegSpelerOost(){
        player.beweegOost();
    }

    public void beweegSpelerWest(){
        player.beweegWest();
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


    public Player getPlayer() {
        return player;
    }
}
