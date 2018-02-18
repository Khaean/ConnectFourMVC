package model;

public interface GameInterface {
	
	public void initializeGame();
	public void initializeGame(int row, int column, int countToWin);
	public void newGame();
	public void processTurn(int column, Player player);
	
	public Board getBoard();
	public Player getCurrentPlayer();
	public Player[] getPlayers();
	public int getWinner();
}
