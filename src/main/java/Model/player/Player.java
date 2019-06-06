package Model.player;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import Model.Tiles.Tile;
import Model.equipment.Equipment;

public class Player {

	private int x;
	private int y;
	private String nickname; // Naam ingevoerd door de speler
	private Equipment[] inventory;
	private int water;
	private int actiesOver;
	private Tile tile; // De tile waar de speler op staat
	
	// Informatie over de speler klasse
	private String className;
	private String description;
	private int maxWater;
	private Color color;
	private Image image;
	
	
	/////////////////////////////////////// Constructor ///////////////////////////////////////
	
	public Player( String nickname, String className, String description, int maxWater, Color color, String imagePath ) {
		
		this.nickname = nickname;
		this.className = className;
		this.description = description;
		this.color = color;
		this.image = new Image( imagePath );
		
		this.maxWater = maxWater;
		water = maxWater;
		
	}
	
	/////////////////////////////////////// Methods ///////////////////////////////////////
	/*
	public void giveWater( Player reciever, int waterAmount ) {
		
		// Hoe veel water kunnen we echt geven?
		// Kan niet meer zijn dan we hebben
		waterAmount = Math.min( water, waterAmount );
		// Kan niet meer zijn dan dat de ontvanger nog kan hebben
		waterAmount = Math.min( reciever.getMaxWater()-reciever.getWater(), waterAmount );
		
		// Geef het goede aantal water
		reciever.setWater( reciever.getWater() - waterAmount );
		this.setWater( this.getWater() + waterAmount );
		
	}
	*/

	public void moveUp(){
		if(this.tile.y > 0){
			this.y += 1;
			findCurrentTile();
		}
		else{
			return;
		}
	}

	public void moveDown(){
		if(this.tile.y < 5){
			this.y += 1;
			findCurrentTile();
		}
		else{
			return;
		}
	}

	public void moveLeft(){
		if(this.tile.x > 0){
			this.x -= 1;
			findCurrentTile();
		}
		else{
			return;
		}
	}


	public void moveRight(){
		if(this.tile.x < 5){
			this.x += 1;
			findCurrentTile();
		}
		else{
			return;
		}
	}

	public void giveWater(Player reciever){
		if((this.water > 0 && reciever.water < reciever.maxWater) && (this.tile == reciever.tile)){
			this.subtractWater(1);
			reciever.addWater(1);
		}
	}
	/////////////////////////////////////// Getters & Setters ///////////////////////////////////////
	
	public int getWater() {
		return water;
	}

	public void addWater(int water ) {

		this.water = this.water + water;

	}



	public void subtractWater(int water ) {
		
		this.water = this.water - water;
		
		if (water <= 0) {
			// RIP
		}
	
	}

	public void findCurrentTile(){
		int locX = this.x;
		int locY = this.y;
		setTile(locX, locY);
	}

	public void setTile(int x, int y){
		//vind de tile waar de speler op staat.
		//zoek door alle tiles en vind degene met de juiste coordianten en plaast deze in tile variabel.
	}
	
	public int getMaxWater() {
		return maxWater;
	}

	public String getNickname() {
		return nickname;
	}

	public Equipment[] getInventory() {
		return inventory;
	}

	public int getActiesOver() {
		return actiesOver;
	}

	public Tile getTile() {
		return tile;
	}

	public String getClassName() {
		return className;
	}

	public String getDescription() {
		return description;
	}

	public Color getColor() {
		return color;
	}

	public Image getImage() {
		return image;
	}
}
