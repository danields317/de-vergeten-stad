package Model.Tiles;

import Model.Bord.Onderdeel;
import Model.player.Player;
import javafx.scene.image.Image;
import observers.BordObservable;
import observers.BordObserver;

import java.util.ArrayList;

public class Tile implements BordObservable{

    private ArrayList<BordObserver> observers = new ArrayList<>();

    public enum Varianten{EQUIPMENT, FATAMORGANA, PART, TUNNEL, WATERPUT, FINISH, STORM}
    private Varianten variant;

    private boolean discovered;
    private Image undiscoveredImage;
    private Image discoveredImage;

    private int aantalZandTegels;
    private boolean hasZonneSchild;

    private ArrayList<Onderdeel> onderdelen;
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
        System.out.println("REEEEEEEEEEEEEE");
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
        notifyAllObservers();
    }

    public void removeAllZand(){
        this.aantalZandTegels = 0;
        notifyAllObservers();
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
        System.out.println("image jaa");
        this.discoveredImage = new Image(pad);
        System.out.println("image neee");
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

    public void setOnderdeel(Onderdeel onderdeel){
        onderdelen.add(onderdeel);
        notifyAllObservers();
    }

    public int getZand() { return aantalZandTegels; }

    public boolean hasZand(){
        return aantalZandTegels > 0;
    }

    public void setLocation(int x, int y){
        this.x = x;
        this.y = y;
    }

    public ArrayList <Onderdeel> getOnderdelen(){
        return onderdelen;
    }

    public ArrayList<Player> getSpelers(){ return  spelers; }

    public void addSpeler(Player speler){
        spelers.add(speler);
        notifyAllObservers();
    }

    public void removeSpeler(Player speler){
        spelers.remove(speler);
        notifyAllObservers();
    }

    public boolean isDiscovered() { return discovered; }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void setZonneSchild(){
        if(hasZonneSchild == false){
            hasZonneSchild = true;
            notifyAllObservers();
        }
    }

    public boolean hasOnderdeel(){
        return !onderdelen.isEmpty();
    }

    public void removeOnderdeel(){
        if (!onderdelen.isEmpty()){
            onderdelen.remove(0);
            notifyAllObservers();
        }
    }

    public void removeZonneSchild(){
        hasZonneSchild = false;
        notifyAllObservers();
    }

    public Image getDiscoveredImage(){
        return discoveredImage;
    }

    public boolean hasZonneSchild(){
        return hasZonneSchild;
    }

    public void setDiscovered(boolean discovered) {
        this.discovered = discovered;
    }

    public void setAantalZandTegels(int aantalZandTegels) {
        this.aantalZandTegels = aantalZandTegels;
    }

    public void setHasZonneSchild(boolean hasZonneSchild) {
        this.hasZonneSchild = hasZonneSchild;
    }
}
