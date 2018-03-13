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
	private int _lastRow;
	private int _lastColumn;
	
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
			
			_lastColumn = column;
			_lastRow = row+1;
		}
		else
		{
			_board[row][column] = player.getPlayerNumber();
			_lastPosition.put(column, 0);
			
			_lastColumn = column;
			_lastRow = 0;
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
	 * Check if there is the player won in the game
	 * 
	 * @return boolean winner number
	 */
	@Override
	public boolean checkWinner( int player) {
		
		int row = _lastRow;
		int col = _lastColumn;
		return didWin(player, row, col, -1, 0) ||
				didWin(player, row, col, 0, -1) ||
                didWin(player, row, col, -1, -1) ||
                didWin(player, row, col, 1, -1);
	}
	
	private boolean didWin(int player, int row, int col, int rowDelta, int colDelta) {
	    
		int matches = 0;
	    int i = 0;
	    int iRow = row-rowDelta*_countToWin;
	    int iCol=col-colDelta*_countToWin;

	    while(i <= 2*_countToWin) 
	    {    	
	    	if(iRow < _rowCount-1 && iRow >= 0 && iCol < _columnCount-1 && iCol >= 0)
	    	{
	    		if ( matches >= _countToWin)
	    			break;
		        else if ( _board[iRow][iCol] == null || _board[iRow][iCol] != player )
		        	matches = 0;
	    		else if ( _board[iRow][iCol] == player )
		            matches++;
	    	}
	    	
	    	iRow += rowDelta;
	    	iCol += colDelta;
	    	i++;
	    }
	    
	    return matches >= _countToWin;
	}
	
	
	
	
	
	
}
