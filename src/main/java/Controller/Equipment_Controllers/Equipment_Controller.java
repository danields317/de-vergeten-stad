package Controller.Equipment_Controllers;

import Model.equipment.Equipment;
import javafx.scene.image.Image;

public class Equipment_Controller {

    Equipment equipment;

    public Equipment_Controller() {
        equipment = new Equipment("Zonneschild", "DUMMY", "placeholder.png");
    }

    public void gebruikEquipment() {
        System.out.println("Gebruik equipment!");
    }


    public Equipment getEquipment(){
        return equipment;
    }

    public Image getImage() {
        return equipment.getImage();
    }

}
