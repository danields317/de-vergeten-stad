package Controller.Equipment_Controllers;

import Model.equipment.Equipment;
import Model.equipment.Zonneschild;
import javafx.scene.image.Image;

public class EquipmentController {

    Equipment equipment;

    public EquipmentController() {
        equipment = new Zonneschild();
    }

    public void gebruikEquipment() {

    }

    public Equipment getEquipment(){
        return equipment;
    }

    public Image getImage() {
        return equipment.getImage();
    }

}
