package hr.degordian.armyWars.terrain;

import hr.degordian.armyWars.units.Cavalryman;

/**
 * Big grassy plains terrain which gives minor advantage to {@link Cavalryman}.
 * 
 * @author Josip Tomić
 */
public class PlainsTerrain extends NormalTerrain {

	/**
	 * Creates new plains terrain.
	 */
	public PlainsTerrain() {
		super();
		modificators.put("Cavalryman", 1.2f);
		name = "Plains";
	}
	
}
