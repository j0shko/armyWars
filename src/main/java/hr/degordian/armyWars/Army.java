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
		StringBuffer returnString = new StringBuffer();
		returnString.append("General:").append(general).append('\n');
		final int numForRow = 5;
		int currentNum = 0;
		for (Unit unit : allUnits) {
			returnString.append(unit);
			if (currentNum < numForRow) {
				currentNum++;
				returnString.append(", ");
			} else {
				currentNum = 0;
				returnString.append('\n');
			}
		}
		returnString.delete(returnString.length()-2, returnString.length());
		
		return returnString.toString();
	}
}
