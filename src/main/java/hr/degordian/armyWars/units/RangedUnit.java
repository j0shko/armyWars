package hr.degordian.armyWars.units;

public abstract class RangedUnit implements Unit {

	public static final int MAX_ACCURACY = 100;
	
	private int accuracy;
	
	public RangedUnit(int accuracy) {
		this.accuracy = accuracy;
	}
	
	public int getAccuracy() {
		return accuracy;
	}
	
	@Override
	public boolean isRanged() {
		return true;
	}
	
	public abstract boolean isStrongAgainst(Unit otherUnit);
}
