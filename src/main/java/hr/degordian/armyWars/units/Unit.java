package hr.degordian.armyWars.units;

public interface Unit {
	boolean isRanged();
	
	public abstract boolean isStrongAgainst(Unit otherUnit);
}

