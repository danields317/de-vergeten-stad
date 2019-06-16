package observers;

public interface OnderdeelObservable {

    public void register(OnderdeelObserver observer);
    public void notifyAllObservers();
}
