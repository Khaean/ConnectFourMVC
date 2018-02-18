package view;

import model.GameInterface;
import model.Player;

public class ConnectFourView implements ConnectFourViewInterface{
	
	private GameInterface _game;
	
	public ConnectFourView(GameInterface game) {
		_game = game;
	}
	
	/**
	 * Print the current state of the board 
	 */
	@Override
	public void printBoard() {
		
		for (int i = _game.getBoard().getRowCount()-1; i >= 0; i--) {
			//Print upper delimiter
			if (i == _game.getBoard().getRowCount()-1)
			{
				for (int j = 0; j < _game.getBoard().getBoard()[i].length; j++) 
					System.out.print("--");
				System.out.println("-");
			}
			
			//Print the board
			for (int j = 0; j < _game.getBoard().getBoard()[i].length; j++) 
			{
				System.out.print("|");
				if ( _game.getBoard().getValueAt(i, j) == 0 )
					System.out.print(" ");
				else
					System.out.print(_game.getPlayers()[_game.getBoard().getValueAt(i, j)-1].getPlayerColor().charAt(0));
			}
			System.out.print("|");
			System.out.println();
			
			//Print lower delimiter
			if (i == 0)
			{
				for (int j = 0; j < _game.getBoard().getBoard()[i].length; j++) 
					System.out.print("--");
				System.out.println("-");
			}
		}
	}

	@Override
	public void printWinner(Player player) {
		System.out.println("Player "+ player.getPlayerNumber() +" ["+ player.getPlayerColor() +"] wins!");
	}
	
	@Override
	public void printlnMessage(String message) {
		System.out.println(message);
	}
	
	@Override
	public void printMessage(String message) {
		System.out.println(message);
	}
	
	
}
