package units;

public class Swordsman extends Unit {

	public Swordsman(int strength) {
		super(strength);
	}
	
	@Override
	public boolean isWeakAgainst(Unit otherUnit) {
		return otherUnit instanceof Cavalryman;
	}

	@Override
	public boolean isStrongAgainst(Unit otherUnit) {
		return otherUnit instanceof Archer;
	}

}
