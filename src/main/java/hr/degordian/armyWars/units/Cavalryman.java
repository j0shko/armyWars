package hr.degordian.armyWars.units;

/**
 * Mounted {@link Unit} which uses melee weapons like swords and warhammers.
 * Strong against {@link Swordsman}.
 * 
 * @author Josip Tomić
 */
public class Cavalryman extends MeleeUnit {

	/**
	 * Creates cavalryman with given strength.
	 * 
	 * @param strength strength of the cavalryman.
	 */
	public Cavalryman(int strength) {
		super(strength);
	}
	
	@Override
	public boolean isStrongAgainst(Unit otherUnit) {
		return otherUnit instanceof Swordsman;
	}

	@Override
	public String toString() {
		return "Cavalryman ("+ getStrength() + ")";
	}
}
