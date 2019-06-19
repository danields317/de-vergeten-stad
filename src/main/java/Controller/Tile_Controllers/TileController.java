package Controller.Tile_Controllers;

import Controller.Player_Controllers.PlayerController;
import Model.Bord.Onderdeel;
import Model.Tiles.*;
import Model.player.Player;
import Model.storm.StormEventBeweging;
import View.bord_views.SpeelbordView;
import observers.BordObserver;
import observers.OnderdeelObserver;

import java.util.ArrayList;
import java.util.Random;

public class TileController {

    Random random = new Random();

    private static TileController tileController;
    private static EquipmentController equipmentController = EquipmentController.getInstance();

    ArrayList<Tile> tiles = new ArrayList<>();
    ArrayList<Tile> randomTiles = new ArrayList<>();
    ArrayList<Onderdeel> onderdelen = new ArrayList<>();


    PlayerController playerController;

    public int counter = 0;

    private TileController(){
        makeTiles();
        randomizeTiles(tiles);
        randomTiles.add(12, new Storm());
        beginZand();
        setTileLocations();
        maakOnderdelen();
    }

    public static TileController getInstance(){
        if (tileController == null){
            tileController = new TileController();
        }
        return tileController;
    }

    public void tileClicked(int x, int y) {
        Tile tile = getTileByLocation(y,x);
        tile.discoverTile();
        tile.removeZandTegel();
    }

    /**
     * Volgens de spel regels zijn er in totaal 24 tiles, dus loopt de for loop tot 24.
     */
    private void makeTiles(){
        for (int i = 0; i < 24; i++){
            if (i < 1){
                tiles.add(new Finish());
            }else if (i < 2){
                tiles.add(new FataMorgana());
            }else if (i < 4){
                tiles.add(new Waterput());
            }else if (i < 7){
                tiles.add(new Tunnel(equipmentController.getEquipment()));
            }else if (i < 8){
                tiles.add(new PartTile(PartTile.Richtingen.OMHOOG, PartTile.Soorten.KOMPAS));
            }else if (i < 9){
                tiles.add(new PartTile(PartTile.Richtingen.OMHOOG, PartTile.Soorten.PROPELOR));
            }else if (i < 10){
                tiles.add(new PartTile(PartTile.Richtingen.OMHOOG, PartTile.Soorten.MOTOR));
            }else if (i < 11){
                tiles.add(new PartTile(PartTile.Richtingen.OMHOOG, PartTile.Soorten.OBELISK));
            }else if (i < 12){
                tiles.add(new PartTile(PartTile.Richtingen.OPZIJ, PartTile.Soorten.KOMPAS));
            }else if (i < 13){
                tiles.add(new PartTile(PartTile.Richtingen.OPZIJ, PartTile.Soorten.PROPELOR));
            }else if (i < 14){
                tiles.add(new PartTile(PartTile.Richtingen.OPZIJ, PartTile.Soorten.MOTOR));
            }else if (i < 15) {
                tiles.add(new PartTile(PartTile.Richtingen.OPZIJ, PartTile.Soorten.OBELISK));
            }else if (i < 16) {
                tiles.add(new StartTile(equipmentController.getEquipment()));
            }else{
                tiles.add(new EquipmentTile(equipmentController.getEquipment()));
            }
        }
    }


    private void randomizeTiles(ArrayList<Tile> tiles){

        if (tiles.isEmpty()){
            return;
        }

        int randomInt = random.nextInt(tiles.size());
        randomTiles.add(tiles.get(randomInt));
        tiles.remove(randomInt);

        randomizeTiles(tiles);
    }

