package Model.storm;

public class StormEventSterker implements StormEvent {

    String naam = "STERKER";

    public StormEventSterker(){
    }

    @Override
    public String getNaam() {
        return naam;
    }
}
