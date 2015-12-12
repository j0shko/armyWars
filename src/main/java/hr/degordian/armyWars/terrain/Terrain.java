package hr.degordian.armyWars.terrain;

import hr.degordian.armyWars.units.Unit;

public interface Terrain {
	
	float getArcherModificator();
	float getCavalrymanModificator();
	float getSpearmanModificator();
	float getSwordsmanModificator();
	float getModificationForUnit(Unit unit);
}
