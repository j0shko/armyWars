package hr.degordian.armyWars.units;

import hr.degordian.armyWars.Army;

/**
 * Single unit for the {@link Army}.
 * 
 * @author Josip Tomić
 */
public interface Unit {
	/**
	 * Returns true if unit is ranged.
	 * 
	 * @return true if unit is ranged
	 */
	boolean isRanged();
	
	/**
	 * Returns true if unit is strong against <code>otherUnit</code>
	 * 
	 * @param otherUnit other {@link Unit}
	 * @return true if unit is strong against other unit
	 */
	public abstract boolean isStrongAgainst(Unit otherUnit);
}

