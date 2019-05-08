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
	
	/////////////////////////////////////// Constructor ///////////////////////////////////////
	
	public Bord() {
		
		backgroundImage = new Image("resources/placeholder.png");
		
	}
	
	/////////////////////////////////////// Methods ///////////////////////////////////////
	
	public void render(GraphicsContext gc) {
		
		gc.drawImage(backgroundImage, 0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
		
	}
	
	public void addPlayer(Player player) {
		players[players.length] = player;
	}
	
}
