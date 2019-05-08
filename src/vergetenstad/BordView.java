package vergetenstad;

import javafx.scene.canvas.GraphicsContext;

public class BordView {

	private Bord bord;
	private BordController controller;
	
	/////////////////////////////////////// Constructor ///////////////////////////////////////
	
	public BordView(BordController bordController) {
		
		controller = bordController;
		bord = controller.getBord();
		
	}
	
	/////////////////////////////////////// Methods ///////////////////////////////////////
	
	public void render(GraphicsContext gc) {
		
		gc.drawImage(bord.getBackgroundImage(), 0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
		
	}
	
}
