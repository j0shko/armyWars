package hr.degordian.armyWars;

import hr.degordian.armyWars.units.MeleeUnit;
import hr.degordian.armyWars.units.Unit;

import java.util.ArrayList;
import java.util.List;

public class Army {

	private MeleeUnit general;
	
	private List<Unit> allUnits = new ArrayList<>();
	
	public Army(MeleeUnit general) {
		this.general = general;
	}
	
	public MeleeUnit getGeneral() {
		return general;
	}
	
	public int getUnitCount() {
		return allUnits.size();
	}
	
	public void addUnit(Unit unit) {
		allUnits.add(unit);
	}
	
	public List<Unit> getAllUnits() {
		return allUnits;
	}
	
	@Override
	public String toString() {
		String returnString = "General: " + general + "\n";
		for (Unit unit : allUnits) {
			returnString += unit + "\n";
		}
		return returnString;
	}
}
