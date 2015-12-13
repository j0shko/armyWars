package hr.degordian.armyWars.units;

/**
 * Classic ranged {@link Unit} with bow and arrows.
 * 
 * @author Josip Tomić
 */
public class Archer extends RangedUnit {

	/**
	 * Creates archer with given accuracy.
	 * 
	 * @param accuracy accuracy of the archer
	 */
	public Archer(int accuracy) {
		super(accuracy);
	}
	
	@Override
	public boolean isStrongAgainst(Unit otherUnit) {
		return false;
	}
	
	@Override
	public String toString() {
		return "Archer ("+ getAccuracy() + ")";
	}
}
