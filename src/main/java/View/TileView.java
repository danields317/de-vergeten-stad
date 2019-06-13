package View;

import Controller.Tile_Controllers.TileController;
import Model.Tiles.Tile;
import View.bord_views.SpeelbordView;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import observers.BordObservable;
import observers.BordObserver;

public class TileView implements BordObserver{

    TileController tileController;
    public final int tileSize = 105;

    StackPane stackPane;
    ImageView tileImageView;
    ImageView zandImageView;
    GridPane gridPane;
    StackPane onderdeelStackPane;
    Label zandLabel;
    ImageView zonneschildImageView;

    ImageView archeoloogImageView;
    ImageView klimmerImageView;
    ImageView meteooroloogImageView;
    ImageView navigatorImageView;
    ImageView verkennerImageView;
    ImageView waterdragerImageView;

    Image zandImage = new Image("/Tiles/Low_Sand.png");
    Image zandImageGeblokkeerd = new Image("/Tiles/High_Sand.png");
    Image zonneschildImage = new Image("/placeholder.png");

    Image archeoloogImage = new Image("/placeholder.png");
    Image klimmerImage = new Image("/placeholder.png");
    Image meteooroloogImage = new Image("/placeholder.png");
    Image navigatorImage = new Image("/placeholder.png");
    Image verkennerImage = new Image("/placeholder.png");
    Image waterdragerImage = new Image("/placeholder.png");

    public TileView(Image image){
        tileController = TileController.getInstance();
        tileController.registerObserver(this, tileController.counter);

        stackPane = new StackPane();
        stackPane.getStyleClass().add("tile");

        tileImageView = new ImageView(image);
        tileImageView.setFitHeight(tileSize);
        tileImageView.setFitWidth(tileSize);

        zandImageView = new ImageView(zandImage);
        zandImageView.setFitHeight(tileSize);
        zandImageView.setFitWidth(tileSize);

        archeoloogImageView = new ImageView(archeoloogImage);
        archeoloogImageView.setFitWidth(tileSize/3);
        archeoloogImageView.setFitHeight(tileSize/3);
        archeoloogImageView.setOpacity(0);
        klimmerImageView = new ImageView(klimmerImage);
        klimmerImageView.setFitWidth(tileSize/3);
        klimmerImageView.setFitHeight(tileSize/3);
        klimmerImageView.setOpacity(0);
        meteooroloogImageView = new ImageView(meteooroloogImage);
        meteooroloogImageView.setFitWidth(tileSize/3);
        meteooroloogImageView.setFitHeight(tileSize/3);
        meteooroloogImageView.setOpacity(0);
        navigatorImageView = new ImageView(navigatorImage);
        navigatorImageView.setFitWidth(tileSize/3);
        navigatorImageView.setFitHeight(tileSize/3);
        navigatorImageView.setOpacity(0);
        verkennerImageView = new ImageView(verkennerImage);
        verkennerImageView.setFitWidth(tileSize/3);
        verkennerImageView.setFitHeight(tileSize/3);
        verkennerImageView.setOpacity(0);
        waterdragerImageView = new ImageView(waterdragerImage);
        waterdragerImageView.setFitWidth(tileSize/3);
        waterdragerImageView.setFitHeight(tileSize/3);
        waterdragerImageView.setOpacity(0);

        onderdeelStackPane = new StackPane();
        zandLabel = new Label(" ");
        zandLabel.setMinWidth(tileSize/3);
        zandLabel.setMinHeight(tileSize/3);

        gridPane = new GridPane();
        gridPane.add(zandLabel, 1, 1);
        gridPane.add(onderdeelStackPane, 1, 1);
        gridPane.add(archeoloogImageView, 0, 0);
        gridPane.add(klimmerImageView, 2, 0);
        gridPane.add(meteooroloogImageView, 0, 1);
        gridPane.add(navigatorImageView, 2, 1);
        gridPane.add(verkennerImageView, 0, 2);
        gridPane.add(waterdragerImageView, 2, 2);

        zonneschildImageView = new ImageView(zonneschildImage);
        zandImageView.setFitHeight(tileSize);
        zandImageView.setFitWidth(tileSize);

        stackPane.getChildren().addAll(tileImageView, zandImageView, gridPane/*, zonneschildImageView*/);
        stackPane.setMaxWidth(tileSize);
        stackPane.setMaxHeight(tileSize);
    }

    public StackPane maakTile() {

        return stackPane;

    }

    public void checkZand(int zand){
        switch(zand){
            case 0:
                // Geen zand
                zandImageView.setOpacity(0);
                zandLabel.setText("");
                break;
            case 1:
                // Laag zand
                zandImageView.setOpacity(1);
                zandImageView.setImage(zandImage);
                zandLabel.setText(" 1");
                break;
            default:
                // Hoog zand
                zandImageView.setOpacity(1);
                zandImageView.setImage(zandImageGeblokkeerd);
                zandLabel.setText(" "+zand);
        }

    }

    public void update(BordObservable bo){
        Tile tile = (Tile) bo;
        tileImageView.setImage(tile.getImage());
        checkZand(tile.getZand());
    }
}
