package Controller.Tile_Controllers;

import Controller.Player_Controllers.PlayerController;
import Model.Tiles.*;
import Model.player.Player;
import Model.storm.StormEventBeweging;
import View.bord_views.SpeelbordView;
import observers.BordObserver;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class TileController {

    Random random = new Random();

    private static TileController tileController;
    private static EquipmentController equipmentController = EquipmentController.getInstance();

    ArrayList<Tile> tiles = new ArrayList<>();
    ArrayList<Tile> randomTiles = new ArrayList<>();

    PlayerController playerController;

    public int counter = 0;

    private TileController(){
        makeTiles();
        randomizeTiles(tiles);
        randomTiles.add(12, new Storm());
        beginZand();
        setTileLocations();
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

                stormY = stormY + moveStormY;
                stormX = stormX + moveStormX;

                moveSpeler(stormX, stormY, moveStormX, moveStormY);
            }
        }
    }

    private void moveSpeler(int tileX, int tileY,int moveStormX, int moveStormY){
        Player player = playerController.getPlayer();
        int playerX = player.getX();
        int playerY = player.getY();
        if (playerX == tileX && playerY == tileY){
            if (moveStormX == -1){
                playerController.moveOost(false);
            } else if (moveStormX == 1){
                playerController.moveWest(false);
            } else if (moveStormY == -1){
                playerController.moveZuid(false);
            } else if (moveStormY == 1){
                playerController.moveNoord(false);
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

    public void useTileDiscoveredAction(int x, int y){
        System.out.println(getTileByLocation(y, x).getClass());
        Tile tile = (getTileByLocation(y, x));
        if(tile.getClass().equals(EquipmentTile.class) || tile.getClass().equals(StartTile.class)){
            EquipmentTile Etile = (EquipmentTile) tile;
            Etile.geefEquipment();
            //geef equipment
        }
        else if (tile.getClass().equals(Waterput.class)){
            Waterput Wtile = (Waterput) tile;
            Wtile.geefWater();
            //geef water
        }
        else if (tile.getClass().equals(Tunnel.class)){
            Tunnel Ttile = (Tunnel) tile;
            Ttile.geefSchaduw();
            //geen zon brand
        }
        else if (tile.getClass().equals(PartTile.class)){
            PartTile Ptile = (PartTile) tile;
            Ptile.geefHint();
            //ontdek hint
        }
        else if (tile.getClass().equals(Finish.class)){
            Finish Ftile = (Finish) tile;
            Ftile.isSpelKlaar();
            //ga ff checken of je hebt gewonnen
        }
        else{
            System.out.println("zon kanker tile die geen kanker doet (Tilecontroller)");
        }
    }

    public void notifyView(){
        randomTiles.get(0).notifyAllObservers();
    }

    public ArrayList<Tile> getTiles(){
        return this.randomTiles;
    }
}
