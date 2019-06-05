package Model.storm;

public class StormEventBeweging extends StormEvent{

    public enum Rightingen{NOORD,OOST,ZUID,WEST};
    private Rightingen righting;

    public enum Stappen{one,two,three};
    private Stappen stappen;

    public StormEventBeweging(Namen naam, Rightingen righting, Stappen stappen) {
        super(naam);
        this.righting = righting;
        this.stappen = stappen;
    }


}
