package Controller.Tile_Controllers;


import Model.equipment.*;

import java.util.List;

/**
 * Deze klasse maakt equipment aan en geeft de optie om equipment uit te delen aan tiles.
 */

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

    /**
     * Genereert alle equipment.
     */



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

    /**
     * Deze functie maakt de lijst met equipment aan als deze nog niet bestaat.
     * Als de lijst wel bestaat wordt er een equipment object teruggegeven.
     * @return één equipment object.
     */

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
