package Controller.Tile_Controllers;


import Model.equipment.*;

import java.util.List;

public class EquipmentController {

    private static EquipmentController equipmentController;
    private Equipment[] equipmentList;
    private int equipmentCounter = 0;

    public static EquipmentController getInstance(){
        if (equipmentController == null){
            equipmentController = new EquipmentController();
        }
        return equipmentController;
    }



    private void generateEquipment() {
        equipmentList = new Equipment[12];

        int i = 0;
        equipmentList[i] = new Waterreserve(); i++;
        equipmentList[i] = new Tijdschakelaar(); i++;
        equipmentList[i] = new Aardekijker(); i++;
        equipmentList[i] = new Aardekijker(); i++;
        equipmentList[i] = new Zonneschild(); i++;
        equipmentList[i] = new Zonneschild(); i++;
        equipmentList[i] = new Duinkanon(); i++;
        equipmentList[i] = new Duinkanon(); i++;
        equipmentList[i] = new Duinkanon(); i++;
        equipmentList[i] = new Jetpack(); i++;
        equipmentList[i] = new Jetpack(); i++;
        equipmentList[i] = new Jetpack(); i++;
    }

    public Equipment getEquipment() {
        if (equipmentList == null) {
            generateEquipment();
        }
        if (equipmentCounter == 12){
            equipmentCounter = 0;
        }
        Equipment equipment = equipmentList[equipmentCounter];
        equipmentCounter++;
        return equipment;
    }
}
