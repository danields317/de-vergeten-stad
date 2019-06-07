package observers;

public interface SpelerObservable {
    public void register(SpelerObserver observer);
    public void notifyAllObservers();
    public int getMaxWater();
    public int getWater();
}
