package Model.storm;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author ryanr
 * @author daniel
 */
public class Storm {

    private int x;
    private int y;
    private int sterkte;
    private ArrayList<StormEvent> stormEvents = new ArrayList<>();
    private ArrayList<StormEvent> randomStormEvents = new ArrayList<>();
    private Random random = new Random();
    private int stapelCounter = 0;


    public Storm(){
        x = 2;
        y = 2;
        sterkte = 2;
        makeEvents();
        randomizeEvents(stormEvents);
    }

    public void makeEvents(){
        for (int i = 0; i < 31; i++){
            if (i < 4) {
                stormEvents.add(new StormEvent(StormEvent.Namen.BRANDT));
            }else if (i < 7){
                stormEvents.add(new StormEvent(StormEvent.Namen.STERKER));
            }else if (i < 10){
                stormEvents.add(new StormEventBeweging(StormEvent.Namen.BEWEGING, StormEventBeweging.Rightingen.NOORD, StormEventBeweging.Stappen.ONE));
            }else if (i < 13){
                stormEvents.add(new StormEventBeweging(StormEvent.Namen.BEWEGING, StormEventBeweging.Rightingen.OOST, StormEventBeweging.Stappen.ONE));
            }else if (i < 16){
                stormEvents.add(new StormEventBeweging(StormEvent.Namen.BEWEGING, StormEventBeweging.Rightingen.ZUID, StormEventBeweging.Stappen.ONE));
            }else if (i < 19){
                stormEvents.add(new StormEventBeweging(StormEvent.Namen.BEWEGING, StormEventBeweging.Rightingen.WEST, StormEventBeweging.Stappen.ONE));
            }else if (i < 21){
                stormEvents.add(new StormEventBeweging(StormEvent.Namen.BEWEGING, StormEventBeweging.Rightingen.NOORD, StormEventBeweging.Stappen.TWO));
            }else if (i < 23){
                stormEvents.add(new StormEventBeweging(StormEvent.Namen.BEWEGING, StormEventBeweging.Rightingen.OOST, StormEventBeweging.Stappen.TWO));
            }else if (i < 25){
                stormEvents.add(new StormEventBeweging(StormEvent.Namen.BEWEGING, StormEventBeweging.Rightingen.ZUID, StormEventBeweging.Stappen.TWO));
            }else if (i < 27){
                stormEvents.add(new StormEventBeweging(StormEvent.Namen.BEWEGING, StormEventBeweging.Rightingen.WEST, StormEventBeweging.Stappen.TWO));
            }else if (i < 28){
                stormEvents.add(new StormEventBeweging(StormEvent.Namen.BEWEGING, StormEventBeweging.Rightingen.NOORD, StormEventBeweging.Stappen.THREE));
            }else if (i < 29){
                stormEvents.add(new StormEventBeweging(StormEvent.Namen.BEWEGING, StormEventBeweging.Rightingen.OOST, StormEventBeweging.Stappen.THREE));
            }else if (i < 30){
                stormEvents.add(new StormEventBeweging(StormEvent.Namen.BEWEGING, StormEventBeweging.Rightingen.ZUID, StormEventBeweging.Stappen.THREE));
            }else if (i < 31){
                stormEvents.add(new StormEventBeweging(StormEvent.Namen.BEWEGING, StormEventBeweging.Rightingen.WEST, StormEventBeweging.Stappen.THREE));
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


    public void voerStormEventsUit(){
        for (int i = 0; i < sterkte; i++){
            switch (randomStormEvents.get(stapelCounter).getNaam()){
                case BEWEGING:
                    break;
                case BRANDT:
                    break;
                case STERKER:
                    break;
                default:
                    System.out.println("DIT HOORT NIET");
            }
        }
    }

    public void beweegStorm(StormEventBeweging.Rightingen righting, StormEventBeweging.Stappen stappen){
        switch (righting){
            case NOORD:
                y -= stappen.getNumber();
                break;
            case OOST:
                x += stappen.getNumber();
                break;
            case ZUID:
                y += stappen.getNumber();
                break;
            case WEST:
                x -= stappen.getNumber();
                break;
            default:
                System.out.println("DIT HOORT NIET");
        }
    }




    public ArrayList<StormEvent> getStormEvents() {
        return randomStormEvents;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static void main(String[] args){
        Storm storm = new Storm();
        for (StormEvent s : storm.getStormEvents()){
            System.out.println(s.getNaam());
        }

    }

}
