package Model.equipment;

public class Waterreserve extends Equipment{

	/////////////////////////////////////// Constructor ///////////////////////////////////////

	public Waterreserve() {
	
		super("Waterreserve", "Alle spelers die met jou op dezelfde tegel staan ontvangen 2 water.", EquipmentKaarten.WATERRESERVE);
		this.equipmentKaart = EquipmentKaarten.WATERRESERVE;
	}

}
