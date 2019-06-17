package observers;

public interface Acties_Observable {
        public void register(Acties_Observer observer);
        public void notifyAllObservers();
        public String[] getActies();

}
