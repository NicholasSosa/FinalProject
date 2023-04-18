import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Game  {
	private boolean computer;
	private int player1;
	private int player2;
	private String playerOne;
	private String playerTwo;
	private static int highScore;
	
	//starts the game and prints the menu
	public Game() throws IOException {
		printMenu();
		
	}
	
	//allows users to enter their name 
	// if playing against computer, User 1 enters name 
	//if playing 2 players, User 1 and User 2 enters name 
	public void setName() throws IOException {
		Scanner keyboard = new Scanner(System.in);
		if(computer) {
			System.out.println("Enter your name");
			playerOne = keyboard.nextLine();
			playerTwo = "Computer";
		}
		else {
			System.out.println("Player 1 Enter Your Name");
			playerOne = keyboard.nextLine();
			System.out.println("Player 2 Enter Your Name");
			playerTwo = keyboard.nextLine();
		}
		
	}
	
	//Get's the user input and calls functions based on their input
	public void getUserInput() throws IOException {
		Scanner kb1 = new Scanner(System.in);
		int num = kb1.nextInt();
		while (num != 1 && num != 2 && num != 3 && num != 4 && num != 5)
		{
			System.out.println("Error Enter a number (1-5): ");
			printMenu();
			num = kb1.nextInt();
		}
		if(num == 1) {
			printRules();
		}
		else if(num==2){
			computer = true;
			setName();
			play();
		}
		else if(num==3) {
			computer = false;
			setName();
			play();
		}
		else if(num ==4) {
			getHighScore();
		}
		else {
			System.out.println("Thank You For Playing");
			if(player1>player2) {
				System.out.println(playerOne + "won the game!");
			}
			else if(player2>player1) {
				System.out.println(playerTwo + "won the game!");
			}
			else {
				System.out.println("The Game Tied!");
			}
			
		}
		
	}
	
	//plays the game. User 1 can input Rock, Paper, or Scissor
	//Computer randomly chooses an option  
	public void play() throws IOException {
		System.out.println("Player 1 Enter Rock, Paper, or Scissor");
		Scanner kb = new Scanner(System.in);
		String choice1 = kb.nextLine();
		String choice2;
		
		if(computer) {
			//gets random integer 
			int r = (int)(Math.random()*3) +1;
			if(r==1) {
				choice2 = "Rock";
			}
			else if(r==2){
				choice2 = "Paper";
			}
			else {
				choice2 = "Scissor";
			}
		}
		else {
			System.out.println("Player 2 Enter Rock, Paper, or Scissor");
			choice2 =kb.nextLine();
			
		}
		//calls function to check who won
		checkWinner(choice1,choice2);

	}
	
	//check who won
	public void checkWinner(String a, String b) throws IOException {
		Scanner kb = new Scanner(System.in);
		
		//if players had same choice
		if(a.equals(b)) {
			System.out.println("Tie");
			
			System.out.println("Enter 1 to continue playing");
			System.out.println("Enter 2 to go back to menu");
			int num = kb.nextInt();
			//play again or go to menu
			if(num ==1) {
				play();
			}
			else {
				printMenu();
			}
		}
		
		//rock against scissors. Rock wins 
		if(a.equals("Rock")){
			if(b.equals("Scissor")) {
				System.out.println(playerOne + " won");
				player1++;
				System.out.println("Current Score is:" + player1 +"-" +player2 );
				checkHighScore();
				System.out.println("Enter 1 to continue playing");
				System.out.println("Enter 2 to go back to menu");
				int num = kb.nextInt();
				if(num ==1) {
					play();
				}
				else {
					printMenu();
				}
			}
			//Rock against Paper. Paper wins 
			else {
				System.out.println(playerTwo + " won");
				player2++;
				System.out.println("Current Score is:" + player1 +"-" +player2 );
				checkHighScore();
				System.out.println("Enter 1 to continue playing");
				System.out.println("Enter 2 to go back to menu");
				int num = kb.nextInt();
				//Continue playing or go to menu
				if(num ==1) {
					play();
				}
				else {
					printMenu();
				}
			}
			
		}
		//Scissors against Rock. Rock wins 
		else if(a.equals("Scissor")){
			if(b.equals("Rock")) {
				System.out.println(playerTwo + " won");
				player2++;
				System.out.println("Current Score is:" + player1 +"-" +player2 );
				checkHighScore();
				System.out.println("Enter 1 to continue playing");
				System.out.println("Enter 2 to go back to menu");
				int num = kb.nextInt();
				//play again or go to menu
				if(num ==1) {
					play();
				}
				else {
					printMenu();
				}
			}
			//Scissors against Paper. Paper wins 
			else {
				System.out.println(playerOne + " won");
				player1++;
				System.out.println("Current Score is:" + player1 +"-" +player2 );
				checkHighScore();
				System.out.println("Enter 1 to continue playing");
				System.out.println("Enter 2 to go back to menu");
				int num = kb.nextInt();
				//Play again or go to menu 
				if(num ==1) {
					play();
				}
				else {
					printMenu();
				}
			}
		}
		else {
			//Paper against Rock. Rock wins. 
			if(b.equals("Rock")) {
				System.out.println(playerOne + " won");
				player1++;
				//Displays current score
				System.out.println("Current Score is:" + player1 +"-" +player2 );
				checkHighScore();
				System.out.println("Enter 1 to continue playing");
				System.out.println("Enter 2 to go back to menu");
				int num = kb.nextInt();
				//play again or go to menu
				if(num ==1) {
					play();
				}
				else {
					printMenu();
				}
			}
			//Paper against Scissors. Scissors wins. 
			else {
				System.out.println(playerTwo + " won");
				//increments points 
				player2++;
				//Displays current score
				System.out.println("Current Score is:" + player1 +"-" +player2 );
				checkHighScore();
				System.out.println("Enter 1 to continue playing");
				System.out.println("Enter 2 to go back to menu");
				int num = kb.nextInt();
				if(num ==1) {
					play();
				}
				else {
					printMenu();
				}
			}
		}	
	}
	
	//Checks the high score and changes if player gets higher. Adds to file.  
	public void checkHighScore() throws IOException {
		FileWriter fw = new FileWriter("HighScore.dat",false);
		PrintWriter pw = new PrintWriter(fw);
		
		//Checks if player 1 has a higher score then the highscore 
		if(player1>= highScore) {
			pw.println(player1);
			highScore = player1;
		}
		//Checks if player 2 has a higher score then the highscore.
		if(player2>= highScore) {
			pw.println(player2);
			highScore = player2;
		}
	} 
	//prints the high score  
	public void getHighScore() throws IOException{
		System.out.println("The high score is:" + highScore );
		printMenu();
	}
	
	
	
	//prints the menu  
	public void printMenu() throws IOException {
		System.out.println("Enter 1 for Rules");
		System.out.println("Enter 2 to Play Against Computer");
		System.out.println("Enter 3 to play Against Another Player");
		System.out.println("Enter 4 to see High Score");
		System.out.println("Enter 5 to Quit");
		getUserInput();
	}

	//prints the rules 
	public void printRules() throws IOException {
		System.out.println("Choose Rock, Paper, or Scissors");
		System.out.println("Rock wins against scissors, but loses against paper");
		System.out.println("Paper wins against rock, but loses against scissors");
		System.out.println("Scissors wins against paper, but loses against rock");
		printMenu();
	}
	public static void main(String[] args) throws IOException {
		Game rockPaperScissors = new Game();
	}
}