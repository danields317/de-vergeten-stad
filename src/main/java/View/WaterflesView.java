package View;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class WaterflesView {

    private Image[] waterfles3;
    private Image[] waterfles4;
    private Image[] waterfles5;
    private Image placeholder;

    private ImageView imageView;
    private int maxwater;

    public WaterflesView( int maxwater ) {

        this.maxwater = maxwater;

        placeholder = new Image("/placeholder.png");
        imageView = new ImageView(placeholder);

    }

    public ImageView loadWaterfles() {

        waterfles3 = new Image[3];
        waterfles4 = new Image[4];
        waterfles5 = new Image[5];

        for (int water = 0; water < 3; water++) {
            waterfles3[water] = new Image("/veldfles/Fles "+water+"_3.png");
        }

        for (int water = 0; water < 4; water++) {
            waterfles4[water] = new Image("/veldfles/Fles "+water+"_4.png");
        }

        for (int water = 0; water < 5; water++) {
            waterfles5[water] = new Image("/veldfles/Fles "+water+"_5.png");
        }

        return imageView;

    }

    public void updateWaterFles(int water) {

        switch (maxwater) {
            case 3:
                imageView.setImage(waterfles3[water]);
                break;
            case 4:
                imageView.setImage(waterfles4[water]);
                break;
            case 5:
                imageView.setImage(waterfles5[water]);
                break;
            default:
                imageView.setImage(placeholder);
                break;
        }

    }

}
