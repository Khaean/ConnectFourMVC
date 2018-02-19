package main;

import java.util.Scanner;

import controller.ConnectFourController;
import model.Game;
import model.GameInterface;
import view.ConnectFourView;
import view.ConnectFourViewInterface;

public class ConnectFourMain {
	
	/**
	 * Main Method to start the game
	 * 
	 * @param args Unused.
	 * @return Nothing.
	 */
	public static void main (String[] args) 
	{
		//Initialize the scanner used during the session
		Scanner scan = new Scanner(System.in);
		
		GameInterface game = new Game();
		game.initializeGame();
		ConnectFourViewInterface view = new ConnectFourView(game);
		ConnectFourController controller = new ConnectFourController(scan);
		controller.InitializeGame(game);
		controller.InitializeView(view);
		
		Thread mainThread = new Thread(controller);
		
		mainThread.start();
	}
}
