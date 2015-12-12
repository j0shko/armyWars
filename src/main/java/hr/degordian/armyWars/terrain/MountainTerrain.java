package hr.degordian.armyWars.terrain;

public class MountainTerrain extends NormalTerrain {

	public MountainTerrain() {
		super();
		name = "Mountain";
		modificators.put("Archer", 1.2f);
		modificators.put("Cavalryman", 0.5f);
	}
}
