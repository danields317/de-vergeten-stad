package Model.Bord;

import observers.Acties_Observable;
import observers.Acties_Observer;
import observers.PlayerObservable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bram de Jong
 * @version 1.0
 */
public class Acties implements Acties_Observable {
    private List<Acties_Observer> observers = new ArrayList<Acties_Observer>();
    String[] acties = new String[4];

    /**
     * Deze functie maakt van onze actiesOver een String array.
     * Het kijkt naar hoeveel acties we hebben en geeft de correcte
     * path mee.
     * Deze array wordt in de view omgezet naar iets visueels
     * @param sb
     */
    public void maakFotoArray(PlayerObservable sb){
        /*
        De tijdschakelaar kan spelers over de 4 acties heen tillen,
        daarom hebben wij de if nodig.
         */
        if (sb.getActiesOver() > 4) {
            for (int i = (6 - sb.getActiesOver()); i < 4; i++) {
                acties[i] = "/actie_dubbel.png";
            }
            for (int i = 0; i < (8 - sb.getActiesOver()); i++){
                acties[i] = "/actie_vol.png";
            }
        } else if (sb.getActiesOver() <= 4 && sb.getActiesOver() >= 0) {
            for (int i = 0; i < (4 - sb.getActiesOver()); i++) {
                acties[i] = "/actie_leeg.png";
            }
            for (int i = (4 - sb.getActiesOver()); i < 4; i++) {
                acties[i] = "/actie_vol.png";
            }
        }
                notifyAllObservers();
    }

    @Override
    public void register(Acties_Observer observer) {
        observers.add(observer);
    }


    @Override
    public void notifyAllObservers() {
        for (Acties_Observer s : observers) {
            s.update(this);
        }
    }

    public String[] getActies() {
        return acties;
    }
}
