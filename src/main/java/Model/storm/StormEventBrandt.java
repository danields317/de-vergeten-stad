package Model.storm;

public class StormEventBrandt implements StormEvent {

    private String naam = "BRANDT";

    public StormEventBrandt(){
    }

    @Override
    public String getNaam() {
        return naam;
    }
}
