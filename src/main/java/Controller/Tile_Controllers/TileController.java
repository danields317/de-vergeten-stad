package Controller.Tile_Controllers;

import Model.Tiles.*;
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
//        Tile tile = randomTiles[y][x];
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

    public void moveTiles(StormEventBeweging.Richtingen stormRichting, StormEventBeweging.Stappen stappen, int stormX, int stormY){
        switch (stormRichting){
            case NOORD:
                moveTileZuid(stappen, stormX, stormY);
                break;
            case OOST:
                moveTileWest(stappen, stormX, stormY);
                break;
            case ZUID:
                moveTileNoord(stappen, stormX, stormY);;
                break;
            case WEST:
                moveTileOost(stappen, stormX, stormY);
                break;
            default:
                System.out.println("Dit hoort niet");
        }
    }

    private void moveTileNoord(StormEventBeweging.Stappen stappen, int stormX, int stormY){
        moveTile(stappen, stormX, stormY,0,1);
    }
    private void moveTileOost(StormEventBeweging.Stappen stappen, int stormX, int stormY){
        moveTile(stappen, stormX, stormY, -1, 0);
    }
    private void moveTileZuid(StormEventBeweging.Stappen stappen, int stormX, int stormY){
        moveTile(stappen, stormX, stormY, 0, -1);
    }
    private void moveTileWest(StormEventBeweging.Stappen stappen, int stormX, int stormY){
        moveTile(stappen, stormX, stormY, 1, 0);
    }

    private void moveTile(StormEventBeweging.Stappen stappen, int stormX, int stormY, int moveStormX, int moveStormY){

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
            }
        }
    }

    public void beginZand(){
        randomTiles.get(2).addZandTegel();
        randomTiles.get(6).addZandTegel();
        randomTiles.get(8).addZandTegel();
        randomTiles.get(10).addZandTegel();
        randomTiles.get(14).addZandTegel();
        randomTiles.get(16).addZandTegel();
        randomTiles.get(18).addZandTegel();
        randomTiles.get(22).addZandTegel();
    }


    private Tile getTileByLocation(int y, int x){
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

    public ArrayList<Tile> getTiles(){
        return this.randomTiles;
    }
}
