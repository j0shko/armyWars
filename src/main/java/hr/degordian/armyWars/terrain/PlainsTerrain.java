package hr.degordian.armyWars.terrain;

public class PlainsTerrain extends NormalTerrain {

	public PlainsTerrain() {
		super();
		modificators.put("Cavalryman", 1.2f);
		name = "Plains";
	}
	
}
