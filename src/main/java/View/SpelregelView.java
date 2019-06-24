package View;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class SpelregelView {

    Stage stage;
    private int aantalSpelregels = 8;
    private int huidigePaginaIndex = 0;
    Image[] images;

    public SpelregelView(){
        stage = new Stage();
        images = new Image[aantalSpelregels];
        for (int i = 1; i <= aantalSpelregels; i++){
            Image image = new Image("/Spelregels/spelregels" + i + ".png");
            images[i-1] = image;
        }
        StackPane stackPane = new StackPane();
        ImageView imageView = new ImageView();
        imageView.setFitWidth(900);
        imageView.setFitHeight(900);

        imageView.setImage(images[huidigePaginaIndex]);

        ImageView buttonLeft = new ImageView(new Image("/Spelregels/buttonLeft.png"));
        ImageView buttonRight = new ImageView(new Image("/Spelregels/buttonRight.png"));

        Label pagina = new Label(Integer.toString(huidigePaginaIndex+1) + "/" + Integer.toString(aantalSpelregels));

        buttonLeft.setOnMouseClicked(e -> {
            if (huidigePaginaIndex == 0){
                huidigePaginaIndex = aantalSpelregels-1;
            } else {
                huidigePaginaIndex--;
            }
            pagina.setText(Integer.toString(huidigePaginaIndex+1) + "/" + Integer.toString(aantalSpelregels));
            imageView.setImage(images[huidigePaginaIndex]);
        });
        buttonRight.setOnMouseClicked(e -> {
            if (huidigePaginaIndex == aantalSpelregels-1){
                huidigePaginaIndex = 0;
            } else {
                huidigePaginaIndex++;
            }
            pagina.setText(Integer.toString(huidigePaginaIndex+1) + "/" + Integer.toString(aantalSpelregels));
            imageView.setImage(images[huidigePaginaIndex]);
        });

        stackPane.getChildren().add(imageView);
        stackPane.getChildren().add(buttonLeft);
        stackPane.getChildren().add(buttonRight);
        stackPane.getChildren().add(pagina);

        stackPane.setAlignment(buttonLeft, Pos.BOTTOM_LEFT);
        stackPane.setAlignment(buttonRight, Pos.BOTTOM_RIGHT);
        stackPane.setAlignment(pagina, Pos.BOTTOM_CENTER);

        Scene scene = new Scene(stackPane);
        scene.getStylesheets().add("/css/game.css");

        pagina.getStyleClass().add("pagina");
        buttonLeft.getStyleClass().add("spelregelButton");
        buttonRight.getStyleClass().add("spelregelButton");

        stage.setScene(scene);
        stage.setTitle("Spelregels");
        stage.getIcons().add(new Image("/placeholder.png"));
        stage.show();
    }
}
