package Model.Tiles;

public class PartTile extends Tile {

	public enum Richtingen{OPZIJ, OMHOOG}
	private Richtingen richting;

	public enum Soorten{OBELISK, PROPELOR, MOTOR, KOMPAS}
	private Soorten soort;

	public PartTile(Richtingen richting, Soorten soort) {
		super("/placeholder.png", "/placeholder.png", Varianten.PART);
		this.richting = richting;
		this.soort = soort;
	}

    public Richtingen getRichting() {
        return richting;
    }

    public Soorten getSoort(){
	    return soort;
    }
}
