package observers;

public interface Player_Menu_Observable {
    public void register(Player_Menu_Observer observer);
    public void notifyAllObservers();
}
