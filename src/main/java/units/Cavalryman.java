package units;

public class Cavalryman extends Unit {

	public Cavalryman(int strength) {
		super(strength);
	}
	
	@Override
	public boolean isWeakAgainst(Unit otherUnit) {
		return otherUnit instanceof Spearman;
	}

	@Override
	public boolean isStrongAgainst(Unit otherUnit) {
		return otherUnit instanceof Swordsman;
	}

}
