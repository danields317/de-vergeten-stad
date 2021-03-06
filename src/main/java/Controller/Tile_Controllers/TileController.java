package Controller.Tile_Controllers;

import Controller.Player_Controllers.PlayerController;
import Model.Bord.Onderdeel;
import Model.Tiles.*;
import Model.data.StaticData;
import Model.equipment.*;
import Model.player.Player;
import Model.storm.Stappen;
import View.ViewManager;
import View.bord_views.SpeelbordView;
import javafx.application.Platform;
import observers.BordObserver;
import observers.OnderdeelObserver;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

/**
 * Deze klasse wordt gebruikt om tile behaviour uit te voeren zoals het verplaatsen van tiles.
 *
 * @author ryan
 * @author Daniël
 * @author Tim
 */
public class TileController {

    Random random = new Random();

    public static TileController tileController;
    public static TileController cheatController;
    private static EquipmentController equipmentController = EquipmentController.getInstance();

    ArrayList<Tile> tiles = new ArrayList<>();
    ArrayList<Tile> randomTiles = new ArrayList<>();
    ArrayList<Onderdeel> onderdelen = new ArrayList<>();

    PlayerController playerController;

    public int counter = 0;

    public TileController(){
        makeTiles();
        randomizeTiles(tiles);
        randomTiles.add(12, new Storm());
        beginZand();
        setTileLocations();
        maakOnderdelen();
    }

    private TileController(Object roominfo){
        Map<String, Object> tilesMap = (Map)((Map) roominfo).get("tiles");
        makeTilesFormFB(tilesMap);
    }

    public static TileController getCheatInstance(){
        if (cheatController == null){
            cheatController = new TileController();
        }
        return cheatController;
    }

    public static TileController getInstance(){
        if (tileController == null){
            StaticData staticData = StaticData.getInstance();
            Object roominfo = staticData.getRoomInfo();
            tileController = new TileController(roominfo);
        }
        return tileController;
    }

    public void tileClicked(int x, int y) {
        Tile tile = getTileByLocation(y,x);
        tile.discoverTile();
        tile.removeZandTegel();
    }

    /**
     * Deze functie initialiseert alle tiles.
     * Volgens de spel regels zijn er in totaal 24 tiles, dus loopt de for loop tot 24.
     *
     * @author ryan
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
        EquipmentTile.resetTeller();
    }


    /**
     * Deze functie randomized een ArrayList die hij mee krijgt.
     * Het is een recursive method.
     *
     * @param tiles Een ArrayList van het type Tile.
     * @author ryan
     */
    private void randomizeTiles(ArrayList<Tile> tiles){

        if (tiles.isEmpty()){
            return;
        }

        int randomInt = random.nextInt(tiles.size());
        randomTiles.add(tiles.get(randomInt));
        tiles.remove(randomInt);

        randomizeTiles(tiles);
    }

    /**
     * Deze functie geeft de tiles die zijn gerandomized een x en y waarde.
     *
     * @author ryan
     */
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


    public void moveTileNoord(Stappen stappen, int stormX, int stormY){
        moveTile(stappen, stormX, stormY,0,1);
    }
    public void moveTileOost(Stappen stappen, int stormX, int stormY){
        moveTile(stappen, stormX, stormY, -1, 0);
    }
    public void moveTileZuid(Stappen stappen, int stormX, int stormY){
        moveTile(stappen, stormX, stormY, 0, -1);
    }
    public void moveTileWest(Stappen stappen, int stormX, int stormY){
        moveTile(stappen, stormX, stormY, 1, 0);
    }

