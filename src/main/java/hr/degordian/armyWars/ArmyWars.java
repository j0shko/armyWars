package hr.degordian.armyWars;

import hr.degordian.armyWars.terrain.MountainTerrain;
import hr.degordian.armyWars.terrain.NormalTerrain;
import hr.degordian.armyWars.terrain.PlainsTerrain;
import hr.degordian.armyWars.terrain.Terrain;
import hr.degordian.armyWars.terrain.WoodTerrain;
import hr.degordian.armyWars.units.Archer;
import hr.degordian.armyWars.units.Cavalryman;
import hr.degordian.armyWars.units.MeleeUnit;
import hr.degordian.armyWars.units.RangedUnit;
import hr.degordian.armyWars.units.Spearman;
import hr.degordian.armyWars.units.Swordsman;
import hr.degordian.armyWars.units.Unit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ArmyWars {

	public static final int MAX_ARMY_SIZE = 50;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int size = getInput("army size", 1, MAX_ARMY_SIZE, br);
		
		System.out.println("\n================================");
		Army army1 = createArmy(1, size, br);
		System.out.println("\n================================");
		Army army2 = createArmy(2, size, br);
		
		System.out.println();
		System.out.println("Army 1:");
		System.out.println(army1);
		System.out.println("--------------------------------");
		System.out.println("Army 2:");
		System.out.println(army2);
		
		System.out.println("\n================================");
		Terrain terrain = chooseTerrain(br);
		
		System.out.println("\n================================");
		System.out.println("Let the battle begin!");
		br.readLine();
		
		Battle battle = new Battle(army1, army2);
		battle.setTerrain(terrain);
		String winner = battle.fight() == army1 ? "Army 1" : "Army 2";
		
		System.out.println();
		System.out.println(winner + " has won!");
	}
	
	//------------------ side methods ---------------------
	
	private static boolean checkIfValid(String intString, int minNum, int maxNum) {
		int num;
		try {
			num = Integer.parseInt(intString);
		} catch (NumberFormatException e) {
			return false;
		}
		return num >= minNum && num <= maxNum;
	}
	
	public static int randomInRange(int min, int max) {
		Random ran = new Random();
		return ran.nextInt(max-min+1) + min;
	}
	
	private static int getInput(String inputName, int min, int max, BufferedReader br) throws IOException {
		boolean validInput;
		int input = min;
		do {
			System.out.print("Enter " + inputName + ". ("+min +"-"+max+") > ");
			String inputString = br.readLine();
			validInput = checkIfValid(inputString, min, max);
			if (!validInput) {
				System.out.println("Invalid " + inputName + ".");
			} else {
				input = Integer.parseInt(inputString);
			}
		} while (!validInput);
		
		return input;
	}
	
	//------------------ army generation ---------------------
	
	private static Army createArmy(int armyNum, int size, BufferedReader br) throws IOException {
		System.out.println("Now follows settings for Army " + armyNum + ".");
		
		int generalStrength = getInput("general strength", 1, MeleeUnit.MAX_STRENGTH, br);
		Army army = new Army(new Cavalryman(generalStrength));
		
		System.out.println();
		System.out.println("Possible army generator types.");
		System.out.println("(1) Set every unit type and it's strenght/accuracy");
		System.out.println("(2) Set only count of every unit type");
		System.out.println("(3) Default army structure, equal ammount of every unit type with random strenght/accuracy");
		
		int generatorType = getInput("army generator type", 1, 3, br);
		
		switch(generatorType) {
		case 1:
			eachUnitSettingsCreation(army, size, br);
			break;
		case 2:
			unitTypeCountCreation(army, size, br);
			break;
		case 3:
			defaultCreation(army, size);
		}
		
		return army;
	}
	
	private static void eachUnitSettingsCreation(Army army, int size, BufferedReader br) throws IOException {
		int archerCount = 0;
		System.out.println("Possible types and it's codes:");
		System.out.println("(1) Archer");
		System.out.println("(2) Cavalryman");
		System.out.println("(3) Swordsman");
		System.out.println("(4) Spearman");
		System.out.println("There must be at least one melee unit.");
		for (int i = 0; i < size; i++) {
			System.out.print("Unit " + (i+1) + ": ");
			int typeCode;
			if (archerCount == size-1) {
				typeCode = getInput("unit type", 2, 4, br);
			} else {
				typeCode = getInput("unit type", 1, 4, br);
			}
			
			if (typeCode == 1) {
				int accuracy = getInput("accuracy", RangedUnit.MIN_ACCURACY, RangedUnit.MAX_ACCURACY, br);
				army.addUnit(new Archer(accuracy));
				archerCount++;
			} else {
				int strength = getInput("strength", MeleeUnit.MIN_STRENGTH, MeleeUnit.MAX_STRENGTH, br);
				Unit newUnit = null;
				switch (typeCode) {
				case 2: 
					newUnit = new Cavalryman(strength); 
					break;
				case 3:
					newUnit = new Swordsman(strength); 
					break;
				case 4: 
					newUnit = new Spearman(strength); 
					break;
				}
				army.addUnit(newUnit);
			}
		}
	}

	private static void defaultCreation(Army army, int size) {
		int archerCount = (int) Math.ceil(size/4);
		int meleeCount = (int) Math.floor(size/4);
		addUnits("Archer", army, archerCount);
		addUnits("Cavalryman", army, meleeCount);
		addUnits("Swordsman", army, meleeCount);
		addUnits("Spearman", army, meleeCount);
	}
	
	private static void unitTypeCountCreation(Army army, int size, BufferedReader br) throws IOException {
		System.out.println("Enter count for every unit type (Archer, Cavalryman, Swordsman, Spearman)."+
				"There must be at least one melee unit.");
		int armysize = size;
		int archerCount = getInput("archer count", 0, armysize-1, br);
		addUnits("Archer", army, archerCount);
		armysize -= archerCount;

		int cavalrymanCount = getInput("cavalryman count", 0, armysize, br);
		addUnits("Cavalryman", army, cavalrymanCount);
		armysize -= cavalrymanCount;

		if (armysize == 0) {
			return;
		}
		int swordsmanCount = getInput("swordsman count", 0, armysize, br);
		addUnits("Swordsman", army, swordsmanCount);
		armysize -= swordsmanCount;

		addUnits("Spearman", army, armysize);
	}
	
	private static void addUnits(String unitName, Army army, int count) {
		switch(unitName) {
		case "Archer" :
			for (int i = 0; i < count; i++) {
				army.addUnit(new Archer(randomInRange(Archer.MIN_ACCURACY, Archer.MAX_ACCURACY)));
			}
			break;
		case "Swordsman" :
			for (int i = 0; i < count; i++) {
				army.addUnit(new Swordsman(randomInRange(MeleeUnit.MIN_STRENGTH, MeleeUnit.MAX_STRENGTH)));
			}
			break;
		case "Cavalryman":
			for (int i = 0; i < count; i++) {
				army.addUnit(new Cavalryman(randomInRange(MeleeUnit.MIN_STRENGTH, MeleeUnit.MAX_STRENGTH)));
			}
			break;
		case "Spearman":
			for (int i = 0; i < count; i++) {
				army.addUnit(new Spearman(randomInRange(MeleeUnit.MIN_STRENGTH, MeleeUnit.MAX_STRENGTH)));
			}
		}
	}
	
	// ------------------ choosing  terrain ----------------
	
	private static Terrain chooseTerrain(BufferedReader br) throws IOException {
		List<Terrain> possibleTerrains = new ArrayList<>();
		possibleTerrains.add(new NormalTerrain());
		possibleTerrains.add(new MountainTerrain());
		possibleTerrains.add(new PlainsTerrain());
		possibleTerrains.add(new WoodTerrain());
		
		System.out.println("Please choose terrain.");
		
		for (int i = 0, end = possibleTerrains.size(); i < end; i++) {
			System.out.println("(" + (i+1) + ") " + possibleTerrains.get(i));
		}
		
		int terrainNum = getInput("terrain", 1, possibleTerrains.size(), br);
		
		return possibleTerrains.get(terrainNum - 1);
	}
}
