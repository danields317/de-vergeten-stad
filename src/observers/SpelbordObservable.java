package observers;

public interface SpelbordObservable {
	public void register(SpelbordObserver observer);
	public void notifyAllObservers();
	public String getScore();
	public String getGivenUsername();
	public String getGivenPassword();
	public boolean isLoginCorrect();
}
