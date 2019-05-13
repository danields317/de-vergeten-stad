package Controller.Bord_Controllers;

import Model.player.Users;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class Button_Controller {

    private Users users = new Users();
    private String character;
    private Button button1 = new Button("Archeoloog");
    private Button button2 = new Button("Klimmer");
    private Button button3 = new Button("Meteooroloog");
    private Button button4 = new Button("Navigator");
    private Button button5 = new Button("Verkenner");
    private Button button6 = new Button("Waterdrager");

    public Button_Controller(Group root, String self) {

        GridPane gridPane= new GridPane();

        //Setting size for the pane
        gridPane.setMinSize(40, 40);

        //Setting the padding
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        //Setting the vertical and horizontal gaps between the columns
        gridPane.setVgap(5);
        gridPane.setHgap(5);




        switch (self){
            case "Archeoloog":
                setButtonsArcheoloog();
                break;
            case "Klimmer":
                setButtonsKlimmer();
                break;
            case "Meteooroloog":
                break;
            case "Navigator":
                break;
            case "Verkenner":
                break;
            case "Waterdrager":
                break;
        }
        double smallButton = 125.0;
        button1.setMinWidth(smallButton);
        button2.setMinWidth(smallButton);
        button3.setMinWidth(smallButton);
        button4.setMinWidth(smallButton);
        button5.setMinWidth(smallButton);
        button6.setMinWidth(smallButton);

        gridPane.add(button1, 0 ,0);
        gridPane.add(button2, 0, 1);
        gridPane.add(button3, 0 ,2);
        gridPane.add(button4, 0, 3);
        gridPane.add(button5, 0 ,4);
        gridPane.add(button6, 0, 5);
        root.getChildren().add(gridPane);



    }

    public void setButtonsArcheoloog(){
        button1.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle (ActionEvent event){
                System.out.println("You cant give yourself water.");
            }
        });
        button2.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle (ActionEvent event){
                users.getArcheoloogController().giveWater(users.getKlimmerController().getPlayer(), 1);
                System.out.println("Klimmer Water: " + users.getKlimmerController().getPlayer().getWater());
                System.out.println("Archeoloog Water: " + users.getArcheoloogController().getPlayer().getWater());
            }
        });
        button3.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle (ActionEvent event){
                users.getArcheoloogController().giveWater(users.getMeteooroloogController().getPlayer(), 1);
                System.out.println("Meteooroloog Water: " + users.getMeteooroloogController().getPlayer().getWater());
                System.out.println("Archeoloog Water: " + users.getArcheoloogController().getPlayer().getWater());
            }
        });
        button4.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle (ActionEvent event){
                users.getArcheoloogController().giveWater(users.getNavigatorController().getPlayer(), 1);
                System.out.println("Navigator Water: " + users.getNavigatorController().getPlayer().getWater());
                System.out.println("Archeoloog Water: " + users.getArcheoloogController().getPlayer().getWater());
            }
        });
        button5.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle (ActionEvent event){
                users.getArcheoloogController().giveWater(users.getVerkennerController().getPlayer(), 1);
                System.out.println("Verkenner Water: " + users.getVerkennerController().getPlayer().getWater());
                System.out.println("Acrcheoloog Water: " + users.getArcheoloogController().getPlayer().getWater());
            }
        });
        button6.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle (ActionEvent event){
                users.getArcheoloogController().giveWater(users.getWaterdragerController().getPlayer(), 1);
                System.out.println("Waterdrager Water: " + users.getWaterdragerController().getPlayer().getWater());
                System.out.println("Acrcheoloog Water: " + users.getArcheoloogController().getPlayer().getWater());
            }
        });
    }

    public void setButtonsKlimmer(){
        button1.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle (ActionEvent event){
                users.getKlimmerController().giveWater(users.getArcheoloogController().getPlayer(), 1);
                System.out.println("Archeoloog Water: " + users.getArcheoloogController().getPlayer().getWater());
                System.out.println("Klimmer Water: " + users.getKlimmerController().getPlayer().getWater());

            }
        });
        button2.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle (ActionEvent event){
                System.out.println("You cant give yourself water.");
            }
        });
        button3.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle (ActionEvent event){
                users.getKlimmerController().giveWater(users.getMeteooroloogController().getPlayer(), 1);
                System.out.println("Meteooroloog Water: " + users.getMeteooroloogController().getPlayer().getWater());
                System.out.println("Klimmer Water: " + users.getKlimmerController().getPlayer().getWater());
            }
        });
        button4.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle (ActionEvent event){
                users.getKlimmerController().giveWater(users.getNavigatorController().getPlayer(), 1);
                System.out.println("Navigator Water: " + users.getNavigatorController().getPlayer().getWater());
                System.out.println("Klimmer Water: " + users.getKlimmerController().getPlayer().getWater());
            }
        });
        button5.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle (ActionEvent event){
                users.getKlimmerController().giveWater(users.getVerkennerController().getPlayer(), 1);
                System.out.println("Verkenner Water: " + users.getVerkennerController().getPlayer().getWater());
                System.out.println("Klimmer Water: " + users.getKlimmerController().getPlayer().getWater());
            }
        });
        button6.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle (ActionEvent event){
                users.getKlimmerController().giveWater(users.getWaterdragerController().getPlayer(), 1);
                System.out.println("Waterdrager Water: " + users.getWaterdragerController().getPlayer().getWater());
                System.out.println("Klimmer Water: " + users.getKlimmerController().getPlayer().getWater());
            }
        });
    }





}