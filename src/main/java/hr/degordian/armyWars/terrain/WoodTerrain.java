package hr.degordian.armyWars.terrain;

import hr.degordian.armyWars.units.Archer;
import hr.degordian.armyWars.units.Cavalryman;
import hr.degordian.armyWars.units.Spearman;
import hr.degordian.armyWars.units.Swordsman;

/**
 * Wood terrain which gives minor advantage to on foot melee units ({@link Swordsman}, {@link Spearman}), 
 * and minor disadvantage to {@link Archer}s and mounted melee units ({@link Cavalryman}).
 *  
 * @author Josip Tomić
 */
public class WoodTerrain extends NormalTerrain {

	/**
	 * Creates new wood terrain.
	 */
	public WoodTerrain() {
		super();
		name = "Wood";
		modificators.put("Archer", 0.8f);
		modificators.put("Cavalryman", 0.8f);
		modificators.put("Swordsman", 1.2f);
		modificators.put("Spearman", 1.2f);
	}
}
