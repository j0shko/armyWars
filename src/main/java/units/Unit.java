package units;

public abstract class Unit {

	private final int strength;
	
	public Unit(int strength) {
		this.strength = strength;
	}
	
	public int getStrength() {
		return strength;
	}
	
	public abstract boolean isWeakAgainst(Unit otherUnit);
	
	public abstract boolean isStrongAgainst(Unit otherUnit);
}

