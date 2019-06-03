package Controller.Player_Controllers;

import Model.player.*;
import javafx.scene.paint.Color;

public class Archeoloog_Controller extends Player_controller implements SpecialMove {

    public Archeoloog_Controller(String nickname){
        super( nickname, "Archeoloog", "PLACEHOLDER", 3, Color.ORANGE, "resources/placeholder.png" );
    }
}
