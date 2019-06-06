package Controller.Tile_Controllers;

import Model.storm.Storm;
import Model.storm.StormEvent;
import Model.storm.StormEventBeweging;
import observers.BordObserver;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author ryan
 */
public class StormController {

    static StormController stormcontroller;
    Storm storm;

    private ArrayList<StormEvent> stormEvents = new ArrayList<>();
    private ArrayList<StormEvent> randomStormEvents = new ArrayList<>();
    private Random random = new Random();
    private int stapelCounter;

    private StormController(){
        storm = new Storm();
        makeEvents();
        randomizeEvents(stormEvents);
        stapelCounter = 0;
    }

    public static StormController getInstance(){
        if (stormcontroller == null){
            stormcontroller = new StormController();
        }
        return stormcontroller;
    }

    /**
     * Deze functie maakt alle stormevents aan die kunnen gebeuren.
     * Volgens de spel regels zijn er 31 stormevents in totaal dus daarom loopt de for loop tot 31.
     */
    public void makeEvents(){
        for (int i = 0; i < 31; i++){
            if (i < 4) {
                stormEvents.add(new StormEvent(StormEvent.Namen.BRANDT));
            }else if (i < 7){
                stormEvents.add(new StormEvent(StormEvent.Namen.STERKER));
            }else if (i < 10){
                stormEvents.add(new StormEventBeweging(StormEvent.Namen.BEWEGING, StormEventBeweging.Richtingen.NOORD, StormEventBeweging.Stappen.ONE));
            }else if (i < 13){
                stormEvents.add(new StormEventBeweging(StormEvent.Namen.BEWEGING, StormEventBeweging.Richtingen.OOST, StormEventBeweging.Stappen.ONE));
            }else if (i < 16){
                stormEvents.add(new StormEventBeweging(StormEvent.Namen.BEWEGING, StormEventBeweging.Richtingen.ZUID, StormEventBeweging.Stappen.ONE));
            }else if (i < 19){
                stormEvents.add(new StormEventBeweging(StormEvent.Namen.BEWEGING, StormEventBeweging.Richtingen.WEST, StormEventBeweging.Stappen.ONE));
            }else if (i < 21){
                stormEvents.add(new StormEventBeweging(StormEvent.Namen.BEWEGING, StormEventBeweging.Richtingen.NOORD, StormEventBeweging.Stappen.TWO));
            }else if (i < 23){
                stormEvents.add(new StormEventBeweging(StormEvent.Namen.BEWEGING, StormEventBeweging.Richtingen.OOST, StormEventBeweging.Stappen.TWO));
            }else if (i < 25){
                stormEvents.add(new StormEventBeweging(StormEvent.Namen.BEWEGING, StormEventBeweging.Richtingen.ZUID, StormEventBeweging.Stappen.TWO));
            }else if (i < 27){
                stormEvents.add(new StormEventBeweging(StormEvent.Namen.BEWEGING, StormEventBeweging.Richtingen.WEST, StormEventBeweging.Stappen.TWO));
            }else if (i < 28){
                stormEvents.add(new StormEventBeweging(StormEvent.Namen.BEWEGING, StormEventBeweging.Richtingen.NOORD, StormEventBeweging.Stappen.THREE));
            }else if (i < 29){
                stormEvents.add(new StormEventBeweging(StormEvent.Namen.BEWEGING, StormEventBeweging.Richtingen.OOST, StormEventBeweging.Stappen.THREE));
            }else if (i < 30){
                stormEvents.add(new StormEventBeweging(StormEvent.Namen.BEWEGING, StormEventBeweging.Richtingen.ZUID, StormEventBeweging.Stappen.THREE));
            }else if (i < 31){
                stormEvents.add(new StormEventBeweging(StormEvent.Namen.BEWEGING, StormEventBeweging.Richtingen.WEST, StormEventBeweging.Stappen.THREE));
            }
        }
    }

    public void randomizeEvents(ArrayList<StormEvent> stormEvents){

        if (stormEvents.isEmpty()){
            return;
        }

        int randomInt = random.nextInt(stormEvents.size());
        randomStormEvents.add(stormEvents.get(randomInt));
        stormEvents.remove(randomInt);

        randomizeEvents(stormEvents);
    }

    public void voerStormEventsUit(ArrayList<StormEvent> stormEvents){
        int tmpSterkte = storm.getSterkte();
        for (int i = 0; i < tmpSterkte; i++){
            StormEvent stormEvent = stormEvents.get(stapelCounter);
            switch (stormEvent.naam){
                case BEWEGING:
                    beweegStorm(((StormEventBeweging) stormEvent).richting, ((StormEventBeweging) stormEvent).stappen);
                    break;
                case BRANDT:
//                    zonBrandt();
                    break;
                case STERKER:
                    storm.stormWordtSterker();
                    break;
                default:
                    System.out.println("DIT HOORT NIET");
            }
            stapelCounter++;
        }
    }

    public void beweegStorm(StormEventBeweging.Richtingen richting, StormEventBeweging.Stappen stappen){
        System.out.println("------------Begin-----------" + richting.toString());
        System.out.println(storm.getX());
        System.out.println(storm.getY());
        switch (richting){
            case NOORD:
                storm.beweegNoord(stappen);
                break;
            case OOST:
                storm.beweegOost(stappen);
                break;
            case ZUID:
                storm.beweegZuid(stappen);
                break;
            case WEST:
                storm.beweegWest(stappen);
                break;
            default:
                System.out.println("DIT HOORT NIET");
        }
        System.out.println("------------Bewogen-----------");
        System.out.println(storm.getX());
        System.out.println(storm.getY());
    }

    public void registerObserver(BordObserver bo){ storm.register(bo); }


}
