package Controller.Bord_Controllers;

import Controller.Login_Controllers.Login_Controller;
import Controller.Player_Controllers.Waterdrager_Controller;
import Controller.Tile_Controllers.Part_Controller;
import Controller.Tile_Controllers.Tile_Controller;
import Model.Bord.Bord;
import Model.Tiles.Tile;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.layout.GridPane;
import observers.*;

import java.util.ArrayList;

public class Bord_Controller {

    private End_Controller endController;
    private Field_Controller fieldController;
    private Item_Controller itemController;
    private Menu_Controller menuController;
    private Player_Menu_Controller playerMenuController;
    private Water_Controller waterController;
    private Zandstormmeter_Controller zandstormmeterController;
    private Group root;
    private String self;

   /* public Bord_Controller(){
       // this.root = root
        // this.self = self;
     // setUpBord();

    }*/

//    public void setUpBord(){
//        fieldController = new Field_Controller(root);
//        playerMenuController = new Player_Menu_Controller(root, self);
//
//


    static Bord_Controller bordController;
    Bord bord;

    private Bord_Controller() {
        bord = new Bord();
    }

    // Singleton Pattern.
    // now we can call: SpelbordController.getInstance()  from everywhere
    // AND it guarantees there is only 1 instance.
    public static Bord_Controller getInstance() {
        if (bordController == null) {
            bordController = new Bord_Controller();
        }
        return bordController;
    }


    public void registerObserver(BordObserver sbv) {
        bord.register(sbv);
    }




}
