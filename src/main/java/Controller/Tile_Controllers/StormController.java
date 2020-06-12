package Controller.Tile_Controllers;

import Controller.Controller;
import Controller.Player_Controllers.FunctieController;
import Controller.Player_Controllers.PlayerController;
import Model.data.StaticData;
import Model.storm.*;
import factories.StormEventFactory;
import observers.StormObserver;

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

    private StormEventFactory factory;

    static StormController stormcontroller;
    Storm storm;
    private ArrayList<StormEvent> stormEvents = new ArrayList<>();
    private ArrayList<StormEvent> randomStormEvents = new ArrayList<>();
    private Random random = new Random();
    private int stapelCounter;

    TileController tileController;

    private boolean hasMadeEvents = false;

    private int aantalStormEvents = 30;

    public StormController(StormEventFactory factory){
        this.factory = factory;

        storm = new Storm();
        makeEvents();
        randomizeEvents(stormEvents);
        stapelCounter = 0;
        tileController = TileController.getInstance();
    }

    public static StormController getInstance(){
        if (stormcontroller == null){
            stormcontroller = new StormController(new StormEventFactory());
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
                stormEvents.add(factory.createStormEvent("BRANDT"));
            }else if (i < 7){
                stormEvents.add(factory.createStormEvent("STERKER"));
            }else if (i < 10){
                stormEvents.add(factory.createStormEventBeweging("NOORD", Stappen.ONE));
            }else if (i < 13){
                stormEvents.add(factory.createStormEventBeweging("OOST", Stappen.ONE));
            }else if (i < 16){
                stormEvents.add(factory.createStormEventBeweging("ZUID", Stappen.ONE));
            }else if (i < 19){
                stormEvents.add(factory.createStormEventBeweging("WEST", Stappen.ONE));
            }else if (i < 21){
                stormEvents.add(factory.createStormEventBeweging("NOORD", Stappen.TWO));
            }else if (i < 23){
                stormEvents.add(factory.createStormEventBeweging("OOST", Stappen.TWO));
            }else if (i < 25){
                stormEvents.add(factory.createStormEventBeweging("ZUID", Stappen.TWO));
            }else if (i < 27){
                stormEvents.add(factory.createStormEventBeweging("WEST", Stappen.TWO));
            }else if (i < 28){
                stormEvents.add(factory.createStormEventBeweging("NOORD", Stappen.THREE));
            }else if (i < 29){
                stormEvents.add(factory.createStormEventBeweging("OOST", Stappen.THREE));
            }else if (i < 30){
                stormEvents.add(factory.createStormEventBeweging("ZUID", Stappen.THREE));
            }else{
                stormEvents.add(factory.createStormEventBeweging("WEST", Stappen.THREE));
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
                switch (stormEvent.getNaam()){
                    case "NOORD":
                        beweegStorm(stormEvent.getNaam(), ((StormEventNoord) stormEvent).getStappen());
                        break;
                    case "OOST":
                        beweegStorm(stormEvent.getNaam(), ((StormEventOost) stormEvent).getStappen());
                        break;
                    case "ZUID":
                        beweegStorm(stormEvent.getNaam(), ((StormEventZuid) stormEvent).getStappen());
                        break;
                    case "WEST":
                        beweegStorm(stormEvent.getNaam(), ((StormEventWest) stormEvent).getStappen());
                        break;
                    case "BRANDT":
                        if (PlayerController.getInstance().checkPlayerWater()){
                            (FunctieController.getInstance()).endLose();
                        } else {
                            Controller controller = Controller.getInstance();
                            controller.zonBrand();
                            (FunctieController.getInstance()).updateInfo();
                        }
                        break;
                    case "STERKER":
                        storm.stormWordtSterker();
                        break;
                    default:
                        //System.out.println("DIT HOORT NIET: StormController");
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

    /**
     * Deze functie is een door geef luik om de storm een bepaalde richting op te laten bewegen.
     *
     * @param naam De richting die de storm op beweegt.
     * @param stappen Het aantal stappen dat de storm beweegt.
     */
    private void beweegStorm(String naam, Stappen stappen){
        switch (naam){
            case "NOORD":
                tileController.moveTileZuid(stappen, storm.getX(), storm.getY());
                storm.beweegNoord(stappen);
                break;
            case "OOST":
                tileController.moveTileWest(stappen, storm.getX(), storm.getY());
                storm.beweegOost(stappen);
                break;
            case "ZUID":
                tileController.moveTileNoord(stappen, storm.getX(), storm.getY());
                storm.beweegZuid(stappen);
                break;
            case "WEST":
                tileController.moveTileOost(stappen, storm.getX(), storm.getY());
                storm.beweegWest(stappen);
                break;
            default:
                //System.out.println("DIT HOORT NIET: StormController");
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
     * @param stormEvents Een Map waarin alle stormEvents zitten.
     * @author ryan
     */
    private void makeRandomStormEventsFB(Map<String, Object> stormEvents){
        ArrayList<StormEvent> events = new ArrayList<>();
        for (int i = 0; i <= aantalStormEvents; i++){
            if (stormEvents.get(Integer.toString(i)).equals("BRANDT")){
                events.add(factory.createStormEvent("BRANDT"));
            } else if (stormEvents.get(Integer.toString(i)).equals("STERKER")){
                events.add(factory.createStormEvent("STERKER"));
            } else if (stormEvents.get(Integer.toString(i)).equals("BEWEGINGNOORDONE")){
                events.add(factory.createStormEventBeweging("NOORD", Stappen.ONE));
            } else if (stormEvents.get(Integer.toString(i)).equals("BEWEGINGNOORDTWO")){
                events.add(factory.createStormEventBeweging("NOORD", Stappen.TWO));
            } else if (stormEvents.get(Integer.toString(i)).equals("BEWEGINGNOORDTHREE")){
                events.add(factory.createStormEventBeweging("NOORD", Stappen.THREE));
            } else if (stormEvents.get(Integer.toString(i)).equals("BEWEGINGOOSTONE")){
                events.add(factory.createStormEventBeweging("OOST", Stappen.ONE));
            } else if (stormEvents.get(Integer.toString(i)).equals("BEWEGINGOOSTTWO")){
                events.add(factory.createStormEventBeweging("OOST", Stappen.TWO));
            } else if (stormEvents.get(Integer.toString(i)).equals("BEWEGINGOOSTTHREE")){
                events.add(factory.createStormEventBeweging("OOST", Stappen.THREE));
            } else if (stormEvents.get(Integer.toString(i)).equals("BEWEGINGZUIDONE")){
                events.add(factory.createStormEventBeweging("ZUID", Stappen.ONE));
            } else if (stormEvents.get(Integer.toString(i)).equals("BEWEGINGZUIDTWO")){
                events.add(factory.createStormEventBeweging("ZUID", Stappen.TWO));
            } else if (stormEvents.get(Integer.toString(i)).equals("BEWEGINGZUIDTHREE")){
                events.add(factory.createStormEventBeweging("ZUID", Stappen.THREE));
            } else if (stormEvents.get(Integer.toString(i)).equals("BEWEGINGWESTONE")){
                events.add(factory.createStormEventBeweging("WEST", Stappen.ONE));
            } else if (stormEvents.get(Integer.toString(i)).equals("BEWEGINGWESTTWO")){
                events.add(factory.createStormEventBeweging("WEST", Stappen.TWO));
            } else if (stormEvents.get(Integer.toString(i)).equals("BEWEGINGWESTTHREE")){
                events.add(factory.createStormEventBeweging("WEST", Stappen.THREE));
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
