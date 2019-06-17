package Model.Tiles;

import Model.player.Player;
import javafx.scene.image.Image;
import observers.BordObservable;
import observers.BordObserver;

import java.util.ArrayList;

public class Tile implements BordObservable{

    private ArrayList<BordObserver> observers = new ArrayList<>();

    public enum Varianten{EQUIPMENT, FATAMORGANA, PART, TUNNEL, WATERPUT, FINISH}
    private Varianten variant;

    private boolean discovered;
    private Image undiscoveredImage;
    private Image discoveredImage;

    private int aantalZandTegels;

    private ArrayList<PartTile.Soorten> onderdelen;
    private ArrayList<Player> spelers;

    private int x;
    private int y;

    public Tile(String undiscoveredImage, String discoveredImage, Varianten variant){
        this.undiscoveredImage = new Image(undiscoveredImage);
        this.discoveredImage = new Image(discoveredImage);
        onderdelen = new ArrayList<>();
        spelers = new ArrayList<>();
        discovered = false;
        this.variant = variant;
    }

    public void addZandTegel(){
        aantalZandTegels += 1;
        notifyAllObservers();
    }

    public void removeZandTegel(){
        if (aantalZandTegels == 0) {return;}

        aantalZandTegels -= 1;
        notifyAllObservers();
    }

    public void removeTweeZandTegels(){
        removeZandTegel();
        removeZandTegel();
    }

    public void discoverTile(){
        if (discovered || aantalZandTegels > 0) {return;}

        discovered = true;
        notifyAllObservers();
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
//        setCurrentImage(discoveredImage);
        notifyAllObservers();
    }

    public void setUndiscoveredImage(String pad){
        this.undiscoveredImage = new Image(pad);
//        setCurrentImage(discoveredImage);
        notifyAllObservers();
    }

    public Image getImage(){
        if (discovered) {
            return discoveredImage;
        } else {
            return undiscoveredImage;
        }
    }

    public void setOnderdeel(PartTile.Soorten onderdeel){
        onderdelen.add(onderdeel);
        notifyAllObservers();
    }

    public int getZand() { return aantalZandTegels; }

    public void setLocation(int x, int y){
        this.x = x;
        this.y = y;
    }

    public ArrayList <PartTile.Soorten> getOnderdelen(){
        return onderdelen;
    }

    public ArrayList<Player> getSpelers(){ return  spelers; }

    public void addSpeler(Player speler){
        spelers.add(speler);
    }

    public void removeSpeler(Player speler){
        spelers.remove(speler);
    }

    public boolean isDiscovered() { return discovered; }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }
}
