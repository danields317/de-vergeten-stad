package Controller.Tile_Controllers;

import Model.Tiles.Tile;
import javafx.scene.paint.Color;

public class Tile_Controller{

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

    /**
     * Check if this tile collides with another.
     * @param tile Tile to check collision with.
     * @return boolean True if the tiles collide, otherwise false.
     * @author Tim
     */
    public boolean checkCollision(Tile tile) {
        return this.tile.x + this.tile.size > tile.x
                && this.tile.x < tile.x + tile.size
                && this.tile.y + this.tile.size > tile.y
                && this.tile.y < tile.y + tile.size;
    }
}
