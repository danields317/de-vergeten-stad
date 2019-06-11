package observers;

public interface WaterObservable {
    public void register(WaterObserver observer);
    public void notifyAllObservers();
    public String getImgWater();

}
