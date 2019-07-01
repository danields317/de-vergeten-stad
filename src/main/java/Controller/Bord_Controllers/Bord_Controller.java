package Controller.Bord_Controllers;

import Model.Bord.Bord;
import javafx.scene.Group;
import observers.*;

public class Bord_Controller {

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
