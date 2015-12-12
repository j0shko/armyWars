package hr.degordian.armyWars.units;

public abstract class MeleeUnit implements Unit {

	public static final float STRONGER_COEF = 1.4f;
	
	public static final int MAX_STRENGTH = 100;
	public static final int MIN_STRENGTH = 1;
	
	private int strength;
	
	public MeleeUnit(int strength) {
		this.strength = strength;
	}
	
	public int getStrength() {
		return strength;
	}
	
	public abstract boolean isStrongAgainst(Unit otherUnit);

	@Override
	public boolean isRanged() {
		return false;
	}
}
