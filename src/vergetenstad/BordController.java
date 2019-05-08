package vergetenstad;

import javafx.scene.image.Image;
import vergetenstad.player.Player;

public class BordController {

	private Bord bord;
	private BordView view;
	
	/////////////////////////////////////// Constructor ///////////////////////////////////////
	
	public BordController() {
		
		bord = new Bord();
		view = new BordView( this );
		
		bord.setBackgroundImage(new Image("resources/placeholder.png"));
		
	}
	
	/////////////////////////////////////// Methods ///////////////////////////////////////
	
	public void addPlayer(Player player) {
		
		bord.getPlayers()[bord.getPlayers().length] = player;
		
	}

	/////////////////////////////////////// Getters & Setters ///////////////////////////////////////
	
	public Bord getBord() {
		return bord;
	}

	public void setBord(Bord bord) {
		this.bord = bord;
	}

	public BordView getView() {
		return view;
	}

	public void setView(BordView view) {
		this.view = view;
	}
	
}
