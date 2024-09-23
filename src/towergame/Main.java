package towergame;

public class Main {

	public static void main(String[] args) {
		
		Player player = new Player(100);
		Game game = new Game(5, 10, player);
		game.startGame();
	}
}
