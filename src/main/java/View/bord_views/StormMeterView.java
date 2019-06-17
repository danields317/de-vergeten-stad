package View.bord_views;

import Controller.Bord_Controllers.Storm_Meter_Controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import observers.StormMeterObservable;
import observers.StormMeterObserver;

public class StormMeterView implements StormMeterObserver {
    Storm_Meter_Controller storm_meter_controller;
    static GridPane view;
    public StormMeterView(){
        this.storm_meter_controller = Storm_Meter_Controller.getInstance();
        storm_meter_controller.registerObserver(this);
    }

    public GridPane createInitialGridPane(){
        storm_meter_controller.setStormController();
        storm_meter_controller.register();


        GridPane gridPane = new GridPane();
        gridPane.setMinSize(400, 200);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);


        ImageView waterImage = new ImageView(new Image("/wijzer.png"));
        waterImage.setFitWidth(75.0);
        waterImage.setFitHeight(35.0);
        gridPane.add(waterImage, 0, 0);
        gridPane.setLayoutX(-10);
        gridPane.setLayoutY(515);



        //gridPane.add(image, 0, 0);
        //gridPane.add(scoreText, 1, 0);
        view = gridPane;

        return gridPane;
    }

    private void createUpdatedGridPane(StormMeterObservable sb){
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(400, 200);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);

        ImageView waterImage = new ImageView(new Image("/wijzer.png"));
        waterImage.setFitWidth(75.0);
        waterImage.setFitHeight(35.0);
        gridPane.add(waterImage, 0, 0);
        gridPane.setLayoutX(-10);
        gridPane.setLayoutY(sb.getHoogte());

        view = gridPane;
    }


    public GridPane getView(){
        return view;
    }









    @Override
    public void update(StormMeterObservable sb) {
        createUpdatedGridPane(sb);
    }
}
