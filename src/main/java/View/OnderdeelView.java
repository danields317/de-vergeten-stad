package View;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class OnderdeelView {

    private ImageView propellerView;
    private ImageView beacon;
    private ImageView motor;
    private ImageView zonnewijzer;

    public ImageView loadPropeller(){
        Image propeller = new Image("/Onderdelen/PropBoven.png");
        propellerView = new ImageView(propeller);
        propellerView.setFitWidth(150);
        propellerView.setFitHeight(150);
        propellerView.setX(1395);
        propellerView.setY(60);

        return propellerView;
    }


    /*private String getPropellorImagePath(){
        try {
            return getClass().getResource("placeholder.png").toURI().getPath();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }*/


}
