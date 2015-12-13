package hr.degordian.armyWars.units;

import hr.degordian.armyWars.Army;

/**
 * Ranged unit of the {@link Army}. 
 * It's specific trait is accuracy which defines with which percent 
 * this unit will successfully shoot and kill other unit.
 * 
 * @author Josip Tomić
 */
public abstract class RangedUnit implements Unit {

	/** Maximum possible accuracy */
	public static final int MAX_ACCURACY = 100;
	/** Minimum possible accuracy */
	public static final int MIN_ACCURACY = 1;

	/** Accuracy of this unit */
	private int accuracy;
	
	/** 
	 * Creates ranged unit with given accuracy.
	 * 
	 * @param accuracy accuracy of this ranged unit
	 */
	public RangedUnit(int accuracy) {
		this.accuracy = accuracy;
	}
	
	/**
	 * Returns accuracy of this ranged unit
	 * 
	 * @return accuracy
	 */
	public int getAccuracy() {
		return accuracy;
	}
	
	@Override
	public boolean isRanged() {
		return true;
	}
	
	@Override
	public abstract boolean isStrongAgainst(Unit otherUnit);
}
