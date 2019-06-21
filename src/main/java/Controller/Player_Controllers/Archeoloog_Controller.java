package Controller.Player_Controllers;

import Model.player.*;
import javafx.scene.paint.Color;

public class Archeoloog_Controller extends PlayerController {

    public Archeoloog_Controller(String nickname){
        super( nickname, "Archeoloog", "PLACEHOLDER", 3, Color.ORANGE, "placeholder.png", Player.SpelerKlassen.ARCHEOLOOG);
    }
}
