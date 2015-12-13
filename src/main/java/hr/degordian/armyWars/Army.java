package hr.degordian.armyWars;

import hr.degordian.armyWars.units.Cavalryman;
import hr.degordian.armyWars.units.Unit;

import java.util.ArrayList;
import java.util.List;

/**
 * Defines an army with a general (which is type of {@link Cavalryman})
 * with list of units that army has.
 * 
 * @author Josip Tomić
 */
public class Army {

	/**
	 * General.
	 */
	private Cavalryman general;
	
	/**
	 * List of all army units.
	 */
	private List<Unit> allUnits = new ArrayList<>();
	
	/**
	 * Creates empty army with given general.
	 * 
	 * @param general general for the army
	 */
	public Army(Cavalryman general) {
		this.general = general;
	}
	
	/**
	 * Returns this army's general
	 * 
	 * @return general
	 */
	public Cavalryman getGeneral() {
		return general;
	}
	
	/**
	 * Returns number of units of this army (not including general).
	 * 
	 * @return number of units of this army
	 */
	public int getUnitCount() {
		return allUnits.size();
	}
	
	/**
	 * Adds unit to this army.
	 * 
	 * @param unit unit to be added
	 */
	public void addUnit(Unit unit) {
		allUnits.add(unit);
	}
	
	/**
	 * Returns all units of this army.
	 * 
	 * @return list of units of this army
	 */
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
