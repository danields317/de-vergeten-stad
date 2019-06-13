package Controller.Bord_Controllers;

import Controller.Tile_Controllers.StormController;
import Model.Bord.StormMeter;
import observers.StormMeterObserver;
import observers.StormObservable;
import observers.StormObserver;

public class Storm_Meter_Controller implements StormObserver {
    static Storm_Meter_Controller storm_meter_controller;
    StormController stormController;
    StormMeter stormMeter;


    public Storm_Meter_Controller() {
        this.stormMeter = new StormMeter();


    }

    public static Storm_Meter_Controller getInstance(){
        if (storm_meter_controller == null) {
            storm_meter_controller = new Storm_Meter_Controller();

        }
        return storm_meter_controller;
    }
    public void registerObserver(StormMeterObserver observer){
        stormMeter.register(observer);
    }
    public void setStormController() {
        stormController = StormController.getInstance();
    }
    public void register(){
        stormController.registerObserver(this);
    }


    @Override
    public void update(StormObservable sb){
        stormMeter.bepaalHoogte(sb.getSubSterkte());
    }
}
