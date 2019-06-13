package observers;

public interface StormMeterObservable {
    public void register(StormMeterObserver observer);
    public void notifyAllObservers();
    public int getHoogte();
}
