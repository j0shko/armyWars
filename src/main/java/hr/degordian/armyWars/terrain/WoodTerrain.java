package hr.degordian.armyWars.terrain;

public class WoodTerrain extends NormalTerrain {

	public WoodTerrain() {
		super();
		name = "Wood";
		modificators.put("Archer", 0.8f);
		modificators.put("Cavalryman", 0.8f);
		modificators.put("Swordsman", 1.2f);
		modificators.put("Spearman", 1.2f);
	}
}
