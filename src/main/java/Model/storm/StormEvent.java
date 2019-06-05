package Model.storm;

public class StormEvent {

    public enum Namen{BEWEGING, STERKER, BRANDT};
    private Namen naam;

    public StormEvent(Namen naam){
        this.naam = naam;
    }
}
