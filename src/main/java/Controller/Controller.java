package Controller;

import Controller.Player_Controllers.*;

public class Controller{

    static Controller controller;
    PlayerController playcont = PlayerController.getInstance();

    public static Controller getInstance() {
        if (controller == null) {
            controller = new Controller();
        }
        return controller;
    }

    public void zonBrand(){
        playcont.removeWater();
    }
}
