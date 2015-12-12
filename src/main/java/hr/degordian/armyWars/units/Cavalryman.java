package hr.degordian.armyWars.units;

public class Cavalryman extends MeleeUnit {

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
