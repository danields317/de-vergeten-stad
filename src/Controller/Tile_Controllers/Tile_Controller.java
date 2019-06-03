package Controller.Tile_Controllers;

import Engine.behavior.behaviors.Collidable;
import Model.Tiles.Tile;
import javafx.scene.paint.Color;

public class Tile_Controller implements Collidable{

    private Tile tile;

    public Tile_Controller(String imagePath, String undiscoveredImagePath){
        tile = new Tile(imagePath, undiscoveredImagePath);
    }

    public Tile_Controller(String imagePath, String undiscoveredImagePath, boolean direction, Color color){
        tile = new Tile(imagePath, undiscoveredImagePath, direction, color);
    }

    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }

    @Override
    public void handleCollision(Collidable collidable){
        /*if(collidable instanceof tile){

        }*/
    }
}
