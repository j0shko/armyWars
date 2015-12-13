package hr.degordian.armyWars;

import hr.degordian.armyWars.terrain.NormalTerrain;
import hr.degordian.armyWars.terrain.Terrain;
import hr.degordian.armyWars.units.MeleeUnit;
import hr.degordian.armyWars.units.RangedUnit;
import hr.degordian.armyWars.units.Unit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Battle of two armies. Defined by two armies and a terrain on which they fight on.
 * Battle consist of two stages, stage 1 where ranged units of both armies shoot in the same time, 
 * and stage 2 where melee units fight to the death
 * 
 * @author Josip Tomić
 */
public class Battle {

	/** Winner of the battle */
	private Army winner;
	/** Loser of the battle */
	private Army loser;
	
	/** First army */
	private Army army1;
	/** Second army */
	private Army army2;
	
	/** Terrain of battle */
	private Terrain terrain = new NormalTerrain();
	
	/**
	 * Creates a battle of two armies with default terrain.
	 * 
	 * @param army1 first army
	 * @param army2 second army
	 */
	public Battle(Army army1, Army army2) {
		this.army1 = army1;
		this.army2 = army2;
	}
	
	/**
	 * Sets battle terrain to given one.
	 * 
	 * @param terrain terrain to be set
	 */
	public void setTerrain(Terrain terrain) {
		this.terrain = terrain;
	}
	
	/**
	 * Fight of two armies which results in winner and loser. 
	 * Fight consists of two stages. In first stage ranged units of both
	 * armies shoot in the same time. In second stage melee units fight to death.
	 * 
	 * @return winner of the fight
	 */
	public Army fight() {
		System.out.println("Terrain: " + terrain);
		
		// ranged fight
		System.out.println("\nArchers shoot. \n--------------------------------");
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
	
	/**
	 * Returns winner of the battle. If {@link #fight()} is not executed before this, 
	 * returned army will be null.
	 * 
	 * @return winner of the battle, or null if battle was not fought
	 */
	public Army getWinner() {
		return winner;
	}
	
	/**
	 * Returns loser of the battle. If {@link #fight()} is not executed before this, 
	 * returned army will be null.
	 * 
	 * @return loser of the battle, or null if battle was not fought
	 */
	public Army getLoser() {
		return loser;
	}
	
	/**
	 * Returns ratio of first army's general and second army's general.
	 * 
	 * @return ratio of two armies's general
	 */
	private float getGeneralCoeficient() {
		return army1.getGeneral().getStrength() / army2.getGeneral().getStrength();
	}
	
	/**
	 * First stage of battle where ranged units shoot.
	 * 
	 * @param rangedUnits ranged units that shoot
	 * @param targets all units that can be targeted by shooting units
	 * @param army army identificator, for printing purposes
	 */
	private void rangedUnitsShoot(List<RangedUnit> rangedUnits, List<Unit> targets, int army) {
		Random ran = new Random();
		for (RangedUnit rangedUnit : rangedUnits) {
			float terrainModificator = terrain.getModificatorForUnit(rangedUnit);
			int target = ran.nextInt(targets.size());
			Unit targetUnit = targets.get(target);
			if (ran.nextInt(RangedUnit.MAX_ACCURACY) < rangedUnit.getAccuracy() * getGeneralCoeficient() * terrainModificator) {
				targets.remove(target);
				printDeath(rangedUnit, targetUnit, army);
			}
		}
	}
	
	/**
	 * Returns all ranged units from given list of units.
	 * 
	 * @param units list of units
	 * @returns all ranged units from given list of units
	 */
	private List<RangedUnit> getAllRangedUnits(List<Unit> units) {
		List<RangedUnit> rangedUnits = new ArrayList<>();
		for (Unit unit : units) {
			if (unit.isRanged()) {
				rangedUnits.add((RangedUnit) unit);
			}
		}
		
		return rangedUnits;
	}
	
	/**
	 * Second part of the battle where melee units fight to death.
	 * 
	 * @param meleeUnits1 list of melee units of first army
	 * @param meleeUnits2 list of melee units of second army
	 */
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
				
				float terrainModificator1 = terrain.getModificatorForUnit(unit1);
				float terrainModificator2 = terrain.getModificatorForUnit(unit2);
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
	
	/**
	 * Returns all melee units from given list of units
	 * 
	 * @param units list of units
	 * @return all melee untis from given list of units
	 */
	private List<MeleeUnit> getAllMeleeUnits(List<Unit> units) {
		List<MeleeUnit> meleeUnits = new ArrayList<>();
		for (Unit unit : units) {
			if (!unit.isRanged()) {
				meleeUnits.add((MeleeUnit) unit);
			}
		}
		return meleeUnits;
	}
	
	/**
	 * Prints death of <code>target</code> from <code>killer</code>. 
	 * 
	 * @param killer killer 
	 * @param target target that is killed
	 * @param army army identificator of the killer
	 */
	private static void printDeath (Unit killer, Unit target, int army) {
		System.out.println(killer + " from army "+ army + " has killed " + target +" from army "+ (army == 1? "2" : "1"));
	}
}