    private void setTileLocations(){
        int counter = 0;
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                randomTiles.get(counter).setLocation(i, j);
                counter++;
            }
        }
    }

    public void maakOnderdelen(){
        onderdelen.add(new Onderdeel(PartTile.Soorten.OBELISK));
        onderdelen.add(new Onderdeel(PartTile.Soorten.MOTOR));
        onderdelen.add(new Onderdeel(PartTile.Soorten.KOMPAS));
        onderdelen.add(new Onderdeel(PartTile.Soorten.PROPELOR));
    }


    public void moveTileNoord(StormEventBeweging.Stappen stappen, int stormX, int stormY){
        moveTile(stappen, stormX, stormY,0,1);
    }
    public void moveTileOost(StormEventBeweging.Stappen stappen, int stormX, int stormY){
        moveTile(stappen, stormX, stormY, -1, 0);
    }
    public void moveTileZuid(StormEventBeweging.Stappen stappen, int stormX, int stormY){
        moveTile(stappen, stormX, stormY, 0, -1);
    }
    public void moveTileWest(StormEventBeweging.Stappen stappen, int stormX, int stormY){
        moveTile(stappen, stormX, stormY, 1, 0);
    }

    private void moveTile(StormEventBeweging.Stappen stappen, int stormX, int stormY, int moveStormX, int moveStormY){
        playerController = PlayerController.getInstance();
        for (int i = 0; i < stappen.getNumber(); i++){
            if (stormY+moveStormY <= 4 && stormX+moveStormX >= 0 && stormY+moveStormY >= 0 && stormX+moveStormX <= 4){

                Tile stormTile = getTileByLocation(stormY, stormX);
                Tile tmp = getTileByLocation(stormY+moveStormY, stormX+moveStormX);

                stormTile.setLocation(stormX+moveStormX, stormY+moveStormY);
                tmp.setLocation(stormX, stormY);

                tmp.addZandTegel();

                tmp.notifyAllObservers();
                stormTile.notifyAllObservers();

                SpeelbordView.getInstance().updateSpelBord(tmp, stormTile);

                for (Player speler : tmp.getSpelers()){
                    speler.setLocatie(stormX, stormY);
                }

                stormY = stormY + moveStormY;
                stormX = stormX + moveStormX;
            }
        }
    }

    private void beginZand(){
        randomTiles.get(2).addZandTegel();
        randomTiles.get(6).addZandTegel();
        randomTiles.get(8).addZandTegel();
        randomTiles.get(10).addZandTegel();
        randomTiles.get(14).addZandTegel();
        randomTiles.get(16).addZandTegel();
        randomTiles.get(18).addZandTegel();
        randomTiles.get(22).addZandTegel();
    }


    public Tile getTileByLocation(int y, int x){
        for (Tile tile : randomTiles){
            if (tile.getX() == x && tile.getY() == y){
                return tile;
            }
        }
        return null;
    }

    public void registerObserver(BordObserver bo, int counter){
        randomTiles.get(counter).register(bo);
        this.counter++;
    }

    public void registerOnderdeelObserver(OnderdeelObserver ob){
        for(int i = 0; i < 4; i++){
        onderdelen.get(i).register(ob); }
    }

    public void useTileDiscoveredAction(int x, int y){
        Tile tile = (getTileByLocation(y, x));
        playerController = PlayerController.getInstance();
        Player player = playerController.getPlayer();
        if(tile.getClass().equals(EquipmentTile.class) || tile.getClass().equals(StartTile.class)){
            EquipmentTile eTile = (EquipmentTile) tile;
            //geef equipment
            player.addEquipment(eTile.getEquipment());
        }
        else if (tile.getClass().equals(Waterput.class)){
            Waterput wTile = (Waterput) tile;
            for (Player speler : wTile.getSpelers()){
                speler.addWater(2);
            }
            //geef water
        }
        else if (tile.getClass().equals(Tunnel.class)){
            Tunnel tTile = (Tunnel) tile;
            tTile.geefSchaduw();
            //geen zon brand
        }
        else if (tile.getClass().equals(PartTile.class)){
            PartTile pTile = (PartTile) tile;
            geefHint(pTile);
            //ontdek hint
        }
        else if (tile.getClass().equals(Finish.class)){
            Finish fTile = (Finish) tile;
            fTile.isSpelKlaar();
            //ga ff checken of je hebt gewonnen
        }
        else{
            System.out.println("Dit gaat fout (Tilecontroller)");
        }
    }

    public void geefHint(PartTile tile){
        for(Onderdeel onderdeel:onderdelen){
            if(tile.getSoort().equals(onderdeel.getSoort())){
                if (tile.getRichting() == PartTile.Richtingen.OPZIJ){
                    onderdeel.setY((tile.getY()));
                    checkOnderdeelSpawned(onderdeel);
                }
                else if(tile.getRichting() == PartTile.Richtingen.OMHOOG){
                    onderdeel.setX((tile.getX()));
                    checkOnderdeelSpawned(onderdeel);
                }
                else{
                    System.out.println("gaat fout lol (tilecontroller)");
                }
            }
        }
    }

    public void checkOnderdeelSpawned(Onderdeel onderdeel){
        if(!(onderdeel.getY() == -1) && !(onderdeel.getX() == -1)) {
            Tile onderdeelSpawn = getTileByLocation(onderdeel.getY(), onderdeel.getX());
            onderdeelSpawn.setOnderdeel(onderdeel);
        }
    }

    private Tile getFinsihTile(){
        for (Tile tile : randomTiles){
            if (tile.getVariant() == Tile.Varianten.FINISH){
                return tile;
            }
        }
        return null;
    }

    private boolean checkAlleOnderdelen(){
        int opgepaktCounter = 0;
        for (Onderdeel onderdeel : onderdelen){
            if (onderdeel.isOpgepakt()){
                opgepaktCounter++;
            }
        }
        return opgepaktCounter == 4;
    }

    public boolean checkFinish(){
        return getFinsihTile().getSpelers().size() == 4 && checkAlleOnderdelen();
    }

    public ArrayList<Tile> getTiles(){
        return this.randomTiles;
    }
}
