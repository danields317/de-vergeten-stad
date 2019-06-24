package Model.Tiles;

import Model.equipment.Equipment;

public class StartTile extends EquipmentTile {

    public StartTile(Equipment equipment) {
        super(equipment);
        super.setVariant(Varianten.START);
        this.setUndiscoveredImage("/Tiles/Undiscovered_Crash.png");
    }

}
