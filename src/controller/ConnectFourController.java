package controller;

import java.util.Scanner;

import model.GameInterface;
import model.InvalidColumnException;
import model.InvalidPlayerException;
import model.Player;
import view.ConnectFourViewInterface;

public class ConnectFourController implements Runnable{
	
	private GameInterface _game;
	private ConnectFourViewInterface _view;
	private Scanner _scan;
		
	public ConnectFourController( Scanner scan ) {
		_scan = scan;
	}
	
	public void InitializeGame( GameInterface game) {
		_game = game;
	}
	
	public void InitializeView( ConnectFourViewInterface view) {
		_view = view;
	}
	
	/**
	 * Method to request user the column number
	 * 
	 * @param int player player's number, should be 1 or 2
	 * @return int the column number
	 */
	private int requestInput(Player player) 
	{
		int column = 0;
 		
 		//Keep asking player to enter a value as long as the value is not an integer or is not the board range
 		//Also check if the column is already full
 		do {
 			_view.printMessage("Player " + player.getPlayerNumber() + " ["+ player.getPlayerColor() +"] - choose column (1-" + _game.getBoard().getColumnCount() + "): ");
 			
 			//Check if the input is an integer
			while (!_scan.hasNextInt()) 
			{
				_view.printlnMessage("Please enter a number between 1 and " + _game.getBoard().getColumnCount());
				_scan.next();
			}
			
			//Get the column input by user
		    column = _scan.nextInt() -1;
 		}
		while (testInput(column));
 		
 		return column;
	}
	
	/**
	 * Method to test the column number
	 * 
	 * @param int  column Column to be tested
	 * @return boolean return if disc can be dropped in this column
	 */
	private boolean testInput(int column) 
	{
		//Check if column is full
	    if(_game.getBoard().columnIsFull(column))
	    {
	    	column = -1; //Reset column to -1 as column is already full
	    	_view.printlnMessage("Column is Full, please choose another column.");
	    }
	    //return true if column is in the range
		return (column < 0 || column > _game.getBoard().getColumnCount()-1);
	}
	
	/**
	 * Method to run the game
	 * 
	 * @return nothing
	 */
	public void run() {
		boolean moreGame = true;
		
		while(moreGame)
		{
			moreGame = false;
			_view.printBoard();
			//Check if there is any winner or if board is full
			//If not continue the game
			while( !_game.getBoard().boardIsfull() && _game.getWinner()==0 )
			{
				int column = requestInput(_game.getCurrentPlayer());
				
				//Drop the next disc
				try {
					_game.processTurn(column, _game.getCurrentPlayer());
				}
				catch(InvalidColumnException e) {
					_view.printlnMessage("Invalid Column!");
				}
				catch(InvalidPlayerException e) {
					_view.printlnMessage("Invalid Player!");
				}
				
				//Print the current board state to the console
				_view.printBoard();
				
				//If any winner, print the result on console
				int winner = _game.getWinner();
				if (winner!=0) 
					_view.printWinner(_game.getPlayers()[winner - 1]);
			}
			
			//If no winner, it's a DRAW
			if(_game.getWinner()==0)
				_view.printlnMessage("No Winner! This is a DRAW!");
			
			//Ask players if they want to play more
			_view.printMessage("One more game (Y/N)? ");
			
			//Check only if player choose Y or y
			if (_scan.next().equalsIgnoreCase("y") )
			{
				moreGame = true;
				_game.newGame();
			}
		}
	}
	
	
	
}
