import java.util.Random;

public class Game {

	// Global Constant for dimensions
	private final static int DIM1 = 12;
	private final static int DIM2 = 12;

	// Return World with dashboard
	private boolean[][] initWorld() {

		boolean[][] newWorld = new boolean[DIM1][DIM2];
		Random randomBoolean = new Random();

		// Creates random boolean values in array[][]
		for (int x = 1; x < newWorld.length - 1; x++) {
			for (int y = 1; y < newWorld[x].length - 1; y++) {
				// Boolean random values
				newWorld[x][y] = randomBoolean.nextBoolean();
			}
		}
		return newWorld;
	}

	// Show updated world
	private void showWorld(boolean[][] world) {

		// For loop for Array
		for (boolean[] row : world) {
			for (boolean column : row) {
				// If in column is True
				if (column) {
					System.out.print("\u001B[42m  " + "\u001B[0m"+ "  ");
					// If false
				} else {
					System.out.print("\u001B[0m  ");
				}
			}
			System.out.println("\u001B[0m  \n");
		}
	}

	// Applies the 4 rules and give me next generation
	private boolean[][] appliesRules(boolean[][] world) {
		// Give me row and column from World
		for (int row = 1; row < world.length - 1; row++) {
			for (int column = 1; column < world[row].length - 1; column++) {
				// Call method
				int neighborLife = countNeighbor(world, column, row);
				// Logic
				if (neighborLife == 3 && !world[column][row]) {
					world[column][row] = true;
				}
				if ( neighborLife < 2 || neighborLife > 3) {
					world[column][row] = false;
				}
			}
		}

		return world;
	}


	// Returns number of neighbors of a cell
	private int countNeighbor(boolean[][] world, int x, int y) {
		int returnValue = 0;
		for (int i = x - 1; i <= x + 1; ++i)
			for (int j = y - 1; j <= y + 1; ++j)
				if (world[i][j])
					returnValue += 1;
		// Counting a neighbor too much?
		if (world[x][y])
			returnValue -= 1;
		return returnValue;
	}


	public static void main(String[] args) {

		Game game = new Game();

		// Welt initialisieren
		boolean[][] world = game.initWorld();
		System.out.println("Startkonstellation");
		game.showWorld(world);

		// Test
		for (int i = 1; i <= 100; i++) {
			world = game.appliesRules(world);
			System.out.println("Generation " + i);
			game.showWorld(world);
		}
	}
}
