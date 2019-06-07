package Model.Tiles;

import Model.player.Player;
import javafx.scene.image.Image;
import observers.BordObservable;
import observers.BordObserver;

import java.net.URISyntaxException;
import java.util.ArrayList;

public class Tile implements BordObservable{

    private ArrayList<BordObserver> observers = new ArrayList<>();

    public enum Varianten{EQUIPMENT, FATAMORGANA, PART, TUNNEL, WATERPUT, FINISH}
    private Varianten variant;

    private boolean isDiscovered;
    private Image undiscoveredImage;
    private Image discoveredImage;

    private int aantalZandTegels;

    private ArrayList<PartTile> onderdelen;
    private ArrayList<Player> spelers;

    public Tile(String undiscovered, String discovered, Varianten variant){
        onderdelen = new ArrayList<>();
        spelers = new ArrayList<>();
        isDiscovered = false;
        this.undiscoveredImage = new Image(undiscovered);
        this.discoveredImage = new Image(discovered);
        this.variant = variant;
    }

    public void addZandTegel(){
        aantalZandTegels += 1;
    }

    public void removeZandTegel(){
        if (aantalZandTegels > 0){
            aantalZandTegels -= 1;
        }
    }

    public void removeTweeZandTegels(){
        if (aantalZandTegels == 1){
            aantalZandTegels -= 1;
        }
        else if (aantalZandTegels > 1){
            aantalZandTegels -= 2;
        }
    }

    public void discoverTile(){
        if (!isDiscovered){
            isDiscovered = true;
        }
    }

    public Varianten getVariant(){
        return variant;
    }

	public void register(BordObserver bo){
	    observers.add(bo);
    }

    public void notifyAllObservers(){
	    for (BordObserver b : observers){
	        b.update(this);
        }
    }
}
