package Model.storm;

public class StormEventBeweging extends StormEvent{

    public enum Richtingen{NOORD,OOST,ZUID,WEST}
    public Richtingen richting;

    public Stappen stappen;

    public StormEventBeweging(Namen naam, Richtingen richting, Stappen stappen) {
        super(naam);
        this.richting = richting;
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
