package towergame;

public class Tower {
	private int attack;
	private int range;
	private int cost;
	
	public Tower(int attack, int range, int cost) {
		super();
		this.attack = attack;
		this.range = range;
		this.cost = cost;
	}
	
	public boolean attack(Enemy enemy, int towerPosition) {
		if (enemy.getPosition() <= towerPosition + range) {
			// attack enemy
			boolean enemyAlive = enemy.lifeDamage(this.attack);
			return enemyAlive;
		}
		else {
			return false;
		}
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}
	
}
