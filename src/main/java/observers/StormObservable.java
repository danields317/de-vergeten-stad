package observers;

import javafx.scene.image.Image;

public interface StormObservable {
    public int getX();
    public int getY();
    public Image getImage();
    public int getSubSterkte();
    public void register(StormObserver observer);
    public void notifyAllObservers();
}
