package observers;

public interface LoginObservable {
    public void register(LoginObserver observer);
    public void notifyAllObservers();
    public String getScore();
    public String getGivenUsername();
    public String getGivenPassword();
    public String getError();
    public boolean isLoginCorrect();
}
