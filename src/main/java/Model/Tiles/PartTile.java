package Model.Tiles;

public class PartTile extends Tile {

	public enum Richtingen{OPZIJ, OMHOOG}
	private Richtingen richting;

	public enum Soorten{OBELISK, PROPELOR, MOTOR, KOMPAS}
	private Soorten soort;

	public PartTile(Richtingen richting, Soorten soort) {
		super("/Tiles/Undiscovered.png", "/Tiles/Motor_Y.png", Varianten.PART);
		this.richting = richting;
		this.soort = soort;
		String juistePNG = vindJuistePNG(richting, soort);
		super.setDiscoveredImage(juistePNG);
	}

	public String vindJuistePNG(Richtingen r, Soorten s){
		String pad = "/placeholder.png";
		String as = null;

		if(r == Richtingen.OPZIJ){
			as = "X";
		}
		else as = "Y";

		switch (s){
			case MOTOR:
				pad = "/Tiles/Motor_" + as + ".png";
				break;
			case KOMPAS:
				pad = "/Tiles/Navigation_" + as + ".png";
				break;
			case OBELISK:
				pad = "/Tiles/Crystal_" + as + ".png";
				break;
			case PROPELOR:
				pad = "/Tiles/Propeller_" + as + ".png";
				break;
		}
		return pad;
	}

	public void geefHint(){
		System.out.println("Hoi ik ben een onderdeelTile");
	}


    public Richtingen getRichting() {
        return richting;
    }

    public Soorten getSoort(){
	    return soort;
    }
}
