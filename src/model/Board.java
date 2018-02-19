package model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Board implements BoardInterface{
	
	private Integer[][] _board;
	
	//Default value for the board
	private final static int _ColumnCountDefault = 7;
	private final static int _RowCountDefault = 6;
	private final static int _CountToWinDefault = 4;
	
	//Value used during the game
	private int _rowCount;
	private int _columnCount;
	private int _countToWin;
	
	// Set to store column already full
	private Set<Integer> _columnFull;
	
	// Store the row of the last added position
	private Map<Integer,Integer> _lastPosition;
	
	/**
	 * Default constructor using default parameter
	 */
	public Board() {
		intializeBoard(_RowCountDefault, _ColumnCountDefault, _CountToWinDefault);
	}
	
	/**
	 * Constructor used when game needs to be customized 
	 * 
	 * @param row Number of row
	 * @param column Number of column
	 * @param countToWin Number of disc to be aligned to win the game
	 */
	public Board(int row, int column, int countToWin) {
		intializeBoard(row, column, countToWin);
	}
	
	/**
	 * Method to initialize the board
	 * 
	 * @param row Number of row
	 * @param column Number of column
	 * @param countToWin Number of disc to be aligned to win the game
	 */
	private void intializeBoard(int row, int column, int countToWin)
	{
		_board = new Integer[row][column];
		_rowCount = row;
		_columnCount = column;
		_countToWin = countToWin;
		
		_columnFull = new HashSet<Integer>();
		_lastPosition = new HashMap<Integer,Integer>();
	}
	
	/**
	 * Reset the board to empty
	 */
	@Override
	public void reset() {
		for (int i = 0; i < _board.length; i++)
            for (int j = 0; j < _board[i].length; j++)
            	_board[i][j] = null;

		_columnFull = new HashSet<Integer>();
		_lastPosition = new HashMap<Integer,Integer>();
	}
	
	/**
	 * Method to drop the disc in the board
	 * 
	 * @param int column number where to drop the disc
	 * @param player Player dropping the disc
	 */
	@Override
	public void dropDisc(int column, Player player) {
		
		if (column < 0 || column > _columnCount || columnIsFull(column))
			throw new InvalidColumnException();
		
		int row = 0;
		//Find which row to drop the disc
		if( _lastPosition.containsKey(column) )
		{
			row = _lastPosition.get(column);
			_board[row+1][column] = player.getPlayerNumber();
			
			if (row +1  == _rowCount -1)
				_columnFull.add(column);
			
			_lastPosition.put(column, row+1);
		}
		else
		{
			_board[row][column] = player.getPlayerNumber();
			_lastPosition.put(column, 0);
		}
	}
	
	/**
	 * Method to check if the board is already full or not
	 * 
	 * @return boolean return true if full
	 */
	@Override
	public boolean boardIsfull() {
		return _columnFull.size() == _columnCount;
	}
	
	/**
	 * Method to check if a column is already full or not
	 * 
	 * @return boolean return true if full
	 */
	@Override
	public boolean columnIsFull(int column) {
		return _columnFull.contains(column);
	}
	
	/**
	 * Get the number of row in the board
	 * 
	 * @return int number of row
	 */
	public int getRowCount() {
		return _rowCount;
	}
	
	/**
	 * Get the number of column in the board
	 * 
	 * @return int number of column
	 */
	public int getColumnCount() {
		return _columnCount;
	}
	
	/**
	 * Get the board itself
	 * 
	 * @return Integer[][] board itself
	 */
	public Integer[][] getBoard() {
		return _board;
	}
	
	/**
	 * Get the value present in a certain cell
	 * 
	 * @return int value in cell
	 */
	public int getValueAt(int row, int column) {
		if (_board[row][column] == null)
			return 0;
		
		return _board[row][column];
	}
	
	/**
	 * Check if there is any winner in the game
	 * if return 0, no winner
	 * 
	 * @return int winner number
	 */
	@Override
	public int checkWinner() {
		//Check if X straight horizontal
		for(int row = 0; row < _rowCount; row++)
		{
			for(int column = 0; column < _columnCount-_countToWin-1; column++)
			{
				int i = 1;
				while (column+i<_columnCount && row+i<_rowCount && _board[row][column] != null 
						&& _board[row][column] == _board[row][column+i])
					i++;
				
				if( i > _countToWin - 1 )
					return _board[row][column];
			}
		}
		
		//Check if X straight vertical or diagonal >>> X defined at the beginning of the game
		for(int row = 0; row < _rowCount-_countToWin+1; row++)
		{
			for(int column = 0; column < _columnCount; column++)
			{
				int i = 1;
				while (_board[row][column] != null && _board[row][column] == _board[row+i][column])
					i++;
				
				if( i > _countToWin - 1 )
					return _board[row][column];
			}
			
			//Check if X straight diagonal  bottom left
			for(int column = 0; column < _columnCount-_countToWin-1; column++)
			{
				int i = 1;
				while (column+i<_columnCount && row+i<_rowCount && _board[row][column] != null 
						&& _board[row][column] == _board[row+i][column+i])
					i++;
				
				if( i > _countToWin - 1 )
					return _board[row][column];
			}
			
			//Check if X straight diagonal bottom right
			for(int column = _countToWin-1; column < _columnCount; column++)
			{
				int i = 1;
				while (column-i>=0 && row+i<_rowCount && _board[row][column] != null 
						&& _board[row][column] == _board[row+i][column-i])
					i++;
				 
				if( i > _countToWin - 1 )
					return _board[row][column];
			}
		}
		 
		return new Integer(0);
	}
	
	
	
	
	
	
}
