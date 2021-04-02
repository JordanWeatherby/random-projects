import java.util.*;
import java.io.*;
class Hangman {
	
	boolean gameWon;
	boolean correctInput = false;
	String wordToBeGuessed;
	char[] guessWordArray;
	char[] answerArray;
	int lettersGuessed;
	int lives = 10;
	ArrayList<String> wordArray = new ArrayList<String>();
	Random rand = new Random();
	
	//Constructor innit
	public Hangman(){
		
	}
	
	//Asks user for a word/phrase to be guessed
	public void startWord(){
		while(correctInput == false){
			Scanner userInput = new Scanner( System.in );
			System.out.print("Enter a word or phrase to be guessed: ");

			wordToBeGuessed = userInput.next().toUpperCase();
			System.out.println(wordToBeGuessed);
			if(wordToBeGuessed.contains("RANDOM")){
				readFile();
				int randomNum = rand.nextInt(wordArray.size() + 1);
				wordToBeGuessed = wordArray.get(randomNum).toUpperCase();
				correctInput = true;
			}
			else if (wordToBeGuessed.contains("  ") || wordToBeGuessed == " "){
				System.out.println("Incorrect input, try again!");
				correctInput = false;
			} else{
				correctInput = true;
			}
		}
		
		
		guessWordArray = new char[wordToBeGuessed.length()];
		answerArray = new char[wordToBeGuessed.length()];
		
		for(int i=0; i < guessWordArray.length; i++){
			guessWordArray[i] = wordToBeGuessed.charAt(i);
			
			if(wordToBeGuessed.charAt(i) == ' ') {
				answerArray[i] = ' ';
				lettersGuessed++;
			}else {
				answerArray[i] = '-';
			}
		}
	//Magic code to clear the terminal
	System.out.print("\033[H\033[2J");  
    System.out.flush(); 
	printAnswer();
	
	}
	
	
	//Prints the array of letters guessed by the user
	public void printAnswer(){
		for(int i=0; i < answerArray.length; i++){
			System.out.print(answerArray[i]);
			
		}
		System.out.print("                    Lives remaining: "+lives);
		System.out.println();
	}
	
	//Asks the user to guess a letter
	public void guessChar(){
		Scanner userInput = new Scanner( System.in );
		System.out.print("Guess a letter: ");
		char letter = (userInput.next().toUpperCase()).charAt(0);
		
		boolean found = false;
		for(int i=0; i < guessWordArray.length; i++){
			
			if(answerArray[i] == letter){
				System.out.println("You already guessed that letter!");
				found = true;
				break;
			} else if(guessWordArray[i] == letter){
				answerArray[i] = letter;
				lettersGuessed++;
				found = true;
			}
		}
		if(found == false){
				System.out.println("That letter isn't in the word! -1 life");
				lives--;
			}
		printAnswer();
	}
	
	public void readFile(){

        // create String variable to store each line
        String line = "";

		
	try {
	Scanner myFile = new Scanner(new FileReader("words.txt"));
	// while loop
        while (myFile.hasNext()) {
            // find next line
            line = myFile.next();
            wordArray.add(line); // Adding line to ArrayList
        }
        myFile.close();
	} catch (FileNotFoundException ex) {

		System.err.println("File not found");
	}
       
        

		
        
    }
	
	//Initialises the game and tests if the user has won
	public void play(){
		startWord();
		
		while(gameWon == false){
			guessChar();
			
			if(lettersGuessed == wordToBeGuessed.length()){
				gameWon = true;
			}
			if(lives == 0){
				break;
			}
		}
		
		if(gameWon == true){
			System.out.println("You Won!");
		} else{
			System.out.println();
			System.out.println("You ran out of lives!");
			System.out.println("The word was: "+wordToBeGuessed);
		}
		
	}

	public static void main(String[] args){
		Hangman A = new Hangman();
		A.play();
	
	}
}

