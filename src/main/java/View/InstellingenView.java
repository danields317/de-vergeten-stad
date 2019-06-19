package View;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class InstellingenView {

    public ImageView InstellingenView(){
        ImageView imageView = new ImageView(new Image("/Spelregels/instellingen.png"));
        imageView.setOnMouseClicked(e -> {
            new SpelregelView();
        });
        imageView.setLayoutX(1500);
        imageView.setLayoutY(830);
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
        return imageView;
    }
}
