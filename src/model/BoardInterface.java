package model;

public interface BoardInterface {
	
	public void reset();
	public void dropDisc(int column, Player player);
	public boolean boardIsfull();
	public boolean columnIsFull(int column);
	public boolean checkWinner( int player );
	
}
