package vergetenstad;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import vergetenstad.Tiles.Tile;
import vergetenstad.player.Player;

public class Bord {

	private Tile[][] tiles;
	private Player[] players;
	private int stormLevel = 2;
	private int stormProgress = 0;
	private Image backgroundImage;
	
	private boolean collectedArtifactPropeller = false;
	private boolean collectedArtifactMotor = false;
	private boolean collectedArtifactZonnekristal = false;
	private boolean collectedArtifactNavigatiemodule = false;
	
	/////////////////////////////////////// Getters & Setters ///////////////////////////////////////
	
	public Tile[][] getTiles() {
		return tiles;
	}
	public void setTiles(Tile[][] tiles) {
		this.tiles = tiles;
	}
	public Player[] getPlayers() {
		return players;
	}
	public void setPlayers(Player[] players) {
		this.players = players;
	}
	public int getStormLevel() {
		return stormLevel;
	}
	public void setStormLevel(int stormLevel) {
		this.stormLevel = stormLevel;
	}
	public int getStormProgress() {
		return stormProgress;
	}
	public void setStormProgress(int stormProgress) {
		this.stormProgress = stormProgress;
	}
	public Image getBackgroundImage() {
		return backgroundImage;
	}
	public void setBackgroundImage(Image backgroundImage) {
		this.backgroundImage = backgroundImage;
	}
	public boolean isCollectedArtifactPropeller() {
		return collectedArtifactPropeller;
	}
	public void setCollectedArtifactPropeller(boolean collectedArtifactPropeller) {
		this.collectedArtifactPropeller = collectedArtifactPropeller;
	}
	public boolean isCollectedArtifactMotor() {
		return collectedArtifactMotor;
	}
	public void setCollectedArtifactMotor(boolean collectedArtifactMotor) {
		this.collectedArtifactMotor = collectedArtifactMotor;
	}
	public boolean isCollectedArtifactZonnekristal() {
		return collectedArtifactZonnekristal;
	}
	public void setCollectedArtifactZonnekristal(boolean collectedArtifactZonnekristal) {
		this.collectedArtifactZonnekristal = collectedArtifactZonnekristal;
	}
	public boolean isCollectedArtifactNavigatiemodule() {
		return collectedArtifactNavigatiemodule;
	}
	public void setCollectedArtifactNavigatiemodule(boolean collectedArtifactNavigatiemodule) {
		this.collectedArtifactNavigatiemodule = collectedArtifactNavigatiemodule;
	}
	
}
