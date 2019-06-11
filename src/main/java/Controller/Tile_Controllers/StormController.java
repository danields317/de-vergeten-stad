package Controller.Tile_Controllers;

import Model.storm.Storm;
import Model.storm.StormEvent;
import Model.storm.StormEventBeweging;
import observers.StormObserver;

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

    TileController tileController;

    private StormController(){
        storm = new Storm();
        makeEvents();
        randomizeEvents(stormEvents);
        stapelCounter = 0;
        tileController = TileController.getInstance();
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
    private void makeEvents(){
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
            }else{
                stormEvents.add(new StormEventBeweging(StormEvent.Namen.BEWEGING, StormEventBeweging.Richtingen.WEST, StormEventBeweging.Stappen.THREE));
            }
        }
    }

    private void randomizeEvents(ArrayList<StormEvent> stormEvents){

        if (stormEvents.isEmpty()){
            return;
        }

        int randomInt = random.nextInt(stormEvents.size());
        randomStormEvents.add(stormEvents.get(randomInt));
        stormEvents.remove(randomInt);

        randomizeEvents(stormEvents);
    }

    public void voerStormEventsUit(){
        int tmpSterkte = storm.getSterkte();
        for (int i = 0; i < tmpSterkte; i++){
            if (stapelCounter < randomStormEvents.size()){
                StormEvent stormEvent = randomStormEvents.get(stapelCounter);
                switch (stormEvent.naam){
                    case BEWEGING:
                        beweegStorm(((StormEventBeweging) stormEvent).richting, ((StormEventBeweging) stormEvent).stappen);
                        System.out.println("Storm beweegt");
                        break;
                    case BRANDT:
//                    zonBrandt();
                        System.out.println("Zon brandt");
                        break;
                    case STERKER:
                        storm.stormWordtSterker();
                        System.out.println("Storm subSterkte increased");
                        break;
                    default:
                        System.out.println("DIT HOORT NIET");
                }
                stapelCounter++;
            }else{
                stapelCounter = 0;
                makeEvents();
                randomizeEvents(stormEvents);
            }
        }
    }

    private void beweegStorm(StormEventBeweging.Richtingen richting, StormEventBeweging.Stappen stappen){
        switch (richting){
            case NOORD:
                tileController.moveTiles(richting, stappen, storm.getX(), storm.getY());
                storm.beweegNoord(stappen);
                break;
            case OOST:
                tileController.moveTiles(richting, stappen, storm.getX(), storm.getY());
                storm.beweegOost(stappen);
                break;
            case ZUID:
                tileController.moveTiles(richting, stappen, storm.getX(), storm.getY());
                storm.beweegZuid(stappen);
                break;
            case WEST:
                tileController.moveTiles(richting, stappen, storm.getX(), storm.getY());
                storm.beweegWest(stappen);
                break;
            default:
                System.out.println("DIT HOORT NIET");
        }
    }

    public void registerObserver(StormObserver bo){ storm.register(bo); }


}