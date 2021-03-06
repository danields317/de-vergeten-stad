package Model.Tiles;

import Model.Bord.Onderdeel;
import Model.player.Player;
import javafx.scene.image.Image;
import observers.BordObservable;
import observers.BordObserver;

import java.util.ArrayList;

/**
 * De Tile klasse is de superklasse voor alle andere tiles in de game.
 * Deze klasse slaat de informatie voor elke tile op.
 */

public class Tile implements BordObservable{

    private ArrayList<BordObserver> observers = new ArrayList<>();

    public enum Varianten{EQUIPMENT, FATAMORGANA, PART, TUNNEL, WATERPUT, FINISH, STORM, START}
    private Varianten variant;

    private boolean discovered;
    private Image undiscoveredImage;
    private Image discoveredImage;

    private int aantalZandTegels;
    private boolean hasZonneSchild = false;

    private ArrayList<Onderdeel> onderdelen;
    private ArrayList<Player> spelers;
    private ArrayList<String> players = new ArrayList<>();

    private int x;
    private int y;

    public Tile(){

    }
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
        this.discoveredImage = new Image(pad);
        notifyAllObservers();
    }

    public void setUndiscoveredImage(String pad){
        this.undiscoveredImage = new Image(pad);
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

    public void addPlayer(String player){
        players.add(player);
        notifyAllObservers();
    }

    public void removePlayer(String player){
        for(int i = 0; i < players.size(); i++){
            if(players.get(i).equals(player)) {
                players.remove(i);
            }
        }
        notifyAllObservers();
    }

    public void emptyPlayers() {
        players.clear();
    }

    public ArrayList<String> getPlayers() {
        return players;
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

    public void removeOnderdeelSoort(Onderdeel onderdeel){
        for (Onderdeel ond : onderdelen){
            if (ond.getSoort().equals(onderdeel.getSoort())) {
                onderdelen.remove(onderdeel);
                notifyAllObservers();
            }
        }
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

    public void setVariant(Varianten variant){
        this.variant = variant;
    }
}
