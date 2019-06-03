package Model.Stormkaart;

import javafx.scene.image.Image;

public class Stormkaart {

    private Image image;

    public Stormkaart(String imagePath){
        this.image = new Image(imagePath);
    }

}
