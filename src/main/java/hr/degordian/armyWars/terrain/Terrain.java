package hr.degordian.armyWars.terrain;

import hr.degordian.armyWars.Battle;
import hr.degordian.armyWars.units.Archer;
import hr.degordian.armyWars.units.Cavalryman;
import hr.degordian.armyWars.units.Spearman;
import hr.degordian.armyWars.units.Swordsman;
import hr.degordian.armyWars.units.Unit;

/**
 * Terrain which gives advantages and disadvantages to specified {@link Unit}s
 * in {@link Battle}.
 * 
 * @author Josip Tomić
 */
public interface Terrain {
	/**
	 * Returns modificator for {@link Archer}'s main trait.
	 * 
	 * @return modificator for {@link Archer}'s main trait.
	 */
	float getArcherModificator();
	
	/**
	 * Returns modificator for {@link Cavalryman}'s main trait.
	 * 
	 * @return modificator for {@link Cavalryman}'s main trait.
	 */
	float getCavalrymanModificator();
	
	/**
	 * Returns modificator for {@link Spearman}'s main trait.
	 * 
	 * @return modificator for {@link Spearman}'s main trait.
	 */
	float getSpearmanModificator();
	
	/**
	 * Returns modificator for {@link Swordsman}'s main trait.
	 * 
	 * @return modificator for {@link Swordsman}'s main trait.
	 */
	float getSwordsmanModificator();
	
	/**
	 * Returns modificator for given unit based on it's type.
	 * 
	 * @param unit unit
	 * @return modificator for given unit
	 */
	float getModificatorForUnit(Unit unit);
}
