package View.bord_views;

import Controller.Bord_Controllers.Player_Menu_Controller;
import observers.Player_Menu_Observable;
import observers.Player_Menu_Observer;

public class Player_Menu_View implements Player_Menu_Observer {
    Player_Menu_Controller player_menu_controller;

    public Player_Menu_View(){
        this.player_menu_controller = Player_Menu_Controller.getInstance();
        player_menu_controller.registerObserver(this);
    }

    @Override
    public void update(Player_Menu_Observable sb){

    }
}
