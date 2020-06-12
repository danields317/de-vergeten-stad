package Model.storm;

public class StormEventZuid implements StormEvent {

    private String naam = "ZUID";
    private Stappen stappen;

    public StormEventZuid(Stappen stappen) {
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
