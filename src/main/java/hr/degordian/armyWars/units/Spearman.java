package hr.degordian.armyWars.units;

/**
 * Melee {@link Unit} which travels on foot and uses long spear.
 * It's strong against {@link Cavalryman}.
 * 
 * @author Josip Tomić
 */
public class Spearman extends MeleeUnit {

	/**
	 * Creates spearman with given strength
	 * 
	 * @param strength strength of the spearman
	 */
	public Spearman(int strength) {
		super(strength);
	}
	
	@Override
	public boolean isStrongAgainst(Unit otherUnit) {
		return otherUnit instanceof Cavalryman;
	}

	@Override
	public String toString() {
		return "Spearman ("+ getStrength() + ")";
	}
}
