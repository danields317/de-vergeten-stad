package View;

import Controller.Tile_Controllers.TileController;
import Model.Tiles.Tile;
import Model.player.Player;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import observers.BordObservable;
import observers.BordObserver;

import java.util.ArrayList;

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

    ImageView beaconImageView;
    ImageView engineImageView;
    ImageView propellerImageView;
    ImageView zonnewijzerImageView;

    Image zandImage = new Image("/Tiles/Low_Sand.png");
    Image zandImageGeblokkeerd = new Image("/Tiles/High_Sand.png");
    Image zonneschildImage = new Image("/zonneschild.png");

    Image archeoloogImage = new Image("/placeholder.png");
    Image klimmerImage = new Image("/placeholder.png");
    Image meteooroloogImage = new Image("/placeholder.png");
    Image navigatorImage = new Image("/placeholder.png");
    Image verkennerImage = new Image("/placeholder.png");
    Image waterdragerImage = new Image("/placeholder.png");

    Image beaconImage = new Image("/Onderdelen/BeaconBoven.png");
    Image engineImage = new Image("/Onderdelen/EngineBoven.png");
    Image propellerImage = new Image("/Onderdelen/PropBoven.png");
    Image zonnewijzerImage = new Image("/Onderdelen/ZonnewijzerBoven.png");

    public TileView(Image image, Tile tile){
        tileController = TileController.getInstance();
        tileController.registerObserver(this, tile);

        stackPane = new StackPane();
        stackPane.getStyleClass().add("tile");

        tileImageView = new ImageView(image);
        tileImageView.setFitHeight(tileSize);
        tileImageView.setFitWidth(tileSize);

        zandImageView = new ImageView(zandImage);
        zandImageView.setFitHeight(tileSize);
        zandImageView.setFitWidth(tileSize);
        zandImageView.setOpacity(0);

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

        beaconImageView = new ImageView(beaconImage);
        beaconImageView.setFitWidth(tileSize/3);
        beaconImageView.setFitHeight(tileSize/3);
        engineImageView = new ImageView(engineImage);
        engineImageView.setFitWidth(tileSize/3);
        engineImageView.setFitHeight(tileSize/3);
        propellerImageView = new ImageView(propellerImage);
        propellerImageView.setFitWidth(tileSize/3);
        propellerImageView.setFitHeight(tileSize/3);
        zonnewijzerImageView = new ImageView(zonnewijzerImage);
        zonnewijzerImageView.setFitWidth(tileSize/3);
        zonnewijzerImageView.setFitHeight(tileSize/3);

        onderdeelStackPane = new StackPane();
        onderdeelStackPane.getChildren().addAll(zonnewijzerImageView, propellerImageView, beaconImageView, engineImageView);

        zandLabel = new Label("");
        zandLabel.setMinWidth(tileSize/3);
        zandLabel.setMinHeight(tileSize/3);

        gridPane = new GridPane();
        gridPane.add(zandLabel, 1, 1);
        gridPane.add(onderdeelStackPane, 1, 0);
        gridPane.add(archeoloogImageView, 0, 0);
        gridPane.add(klimmerImageView, 2, 0);
        gridPane.add(meteooroloogImageView, 0, 1);
        gridPane.add(navigatorImageView, 2, 1);
        gridPane.add(verkennerImageView, 0, 2);
        gridPane.add(waterdragerImageView, 2, 2);

        zonneschildImageView = new ImageView(zonneschildImage);
        zonneschildImageView.setFitHeight(tileSize);
        zonneschildImageView.setFitWidth(tileSize);
        zonneschildImageView.setOpacity(0);

        stackPane.getChildren().addAll(tileImageView, zandImageView, gridPane, zonneschildImageView);
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

    public void checkSpelers(ArrayList<Player> spelers){
        archeoloogImageView.setOpacity(0);
        klimmerImageView.setOpacity(0);
        meteooroloogImageView.setOpacity(0);
        navigatorImageView.setOpacity(0);
        verkennerImageView.setOpacity(0);
        waterdragerImageView.setOpacity(0);
        for(Player speler :spelers){
            switch (speler.getClassName()){
                case "Archeoloog":
                    archeoloogImageView.setOpacity(1);
                    break;
                case "Klimmer":
                    klimmerImageView.setOpacity(1);
                    break;
                case "Meteroloog":
                    meteooroloogImageView.setOpacity(1);
                    break;
                case "Navigator":
                    navigatorImageView.setOpacity(1);
                    break;
                case "Verkenner":
                    verkennerImageView.setOpacity(1);
                    break;
                case "Waterdrager":
                    waterdragerImageView.setOpacity(1);
                    break;
                default: continue;
            }
        }
    }

    /*public void checkOnderdelen(ArrayList<PartTile> onderdelen){
        beaconImageView.setOpacity(0);
        propellerImageView.setOpacity(0);
        engineImageView.setOpacity(0);
        zonnewijzerImageView.setOpacity(0);
        for ( )
    }*/

    public void checkZonneSchild(boolean heeftZonneschild) {
        if (heeftZonneschild) {
            zonneschildImageView.setOpacity(0.40);
        } else {
            zonneschildImageView.setOpacity(0);
        }
    }

    public void update(BordObservable bo){

        Tile tile = (Tile) bo;
        tileImageView.setImage(tile.getImage());
        checkZand(tile.getZand());
        checkZonneSchild(tile.heeftZonneschild());
        //checkSpelers(tile.getSpelers());

    }
}
