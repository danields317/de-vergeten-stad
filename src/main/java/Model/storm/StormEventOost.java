package Model.storm;

public class StormEventOost implements StormEvent {

    private String naam = "OOST";
    private Stappen stappen;

    public StormEventOost(Stappen stappen) {
        this.stappen = stappen;
    }

    public Stappen getStappen() {
        return stappen;
    }

    @Override
    public String getNaam() {
        return naam;
    }
}
