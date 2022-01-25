/**
 * An enum class that defines the
 * values <b>PLAYING</b>,  <b>DRAW</b>,
 *  <b>XWIN</b> and <b>OWIN</b>
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */

public enum GameState{
	PLAYING, //  this game is ongoing
	DRAW,   // this game is a draw,
	XWIN,   // this game as been won by the first player
	OWIN    // this game as been won by the second player 
}