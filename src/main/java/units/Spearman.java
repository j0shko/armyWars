package units;

public class Spearman extends Unit {

	public Spearman(int strength) {
		super(strength);
	}
	
	@Override
	public boolean isWeakAgainst(Unit otherUnit) {
		return otherUnit instanceof Archer;
	}

	@Override
	public boolean isStrongAgainst(Unit otherUnit) {
		return otherUnit instanceof Cavalryman;
	}

}
