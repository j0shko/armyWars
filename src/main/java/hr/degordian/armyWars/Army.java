package hr.degordian.armyWars;

import units.Unit;

public class Army {

	private int unitCount;
	
	
	private Unit general;
	
	public Army(Unit general) {
		this.general = general;
	}
	
	public Unit getGeneral() {
		return general;
	}
	
	public int getUnitCount() {
		return unitCount;
	}
}
