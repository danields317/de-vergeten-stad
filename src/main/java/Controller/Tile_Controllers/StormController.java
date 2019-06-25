package Controller.Tile_Controllers;

import Controller.Bord_Controllers.SoundController;
import Controller.Controller;
import Controller.Player_Controllers.FunctieController;
import Controller.Player_Controllers.PlayerController;
import Model.data.StaticData;
import Model.storm.Storm;
import Model.storm.StormEvent;
import Model.storm.StormEventBeweging;
import View.bord_views.SpeelbordView;
import javafx.application.Platform;
import observers.StormObserver;

import java.lang.invoke.SwitchPoint;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

/**
 * De StormController klasse zorgt ervoor dat de storm functies uitvoert aan de hand van StormEvents.
 * Ook update deze klasse de storm als er een update is in FireBase.
 *
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

    private boolean hasMadeEvents = false;

    private int aantalStormEvents = 30;

    public StormController(){
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
     * De for loop loopt tot en met 30 omdat er 30 StormEvents zijn.
     */
    private void makeEvents(){
        for (int i = 0; i <= aantalStormEvents; i++){
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

    /**
     * Deze functie randomized een ArrayList die hij mee krijgt.
     * De functie is recusrsive.
     *
     * @param stormEvents De ArrayList die wordt gerandomized, bestaande uit StormEvents.
     * @auhtor ryan
     */
    private void randomizeEvents(ArrayList<StormEvent> stormEvents){

        if (stormEvents.isEmpty()){
            return;
        }

        int randomInt = random.nextInt(stormEvents.size());
        randomStormEvents.add(stormEvents.get(randomInt));
        stormEvents.remove(randomInt);

        randomizeEvents(stormEvents);
    }

    /**
     * Deze functie loopt door alle StormEvents en voert aan de ahnd van het type event een actie uit.
     *
     * Er zijn 3 cases die andere functionaliteiten van de storm uitvoeren.
     *
     * De else statement reset de stapelCounter en randomized de stormEvents opnieuw.
     *
     * @author ryan
     */
    public void voerStormEventsUit(){
        int tmpSterkte = storm.getSterkte();
        for (int i = 0; i < tmpSterkte; i++){
            if (stapelCounter < randomStormEvents.size()){
                StormEvent stormEvent = randomStormEvents.get(stapelCounter);
                switch (stormEvent.naam){
                    case BEWEGING:
                        beweegStorm(((StormEventBeweging) stormEvent).richting, ((StormEventBeweging) stormEvent).stappen);
                        break;
                    case BRANDT:
                        if (checkPlayerWater()){
                            (FunctieController.getInstance()).endLose();
                        } else {
                            Controller controller = Controller.getInstance();
                            controller.zonBrand();
                            (FunctieController.getInstance()).updateInfo();
                        }
                        break;
                    case STERKER:
                        storm.stormWordtSterker();
                        break;
                    default:
                        System.out.println("DIT HOORT NIET: StormController");
                }
                stapelCounter++;
            }else{
                stapelCounter = 0;
                makeEvents();
                randomizeEvents(stormEvents);
            }
        }
        storm.notifyAllObservers();
        tileController.checkZandCounter();
    }

    private boolean checkPlayerWater(){
        StaticData staticData = StaticData.getInstance();
        Map<String, Object> gebruikers = (Map)((Map)staticData.getRoomInfo()).get("Selectable_classes");
        for (int i = 0; i < 4; i++){
            if (((Map)(gebruikers.get(Integer.toString(i)))).get("water").equals("0")){
                return true;
            }
        }
        return false;
    }

    /**
     * Deze functie is een door geef luik om de storm een bepaalde richting op te laten bewegen.
     *
     * @param richting De richting die de storm op beweegt.
     * @param stappen Het aantal stappen dat de storm beweegt.
     */
    private void beweegStorm(StormEventBeweging.Richtingen richting, StormEventBeweging.Stappen stappen){
        switch (richting){
            case NOORD:
                tileController.moveTileZuid(stappen, storm.getX(), storm.getY());
                storm.beweegNoord(stappen);
                break;
            case OOST:
                tileController.moveTileWest(stappen, storm.getX(), storm.getY());
                storm.beweegOost(stappen);
                break;
            case ZUID:
                tileController.moveTileNoord(stappen, storm.getX(), storm.getY());
                storm.beweegZuid(stappen);
                break;
            case WEST:
                tileController.moveTileOost(stappen, storm.getX(), storm.getY());
                storm.beweegWest(stappen);
                break;
            default:
                System.out.println("DIT HOORT NIET: StormController");
        }

    }

    public ArrayList<StormEvent> getStormEvents() {
        return randomStormEvents;
    }

    public Storm getStorm(){
        return storm;
    }

    public void registerObserver(StormObserver bo){ storm.register(bo); }

    public void update(){storm.notifyAllObservers();}

    /**
     * Deze functie update de storm, de stormevents en de stapelCounter met informatie uit FireBase.
     *
     * @author ryan
     */
    public void updateData(){
        StaticData staticData = StaticData.getInstance();
        Object roominfo = staticData.getRoomInfo();
        Map<String, Object> stormMap = (Map)((Map) roominfo).get("storm");
        if (!hasMadeEvents){
            makeRandomStormEventsFB((Map)stormMap.get("events"));
            hasMadeEvents = true;
        }
        storm.setSterkte(Integer.valueOf(stormMap.get("sterkte").toString()));
        storm.setSubSterkte(Integer.valueOf(stormMap.get("subSterkte").toString()));
        storm.setLocatie(Integer.valueOf(stormMap.get("x").toString()), Integer.valueOf(stormMap.get("y").toString()));
        setStapelCounter(Integer.valueOf(stormMap.get("stapelCounter").toString()));
    }

    /**
     * Deze functie intialiseerd stormEvents die hij binnen krijgt vanuit FireBase.
     * De for loop gaat tot en met 30 omdat er in totaal 30 stormEvents zijn.
     *
     * @param stormEvents Een Map<String, Object> waarin alle stormEvents zitten.
     * @author ryan
     */
    private void makeRandomStormEventsFB(Map<String, Object> stormEvents){
        ArrayList<StormEvent> events = new ArrayList<>();
        for (int i = 0; i <= aantalStormEvents; i++){
            if (stormEvents.get(Integer.toString(i)).equals("BRANDT")){
                events.add(new StormEvent(StormEvent.Namen.BRANDT));
            } else if (stormEvents.get(Integer.toString(i)).equals("STERKER")){
                events.add(new StormEvent(StormEvent.Namen.STERKER));
            } else if (stormEvents.get(Integer.toString(i)).equals("BEWEGINGNOORDONE")){
                events.add(new StormEventBeweging(StormEvent.Namen.BEWEGING, StormEventBeweging.Richtingen.NOORD,StormEventBeweging.Stappen.ONE));
            } else if (stormEvents.get(Integer.toString(i)).equals("BEWEGINGNOORDTWO")){
                events.add(new StormEventBeweging(StormEvent.Namen.BEWEGING, StormEventBeweging.Richtingen.NOORD,StormEventBeweging.Stappen.TWO));
            } else if (stormEvents.get(Integer.toString(i)).equals("BEWEGINGNOORDTHREE")){
                events.add(new StormEventBeweging(StormEvent.Namen.BEWEGING, StormEventBeweging.Richtingen.NOORD,StormEventBeweging.Stappen.THREE));
            } else if (stormEvents.get(Integer.toString(i)).equals("BEWEGINGOOSTONE")){
                events.add(new StormEventBeweging(StormEvent.Namen.BEWEGING, StormEventBeweging.Richtingen.OOST,StormEventBeweging.Stappen.ONE));
            } else if (stormEvents.get(Integer.toString(i)).equals("BEWEGINGOOSTTWO")){
                events.add(new StormEventBeweging(StormEvent.Namen.BEWEGING, StormEventBeweging.Richtingen.OOST,StormEventBeweging.Stappen.TWO));
            } else if (stormEvents.get(Integer.toString(i)).equals("BEWEGINGOOSTTHREE")){
                events.add(new StormEventBeweging(StormEvent.Namen.BEWEGING, StormEventBeweging.Richtingen.OOST,StormEventBeweging.Stappen.THREE));
            } else if (stormEvents.get(Integer.toString(i)).equals("BEWEGINGZUIDONE")){
                events.add(new StormEventBeweging(StormEvent.Namen.BEWEGING, StormEventBeweging.Richtingen.ZUID,StormEventBeweging.Stappen.ONE));
            } else if (stormEvents.get(Integer.toString(i)).equals("BEWEGINGZUIDTWO")){
                events.add(new StormEventBeweging(StormEvent.Namen.BEWEGING, StormEventBeweging.Richtingen.ZUID,StormEventBeweging.Stappen.TWO));
            } else if (stormEvents.get(Integer.toString(i)).equals("BEWEGINGZUIDTHREE")){
                events.add(new StormEventBeweging(StormEvent.Namen.BEWEGING, StormEventBeweging.Richtingen.ZUID,StormEventBeweging.Stappen.THREE));
            } else if (stormEvents.get(Integer.toString(i)).equals("BEWEGINGWESTONE")){
                events.add(new StormEventBeweging(StormEvent.Namen.BEWEGING, StormEventBeweging.Richtingen.WEST,StormEventBeweging.Stappen.ONE));
            } else if (stormEvents.get(Integer.toString(i)).equals("BEWEGINGWESTTWO")){
                events.add(new StormEventBeweging(StormEvent.Namen.BEWEGING, StormEventBeweging.Richtingen.WEST,StormEventBeweging.Stappen.TWO));
            } else if (stormEvents.get(Integer.toString(i)).equals("BEWEGINGWESTTHREE")){
                events.add(new StormEventBeweging(StormEvent.Namen.BEWEGING, StormEventBeweging.Richtingen.WEST,StormEventBeweging.Stappen.THREE));
            }
        }
        randomStormEvents = events;
    }

    public int getStapelCounter(){
        return stapelCounter;
    }

    public void setStapelCounter(int counter){
        this.stapelCounter = counter;
    }


}
