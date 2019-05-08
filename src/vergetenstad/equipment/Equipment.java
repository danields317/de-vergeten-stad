package vergetenstad.equipment;

import javafx.scene.image.Image;

public class Equipment {

	private String description;
	private Image image;
	
	/////////////////////////////////////// Constructor ///////////////////////////////////////
	
	public Equipment(String description, String imagePath) {
		
		this.description = description;
		this.image = new Image(imagePath);
		
	}
	
	/////////////////////////////////////// Methods ///////////////////////////////////////
	
	
	
	/////////////////////////////////////// Getters & Setters ///////////////////////////////////////
	
	public String getDescription() {
		return description;
	}
	
	public Image getImage() {
		return image;
	}
	
	public void setImage() {
		
	}

}