    /**
     * Deze functie draait de locaties van 2 tiles om in een bepaalde richting voor een x aantal stappen.
     *
     * @param stappen Het aantal stappen dat de storm heeft genomen.
     * @param stormX De x locatie van de storm.
     * @param stormY De y locatie van de storm.
     * @param moveStormX De x locatie waar de storm naartoe beweegt.
     * @param moveStormY De y locatie waar de storm naartoe beweegt.
     * @author ryan
     */
    private void moveTile(Stappen stappen, int stormX, int stormY, int moveStormX, int moveStormY){
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

    /**
     * Deze functie voegt zand toe aan een paar tiles in het begin, dit is volgens de spelregels.
     *
     * @author ryan
     */
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
            player.addEquipment(tTile.getEquipment());
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
            //System.out.println("Dit gaat fout (Tilecontroller)");
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
                    //System.out.println("gaat fout lol (tilecontroller)");
                }
            }
        }
    }

    public void checkOnderdeelSpawned(Onderdeel onderdeel){
        if(!(onderdeel.getY() == -1) && !(onderdeel.getX() == -1) && !onderdeel.isOpgepakt()) {
            Tile onderdeelSpawn = getTileByLocation(onderdeel.getY(), onderdeel.getX());
            onderdeelSpawn.setOnderdeel(onderdeel);
        }
    }

    /**
     * Deze functie checkt voor alle onderdelen of ze al zijn gespawned.
     *
     * @author ryan
     */
    private void checkOnderdelenSpawned(){
        for (Onderdeel onderdeel : onderdelen){
            if(!(onderdeel.getY() == -1) && !(onderdeel.getX() == -1) && !onderdeel.isOpgepakt()) {
                Tile onderdeelSpawn = getTileByLocation(onderdeel.getY(), onderdeel.getX());
                if (!onderdeelSpawn.getOnderdelen().contains(onderdeel)){
                    onderdeelSpawn.setOnderdeel(onderdeel);
                }
            }
        }
    }

    /**
     * Deze functie verwijdert alle onderdelen van de tile waarop hij lag die zijn opgepakt.
     *
     * @author ryan
     */
    private void despawnOnderdelen(){
        for (Onderdeel onderdeel : onderdelen){
            if (onderdeel.isOpgepakt()){
                for(Tile tile:randomTiles){
                    for(Onderdeel oD : tile.getOnderdelen()){
                        if(oD.equals(onderdeel)){
                            tile.removeOnderdeelSoort(onderdeel);
                        }
                    }
                }
                //Tile onderdeelTile = getTileByLocation(onderdeel.getY(), onderdeel.getX());
                //onderdeelTile.removeOnderdeelSoort(onderdeel);
            }
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

    public void checkZandCounter() {
        int zandCounter = 0;
        //int zandMax = 48;
        int zandMax = 9;

        for (Tile tile : randomTiles) {
            zandCounter += tile.getZand();
        }

        if (zandCounter > zandMax) {
            ViewManager.getInstance().loadEndGame(ViewManager.endConditions.SUFFOCATION);
        }

    }

    public ArrayList<Tile> getTiles(){
        return this.randomTiles;
    }

    /**
     * Deze functie update alle data waarover deze klasse gaat met de data uit FireBase.
     *
     * @author ryan
     */
    public void updateData(){
        StaticData staticData = StaticData.getInstance();
        Object roominfo = staticData.getRoomInfo();
        Map<String, Object> tilesMap = (Map)((Map) roominfo).get("tiles");
        updateTilesFromFB(tilesMap);

        Map<String, Object> onderdelenMap = (Map)((Map) roominfo).get("onderdelen");
        Platform.runLater(() -> {
            updateOnderdelenFromFB(onderdelenMap);
            //despawnOnderdelen();
            //checkOnderdelenSpawned();
        });

        //updateOnderdelenFromFB(onderdelenMap);
        checkOnderdelenSpawned();
        despawnOnderdelen();
    }

    /**
     * Deze functie update de onderdelen met data vanuit FireBase.
     *
     * @param onderdelenMap Een Map met informatie over de onderdelen
     * @author ryan
     */
    private void updateOnderdelenFromFB(Map<String, Object> onderdelenMap){
        for (int i = 0; i <onderdelen.size(); i++){
            Map<String, Object> onderdeelFB = (Map)onderdelenMap.get(Integer.toString(i));
            Onderdeel onderdeel = onderdelen.get(i);

            int x = Integer.valueOf(onderdeelFB.get("x").toString());
            int y = Integer.valueOf(onderdeelFB.get("y").toString());
            if((onderdeelFB.get("opgepakt").toString()).equals("true")){
                onderdeel.pakOp();
            }
            onderdeel.setX(x);
            onderdeel.setY(y);
        }
    }

    /**
     * Deze functie update de tiles met data vanuit FireBase.
     *
     * @param tilesMap Een Map met informatie over de onderdelen
     * @author ryan
     */
    private void updateTilesFromFB(Map<String, Object> tilesMap){
        for (int i = 0; i < 25; i++){
            Map<String, Object> tileFB = (Map)tilesMap.get(Integer.toString(i));
            Tile tile = randomTiles.get(i);

            int x = Integer.valueOf(tileFB.get("x").toString());
            int y = Integer.valueOf(tileFB.get("y").toString());
            boolean discovered = false;
            if((tileFB.get("discovered").toString()).equals("true")){
                discovered = true;
            }
            boolean hasZonneSchild = tileFB.get("hasZonneSchild").toString().equals("true");
            int aantalZand = Integer.valueOf(tileFB.get("aantalZandTegels").toString());
            tile.emptyPlayers();
            String s = tileFB.get("Players").toString();

            if(s.contains("Archeoloog")){
                tile.addPlayer("Archeoloog");
            }
            if(s.contains("Klimmer")){
                tile.addPlayer("Klimmer");
            }
            if(s.contains("Verkenner")){
                tile.addPlayer("Verkenner");
            }
            if(s.contains("Waterdrager")){
                tile.addPlayer("Waterdrager");
            }
            tile.setLocation(x, y);
            tile.setDiscovered(discovered);
            if (hasZonneSchild){
                tile.setZonneSchild();
            }
            tile.setAantalZandTegels(aantalZand);
        }
    }

    /**
     * Deze functie maakt tiles aan, aan de hand van info uit FireBase
     *
     * @param tilesMap Een Map met informatie over de onderdelen
     * @author ryan
     */
    private void makeTilesFormFB(Map<String, Object> tilesMap){
        ArrayList<Tile> fbTiles = new ArrayList<>();
        for (int i = 0; i < 25; i++){
            Map<String, Object> tileFB = (Map)tilesMap.get(Integer.toString(i));
            Tile tile = null;

            String variant = tileFB.get("naam").toString();

            int x = Integer.valueOf(tileFB.get("x").toString());
            int y = Integer.valueOf(tileFB.get("y").toString());
            boolean discovered = false;
            if((tileFB.get("discovered").toString()).equals("true")){
                discovered = true;
            }
            boolean hasZonneSchild = Boolean.getBoolean(tileFB.get("hasZonneSchild").toString());
            int aantalZand = Integer.valueOf(tileFB.get("aantalZandTegels").toString());

            switch (variant){
                case "PART":
                    tile = new PartTile(stringToRichting(tileFB.get("richting").toString()), stringToSoort(tileFB.get("soort").toString()));
                    break;
                case "FATAMORGANA":
                    tile = new FataMorgana();
                    break;
                case "EQUIPMENT":
                    tile = new EquipmentTile(stringToEquipment(tileFB.get("equipment").toString()));
                    break;
                case "TUNNEL":
                    tile = new Tunnel(stringToEquipment(tileFB.get("equipment").toString()));
                    break;
                case "WATERPUT":
                    tile = new Waterput();
                    break;
                case "FINISH":
                    tile = new Finish();
                    break;
                case "STORM":
                    tile = new Storm();
                    break;
                case "START":
                    tile = new StartTile(stringToEquipment(tileFB.get("equipment").toString()));
                    break;
            }

            String s = tileFB.get("Players").toString();

            if(s.contains("Archeoloog")){
                tile.addPlayer("Archeoloog");
            }
            if(s.contains("Klimmer")){
                tile.addPlayer("Klimmer");
            }
            if(s.contains("Verkenner")){
                tile.addPlayer("Verkenner");
            }
            if(s.contains("Waterdrager")){
                tile.addPlayer("Waterdrager");
            }

            tile.setLocation(x, y);
            tile.setDiscovered(discovered);
            tile.setHasZonneSchild(hasZonneSchild);
            tile.setAantalZandTegels(aantalZand);
            fbTiles.add(tile);
        }
        randomTiles = fbTiles;
        maakOnderdelen();
    }

    public Equipment stringToEquipment(String eq){
        switch (eq){
            case "JETPACK":
                return new Jetpack();
            case "AARDEKIJKER":
                return new Aardekijker();
            case "DUINKANON":
                return new Duinkanon();
            case "TIJDSCHAKELAAR":
                return new Tijdschakelaar();
            case "ZONNESCHILD":
                return new Zonneschild();
            case "WATERRESERVE":
                return new Waterreserve();
        }
        return null;
    }
    public PartTile.Richtingen stringToRichting(String richting){
        if (richting.equals("OPZIJ")){
            return PartTile.Richtingen.OPZIJ;
        }
        return PartTile.Richtingen.OMHOOG;
    }
    public PartTile.Soorten stringToSoort(String soort){
        switch (soort){
            case "OBELISK":
                return PartTile.Soorten.OBELISK;
            case "MOTOR":
                return PartTile.Soorten.MOTOR;
            case "KOMPAS":
                return PartTile.Soorten.KOMPAS;
            case "PROPELOR":
                return PartTile.Soorten.PROPELOR;
        }
        return null;
    }

    public ArrayList<Onderdeel> getOnderdelen() {
        return onderdelen;
    }

    public void update(){
        for (Tile tile : randomTiles){
            tile.notifyAllObservers();
        }
    }
}
