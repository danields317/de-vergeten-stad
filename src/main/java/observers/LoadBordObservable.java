package observers;

public interface LoadBordObservable {
    public void register(LoadBordObserver observer);
    public void notifyAllObservers();
}
