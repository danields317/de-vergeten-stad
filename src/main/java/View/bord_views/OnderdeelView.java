package View.bord_views;

import Controller.Tile_Controllers.TileController;
import Model.Bord.Onderdeel;
import Model.Tiles.PartTile;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import observers.OnderdeelObservable;
import observers.OnderdeelObserver;

public class OnderdeelView implements OnderdeelObserver {

    private StackPane propellerView;
    private StackPane beaconView;
    private StackPane motorView;
    private StackPane zonnewijzerView;
    private final double onderdeelOpactiy = 0.8;

    private Label propellerLabel;
    private Label beaconLabel;
    private Label motorLabel;
    private Label zonnewijzerLabel;

    private Rectangle propellerRectangle;
    private Rectangle beaconRectangle;
    private Rectangle motorRectangle;
    private Rectangle zonnewijzerRectangle;

    TileController tileController;

    public StackPane loadPropeller(String X, String Y){

        tileController = TileController.getInstance();
        tileController.registerOnderdeelObserver(this);

        propellerRectangle = new Rectangle(150, 150);
        propellerRectangle.setFill(Color.BLACK);
        propellerRectangle.setOpacity(onderdeelOpactiy);

        propellerLabel = maakLabel(X, Y);
        Image propeller = new Image("/Onderdelen/PropBoven.png");
        ImageView imageview = new ImageView(propeller);
        imageview.setFitWidth(150);
        imageview.setFitHeight(150);

        propellerView = new StackPane();
        propellerView.getChildren().addAll(imageview, propellerRectangle, propellerLabel);
        propellerView.setLayoutX(1393);
        propellerView.setLayoutY(60);

        return propellerView;
    }

    public StackPane loadBeacon(String X, String Y){

        beaconRectangle = new Rectangle(150, 150);
        beaconRectangle.setFill(Color.BLACK);
        beaconRectangle.setOpacity(onderdeelOpactiy);

        beaconLabel = maakLabel(X, Y);
        Image beacon = new Image("/Onderdelen/BeaconZijkant.png");
        ImageView imageview = new ImageView(beacon);
        imageview.setFitWidth(140);
        imageview.setFitHeight(140);

        beaconView = new StackPane();
        beaconView.getChildren().addAll(imageview, beaconRectangle, beaconLabel);
        beaconView.setLayoutX(1393);
        beaconView.setLayoutY(213);

        return beaconView;
    }

    public StackPane loadMotor(String X, String Y){

        motorRectangle = new Rectangle(150, 150);
        motorRectangle.setFill(Color.BLACK);
        motorRectangle.setOpacity(onderdeelOpactiy);

        motorLabel = maakLabel(X, Y);
        Image motor = new Image("/Onderdelen/EngineZijkant.png");
        ImageView imageview = new ImageView(motor);
        imageview.setFitWidth(140);
        imageview.setFitHeight(140);

        motorView = new StackPane();
        motorView.getChildren().addAll(imageview, motorRectangle, motorLabel);
        motorView.setLayoutX(1393);
        motorView.setLayoutY(367);

        return motorView;
    }

    public StackPane loadZonneWijzer(String X, String Y){

        zonnewijzerRectangle = new Rectangle(150, 150);
        zonnewijzerRectangle.setFill(Color.BLACK);
        zonnewijzerRectangle.setOpacity(onderdeelOpactiy);

        Image zonnewijzer = new Image("/Onderdelen/ZonnewijzerBoven.png");
        zonnewijzerLabel = maakLabel(X, Y);
        ImageView imageview = new ImageView(zonnewijzer);
        imageview.setFitWidth(140);
        imageview.setFitHeight(140);

        zonnewijzerView = new StackPane();
        zonnewijzerView.getChildren().addAll(imageview, zonnewijzerRectangle, zonnewijzerLabel);
        zonnewijzerView.setLayoutX(1393);
        zonnewijzerView.setLayoutY(520);

        return zonnewijzerView;
    }

    private Label maakLabel(String X, String Y){
        Label coordinaten = new Label("X:" + X + " Y:" + Y);
        coordinaten.setFont(new Font(40));
        coordinaten.setTextFill(Color.WHITE);
        coordinaten.setOpacity(25);
        return coordinaten;
    }

    private void checkHints(Onderdeel onderdeel){
        String x;
        String y;
        if (onderdeel.getX() == -1){
            x = "?";
        }
        else{
            int tempX = onderdeel.getX() + 1;
            x = String.valueOf(tempX);
        }

        if (onderdeel.getY() == -1){
            y = "?";
        }
        else{
            int tempY = onderdeel.getY() + 1;
            y = String.valueOf(tempY);
        }

        if(onderdeel.getSoort().equals(PartTile.Soorten.KOMPAS)){
            zonnewijzerLabel.setText("X:" + x + " Y:" + y);
        }
        else if(onderdeel.getSoort().equals(PartTile.Soorten.MOTOR)){
            motorLabel.setText("X:" + x + " Y:" + y);
        }
        else if(onderdeel.getSoort().equals(PartTile.Soorten.PROPELOR)){
            propellerLabel.setText("X:" + x + " Y:" + y);
        }
        else if (onderdeel.getSoort().equals(PartTile.Soorten.OBELISK)){
            beaconLabel.setText("X:" + x + " Y:" + y);
        }
    }

    private void checkOpgepakt(Onderdeel onderdeel){
        if (onderdeel.isOpgepakt()){
            if(onderdeel.getSoort().equals(PartTile.Soorten.KOMPAS)){
                zonnewijzerLabel.setOpacity(0);
                zonnewijzerRectangle.setOpacity(0);
            }
            else if(onderdeel.getSoort().equals(PartTile.Soorten.MOTOR)){
                motorLabel.setOpacity(0);
                motorRectangle.setOpacity(0);
            }
            else if(onderdeel.getSoort().equals(PartTile.Soorten.PROPELOR)){
                propellerLabel.setOpacity(0);
                propellerRectangle.setOpacity(0);
            }
            else if (onderdeel.getSoort().equals(PartTile.Soorten.OBELISK)){
                beaconLabel.setOpacity(0);
                beaconRectangle.setOpacity(0);
            }
        }
    }

    public StackPane getPropellerView() {
        return propellerView;
    }

    public StackPane getBeaconView() {
        return beaconView;
    }

    public StackPane getMotorView() {
        return motorView;
    }

    public StackPane getZonnewijzerView() {
        return zonnewijzerView;
    }

    @Override
    public void update(OnderdeelObservable ob) {
        Onderdeel onderdeel = (Onderdeel) ob;
        checkHints(onderdeel);
        checkOpgepakt(onderdeel);
    }
}
