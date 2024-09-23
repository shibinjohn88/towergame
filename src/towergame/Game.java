package towergame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {
	
	private int waves;
	private int pathLength;
	private List<Enemy> enemies = new ArrayList<Enemy>();
	private Player player;
	
	public Game(int waves, int pathLength, Player player) {
		super();
		this.waves = waves;
		this.pathLength = pathLength;
		this.player = player;
	}
	
	public void enemyWave(int wave) {
		//create enemies based on wave
		for (int i=0; i<wave; i++) {
			int life = 1 + wave * 2;
			Random random = new Random();
			int [] speedOptions = {1, 2};
			int speed = speedOptions[random.nextInt(speedOptions.length)];
			Enemy enemy = new Enemy(life, speed);
			this.enemies.add(enemy);
		}
	}
	
	public void playTurn(int wave) {
		System.out.println("Playing a turn...");
		for (Enemy enemy: this.enemies) {
			enemy.move();
			System.out.println("Enemy moved to position " + enemy.getPosition() + "with balance life " + enemy.getLife());
		}
		for (Player.TowerLocation towerLocation: this.player.getTowers()) {
			for (Iterator<Enemy> iterator = enemies.iterator(); iterator.hasNext();) {
				Enemy enemy = iterator.next();
				if (enemy.getPosition() >= towerLocation.getPosition()) {
					System.out.println("Tower attacking enemy at position " + enemy.getPosition());
					if (towerLocation.getTower().attack(enemy, towerLocation.getPosition())) {
						System.out.println("Enemy at position " + enemy.getPosition() + " is defeated.");
						iterator.remove();
					}
				}
			}
		}
		if (enemies.isEmpty()) {
			player.setResources(wave * 50);
			System.out.println("Player gained resources of "+ wave * 50 + " by defeating wave " + wave);
		}
	}
	
	public boolean checkEnemyAtEnd() {
		for (Enemy enemy: this.enemies) {
			if (enemy.getPosition() >= pathLength) {
				System.out.println("Enemy reached end of path");
				return true;
			}
			else {
				continue;
			}
		}
		return false;
	}
	
	public void startGame() {
		
		Scanner scanner = new Scanner(System.in);
		for (int wave = 1; wave <= this.waves; wave++) {
			System.out.println("Enemy wave " + wave + " starting");
			enemyWave(wave);
			
			boolean validChoice = false;
			while (!validChoice) {
				//Players can build towers before each wave
				System.out.println("Do you want to build a tower? (yes/no): ");
		        String towerChoice = scanner.nextLine();
		        if (towerChoice.equalsIgnoreCase("yes")) {  
		        	validChoice = true;
		            System.out.println("Building tower...");
		            Tower tower = new Tower(5, 3, 50);
		            System.out.println("Enter position to build tower (0 to " + pathLength);
		            int position = scanner.nextInt();
		            scanner.nextLine(); //consume the left over newline character
		            player.buildTower(tower, position);
		        } 
		        else if (towerChoice.equalsIgnoreCase("no")){
		        	System.out.println("No tower will be built");
		        	validChoice = true;
		        }
		        else {
		        	System.out.println("Invalid choice");
		        	continue;
		        }
			}
			
			while (enemies != null && !enemies.isEmpty()) {
				playTurn(wave);
				if (checkEnemyAtEnd()) {
					System.out.println("Game Over");
					return;
				}
			}
		}
		scanner.close();  // Close the scanner to prevent resource leaks
		System.out.println("Congratulations! you have defended all the waves!");
		
	}

}
