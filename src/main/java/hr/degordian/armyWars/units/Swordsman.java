package hr.degordian.armyWars.units;

public class Swordsman extends MeleeUnit {

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
