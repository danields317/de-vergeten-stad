package Model.Tiles;

import Model.equipment.Equipment;

public class Tunnel extends Tile {

	private Equipment equipment;

	public Tunnel(Equipment equipment) {
		super("/Tiles/Undiscovered.png", "/Tiles/Tunnel.png", Varianten.TUNNEL);
		this.equipment = equipment;
	}

    public Equipment getEquipment() {
        return equipment;
    }
}
