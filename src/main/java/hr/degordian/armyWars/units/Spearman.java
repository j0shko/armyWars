package hr.degordian.armyWars.units;

public class Spearman extends MeleeUnit {

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
