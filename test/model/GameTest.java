package model;

import static org.junit.Assert.*;
import org.junit.Test;

public class GameTest {

	@Test
	public void testInitializeGameDefault() {
		Game game = new Game();
		game.initializeGame();
		assertEquals(7, game.getBoard().getColumnCount());
		assertEquals(6, game.getBoard().getRowCount());
	}
	
	@Test
	public void testInitializeGameCustom() {
		Game game = new Game();
		game.initializeGame(8,10,4);
		assertEquals(10, game.getBoard().getColumnCount());
		assertEquals(8, game.getBoard().getRowCount());
	}
	
	@Test
	public void testProcessTurn() {
		Game game = new Game();
		game.initializeGame();
		Player player1 = new Player(1, "RED");
		Player player2 = new Player(2, "GREEN");
		game.processTurn(0, player1);
		game.processTurn(0, player2);
		
		assertEquals(1, game.getBoard().getValueAt(0, 0) );
		assertEquals(2, game.getBoard().getValueAt(1, 0) );
	}
	
	@Test
	public void testAGame() {
		Game game = new Game();
		game.initializeGame();
		Player player1 = new Player(1, "RED");
		Player player2 = new Player(2, "GREEN");
		game.processTurn(0, player1);
		assertEquals(0, game.getWinner() );
		game.processTurn(0, player2);
		assertEquals(0, game.getWinner() );		
		game.processTurn(1, player1);
		assertEquals(0, game.getWinner() );
		game.processTurn(1, player2);
		assertEquals(0, game.getWinner() );
		game.processTurn(2, player1);
		assertEquals(0, game.getWinner() );
		game.processTurn(2, player2);
		assertEquals(0, game.getWinner() );
		game.processTurn(3, player1);
		assertEquals(1, game.getWinner() );
	}

}
  