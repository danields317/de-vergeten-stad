package Model.storm;

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
