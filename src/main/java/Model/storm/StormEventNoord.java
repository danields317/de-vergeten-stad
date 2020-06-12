package Model.storm;

public class StormEventNoord implements StormEvent {

    private String naam = "NOORD";
    private Stappen stappen;

    public StormEventNoord(Stappen stappen) {
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
