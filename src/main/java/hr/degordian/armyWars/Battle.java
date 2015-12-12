package hr.degordian.armyWars;

import hr.degordian.armyWars.terrain.NormalTerrain;
import hr.degordian.armyWars.terrain.Terrain;
import hr.degordian.armyWars.units.Archer;
import hr.degordian.armyWars.units.MeleeUnit;
import hr.degordian.armyWars.units.RangedUnit;
import hr.degordian.armyWars.units.Unit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Battle {

	private Army winner;
	private Army loser;
	
	private Army army1;
	private Army army2;
	
	private Terrain terrain = new NormalTerrain();
	
	public Battle(Army army1, Army army2) {
		this.army1 = army1;
		this.army2 = army2;
	}
	
	public void setTerrain(Terrain terrain) {
		this.terrain = terrain;
	}
	
	public Army fight() {
		System.out.println("Terrain: " + terrain);
		
		// ranged fight
		System.out.println("Archers shoot. \n--------------------------------");
		List<RangedUnit> ranged1 = getAllRangedUnits(army1.getAllUnits());
		List<RangedUnit> ranged2 = getAllRangedUnits(army2.getAllUnits());
		
		List<Unit> units1 = new ArrayList<>(army1.getAllUnits());
		List<Unit> units2 = new ArrayList<>(army2.getAllUnits());

		rangedUnitsShoot(ranged1, units2, 1);
		rangedUnitsShoot(ranged2, units1, 2);
		
		// melee fight
		System.out.println("\nMelee units fight. \n--------------------------------");
		List<MeleeUnit> meleeUnits1 = getAllMeleeUnits(units1);
		List<MeleeUnit> meleeUnits2 = getAllMeleeUnits(units2);
		
		meleeUnitsFight(meleeUnits1, meleeUnits2);
		
		winner = meleeUnits1.isEmpty() ? army2 : army1;
		loser = winner == army1 ? army2 : army1;
		return winner;
	}
	
	public Army getWinner() {
		return winner;
	}
	
	public Army getLoser() {
		return loser;
	}
	
	private float getGeneralCoeficient() {
		return army1.getGeneral().getStrength() / army2.getGeneral().getStrength();
	}
	
	private void rangedUnitsShoot(List<RangedUnit> rangedUnits, List<Unit> targets, int army) {
		Random ran = new Random();
		for (RangedUnit rangedUnit : rangedUnits) {
			float terrainModificator = terrain.getModificationForUnit(rangedUnit);
			if (rangedUnit instanceof Archer) {
				
			}
			int target = ran.nextInt(targets.size());
			Unit targetUnit = targets.get(target);
			if (ran.nextInt(RangedUnit.MAX_ACCURACY) < rangedUnit.getAccuracy() * getGeneralCoeficient() * terrainModificator) {
				targets.remove(target);
				printDeath(rangedUnit, targetUnit, army);
			}
		}
	}
	
	private List<RangedUnit> getAllRangedUnits(List<Unit> units) {
		List<RangedUnit> rangedUnits = new ArrayList<>();
		for (Unit unit : units) {
			if (unit.isRanged()) {
				rangedUnits.add((RangedUnit) unit);
			}
		}
		
		return rangedUnits;
	}
	
	private void meleeUnitsFight(List<MeleeUnit> meleeUnits1, List<MeleeUnit> meleeUnits2) {
		do {			
			Collections.shuffle(meleeUnits1);
			Collections.shuffle(meleeUnits2);
			
			int smaller = meleeUnits1.size();
			if (meleeUnits2.size() < smaller) {
				smaller = meleeUnits2.size();
			}
			
			List<MeleeUnit> unitsToRemove1 = new ArrayList<>();
			List<MeleeUnit> unitsToRemove2 = new ArrayList<>();
			for (int i = 0; i < smaller; i++) {
				MeleeUnit unit1 = meleeUnits1.get(i);
				MeleeUnit unit2 = meleeUnits2.get(i);
				
				float strCoef;
				if (unit1.isStrongAgainst(unit2)) {
					strCoef = MeleeUnit.STRONGER_COEF;
				} else if (unit2.isStrongAgainst(unit1)) {
					strCoef = 1 / MeleeUnit.STRONGER_COEF;
				} else {
					strCoef = 1;
				}
				
				float terrainModificator1 = terrain.getModificationForUnit(unit1);
				float terrainModificator2 = terrain.getModificationForUnit(unit2);
				int str1 = (int) (unit1.getStrength()*strCoef*getGeneralCoeficient() * terrainModificator1);
				int str2 = (int) (unit2.getStrength() * terrainModificator2);
				
				if (str1 > str2) {
					unitsToRemove2.add(unit2);
					printDeath(unit1, unit2, 1);
				} else if (str1 < str2) {
					unitsToRemove1.add(unit1);
					printDeath(unit2, unit1, 2);
				} else {
					Random ran = new Random();
					if (ran.nextInt(100) < 50) {
						unitsToRemove2.add(unit2);
						printDeath(unit1, unit2, 1);
					} else {
						unitsToRemove1.add(unit1);
						printDeath(unit2, unit1, 2);
					}
				}
			}
			
			meleeUnits1.removeAll(unitsToRemove1);
			meleeUnits2.removeAll(unitsToRemove2);
		} while (!meleeUnits1.isEmpty() && !meleeUnits2.isEmpty());
	}
	
	private List<MeleeUnit> getAllMeleeUnits(List<Unit> units) {
		List<MeleeUnit> meleeUnits = new ArrayList<>();
		for (Unit unit : units) {
			if (!unit.isRanged()) {
				meleeUnits.add((MeleeUnit) unit);
			}
		}
		return meleeUnits;
	}
	
	private static void printDeath (Unit killer, Unit target, int army) {
		System.out.println(killer + " from army "+ army + " has killed " + target +" from army "+ (army == 1? "2" : "1"));
	}
}
