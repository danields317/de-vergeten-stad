package Model.Tiles;

import Model.equipment.Equipment;

public class EquipmentTile extends Tile {

    private Equipment equipment;

    public EquipmentTile(Equipment equipment){
        super("/placeholder.png", "/placeholder.png", Varianten.EQUIPMENT);
        this.equipment = equipment;
    }

    public Equipment getEquipment() {
        return equipment;
    }


}
