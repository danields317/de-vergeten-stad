package Controller.Bord_Controllers;

import Model.Bord.Player_Menu;
import Model.player.Users;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import observers.Player_Menu_Observable;
import observers.Player_Menu_Observer;

/**
 * @author Bram de Jong
 * @version 1.0
 */
public class Player_Menu_Controller implements Player_Menu_Observer {
    static Player_Menu_Controller player_menu_controller;
    Player_Menu player_menu;

    public Player_Menu_Controller() {
        this.player_menu = new Player_Menu();
    }

    public static Player_Menu_Controller getInstance(){
        //Kijkt of er een player_menu_controller is en maakt er eentje uit als het niet zo is.
        if (player_menu_controller == null) {
            player_menu_controller = new Player_Menu_Controller();

        }
        return player_menu_controller;
    }

    public void registerObserver(Player_Menu_Observer observer){
        player_menu.register(observer);
    }


    @Override
    public void update(Player_Menu_Observable sb){
    }
}