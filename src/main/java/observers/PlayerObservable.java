package observers;

public interface PlayerObservable {
    public void register(PlayerObserver observer);
    public void notifyAllObservers();
    public int getMaxWater();
    public int getWater();
    public int getActiesOver();
}
