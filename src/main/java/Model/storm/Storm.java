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
    private int subSterkte;
    private ArrayList<StormEvent> stormEvents = new ArrayList<>();
    private ArrayList<StormEvent> randomStormEvents = new ArrayList<>();
    private Random random = new Random();
    private int stapelCounter = 0;


    public Storm(){
        x = 2;
        y = 2;
        sterkte = 2;
        subSterkte = 1;
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
        int tmpSterkte = sterkte;
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
                    stormWordtSterker();
                    break;
                default:
                    System.out.println("DIT HOORT NIET");
            }
            stapelCounter++;
        }
    }

    public void beweegStorm(StormEventBeweging.Richtingen richting, StormEventBeweging.Stappen stappen){
        switch (richting){
            case NOORD:
                if (y-stappen.getNumber() >= 0){
                    y -= stappen.getNumber();
                }else y = 0;
                break;
            case OOST:
                if (x+stappen.getNumber() <= 4){
                    x += stappen.getNumber();
                }else x = 4;
                break;
            case ZUID:
                if (y+stappen.getNumber() <= 4){
                    y += stappen.getNumber();
                }else y = 4;
                break;
            case WEST:
                if (x-stappen.getNumber() >= 0){
                    x -= stappen.getNumber();
                }else x = 0;
                break;
            default:
                System.out.println("DIT HOORT NIET");
        }
    }


    public void stormWordtSterker(){
        subSterkte++;
        if (subSterkte < 7){
            sterkte = 3;
        }else if (subSterkte < 11){
            sterkte = 4;
        }else if (subSterkte < 14){
            sterkte = 5;
        }else if (subSterkte < 16){
            sterkte = 6;
        }else return;                        //dood()
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

    public int getSubSterkte(){
        return subSterkte;
    }

    public int getSterkte(){
        return sterkte;
    }

    public static void main(String[] args){
        Storm storm = new Storm();
        for (StormEvent s : storm.getStormEvents()){
            System.out.println(s.naam);
        }

    }

}
