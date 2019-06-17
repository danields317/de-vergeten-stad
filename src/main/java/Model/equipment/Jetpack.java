package Model.equipment;

public class Jetpack extends Equipment {

	/////////////////////////////////////// Constructor ///////////////////////////////////////

	public Jetpack() {
	
		super( "Jetpack","Verplaats naar een niet-geblokkeerde tegel naar keuze. ��n andere speler op de tegel waarop je staat mag meevliegen.", "/placeholder.png");
		this.equipmentKaart = EquipmentKaarten.JETPACK;
	}

}
