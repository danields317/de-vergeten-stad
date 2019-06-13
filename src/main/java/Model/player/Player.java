package Model.player;

import Controller.Equipment_Controllers.Equipment_Controller;
import Model.storm.StormEventBeweging;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import Model.Tiles.Tile;
import Model.equipment.Equipment;
import observers.PlayerObservable;
import observers.PlayerObserver;
import observers.WaterObserver;

import java.util.ArrayList;
import java.util.List;

public class Player implements PlayerObservable {

	private String nickname; // Naam ingevoerd door de speler
	private Equipment_Controller[] inventory;
	private int water;
	private int actiesOver;
	private int x;
	private int y;
	private Tile tile;
	
	// Informatie over de speler klasse
	private String className;
	private String description;
	private int maxWater;
	private Color color;
	private Image image;

	public enum Richingen {NOORD, OOST, WEST, ZUID}

	// List of all Observers of this Observable Objects
	private List<PlayerObserver> observers = new ArrayList<PlayerObserver>();


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
	public Player( String nickname, String className, String description, int maxWater, int water, Color color, String imagePath ) {

		this.nickname = nickname;
		this.className = className;
		this.description = description;
		this.color = color;
		this.image = new Image( imagePath );

		this.maxWater = maxWater;
		this.water = water;

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

	public void giveWater(Player reciever){
		if((this.water > 0 && reciever.water < reciever.maxWater) && (this.tile == reciever.tile)){
			this.subtractWater(1);
			reciever.addWater(1);
			notifyAllObservers();

		}
	}

	public void movePlayer(Richingen riching){
	    switch (riching){
            case NOORD:
                move(0, -1);
                break;
            case OOST:
                move(1, 0);
                break;
            case ZUID:
                move(0, 1);
                break;
            case WEST:
                move(-1, 0);
                break;
        }
    }

    private void move(int moveX, int moveY){
	    if (x < 5 && x >= 0 && y < 5 && y >= 0){
	        x = x + moveX;
	        y = y + moveY;
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
		System.out.println("Ikzit inde water");
		if (this.water <= 0) {
			this.water++;
		}
		notifyAllObservers();
	}

	public void useAction(){
        if (actiesOver > 0){
            actiesOver -= 1;
        }
    }


	public int getMaxWater() {
		return maxWater;
	}

	public String getNickname() {
		return nickname;
	}

	public Equipment_Controller[] getInventory() {
		return inventory;
	}

	public int getActiesOver() {
		return actiesOver;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
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

	public void register(PlayerObserver observer){
		observers.add(observer);
	}

	// Signal all observers that something has changed.
	// Also send <<this>> object to the observers.
	public void notifyAllObservers(){
		System.out.println("Notify");

		for (PlayerObserver s : observers) {
			System.out.println(s + "Player Model");

			s.update(this);
		}
	}

}
