package Controller.Bord_Controllers;

import Controller.Player_Controllers.PlayerController;
import Model.Bord.Acties;
import observers.Acties_Observer;
import observers.PlayerObservable;
import observers.PlayerObserver;

public class Acties_Controller implements PlayerObserver {
    static Acties_Controller acties_controller;
    Acties acties;
    PlayerController player_controller = PlayerController.getInstance();

    public Acties_Controller() {
        this.acties = new Acties();

    }

    public static Acties_Controller getInstance(){
        if (acties_controller == null) {
            acties_controller = new Acties_Controller();

        }
        return acties_controller;
    }

    public void registerObserver(Acties_Observer observer){
        acties.register(observer);
    }

    public void register(){
        player_controller.registerObserver(this);
    }

    @Override
    public void update(PlayerObservable sb){
        acties.maakFotoArray(sb);
    }
}
