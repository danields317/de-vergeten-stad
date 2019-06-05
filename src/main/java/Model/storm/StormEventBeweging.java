package Model.storm;

public class StormEventBeweging extends StormEvent{

    public enum Rightingen{NOORD,OOST,ZUID,WEST}
    private Rightingen righting;

    private Stappen stappen;

    public StormEventBeweging(Namen naam, Rightingen righting, Stappen stappen) {
        super(naam);
        this.righting = righting;
        this.stappen = stappen;
    }

    public enum Stappen {
        ONE(1),
        TWO(2),
        THREE(3);

        private int number;

        Stappen(int number) {
            this.number = number;
        }

        public int getNumber() {
            return number;
        }
    }

}
