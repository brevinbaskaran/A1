import javafx.scene.control.Cell;

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
	
		if (i <0 || i > numberOfCells) {
			throw new IllegalArgumentException("Position" + i + " not in board");}
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

		CellValue tmpstate = valueAt(i);
		if (i <0 || i > numberOfCells) System.out.println("Position" + i + " not in boards");
		if (tmpstate != CellValue.EMPTY) System.out.println("Position" + i + " is already filled"); 

		if (tmpstate == CellValue.EMPTY) {
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
		// X, O, X, O, O  
		// O, E, E, O, O 
		// X, O, E, O, O 

		//code below makes a 2d list of the rows
		CellValue[][] rows = new CellValue[lines][columns];
		int current = 0; // keeps track of the position we last started to append with
		int tmpcount = 0; // keeps track of the row number we are apppending to
		for (int i = 0; i < board.length; i++){
			if (i % lines == 0){
				
				for (int a = current; a < i; a++){
					rows[tmpcount][a] = board[a];
				}
				current = i;
				tmpcount++;

			}
		}
		// code above ^^ makes a 2d list of the rows

		int same = 0;
		for ( int j = 0; j < rows.length; j++){
			for (int k = 0; k < (rows[0].length)-1; k++){
				if ((rows[j][k] == rows[j][k+1]) && (rows[j][k] != CellValue.EMPTY) &&  (rows[j][k+1] != CellValue.EMPTY)) {
					same++;
					if (same == 3){
						if (rows[j][k] == CellValue.X){
							gameState = GameState.XWIN;

						}
						else gameState = GameState.OWIN;
					}
					//fix this print statement to correct condition
					//pretty sure its, if (getvalue % 2 == 0) GameState = XWIN; else OWIN
				}
			}

		}

		int verticalWinCounter = 0;
		for (int i = 0 ; i < columns; i ++){
			int verticalSearch = i + (columns * (lines-(sizeWin-1))); //sizeWin-1 because let says we have 3 as a the sizeWin you only have to search every two starting from vertical
			int startingV = i;
			while (startingV < (verticalSearch+1)) {

				if ( CellValue.EMPTY != board[startingV]  && board[startingV]==board[startingV+columns] ){
					verticalWinCounter++;
					if (verticalWinCounter==sizeWin){

					}
				}

				startingV = startingV + 1;

			}



		}

		// top-right to - bottom-left
		int checkRows = lines-(sizeWin+1);
		int checkCol = columns-(sizeWin+1);

		for (int i =0; i < checkRows;i ++){
			for (int j = 0; j < checkCol; j++){
				for (int k =0; k <= sizeWin; k++){
					if (board[(i * columns) + (k+1)] == board[((i+(k+1)) * columns) + (j+(k+1))] && CellValue.EMPTY != board[k]){

					}

				}
			}
		}
 



		// int diagonalStart = 0;
		// int iter1 = lines - sizeWin * columns;
		// int iter2 = columns - sizeWin;
		// int iter3 = columns+1 * sizeWin-1;

		// while (diagonalStart <=iter1) {
			
		// 	for (int a = iter1; a < iter2; a++){

		// 		int b = a;
		// 		while (b < iter3) {

		// 			if (board[b] == board[b+columns+1]){

		// 			}
					
		// 			b = b + columns + 1;
		// 		}

		// 	}
		// 	diagonalStart = diagonalStart + columns;

		// }



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

		//logic => create a 2d list of the board with rows and then check value of each cell in row and append accordingly
		// 			to the array colbreak and then convert array to string and return the string
		String[] lineBreak = new String [(columns*3)+(columns-2)];
		String[] colBreak = new String[columns];
		
		CellValue[][] tmpboard = new CellValue[lines][columns];
		int cur = 0; // keeps track of the position we last started to append with
		int tmp = 0; // keeps track of the row number we are apppending to
		for (int i = 0; i < board.length; i++){
			if (i % lines == 0){
				
				for (int a = cur; a < i; a++){
					tmpboard[tmp][a] = board[a];
				}
				cur = i;
				tmp++;

			}
		}
		
		// \/ \/ creates an array for the string representation of rows
		for (int i = 0; i < colBreak.length - 1; i++){
			 if (i == (colBreak.length-1) || i == 0){
				 colBreak[i] = " ";
			 }
			 else {
				 colBreak[i] = "|";
				 colBreak[i+1]= " ";
			 }
		}

		// \/ \/ creates an array for the string representation of lines between the rows
		for (int i = 0; i < lineBreak.length; i++){
			lineBreak[i] = "-";
		}
	

		String[][] dispboard = new String[lines][]; // this is the board that we will convert to a string from an array of strings
		
		//in this loop we interate though the dispboard array and take our premade array colbreak and append String values of the cell state, then we append this list to dispboard as a row
		for (int p = 0; p < dispboard.length; p++){
			for (int q = cur; q < colBreak.length; q++){ 
				if (!(colBreak[q] == "|")){
					if (tmpboard[p][q] == CellValue.X){
						colBreak[q] = " X ";
					}
					else if (tmpboard[p][q] == CellValue.O){
						colBreak[q] = " O ";

					}
				}
			}
			
			dispboard[p] = colBreak;
		}
	}
}
