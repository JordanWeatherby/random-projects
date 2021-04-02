import java.util.*;
import java.io.*;
class TicTacToe {
	
	int players;
	char[][] board = {{'-','-','-'},{'-','-','-'},{'-','-','-'}}; //Sets a blank game board
	boolean incorrectInput = true;
	boolean incorrectMove = true;
	boolean gameWon = false;
	boolean gameDrawn = false;
	int moves = 0;
	
	//Constructor
	public TicTacToe() {
		
	}
	
	//Draws the current game board
	public void draw() {
		for(int i=0; i < 3 ; i++ ){
			for(int j=0; j < 3 ; j++ ){
				System.out.print(board[i][j]);
			}
			System.out.print("\n");
		}
	}
	
	//Asks the user for the number of players
	public void getPlayers(){
		
		while(incorrectInput){
			Scanner userInputPlayers = new Scanner( System.in );
			System.out.print("One or two players? ");
			players = userInputPlayers.nextInt();
			
			if(players == 1 || players == 2){
				incorrectInput = false;
			} else{
				System.out.println("Incorrect input");
				incorrectInput = true;
			}
		}
		
	}
	
	//Sees if either player has won
	public void winCondition(){
		if(('X' == board[0][0] && 'X' == board[1][0] && 'X' == board[2][0]) || ('O' == board[0][0] && 'O' == board[1][0] && 'O' == board[2][0]) ){
			gameWon = true;
		} else if(('X' == board[0][1] && 'X' == board[1][1] && 'X' == board[2][1]) || ('O' == board[0][1] && 'O' == board[1][1] && 'O' == board[2][1]) ){
			gameWon = true;
		} else if(('X' == board[0][2] && 'X' == board[1][2] && 'X' == board[2][2]) || ('O' == board[0][2] && 'O' == board[1][2] && 'O' == board[2][2]) ){
			gameWon = true;
		} else if(('X' == board[0][0] && 'X' == board[0][1] && 'X' == board[0][2]) || ('O' == board[0][0] && 'O' == board[0][1] && 'O' == board[0][2]) ){
			gameWon = true;
		} else if(('X' == board[1][0] && 'X' == board[1][1] && 'X' == board[1][2]) || ('O' == board[1][0] && 'O' == board[1][1] && 'O' == board[1][2]) ){
			gameWon = true;
		} else if(('X' == board[2][0] && 'X' == board[2][1] && 'X' == board[2][2]) || ('O' == board[2][0] && 'O' == board[2][1] && 'O' == board[2][2]) ){
			gameWon = true;
		} else if(('X' == board[0][0] && 'X' == board[1][1] && 'X' == board[2][2]) || ('O' == board[0][0] && 'O' == board[1][1] && 'O' == board[2][2]) ){
			gameWon = true;
		} else if(('X' == board[0][2] && 'X' == board[1][1] && 'X' == board[2][0]) || ('O' == board[0][2] && 'O' == board[1][1] && 'O' == board[2][0]) ){
			gameWon = true;
		} else if(moves >= 9){
			gameDrawn = true;
		}
		
	}
	
	// public void playerOneMove(){
		
		// while(incorrectMove){
			// Scanner userInputMove = new Scanner( System.in );
			// System.out.print("P1 where go? ");
			// int x = userInputMove.nextInt();
			// int y = userInputMove.nextInt();
			// if(board[x-1][y-1] == '-'){
				// board[x-1][y-1] = 'O';
				// incorrectMove = false;
			// } else{
				// System.out.println("Already filled");
				// incorrectMove = true;
			// }
		// }
		
	// }
	
	// public void playerTwoMove(){
		// while(incorrectMove){
			// Scanner userInputMove = new Scanner( System.in );
			// System.out.print("P2 where go? ");
			// int i = userInputMove.nextInt();
			// int j = userInputMove.nextInt();
			// if(board[i][j] == '-'){
				// incorrectMove = false;
				// board[i][j] = 'X';
			// } else{
				// System.out.println("Already filled");
				// incorrectMove = true;
			// }
		// }
	// }
	
	public void playerOneMove(){
		Scanner userInputMove = new Scanner( System.in );
		System.out.print("P1 where go? ");
		board[userInputMove.nextInt() - 1][userInputMove.nextInt() - 1] = 'O';
	}
	
	public void playerTwoMove(){
		Scanner userInputMove = new Scanner( System.in );
		System.out.print("P2 where go? ");
		board[userInputMove.nextInt() - 1][userInputMove.nextInt() - 1] = 'X';
	}
		
	
	
	
	//Controls the drawing, moving and winning of the game
	public void play(int noOfPlayers){
		
		if(noOfPlayers == 1 ){
			
			while(gameWon != true){
				draw();
				gameWon = true;
			}
			
		} else {
			draw();
			while(true){
				playerOneMove();
				moves++;
				winCondition();
				draw();
				if(gameWon){ 
					System.out.println("Player 1 wins!"); 
					break;
				} else if(gameDrawn){
					System.out.println("It's a draw :( "); 
					break;
				}
				playerTwoMove();
				moves++;
				winCondition();
				draw();
				if(gameWon){ 
					System.out.println("Player 2 wins!"); 
					break;
				} else if(gameDrawn){
					System.out.println("It's a draw :( "); 
					break;
				}
				
			}
			
		}
	}
	
	
	
	
	public static void main(String[] args){
		TicTacToe A = new TicTacToe();
		A.getPlayers();
		A.play(A.players);
		
	}
}
