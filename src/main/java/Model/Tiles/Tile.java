package Model.Tiles;

import javafx.scene.image.Image;
import Model.player.Player;
import javafx.scene.paint.Color;

public class Tile {

	static int sandLayersUsed = 0;
	
	private int sandLayers = 0; // bij >=2 geblokkeerd
	private Image image;
	private Image undiscoveredImage;
	private boolean discovered = false;
	private Player[] players; // Alle spelers die op deze tile staan
	
	public boolean hasArtifactPropeller = false;
	public boolean hasArtifactMotor = false;
	public boolean hasArtifactZonnekristal = false;
	public boolean hasArtifactKompas = false;

	public int x;
	public int y;
	public double size = 10;
	
	/////////////////////////////////////// Constructor ///////////////////////////////////////
	
	public Tile( String imagePath, String undiscoveredImagePath ) {
		
		this.image = new Image(imagePath);
		this.undiscoveredImage = new Image(undiscoveredImagePath);
		
	}

	public Tile(String imagePath, String undiscoveredImagePath, boolean direction, Color color) {

		this.image = new Image(imagePath);
		this.undiscoveredImage = new Image(undiscoveredImagePath);

	}
	
	/////////////////////////////////////// Methods ///////////////////////////////////////
	
	public void dig( int layers ) {
		
		// Haal x lagen zand weg
		// sandLayers kan niet minder dan 0 zijn
		sandLayers = Math.max( sandLayers - layers, 0);
		
	}
	
	public void dig() {
		dig(1);
	}
	
	public void discover() {
		
		discovered = true;
		
	}
	
	public boolean isBlocked() {
		// Geblokkeerd waneer er 2 of meer zand op zit
		return sandLayers > 1;
	}
	
	/////////////////////////////////////// Getters & Setters ///////////////////////////////////////
	
	public int getSandLayers() {
		return sandLayers;
	}
	
	public boolean isDiscovered() {
		return discovered;
	}

	public Image getImage() {
		return image;
	}

	public Image getUndiscoveredImage() {
		return undiscoveredImage;
	}
}
