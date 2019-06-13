package Model.equipment;

import javafx.scene.image.Image;

public class Equipment {

	enum EquipmentKaarten {AARDEKIJKER, DUINKANON, JETPACK, TIJDSCHAKELAAR, WATERRESERVE, ZONNESCHILD};
	EquipmentKaarten equipmentKaart;

	private String naam;
	private String description;
	private Image image;
	
	/////////////////////////////////////// Constructor ///////////////////////////////////////
	
	public Equipment(String naam, String description, String imagePath) {

		this.naam = naam;
		this.description = description;
		this.image = new Image(imagePath);
	}
	
	/////////////////////////////////////// Methods ///////////////////////////////////////
	
	
	
	/////////////////////////////////////// Getters & Setters ///////////////////////////////////////

	public String getNaam() {
		return naam;
	}

	public String getDescription() {
		return description;
	}
	
	public Image getImage() {
		return image;
	}
	
	public void setImage() {
		
	}

}
