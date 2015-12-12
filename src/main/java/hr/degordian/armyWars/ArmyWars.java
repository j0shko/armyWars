package hr.degordian.armyWars;

import java.util.Random;

import hr.degordian.armyWars.terrain.MountainTerrain;
import hr.degordian.armyWars.terrain.PlainsTerrain;
import hr.degordian.armyWars.terrain.WoodTerrain;
import hr.degordian.armyWars.units.Archer;
import hr.degordian.armyWars.units.Cavalryman;
import hr.degordian.armyWars.units.MeleeUnit;
import hr.degordian.armyWars.units.Spearman;
import hr.degordian.armyWars.units.Swordsman;

public class ArmyWars {

	public static void main(String[] args) {
		MeleeUnit general1 = new Cavalryman(50);
		Army army1 = new Army(general1);
		
		final int maxRandomAccuracy = 65;
		final int minRandomAccuracy = 15;
		final int maxRandomStrength = 85;
		final int minRandomStrength = 35;
		
		for (int i = 0; i < 9; i++) {
			army1.addUnit(new Archer(randomInRange(minRandomAccuracy, maxRandomAccuracy)));
		}
		
		for (int i = 0; i < 4; i++) {
			army1.addUnit(new Cavalryman(randomInRange(minRandomStrength, maxRandomStrength)));
			army1.addUnit(new Spearman(randomInRange(minRandomStrength, maxRandomStrength)));
			army1.addUnit(new Swordsman(randomInRange(minRandomStrength, maxRandomStrength)));
		}
		
		MeleeUnit general2 = new Cavalryman(50);
		Army army2 = new Army(general2);
		
		for (int i = 0; i < 9; i++) {
			army2.addUnit(new Archer(randomInRange(minRandomAccuracy, maxRandomAccuracy)));
		}
		
		for (int i = 0; i < 4; i++) {
			army2.addUnit(new Cavalryman(randomInRange(minRandomStrength, maxRandomStrength)));
			army2.addUnit(new Spearman(randomInRange(minRandomStrength, maxRandomStrength)));
			army2.addUnit(new Swordsman(randomInRange(minRandomStrength, maxRandomStrength)));
		}
		
		System.out.println("Army 1:");
		System.out.println(army1);
		System.out.println("--------------------------------");
		System.out.println("Army 2:");
		System.out.println(army2);
		
		Battle battle = new Battle(army1, army2);
		battle.setTerrain(new MountainTerrain());
		String winner = battle.fight() == army1 ? "Army 1" : "Army 2";
		
		System.out.println();
		System.out.println(winner + " has won!");
	}
	
	public static int randomInRange(int min, int max) {
		Random ran = new Random();
		return ran.nextInt(max-min+1) + min;
	}
}
