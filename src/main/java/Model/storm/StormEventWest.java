package Model.storm;

public class StormEventWest implements StormEvent {

    private String naam = "WEST";
    private Stappen stappen;

    public StormEventWest(Stappen stappen) {
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
