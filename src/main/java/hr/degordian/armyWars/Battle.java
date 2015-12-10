package hr.degordian.armyWars;

public class Battle {

	private Army winner;
	private Army loser;
	
	private Army army1;
	private Army army2;
	
	public Battle(Army army1, Army army2) {
		this.army1 = army1;
		this.army2 = army2;
	}
	
	public Army fight() {
		// TODO crucial part
		return null;
	}
	
	public Army getWinner() {
		return winner;
	}
	
	public Army getLoser() {
		return loser;
	}
}
