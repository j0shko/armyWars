package hr.degordian.armyWars.units;

public class Archer extends RangedUnit {

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
