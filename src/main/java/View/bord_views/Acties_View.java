package View.bord_views;

import Controller.Bord_Controllers.Acties_Controller;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import observers.Acties_Observable;
import observers.Acties_Observer;

public class Acties_View implements Acties_Observer {
    Acties_Controller acties_controller;
    static GridPane view = new GridPane();



    public Acties_View(){
        acties_controller = Acties_Controller.getInstance();
        acties_controller.registerObserver(this);
        acties_controller.register();
    }

    public void maakView(Acties_Observable sb){
        GridPane gp = new GridPane();
        for(int i = 0; i < sb.getActies().length; i++){
            ImageView actiesImage = new ImageView(new Image(sb.getActies()[i]));
            actiesImage.setFitWidth(33);
            actiesImage.setFitHeight(46);
            gp.add(actiesImage, 0, i);
            gp.setLayoutX(1175);
            gp.setLayoutY(682);
        }
        view = gp;
    }

    @Override
    public void update(Acties_Observable sb){
        maakView(sb);
    }

    public static GridPane getView() {
        return view;
    }
}
