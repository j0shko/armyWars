package hr.degordian.armyWars.units;

import hr.degordian.armyWars.Army;

/**
 * Melee unit (which fights in close combat with melee weapons) of the {@link Army}.
 * 
 * @author Josip Tomić
 */
public abstract class MeleeUnit implements Unit {

	/**
	 * Modificator for advantage of some units over others
	 */
	public static final float STRONGER_COEF = 1.4f;
	
	/** Maximum possible strength */
	public static final int MAX_STRENGTH = 100;
	/** Minimum possible strength */
	public static final int MIN_STRENGTH = 1;
	
	/** Strength of this melee unit */
	private int strength;
	
	/**
	 * Creates melee unit with given strength
	 * 
	 * @param strength strength of the unit
	 */
	public MeleeUnit(int strength) {
		this.strength = strength;
	}
	
	/**
	 * Returns strength of this melee unit.
	 * 
	 * @return strength
	 */
	public int getStrength() {
		return strength;
	}
	
	@Override
	public abstract boolean isStrongAgainst(Unit otherUnit);

	@Override
	public boolean isRanged() {
		return false;
	}
}
