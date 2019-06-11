package Controller.Tile_Controllers;

import Model.Tiles.*;
import Model.storm.StormEventBeweging;
import observers.BordObserver;

import java.util.ArrayList;
import java.util.Random;

public class TileController {

    Random random = new Random();

    static TileController tileController;
    static EquipmentController equipmentController;

    ArrayList<Tile> tiles = new ArrayList<>();
    Tile[][] randomTiles = new Tile[5][5];

    private TileController(){
        makeTiles();
        randomizeTiles();
        /*for (Tile[] subTiles : randomTiles) {
            for (Tile tile : subTiles){
                System.out.println(tile.getVariant());
            }
        }*/
    }

    public static TileController getInstance(){
        if (tileController == null){
            tileController = new TileController();
        }
        return tileController;
    }

    /**
     * Volgens de spel regels zijn er in totaal 24 tiles, dus loopt de for loop tot 24.
     */
    private void makeTiles(){
        for (int i = 0; i <= 24; i++){
            if (i < 1){
                tiles.add(new Finish());
            }else if (i < 2){
                tiles.add(new FataMorgana());
            }else if (i < 4){
                tiles.add(new Waterput());
            }else if (i < 7){
                tiles.add(new Tunnel(/*equipmentController.getEquipment()*/));
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
            }else if (i < 15){
                tiles.add(new PartTile(PartTile.Richtingen.OPZIJ, PartTile.Soorten.OBELISK));
            }else if (i < 16){
                tiles.add(new Storm());
            }
            else {
                tiles.add(new EquipmentTile(/*equipmentController.getEquipment()*/));
            }
        }
    }

    private void randomizeTiles(){
        for (int i = 0; i < randomTiles.length; i++) {
            for (int j = 0; j < randomTiles[i].length; j++) {
                if(!tiles.isEmpty()) {
                    int randomInt = random.nextInt(tiles.size());
                    System.out.println(randomInt);
                    randomTiles[i][j] = tiles.get(randomInt);
                    tiles.remove(randomInt);
                }
                else continue;
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
            if (stormY < 4 && stormX > 0 && stormY > 0 && stormX < 4){
                randomTiles[stormY][stormX] = randomTiles[stormY+moveStormY][stormX+moveStormX];
                randomTiles[stormY][stormX].addZandTegel();
                randomTiles[stormY+moveStormY][stormX+moveStormX] = null;
                stormY = stormY + moveStormY;
                stormX = stormX + moveStormX;
            }
        }
    }


    public void registerObserver(BordObserver bo){
        for (Tile[] subTiles : randomTiles) {
            for (Tile tile : subTiles){
                tile.register(bo);
            }
        }
    }

    public Tile[][] getTiles(){
        return this.randomTiles;
    }
}
