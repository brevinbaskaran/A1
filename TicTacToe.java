
import java.io.Console;

public class TicTacToe{

   /**
     * <b>main</b> of the application. Creates the instance of  TicTacToeGame 
     * and starts the game. If two parameters lines  and columns
     * are passed, they are used. If the parameters lines, columns
     * and win are passed, they are used.
     * Otherwise, a default value is used. Defaults values (3) are also
     * used if the paramters are too small (less than 2).
     * @param args
     *            command lines parameters
     */
     public static void main(String[] args) {

        StudentInfo.display();

		Console console = System.console();
     	TicTacToeGame game;
        int lines, columns, win;
        lines = 3;
        columns = 3;
        win = 3;

        if (args.length >= 2) {
            lines = Integer.parseInt(args[0]);
            if(lines<2){
                System.out.println("Invalid argument, using default...");
                lines = 3;
            }
            columns = Integer.parseInt(args[1]);
            if(columns<2){
                System.out.println("Invalid argument, using default...");
                columns = 3;
            }
        }
        if (args.length = 3){
			// The third argument will be ignored and 3 will be used
			win=3; //for simplcity, we will only consider the case of 3 cells to win
        } 
		
        if (args.length > 3){
            System.out.println("Too many arguments. Only the first 3 are used.");
        } 
        
        game = new TicTacToeGame(lines, columns,win);

        while(game.getGameState() == GameState.PLAYING) {
        	
			// your code here
            if (game.getLevel() % 2 == 0){
                System.out.println("X to play: ");
            }
            else {
                System.out.println("O to play: ");
            }

            int cellIndex = Integer.parseInt(console.readLine());
            cellIndex = cellIndex - 1; // need to decrease by one because index starts from 0 

            int maxCells = (lines*columns)-1;

            if (cellIndex < 0 || cellIndex > maxCells) System.out.println("The value should be between 1 and" + maxCells); //ensure user doesn't enter a index out of range
            
            
            else if (game.valueAt(cellIndex) != CellValue.EMPTY) System.out.println("This cell has already been played"); //Ensure the cell is empty 
            
            else game.play(cellIndex); // play the game
            

			
        }
        System.out.println(game);
        System.out.println("Result: " + game.getGameState());
        if (game.getGameState().equals(GameState.XWIN)) System.out.println("X WINS!");
        else if (game.getGameState().equals(GameState.OWIN)) System.out.println("O WINS!");
        else if (game.getGameState().equals(GameState.DRAW)) System.out.println("DRAW"); 
        
        

    }

}