package model;

public class Player {
	
	private final int _number;
	private final String _color;
	private int _winNumber = 0;
	
	public Player(int playerNumber, String color) {
		_number = playerNumber;
		_color = color;				
	}
	
	public void addWin() {
		_winNumber++;
	}
	
	public int getPlayerNumber() {
		return _number;
	}
	
	public String getPlayerColor() {
		return _color;
	}
	
	public int getWinCount() {
		return _winNumber;
	}
}
