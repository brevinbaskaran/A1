/**
 * The class <b>TicTacToeGame</b> is the
 * class that implements the Tic Tac Toe Game.
 * It contains the grid and tracks its progress.
 * It automatically maintain the current state of
 * the game as players are making moves.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */
public class TicTacToeGame {

   /**
	* The board of the game, stored as a single array.
	*/
	private CellValue[] board;


   /**
	* level records the number of rounds that have been
	* played so far. Starts at 0.
	*/
	private int level;

   /**
	* gameState records the current state of the game.
	*/
	private GameState gameState;


   /**
	* lines is the number of lines in the grid
	*/
	private final int lines;

   /**
	* columns is the number of columns in the grid
	*/
	private final int columns;


   /**
	* sizeWin is the number of cell of the same type
	* that must be aligned to win the game. 
	* For simplicity, it will be always 3 in this assignment.
	*/
	private final int sizeWin; 


   /**
	* default constructor, for a game of 3x3, which must
	* align 3 cells
	*/
	public TicTacToeGame(){
		this(3,3,3);
	}

   /**
	* constructor allowing to specify the number of lines
	* and the number of columns for the game. 3 cells must
	* be aligned.
   	* @param lines
    *  the number of lines in the game
    * @param columns
    *  the number of columns in the game
  	*/
		public TicTacToeGame(int lines, int columns){
			// your code here
			// 3 because of the cells
			this(lines, columns, 3);
		
	}

   /**
	* constructor allowing to specify the number of lines
	* and the number of columns for the game, as well as
	* the number of cells that must be aligned to win.
   	* @param lines
    *  the number of lines in the game
    * @param columns
    *  the number of columns in the game
    * @param sizeWin
    *  the number of cells that must be aligned to win.
  	*/
	public TicTacToeGame(int lines, int columns, int sizeWin){
		// your code here
		//use this keyword whenever you want to reference 
		this.lines = lines;
		this.columns=columns;
		this.sizeWin = sizeWin;
		int numberOfboxes = columns * lines;
		level = 0;
	
		//board = new CellValue[numberOfboxes];
		for (int i = 0; i < numberOfboxes; i++){
			//board = new CellValue[numberOfboxes];
			board[i] = CellValue.EMPTY;

		}


	}



   /**
	* getter for the variable lines
	* @return
	* 	the value of lines
	*/
	public int getLines(){
		// your code here
		return lines;
	}

   /**
	* getter for the variable columns
	* @return
	* 	the value of columns
	*/
	public int getColumns(){
		// your code here
		return columns;

	}

   /**
	* getter for the variable level
	* @return
	* 	the value of level
	*/
	public int getLevel(){
		// your code here
		return level;
	}


   /**
	* getter for the variable gameState
	* @return
	* 	the value of gameState
	*/
	public GameState getGameState(){
		// your code here
		return gameState;
	}

   /**
	* getter for the variable sizeWin
	* @return
	* 	the value of sizeWin
	*/
	public int getSizeWin(){
		// your code here
		return sizeWin;
	}

   /**
	* returns the cellValue that is expected next,
	* in other word, which played (X or O) should
	* play next.
	* This method does not modify the state of the
	* game.
	* @return
    *  the value of the enum CellValue corresponding
    * to the next expected value.
  	*/
	public CellValue nextCellValue(){
		// your code here
		
		// level is the number of turns played, and based on assignment file game starts with x 
		// based on info level starts at 0, so odd -> O's turn and even -> X's turn

		if ( level % 2 == 0){ 		// if the turn counter (level) is even then O is the next turn so O is returned
			
			//level ++; // not sure abt this yet --> CHECK 
			return CellValue.O;
		}
		else {   					// if the turn counter (level) is even then O is the next turn so O is returned
			
			//level ++; // not sure abt this yet --> CHECK 
			return CellValue.X;
		}
		
	}

   /**
	* returns the value  of the cell at
	* index i.
	* If the index is invalid, an error message is
	* printed out. The behaviour is then unspecified
   	* @param i
    *  the index of the cell in the array board
    * @return
    *  the value at index i in the variable board.
  	*/
	public CellValue valueAt(int i) {
		int numberOfCells = lines*columns; 
		if (i <0 || i > numberOfCells) System.out.println("Position" + i + " not in boards");
		else return board[i];
	}

   /**
	* This method is called by the next player to play
	* at the cell  at index i.
	* If the index is invalid, an error message is
	* printed out. The behaviour is then unspecified
	* If the chosen cell is not empty, an error message is
	* printed out. The behaviour is then unspecified
	* If the move is valide, the board is updated, as well
	* as the state of the game.
	* To faciliate testing, it is acceptable to keep playing
	* after a game is already won. If that is the case, the
	* a message should be printed out and the move recorded.
	* the  winner of the game is the player who won first
   	* @param i
    *  the index of the cell in the array board that has been
    * selected by the next player
  	*/
	public void play(int i) {

		// your code here
		int numberOfCells = lines*columns; 
		if (i <0 || i > numberOfCells) System.out.println("Position" + i + " not in boards");
		if (board[i] != CellValue.EMPTY) System.out.println("Position" + i + " is already filled");

		if (board[i] == CellValue.EMPTY) {
			level++; //this is where you increment level because in the other function it says it shouldn't change the function 
			board[i] = nextCellValue();

			if(gameState != GameState.PLAYING) {
				System.out.println("You have decided to keep playing after a games is already won");

			}
			else setGameState(i); //This is where you check if anyobody won a game 

		}

		

	}


   /**
	* A helper method which updates the gameState variable
	* correctly after the cell at index i was just set.
	* The method assumes that prior to setting the cell
	* at index i, the gameState variable was correctly set.
	* it also assumes that it is only called if the game was
	* not already finished when the cell at index i was played
	* (the the game was playing). Therefore, it only needs to
	* check if playing at index i has concluded the game
	* So check if 3 cells are formed to win.
//   	* @param i
    *  the index of the cell in the array board that has just
    * been set
  	*/

	private void setGameState(int index){

		// your code here
		

	}



	final String NEW_LINE = System.getProperty("line.separator");
	// returns the OS dependent line separator

   /**
	* Returns a String representation of the game matching
	* the example provided in the assignment's description
	*
   	* @return
    *  String representation of the game
  	*/

	public String toString(){
		// your code here
		// use NEW_LINE defined above rather than \n
		

	}

}
