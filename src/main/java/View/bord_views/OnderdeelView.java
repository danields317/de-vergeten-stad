package View.bord_views;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class OnderdeelView {

    private StackPane propellerView;
    private StackPane beaconView;
    private StackPane motorView;
    private StackPane zonnewijzerView;
    private final double onderdeelOpactiy = 0.8;

    public StackPane loadPropeller(String X, String Y){

        Rectangle rectangle = new Rectangle(150, 150);
        rectangle.setFill(Color.BLACK);
        rectangle.setOpacity(onderdeelOpactiy);

        Label coordinaten = maakLabel(X, Y);
        Image propeller = new Image("/Onderdelen/PropBoven.png");
        ImageView imageview = new ImageView(propeller);
        imageview.setFitWidth(150);
        imageview.setFitHeight(150);

        propellerView = new StackPane();
        propellerView.getChildren().addAll(imageview, rectangle, coordinaten);
        propellerView.setLayoutX(1393);
        propellerView.setLayoutY(60);

        return propellerView;
    }

    public StackPane loadBeacon(String X, String Y){

        Rectangle rectangle = new Rectangle(150, 150);
        rectangle.setFill(Color.BLACK);
        rectangle.setOpacity(onderdeelOpactiy);

        Label coordinaten = maakLabel(X, Y);
        Image beacon = new Image("/Onderdelen/BeaconZijkant.png");
        ImageView imageview = new ImageView(beacon);
        imageview.setFitWidth(140);
        imageview.setFitHeight(140);

        beaconView = new StackPane();
        beaconView.getChildren().addAll(imageview, rectangle, coordinaten);
        beaconView.setLayoutX(1393);
        beaconView.setLayoutY(213);

        return beaconView;
    }

    public StackPane loadMotor(String X, String Y){

        Rectangle rectangle = new Rectangle(150, 150);
        rectangle.setFill(Color.BLACK);
        rectangle.setOpacity(onderdeelOpactiy);

        Label coordinaten = maakLabel(X, Y);
        Image motor = new Image("/Onderdelen/EngineZijkant.png");
        ImageView imageview = new ImageView(motor);
        imageview.setFitWidth(140);
        imageview.setFitHeight(140);

        motorView = new StackPane();
        motorView.getChildren().addAll(imageview, rectangle, coordinaten);
        motorView.setLayoutX(1393);
        motorView.setLayoutY(367);

        return motorView;
    }

    public StackPane loadZonneWijzer(String X, String Y){

        Rectangle rectangle = new Rectangle(150, 150);
        rectangle.setFill(Color.BLACK);
        rectangle.setOpacity(onderdeelOpactiy);

        Image zonnewijzer = new Image("/Onderdelen/ZonnewijzerBoven.png");
        Label coordinaten = maakLabel(X, Y);
        ImageView imageview = new ImageView(zonnewijzer);
        imageview.setFitWidth(140);
        imageview.setFitHeight(140);

        zonnewijzerView = new StackPane();
        zonnewijzerView.getChildren().addAll(imageview, rectangle, coordinaten);
        zonnewijzerView.setLayoutX(1393);
        zonnewijzerView.setLayoutY(520);

        return zonnewijzerView;
    }

    public Label maakLabel(String X, String Y){
        Label coordinaten = new Label("X:" + X + " Y:" + Y);
        coordinaten.setFont(new Font(40));
        coordinaten.setTextFill(Color.BLACK);
        coordinaten.setOpacity(25);
        return coordinaten;
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
}
