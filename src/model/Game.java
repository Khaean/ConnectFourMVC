package model;

public class Game implements GameInterface{
	
	//Board used for the game
	private Board _board;
	private int _isWinner = 0;
	private Player _player1;
	private Player _player2;
	private Player _currentPlayer;
	private Player[] _players;

	/**
	 * Initialize the game with default parameter
	 * 
	 * @return nothing
	 * 
	 */
	@Override
	public void initializeGame() {
		_board = new Board();
		InitializePlayers();
	}
	
	/**
	 * Initialize the game with custom parameter
	 * 
	 * @return nothing
	 * 
	 */
	@Override
	public void initializeGame(int row, int column, int countToWin) {
		_board = new Board(row, column, countToWin);	
		InitializePlayers();	
	}
	
	/**
	 * Create a new game with initial parameter
	 * 
	 * @return nothing
	 * 
	 */
	public void newGame() {
		_board.reset();
		_isWinner = 0;
	}
		
	/**
	 * Process a turn for the game 
	 */
	@Override
	public void processTurn(int column, Player player) {
		
		//Check that the player for controller is the good one
		if (_currentPlayer.getPlayerNumber() != player.getPlayerNumber())
			throw new InvalidPlayerException();
		
		try {
			_currentPlayer = player;
			_board.dropDisc(column, player);
			
		}
		catch(InvalidColumnException e) {
			throw new InvalidColumnException();
		}

		if (_board.checkWinner(_currentPlayer.getPlayerNumber()))
			_isWinner = _currentPlayer.getPlayerNumber();
			
		setNextPlayer();
	}
	
	/**
	 * Method to initialize players
	 * 
	 * @return nothing
	 * 
	 */
	private void InitializePlayers()
	{
		_player1 = new Player(1,"RED");
		_player2 = new Player(2,"GREEN");
		_players = new Player[2];
		_players[0] = _player1;
		_players[1] = _player2;
		
		_currentPlayer = _player1;
	}
	
	/**
	 * Get the list of players
	 * 
	 * @return Player[] list of player
	 */
	public Player[] getPlayers() {
		return _players;
	}
	
	/**
	 * Get the winner of the current game
	 * 
	 * @return int the player number
	 */
	public int getWinner() {
		return _isWinner;
	}
	
	/**
	 * Get the board
	 * 
	 * @return Board return the board
	 */
	public Board getBoard() {
		return _board;
	}
	
	/**
	 * Set the next player to play
	 * 
	 * @return nothing
	 */
	private void setNextPlayer() {
		if (_currentPlayer.getPlayerNumber() == 1)
			_currentPlayer = _player2;
		else
			_currentPlayer = _player1;
	}
	
	/**
	 * Get the current player to play
	 * 
	 * @return Player get the next player
	 */
	public Player getCurrentPlayer() {
		return _currentPlayer;
	}
	
	
}
