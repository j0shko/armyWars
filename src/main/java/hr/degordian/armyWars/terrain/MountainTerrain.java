package hr.degordian.armyWars.terrain;

import hr.degordian.armyWars.units.Archer;
import hr.degordian.armyWars.units.Cavalryman;

/**
 * Mountain terrain which gives minor advantage to {@link Archer}, but
 * big disadvantage to {@link Cavalryman}
 * 
 * @author Josip Tomić
 */
public class MountainTerrain extends NormalTerrain {

	/**
	 * Creates new mountain terrain.
	 */
	public MountainTerrain() {
		super();
		name = "Mountain";
		modificators.put("Archer", 1.2f);
		modificators.put("Cavalryman", 0.5f);
	}
}
