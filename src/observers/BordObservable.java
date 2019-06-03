package observers;

public interface BordObservable {
    public void register(BordObserver observer);
    public void notifyAllObservers();
}
