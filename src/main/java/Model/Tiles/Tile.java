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
    private Image currentImage; //zet hier de discoverd of undiscoverd image en geef deze door naar de manager

    private int aantalZandTegels;

    private ArrayList<PartTile> onderdelen;
    private ArrayList<Player> spelers;

    private int x;
    private int y;

    public Tile(String undiscovered, String discovered, Varianten variant){
        undiscoveredImage = new Image(undiscovered);
        discoveredImage = new Image(discovered);
        currentImage = discoveredImage;
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

    public void setDiscoveredImage(String pad){
        this.discoveredImage = new Image(pad);
        setCurrentImage(discoveredImage);
        notifyAllObservers();
    }

    public void setCurrentImage(Image image){
        this.currentImage = image;
    }

    public Image getImage(){
        return currentImage;
    }

    public int getZand() { return aantalZandTegels; }

    public void setLocation(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }
}
