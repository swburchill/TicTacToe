package ensf409.lab09.TicTacToe;

public class Referee 
{
	public static int P1WinCount = 0;
	public static int P2WinCount = 0;
	public static int turnNumber = 1;//1 for Player1, 2 for Player2
	public static int RoundCounter = 1;
	public static int winner=0;//1 for Player1, 2 for Player 2, -1 for tie. 

	public static void switchTurn()
	{
		if(turnNumber==1)
			turnNumber=2;
		else if(turnNumber==2)
			turnNumber=1;
	}
}
