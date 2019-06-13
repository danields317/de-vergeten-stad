package Model.Tiles;

import Model.equipment.Equipment;

public class StartTile extends EquipmentTile {

    public StartTile(Equipment equipment) {
        super(equipment);
        this.setUndiscoveredImage("/Tiles/Undiscovered_Crash.png");
    }

}
