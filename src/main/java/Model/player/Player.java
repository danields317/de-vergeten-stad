package Model.player;

import Controller.Equipment_Controllers.EquipmentController;
import Controller.Player_Controllers.FunctieController;
import Model.data.StaticData;
import firebase.FirebaseService;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import Model.Tiles.Tile;
import Model.equipment.Equipment;
import observers.*;
import observers.PlayerObservable;
import observers.PlayerObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;

public class Player implements PlayerObservable{

	private String nickname; // Naam ingevoerd door de speler
	private ArrayList<Equipment> inventory = new ArrayList<>();
	private int water;
	private int actiesOver;
	private Tile tile; // De tile waar de speler op staat
	
	// Informatie over de speler klasse
	private String className;
	private String description;
	private int maxWater;
	private Color color;
	private Image image;
	private int x;
	private int y;

	public enum Richingen {NOORD, OOST, WEST, ZUID};
	public enum RichtingenSchuin{NOORDOOST, ZUIDOOST, ZUIDWEST, NOORDWEST};

	public enum SpelerKlassen {
	    ARCHEOLOOG,
        VERKENNER,
        WATERDRAGER,
        KLIMMER,
	    NAVIGATOR,
        METEOROLOOG;
    }
	SpelerKlassen klasse;

	// List of all Observers of this Observable Objects
	private List<PlayerObserver> observers = new ArrayList<PlayerObserver>();


	/////////////////////////////////////// Constructor ///////////////////////////////////////
	
	public Player( String nickname, String className, String description, int maxWater, Color color, String imagePath, SpelerKlassen klasse ) {

		this.nickname = nickname;
		this.className = className;
		this.description = description;
		this.color = color;
		this.image = new Image( imagePath );

		this.maxWater = maxWater;
		water = maxWater;

        actiesOver = 4;

        this.klasse = klasse;
	}
	public Player(String nickname, String className, String description, int maxWater, int water, Color color, String imagePath, SpelerKlassen klasse) {

		this.nickname = nickname;
		this.className = className;
		this.description = description;
		this.color = color;
		this.image = new Image( imagePath );

		this.maxWater = maxWater;
		this.water = water;

        actiesOver = 4;

        this.klasse = klasse;
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

    public void movePlayerSchuin(RichtingenSchuin richting){
		switch (richting){
			case NOORDOOST:
				move(1, -1);
				break;
			case ZUIDOOST:
				move(1, 1);
				break;
			case ZUIDWEST:
				move(-1, 1);
				break;
			case NOORDWEST:
				move(-1, -1);
				break;
		}
	}

    private void move(int moveX, int moveY){
		x = x + moveX;
		y = y + moveY;
    }

	/////////////////////////////////////// Getters & Setters ///////////////////////////////////////
	
	public int getWater() {
		return water;
	}

	@Override
	public int getActiesOver() {
		return actiesOver;
	}

	public void addWater(int water ) {

    	if (this.water + water < maxWater){
            this.water = this.water + water;
        } else {
    	    this.water = maxWater;
        }
		notifyAllObservers();

	}

	public void subtractWater(int water) {

		this.water = this.water - water;

		if (this.water < 0) {
			//(FunctieController.getInstance()).endLose();
			//return;
			System.out.println("player klasse");
		} else {
			notifyAllObservers();
		}

	}

	public void useAction(){
        if (actiesOver > 0){
            actiesOver--;
        }
        notifyAllObservers();
    }

    public boolean actiesOver(){
        return actiesOver > 0;
    }

    public void refillActions(){
        actiesOver = 4;
		notifyAllObservers();
    }

	public int getMaxWater() {
		return maxWater;
	}

	public String getNickname() {
		return nickname;
	}

	public ArrayList<Equipment> getInventory() {
		return inventory;
	}

	public int getActies() {
		return actiesOver;
	}

	public void setLocatie(int x, int y){
		this.x = x;
		this.y = y;
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

    public SpelerKlassen getKlasse(){
        return klasse;
    }

	public void updateData(){
		StaticData staticData = StaticData.getInstance();
		Object classes = ((Map) staticData.getRoomInfo()).get("Selectable_classes");

		for(int i = 0; i < ((Map) classes).size(); i++) {
			Object singeClass = ((Map) classes).get(Integer.toString(i));
			if(((((Map) singeClass).get("name")).toString()).equals(staticData.getClassName())){
				water = (((Long)(((Map) singeClass).get("water"))).intValue());
			}
		}
	}

	public void getTweeActies(){
		this.actiesOver = this.actiesOver + 2;
		notifyAllObservers();
	}

	public void addEquipment(Equipment equipment){
	    inventory.add(equipment);
	    notifyAllObservers();
    }

    public void removeEquipment(Equipment.EquipmentKaarten kaart){
	    for (Equipment equipment : inventory){
	        if (equipment.getEquipmentType().equals(kaart)){
	            inventory.remove(equipment);
	            break;
            }
        }
	    notifyAllObservers();
    }

	public void register(PlayerObserver observer){
		observers.add(observer);
	}

	// Signal all observers that something has changed.
	// Also send <<this>> object to the observers.
	public void notifyAllObservers(){
		for (PlayerObserver s : observers) {
			s.update(this);
		}
	}

}
