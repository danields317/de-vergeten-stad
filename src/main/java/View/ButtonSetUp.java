package View;

import Model.player.Users;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;

public class ButtonSetUp {

    private Users users = new Users();

    public ButtonSetUp(Group root) {


        Button button1 = new Button("1");
        double smallButton = 40.0;
            button1.setMinWidth(smallButton);
            root.getChildren().

        add(button1);
            button1.setOnAction(new EventHandler<ActionEvent>()

        {
            @Override
            public void handle (ActionEvent event){
            Users.archeoloogController.giveWater(Users.meteooroloogController.getPlayer(), 1);
            System.out.println("Meteooroloog Water: " + Users.meteooroloogController.getPlayer().getWater());
            System.out.println("Acrcheoloog Water: " + Users.archeoloogController.getPlayer().getWater());
        }
        });
    }
}
