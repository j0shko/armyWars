package hr.degordian.armyWars.terrain;

import java.util.HashMap;
import java.util.Map;

import hr.degordian.armyWars.units.Archer;
import hr.degordian.armyWars.units.Cavalryman;
import hr.degordian.armyWars.units.Spearman;
import hr.degordian.armyWars.units.Swordsman;
import hr.degordian.armyWars.units.Unit;

/**
 * Normal terrain which does not give any advantage to any unit type.
 * 
 * @author Josip Tomić
 */
public class NormalTerrain implements Terrain {

	/** Name of the terrain */
	protected String name = "Normal";
	/** Map of unit type modificators */
	protected Map<String, Float> modificators = new HashMap<>();

	/** 
	 * Creates new normal terrain.
	 */
	public NormalTerrain() {
		modificators.put("Archer", 1f);
		modificators.put("Cavalryman", 1f);
		modificators.put("Spearman", 1f);
		modificators.put("Swordsman", 1f);
	}
	
	@Override
	public float getArcherModificator() {
		return modificators.get("Archer");
	}

	@Override
	public float getCavalrymanModificator() {
		return modificators.get("Cavalryman");
	}

	@Override
	public float getSpearmanModificator() {
		return modificators.get("Spearman");
	}

	@Override
	public float getSwordsmanModificator() {
		return modificators.get("Swordsman");
	}
	
	@Override
	public float getModificatorForUnit(Unit unit) {
		if (unit instanceof Archer) {
			return getArcherModificator();
		}
		if (unit instanceof Cavalryman) {
			return getCavalrymanModificator();
		}
		if (unit instanceof Spearman) {
			return getSpearmanModificator();
		}
		if (unit instanceof Swordsman) {
			return getSwordsmanModificator();
		}
		return 1;
	}
	
	@Override
	public String toString() {
		StringBuilder output = new StringBuilder();
		output.append(name);
		output.append(" terrain ");
		boolean first = true;
		for (String unitType : modificators.keySet()) {
			float modificator = modificators.get(unitType);
			if (modificator != 1) {
				if (first) {
					first = false;
					output.append("(");
				}
				output.append(unitType);
				output.append(" ");
				output.append(getPercentFromFloat(modificator));
				output.append(", ");
			}
		}
		if (!first) {
			output.delete(output.length()-2, output.length());
			output.append(")");
		}
		
		return output.toString();
	}
	
	/**
	 * Returns percent string from given float with 2 decimal places.
	 * 
	 * @param num number
	 * @return percent string from given number
	 */
	private String getPercentFromFloat(float num) {
		num = num * 100;
		return String.format("%+.2f%%", num-100f);
	}
}