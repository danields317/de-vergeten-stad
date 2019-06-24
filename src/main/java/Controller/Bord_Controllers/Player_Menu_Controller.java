package Controller.Bord_Controllers;

import Controller.Player_Controllers.PlayerController;
import Model.Bord.Player_Menu;
import observers.PlayerObservable;
import observers.PlayerObserver;
import observers.Player_Menu_Observable;
import observers.Player_Menu_Observer;

public class Player_Menu_Controller implements PlayerObserver {
    static Player_Menu_Controller player_menu_controller;
    Player_Menu player_menu;
    PlayerController playerController = PlayerController.getInstance();

    public Player_Menu_Controller() {
        this.player_menu = new Player_Menu();
    }

    public static Player_Menu_Controller getInstance(){
        if (player_menu_controller == null) {
            player_menu_controller = new Player_Menu_Controller();

        }
        return player_menu_controller;
    }

    public void registerObserver(Player_Menu_Observer observer){
        player_menu.register(observer);
    }

    public void registerObservert() {
        playerController.registerObserver((PlayerObserver) this);
    }

    @Override
    public void update(PlayerObservable sb) {
        player_menu.notifyAllObservers();
    }

    public void update(){
        player_menu.notifyAllObservers();
    }

}