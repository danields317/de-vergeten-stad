package View.bord_views;

import Controller.Bord_Controllers.Water_Controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import observers.WaterObservable;
import observers.WaterObserver;


/**
 * <p>
 *     Deze klas zorgt dat de waterfles onder in het speelbord scherm word geladen
 * </p>
 * @author Jason
 * @version 1.0
 * @since 24-0-2019
 */
public class WaterflesView implements WaterObserver {

    Water_Controller waterController;
    static GridPane view;


    /**
     * <p>
     *     Sets up the WaterflesView
     *     And makes the WaterController
     * </p>
     * @since 1.0
     */
    public WaterflesView(){
        waterController = waterController.getInstance();
        waterController.registerObserver();
        waterController.registerObserver((WaterObserver) this);

    }

    /**
     * <p>
     *     Sets up the new view
     * </p>
     * @param sb
     * @since 1.0
     */
    private void createUpdatedGridPane(WaterObservable sb){
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(400, 200);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);

        ImageView waterImage = new ImageView(new Image(sb.getImgWater()));

        waterImage.prefWidth(240.0);
        waterImage.prefHeight(220.0);
        waterImage.setFitWidth(220.0);
        waterImage.setFitHeight(240.0);

        gridPane.add(waterImage, 0,0);
        gridPane.setLayoutX(-62);
        gridPane.setLayoutY(649);
        view = gridPane;
    }


    /**
     *
     * @return Gridpane
     * @since 1.0
     */
    public GridPane createInitialGridPane(){



        GridPane gridPane = new GridPane();
        gridPane.setMinSize(400, 200);
        gridPane.setPadding(new Insets(10, 10, 10, 10));


        view = gridPane;

        return gridPane;
    }

    /**
     * <p>
     *     Provides the gridpane for the view
     * </p>
     * @return Gridpane
     * @since 1.0
     */
    public static GridPane getView() {
        return view;
    }

    /**
     *
     * @param sb
     * @since 1.0
     */
    @Override
    public void update(WaterObservable sb) {
        createUpdatedGridPane(sb);
    }


}
