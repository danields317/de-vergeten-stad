package Model.Tiles;

import Model.equipment.Equipment;

public class EquipmentTile extends Tile {

    private static int aangemaaktTeller = 1;
    private Equipment equipment;

    public EquipmentTile(/*Equipment equipment*/){
        super("/Tiles/Undiscovered.png", "/placeholder.png", Varianten.EQUIPMENT);
        String juistePNG = vindJuistePNG();
        super.setDiscoveredImage(juistePNG);
        aangemaaktTeller ++;
        //this.equipment = equipment;
    }

    public String vindJuistePNG(){
        String pad = "/Tiles/Equipment_" + aangemaaktTeller + ".png";
        return pad;
    }

    public Equipment getEquipment() {
        return equipment;
    }


}
