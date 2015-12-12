package hr.degordian.armyWars;

import hr.degordian.armyWars.units.Cavalryman;
import hr.degordian.armyWars.units.Unit;

import java.util.ArrayList;
import java.util.List;

public class Army {

	private Cavalryman general;
	
	private List<Unit> allUnits = new ArrayList<>();
	
	public Army(Cavalryman general) {
		this.general = general;
	}
	
	public Cavalryman getGeneral() {
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
