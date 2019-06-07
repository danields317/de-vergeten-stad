package View;

import Controller.Tile_Controllers.TileController;
import Model.Tiles.Tile;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import observers.BordObservable;
import observers.BordObserver;

public class TileView implements BordObserver{

    TileController tileController;
    ImageView imageView;
    public final int tileSize = 105;

    public TileView(Image image){
        tileController = TileController.getInstance();
        tileController.registerObserver(this);
        imageView = new ImageView(image);
        imageView.setFitHeight(tileSize);
        imageView.setFitWidth(tileSize);
        imageView.getStyleClass().add("tile");
    }

    public ImageView maakTileImage() {

        return imageView;

    }

    public void update(BordObservable bo){

        Tile tile = (Tile) bo;
        imageView.setImage(tile.getImage());
    }
}
