package hr.degordian.armyWars.units;

/**
 * Melee {@link Unit} which walks on foot and uses sword to fight.
 * It's strong against {@link Spearman}.
 * 
 * @author Josip Tomić
 */
public class Swordsman extends MeleeUnit {

	/**
	 * Creates swordsman with given strength.
	 * 
	 * @param strength strength of the swordsman.
	 */
	public Swordsman(int strength) {
		super(strength);
	}
	
	@Override
	public boolean isStrongAgainst(Unit otherUnit) {
		return otherUnit instanceof Spearman;
	}

	@Override
	public String toString() {
		return "Swordsman ("+ getStrength() + ")";
	}
}
