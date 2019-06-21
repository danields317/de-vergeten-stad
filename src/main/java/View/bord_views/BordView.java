package View.bord_views;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BordView {

    public ImageView maakAchtergrond(double windowWidth, double windowHeight) {
        Image achtergrondImage = new Image("/gamescreenempty.png");
        ImageView achtergrond = new ImageView(achtergrondImage);
        achtergrond.setX(0);
        achtergrond.setY(0);
        achtergrond.setFitWidth(windowWidth);
        achtergrond.setFitHeight(windowHeight);

        return achtergrond;
    }

}
