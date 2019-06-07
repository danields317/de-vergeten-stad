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

    public TileView(Image image){
        tileController = TileController.getInstance();
        tileController.registerObserver(this);
        imageView = new ImageView(image);
        imageView.setFitHeight(115);
        imageView.setFitWidth(115);
    }

    public ImageView maakTileImage() {

        return imageView;

    }

    public void update(BordObservable bo){

        Tile tile = (Tile) bo;
        imageView.setImage(tile.getImage());
    }
}
