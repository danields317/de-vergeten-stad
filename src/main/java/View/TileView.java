package View;

import Controller.Player_Controllers.PlayerController;
import Controller.Tile_Controllers.TileController;
import Model.Bord.Onderdeel;
import Model.Tiles.PartTile;
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

public class TileView implements BordObserver {

    TileController tileController;
    PlayerController playerController = PlayerController.getInstance();

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
    ImageView motorImageView;
    ImageView propellerImageView;
    ImageView wijzerImageView;

    Image zandImage = new Image("/Tiles/Low_Sand.png");
    Image zandImageGeblokkeerd = new Image("/Tiles/High_Sand.png");
    Image zonneschildImage = new Image("/ingame_Sun_Shield.png");

    Image archeoloogImage = new Image("/Players/Archeoloog.png");
    Image klimmerImage = new Image("/Players/Klimmer.png");
    Image meteooroloogImage = new Image("/Players/Meteoroloog.png");
    Image navigatorImage = new Image("/Players/Navigator.png");
    Image verkennerImage = new Image("/Players/Verkenner.png");
    Image waterdragerImage = new Image("/Players/Waterdrager.png");

    Image beacon = new Image("/Onderdelen/Beacon boven.png");
    Image motor = new Image("/Onderdelen/Engine boven.png");
    Image propeller = new Image("/Onderdelen/PropBoven.png");
    Image wijzer = new Image("/Onderdelen/ZonnewijzerBoven.png");


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
        archeoloogImageView.getStyleClass().add("speler");
        klimmerImageView = new ImageView(klimmerImage);
        klimmerImageView.setFitWidth(tileSize/3);
        klimmerImageView.setFitHeight(tileSize/3);
        klimmerImageView.setOpacity(0);
        klimmerImageView.getStyleClass().add("speler");
        meteooroloogImageView = new ImageView(meteooroloogImage);
        meteooroloogImageView.setFitWidth(tileSize/3);
        meteooroloogImageView.setFitHeight(tileSize/3);
        meteooroloogImageView.setOpacity(0);
        meteooroloogImageView.getStyleClass().add("speler");
        navigatorImageView = new ImageView(navigatorImage);
        navigatorImageView.setFitWidth(tileSize/3);
        navigatorImageView.setFitHeight(tileSize/3);
        navigatorImageView.setOpacity(0);
        navigatorImageView.getStyleClass().add("speler");
        verkennerImageView = new ImageView(verkennerImage);
        verkennerImageView.setFitWidth(tileSize/3);
        verkennerImageView.setFitHeight(tileSize/3);
        verkennerImageView.setOpacity(0);
        verkennerImageView.getStyleClass().add("speler");
        waterdragerImageView = new ImageView(waterdragerImage);
        waterdragerImageView.setFitWidth(tileSize/3);
        waterdragerImageView.setFitHeight(tileSize/3);
        waterdragerImageView.setOpacity(0);
        waterdragerImageView.getStyleClass().add("speler");

        beaconImageView = new ImageView(beacon);
        beaconImageView.setFitWidth(tileSize/3);
        beaconImageView.setFitHeight(tileSize/3);
        beaconImageView.setOpacity(0);
        motorImageView = new ImageView(motor);
        motorImageView.setFitWidth(tileSize/3);
        motorImageView.setFitHeight(tileSize/3);
        motorImageView.setOpacity(0);
        propellerImageView = new ImageView(propeller);
        propellerImageView.setFitWidth(tileSize/3);
        propellerImageView.setFitHeight(tileSize/3);
        propellerImageView.setOpacity(0);
        wijzerImageView = new ImageView(wijzer);
        wijzerImageView.setFitWidth(tileSize/3);
        wijzerImageView.setFitHeight(tileSize/3);
        wijzerImageView.setOpacity(0);

        onderdeelStackPane = new StackPane();
        onderdeelStackPane.getChildren().addAll(wijzerImageView, propellerImageView, motorImageView , beaconImageView);

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
        zonneschildImageView.setOpacity(0);
        zandImageView.setFitHeight(tileSize);
        zandImageView.setFitWidth(tileSize);

        stackPane.getChildren().addAll(tileImageView, zandImageView, gridPane, zonneschildImageView);
        stackPane.setMaxWidth(tileSize);
        stackPane.setMaxHeight(tileSize);
    }

    public StackPane maakTile() {

        return stackPane;

    }

    private void clearSpelers(){
        archeoloogImageView.setOpacity(0);
        klimmerImageView.setOpacity(0);
        meteooroloogImageView.setOpacity(0);
        navigatorImageView.setOpacity(0);
        verkennerImageView.setOpacity(0);
        waterdragerImageView.setOpacity(0);
    }


    private void checkSpelers(Tile tile){
        for(String player : tile.getPlayers()){
            if (player.equals("Klimmer")){
                klimmerImageView.setOpacity(1);
            } else if (player.equals("Archeoloog")){
                archeoloogImageView.setOpacity(1);
            } else if (player.equals("Verkenner")){
                verkennerImageView.setOpacity(1);
            } else if (player.equals("Waterdrager")){
                waterdragerImageView.setOpacity(1);
            }
        }
    }

    private void checkZand(int zand){
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

    private void clearOnderdelen(){
        beaconImageView.setOpacity(0);
        motorImageView.setOpacity(0);
        propellerImageView.setOpacity(0);
        wijzerImageView.setOpacity(0);
    }

    private void checkOnderdelen(ArrayList<Onderdeel> onderdelen){
        for(Onderdeel onderdeel: onderdelen){
            if (onderdeel.getSoort().equals(PartTile.Soorten.OBELISK)){
                beaconImageView.setOpacity(1);
            }
            else if (onderdeel.getSoort().equals(PartTile.Soorten.MOTOR)){
                motorImageView.setOpacity(1);
            }
            else if(onderdeel.getSoort().equals(PartTile.Soorten.PROPELOR)){
                propellerImageView.setOpacity(1);
            }
            else if(onderdeel.getSoort().equals(PartTile.Soorten.KOMPAS)){
                wijzerImageView.setOpacity(1);
            }
        }
    }

    public void checkZonneschild(boolean schild){
        if(schild){
            zonneschildImageView.setOpacity(0.4);
        }
        else{
            zonneschildImageView.setOpacity(0);
        }
    }


    public void update(BordObservable bo){
        Tile tile = (Tile) bo;

        clearSpelers();
        tileImageView.setImage(tile.getImage());
        checkZand(tile.getZand());
        checkSpelers(tile);

        clearOnderdelen();
        checkOnderdelen(tile.getOnderdelen());

        checkZonneschild(tile.hasZonneSchild());
    }

}
