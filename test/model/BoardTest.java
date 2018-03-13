package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class BoardTest {
	
	private Board _board = new Board();
	private Player _player1 = new Player(1,"RED");
	private Player _player2 = new Player(2,"GREEN");

	@Test
	public void testReset() {
		_board.reset();
		assertTrue( boardIsEmpty( _board.getBoard() ) );
		
		_board.dropDisc(0, _player1);
		_board.dropDisc(1, _player2);
		_board.dropDisc(1, _player1);
		_board.dropDisc(2, _player2);
		_board.dropDisc(2, _player2);
		assertFalse( boardIsEmpty( _board.getBoard() ) );
		_board.reset();
		assertTrue( boardIsEmpty( _board.getBoard() ) );
	}
	
	public boolean boardIsEmpty(Integer[][] board) {
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[i].length; j++)
                if (board[i][j] != null)
                    return false;
        
        return true;
	}
	
	@Test
	public void testDropDisc() {
			
		_board.dropDisc(0, _player1);
		assertEquals(_player1.getPlayerNumber(), _board.getValueAt(0, 0));
		
		_board.dropDisc(0, _player2);
		assertEquals(_player2.getPlayerNumber(), _board.getValueAt(1, 0));
		
	}
	
	@Test
	public void test4StraightHorizontal() {
		_board.reset();
		_board.dropDisc(0, _player1);
		_board.dropDisc(1, _player1);

		assertFalse(_board.checkWinner(_player1.getPlayerNumber()));
				
		_board.dropDisc(3, _player1);
		_board.dropDisc(2, _player1);
		
		assertTrue(_board.checkWinner(_player1.getPlayerNumber()));
		
		_board.dropDisc(0, _player1);
	}
	
	@Test
	public void test4StraightVertical() {
		_board.reset();
		_board.dropDisc(0, _player1);
		_board.dropDisc(0, _player1);

		assertFalse(_board.checkWinner(_player1.getPlayerNumber()));
		
		_board.dropDisc(0, _player1);
		_board.dropDisc(0, _player1);
		
		assertTrue(_board.checkWinner(_player1.getPlayerNumber()));
		
		_board.dropDisc(0, _player1);
	}
	
	@Test
	public void test4StraightDiagonalBottomLeft() {
		_board.reset();
		_board.dropDisc(0, _player1);
		_board.dropDisc(1, _player2);
		_board.dropDisc(1, _player1);
		_board.dropDisc(2, _player2);
		_board.dropDisc(2, _player2);

		assertFalse(_board.checkWinner(_player1.getPlayerNumber()));
		
		_board.dropDisc(2, _player1);
		_board.dropDisc(3, _player2);
		_board.dropDisc(3, _player2);
		_board.dropDisc(3, _player2);
		_board.dropDisc(3, _player1);
		
		assertTrue(_board.checkWinner(_player1.getPlayerNumber()));
		
		_board.dropDisc(0, _player1);
	}
	
	@Test
	public void test4StraightDiagonalBottomRight() {
		_board.reset();
		_board.dropDisc(3, _player1);
		_board.dropDisc(2, _player2);
		_board.dropDisc(2, _player1);
		_board.dropDisc(1, _player2);
		_board.dropDisc(1, _player2);

		assertFalse(_board.checkWinner(_player1.getPlayerNumber()));
		
		_board.dropDisc(1, _player1);
		_board.dropDisc(0, _player2);
		_board.dropDisc(0, _player2);
		_board.dropDisc(0, _player2);
		_board.dropDisc(0, _player1);
		
		assertTrue(_board.checkWinner(_player1.getPlayerNumber()));
		
		_board.dropDisc(0, _player1);
	}
	
	@Test(expected = InvalidColumnException.class)
	public void testColumnFull() {
		_board.reset();
		_board.dropDisc(0, _player1);
		_board.dropDisc(0, _player2);
		_board.dropDisc(0, _player1);
		_board.dropDisc(0, _player2);
		_board.dropDisc(0, _player1);
		_board.dropDisc(0, _player2);
		
		assertTrue(_board.columnIsFull(0));
		
		_board.dropDisc(0, _player1);
	}
	
	@Test(expected = InvalidColumnException.class)
	public void testWrongColumn() {
		_board.reset();
		_board.dropDisc(8, _player1);
		
		_board.dropDisc(0, _player1);
	}
	
	
	@Test
	public void testBoardFull() {
		_board.reset();
		_board.dropDisc(0, _player1);
		_board.dropDisc(0, _player2);
		_board.dropDisc(0, _player1);
		_board.dropDisc(0, _player2);
		_board.dropDisc(0, _player1);
		_board.dropDisc(0, _player2);
		
		_board.dropDisc(1, _player1);
		_board.dropDisc(1, _player2);
		_board.dropDisc(1, _player1);
		_board.dropDisc(1, _player2);
		_board.dropDisc(1, _player1);
		_board.dropDisc(1, _player2);
		
		_board.dropDisc(2, _player1);
		_board.dropDisc(2, _player2);
		_board.dropDisc(2, _player1);
		_board.dropDisc(2, _player2);
		_board.dropDisc(2, _player1);
		_board.dropDisc(2, _player2);

		_board.dropDisc(3, _player2);
		_board.dropDisc(3, _player1);
		_board.dropDisc(3, _player2);
		_board.dropDisc(3, _player1);
		_board.dropDisc(3, _player2);
		_board.dropDisc(3, _player1);
		
		_board.dropDisc(4, _player1);
		_board.dropDisc(4, _player2);
		_board.dropDisc(4, _player1);
		_board.dropDisc(4, _player2);
		_board.dropDisc(4, _player1);
		_board.dropDisc(4, _player2);
		
		_board.dropDisc(5, _player1);
		_board.dropDisc(5, _player2);
		_board.dropDisc(5, _player1);
		_board.dropDisc(5, _player2);
		_board.dropDisc(5, _player1);
		_board.dropDisc(5, _player2);
		
		_board.dropDisc(6, _player1);
		_board.dropDisc(6, _player2);
		_board.dropDisc(6, _player1);
		_board.dropDisc(6, _player2);
		_board.dropDisc(6, _player1);
		_board.dropDisc(6, _player2);
		
		assertTrue(_board.boardIsfull());
		
	}

}
