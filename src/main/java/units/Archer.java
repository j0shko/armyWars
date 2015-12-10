package units;

public class Archer extends Unit {

	public Archer(int strength) {
		super(strength);
	}
	
	@Override
	public boolean isWeakAgainst(Unit otherUnit) {
		return otherUnit instanceof Swordsman;
	}

	@Override
	public boolean isStrongAgainst(Unit otherUnit) {
		return otherUnit instanceof Spearman;
	}

}
