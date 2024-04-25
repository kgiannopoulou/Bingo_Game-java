import java.util.*; 

// Nikunj Sonigara 
class BingoCard { 
	// List to store the Bingo card numbers 
	List<Integer> numbers = new ArrayList<>(); 
} 

public class GFG { 
	public static void main(String[] args) 
	{ 
		Scanner scanner = new Scanner(System.in); 
		System.out.println("Welcome to Bingo!"); 

		// Random number generator 
		Random random = new Random(); 

		// Create Bingo cards for both players 
		BingoCard player1Card = createBingoCard(random); 
		BingoCard player2Card = createBingoCard(random); 

		// Tracks the current player 
		int currentPlayer = 1; 

		// Main game loop 
		while (true) { 
			// Get the current 
			// player's card 
			BingoCard currentCard = (currentPlayer == 1) 
										? player1Card 
										: player2Card; 

			// Generate a random number between 0 and 25 
			int drawnNumber 
				= generateRandomNumber(random, 0, 25); 
			// Mark the drawn number on both player's cards 
			drawNumberAndMark(player1Card, player2Card, 
							drawnNumber); 

			System.out.println("\nPlayer " + currentPlayer 
							+ " - Drawn Number: "
							+ drawnNumber); 

			// Display both player's cards 
			System.out.println("Player 1's Card:"); 
			displayCard(player1Card); 
			System.out.println( 
				"--------------------------"); 
			System.out.println("Player 2's Card:"); 
			displayCard(player2Card); 
			System.out.println( 
				"--------------------------"); 

			// Check if the current player has achieved 
			// Bingo 
			if (hasBingo(currentCard)) { 
				// Exit the game loop 
				System.out.println( 
					"\nPlayer " + currentPlayer 
					+ " has achieved Bingo! Congratulations!"); 
				break; 
			} 

			// Switch to the other player for the next round 
			currentPlayer = (currentPlayer == 1) ? 2 : 1; 
		} 
		// Close the scanner 
		scanner.close(); 
	} 

	// Generates a random number between min and max 
	// (inclusive) 
	private static int
	generateRandomNumber(Random random, int min, int max) 
	{ 
		return min + random.nextInt(max - min + 1); 
	} 

	// Creates a new Bingo card and shuffles the numbers 
	private static BingoCard createBingoCard(Random random) 
	{ 
		BingoCard card = new BingoCard(); 
		List<Integer> possibleNumbers = new ArrayList<>(); 
		for (int i = 1; i <= 25; ++i) { 
			possibleNumbers.add(i); 
		} 
		Collections.shuffle(possibleNumbers, random); 
		// Add shuffled numbers to the 
		// card 
		card.numbers.addAll(possibleNumbers); 
		return card; 
	} 

	// Marks the drawn number on both player's cards 
	private static void
	drawNumberAndMark(BingoCard player1Card, 
					BingoCard player2Card, int number) 
	{ 
		for (int i = 0; i < player1Card.numbers.size(); 
			++i) { 
			// Mark the number as drawn on 
			// player 1's card 
			if (player1Card.numbers.get(i) == number) { 
				player1Card.numbers.set(i, 0); 
			} 

			// Mark the number as drawn on 
			// player 2's card 
			if (player2Card.numbers.get(i) == number) { 
				player2Card.numbers.set(i, 0); 
			} 
		} 
	} 

	// Checks if the Bingo card has achieved a Bingo pattern 
	private static boolean hasBingo(BingoCard card) 
	{ 
		for (int i = 0; i < 5; ++i) { 
			// Check rows for Bingo 
			if (card.numbers.get(i * 5) == 0
				&& card.numbers.get(i * 5 + 1) == 0
				&& card.numbers.get(i * 5 + 2) == 0
				&& card.numbers.get(i * 5 + 3) == 0
				&& card.numbers.get(i * 5 + 4) == 0) { 
				return true; 
			} 

			// Check columns for Bingo 
			if (card.numbers.get(i) == 0
				&& card.numbers.get(i + 5) == 0
				&& card.numbers.get(i + 10) == 0
				&& card.numbers.get(i + 15) == 0
				&& card.numbers.get(i + 20) == 0) { 
				return true; 
			} 
		} 

		// Check diagonals for Bingo 
		if ((card.numbers.get(0) == 0
			&& card.numbers.get(6) == 0
			&& card.numbers.get(12) == 0
			&& card.numbers.get(18) == 0
			&& card.numbers.get(24) == 0) 
			|| (card.numbers.get(4) == 0
				&& card.numbers.get(8) == 0
				&& card.numbers.get(12) == 0
				&& card.numbers.get(16) == 0
				&& card.numbers.get(20) == 0)) { 
			return true; 
		} 

		// No Bingo pattern found 
		return false; 
	} 

	// Displays the Bingo card 
	private static void displayCard(BingoCard card) 
	{ 
		for (int i = 0; i < card.numbers.size(); ++i) { 
			// Display numbers or 'X' for 
			// drawn numbers 
			System.out.print((card.numbers.get(i) != 0) 
								? card.numbers.get(i) 
								: "X"); 
			System.out.print("\t"); 

			// Move to the next line 
			// after every 5 numbers 
			if ((i + 1) % 5 == 0) { 
				System.out.println(); 
			} 
		} 
	} 
}
