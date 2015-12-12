package hr.degordian.armyWars;

import java.util.Random;

import hr.degordian.armyWars.units.Archer;
import hr.degordian.armyWars.units.Cavalryman;
import hr.degordian.armyWars.units.MeleeUnit;
import hr.degordian.armyWars.units.Spearman;
import hr.degordian.armyWars.units.Swordsman;

public class ArmyWars {

	public static void main(String[] args) {
		MeleeUnit general1 = new Cavalryman(50);
		Army army1 = new Army(general1);
		
		Random ran = new Random();
		for (int i = 0; i < 9; i++) {
			army1.addUnit(new Archer(ran.nextInt(35) + 50));
		}
		
		for (int i = 0; i < 4; i++) {
			army1.addUnit(new Cavalryman(ran.nextInt(25) + 50));
			army1.addUnit(new Spearman(ran.nextInt(25) + 50));
			army1.addUnit(new Swordsman(ran.nextInt(25) + 50));
		}
		
		MeleeUnit general2 = new Cavalryman(50);
		Army army2 = new Army(general2);
		
		for (int i = 0; i < 9; i++) {
			army2.addUnit(new Archer(ran.nextInt(35) + 50));
		}
		
		for (int i = 0; i < 4; i++) {
			army2.addUnit(new Cavalryman(ran.nextInt(25) + 50));
			army2.addUnit(new Spearman(ran.nextInt(25) + 50));
			army2.addUnit(new Swordsman(ran.nextInt(25) + 50));
		}
		
		System.out.println("Army 1:");
		System.out.println(army1);
		System.out.println("--------------------------------");
		System.out.println("Army 2:");
		System.out.println(army2);
		
		Battle battle = new Battle(army1, army2);
		System.out.println(battle.fight() == army1 ? "Army 1" : "Army 2");
	}
}
