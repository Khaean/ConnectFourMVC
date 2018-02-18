package view;

import model.Player;

public interface ConnectFourViewInterface {
	
	public void printBoard();
	public void printWinner(Player player);
	public void printMessage(String message);
	void printlnMessage(String message);
}
