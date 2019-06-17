package Model.equipment;

import javafx.scene.image.Image;

public class Equipment {

	public enum EquipmentKaarten {AARDEKIJKER, DUINKANON, JETPACK, TIJDSCHAKELAAR, WATERRESERVE, ZONNESCHILD};
	EquipmentKaarten equipmentKaart;

	private String naam;
	private String description;

	/////////////////////////////////////// Constructor ///////////////////////////////////////
	
	public Equipment(String naam, String description, EquipmentKaarten equipmentType) {

		this.naam = naam;
		this.description = description;
		this.equipmentKaart = equipmentType;
	}
	
	/////////////////////////////////////// Methods ///////////////////////////////////////
	
	
	
	/////////////////////////////////////// Getters & Setters ///////////////////////////////////////

	public String getNaam() {
		return naam;
	}

	public String getDescription() {
		return description;
	}

	public EquipmentKaarten getEquipmentType() {return equipmentKaart;}


}
