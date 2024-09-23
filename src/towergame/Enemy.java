package towergame;

public class Enemy {
	//enemy has attributes life, position in path, speed
	private int life;
	private int speed;
	private int position = 0;
	
	public Enemy(int life, int speed) {
		super();
		this.life = life;
		this.speed = speed;
	}
	
	//enemy moves position according to speed of enemy
	public void move() {
		this.position += this.speed;
	}
	
	public boolean lifeDamage(int damage) {
		this.life -= damage;
		return this.life <= 0;
	}

	public int getPosition() {
		return position;
	}

	public int getLife() {
		return life;
	}
		
}

