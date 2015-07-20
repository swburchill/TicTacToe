package ensf409.lab09.TicTacToe;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import android.graphics.*;

public class BoardActivity extends Activity
{
	private Button BoardButton0;
	private Button BoardButton1;
	private Button BoardButton2;
	private Button BoardButton3;
	private Button BoardButton4;
	private Button BoardButton5;
	private Button BoardButton6;
	private Button BoardButton7;
	private Button BoardButton8;
	private TextView RoundNumber;
	private TextView WhosTurn;
	private AI ai;
	boolean P1HintPlayed = false;
	boolean P2HintPlayed = false;
	boolean useHintAI = false; // prevent hint ai from running
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.board);
        
        // setup the buttons for the board for player presses
        BoardButton0 = (Button)findViewById(R.id.BoardButton0);
        BoardButton1 = (Button)findViewById(R.id.BoardButton1);
        BoardButton2 = (Button)findViewById(R.id.BoardButton2);
        BoardButton3 = (Button)findViewById(R.id.BoardButton3);
        BoardButton4 = (Button)findViewById(R.id.BoardButton4);
        BoardButton5 = (Button)findViewById(R.id.BoardButton5);
        BoardButton6 = (Button)findViewById(R.id.BoardButton6);
        BoardButton7 = (Button)findViewById(R.id.BoardButton7);
        BoardButton8 = (Button)findViewById(R.id.BoardButton8);
        ai=new AI();
        RoundNumber = (TextView)findViewById(R.id.BoardEditTurn);
        WhosTurn = (TextView)findViewById(R.id.BoardTextTurn);
               
        //all buttons are tied to one listener 'boardButtonListener'
        //to decide which button to put X/O is decided within the listener
        //This is done to make sure that one square update function isn't written 9 times over for each button.
        BoardButton0.setOnClickListener(boardButtonListener);
        BoardButton1.setOnClickListener(boardButtonListener);
        BoardButton2.setOnClickListener(boardButtonListener);
        BoardButton3.setOnClickListener(boardButtonListener);
        BoardButton4.setOnClickListener(boardButtonListener);
        BoardButton5.setOnClickListener(boardButtonListener);
        BoardButton6.setOnClickListener(boardButtonListener);
        BoardButton7.setOnClickListener(boardButtonListener);
        BoardButton8.setOnClickListener(boardButtonListener);
        clearBoard();            
    }
    
    private OnClickListener boardButtonListener = new OnClickListener()
    {
    		public void onClick(View v) 
			{
    			//v= the button that was clicked.
    			Button thisButton=(Button) findViewById(v.getId());
				//if Player 1's turn set that square to X. 
				//NOTE: AI classe's grid coordinates correspond to different button#s coordinates, hence the getBoardCoordinate function.
				if(Referee.turnNumber==1)
				{		
					P1HintPlayed = true;
					P2HintPlayed = true;
					thisButton.setText("X");
					thisButton.setTextColor(0xFF00FF00);
					ai.set_value(getBoardCoordinate(thisButton), 'X');
					thisButton.setClickable(false);
					
					int win=checkWin();
					if(win==1||win==-1)
					{
						showResults(v);
						return;
					}
					
					Referee.switchTurn();
					if(Referee.turnNumber == 1)
					{
						WhosTurn.setText("Player 1's Turn");
					}
					else
					{
						WhosTurn.setText("Player 2's Turn");
					}
					
					if(Settings.P2AI)
					{
						aiTurn(v);
						if(Referee.winner!=0)
							return;
						Referee.RoundCounter++;
						RoundNumber.setText(" "+Referee.RoundCounter);
						
						if(Settings.P1Hints == true)
						{
							aiTurn(v, Referee.turnNumber);
							if(Referee.winner!=0)
								return;
							while(P1HintPlayed == true)
							{
								aiTurn(v);
								if(Referee.winner!=0)
									return;
								Referee.RoundCounter++;
								RoundNumber.setText(" "+Referee.RoundCounter);	
								aiTurn(v, Referee.turnNumber);
								if(Referee.winner!=0)
									return;
							} 
						}  
					}
					
				    if(Settings.P2Hints == true)
					{
				    	if(Settings.P1Hints == true)
						{
							do
							{
								aiTurn(v, Referee.turnNumber);
								if(Referee.winner!=0)
									return;
							}
							while(P1HintPlayed == true && P2HintPlayed == true);
						}
						else
						{
							aiTurn(v, Referee.turnNumber);
							if(Referee.winner!=0)
								return;
						}
					}					
				}
				
				//human p2
				else if(Referee.turnNumber==2 && !Settings.P2AI)
				{
					P1HintPlayed = true;
					P2HintPlayed = true;
					thisButton.setText("O");
					thisButton.setTextColor(0xFFFFFF00);
					ai.set_value(getBoardCoordinate(thisButton), 'O');
					thisButton.setClickable(false);
					
					int win=checkWin();
					if(win==1||win==-1)
					{
						showResults(v);
						return;
					}
					
					Referee.RoundCounter++;
					RoundNumber.setText(" "+Referee.RoundCounter);
					
					Referee.switchTurn();
					if(Referee.turnNumber == 1)
					{
						WhosTurn.setText("Player 1's Turn");
					}
					else
					{
						WhosTurn.setText("Player 2's Turn");
					}
					
				    if(Settings.P1Hints == true)
					{
				    	if(Settings.P2Hints == true)
						{
							do
							{
								aiTurn(v, Referee.turnNumber);
								if(Referee.winner!=0)
									return;
							}
							while(P1HintPlayed == true && P2HintPlayed == true);
						}
						else
						{
							aiTurn(v, Referee.turnNumber);
							if(Referee.winner!=0)
									return;
						}
					}    
				}
			}
    };
    
    public void aiTurn(View v)
    {
    	char playedAt=' ';
    	if(Settings.AILevel==0)
    		playedAt=ai.AI_easy('O');
    	else if(Settings.AILevel==1)
    		playedAt=ai.AI_medium('O');
    	else if(Settings.AILevel==2)
    		playedAt=ai.AI_hard('O');
    	else if(Settings.AILevel==3)
    		playedAt=ai.AI_impossible('O');
    		
    	Button thisButton=getButtonPlayedAt(playedAt);
    	thisButton.setText("O");
    	thisButton.setTextColor(0xFFFFFF00);
    	thisButton.setClickable(false);
    	int win=checkWin();
    	if(win==1||win==-1)
    	{
    		showResults(v);
    		return;
    	}
    	
    	Referee.switchTurn();
    	if(Referee.turnNumber == 1)
		{
			WhosTurn.setText("Player 1's Turn");
		}
		else
		{
			WhosTurn.setText("Player 2's Turn");
		}
    }
    
    public void aiTurn(View v, int turn)
    {
    	char playedAt=' ';
    	if(Settings.P2Hints == true && Referee.turnNumber == 2)
    	{
    		playedAt = ai.AI_Hint('O');
    		P2HintPlayed = true;
    	}
    	else if(Settings.P1Hints == true && Referee.turnNumber == 1)
    	{
    		playedAt = ai.AI_Hint('X');
    		P1HintPlayed = true;
    	}
    	if(playedAt != 'X' && playedAt != 'O')
    	{
    		Button thisButton=getButtonPlayedAt(playedAt);
    		if(Referee.turnNumber == 2)
    		{
    			thisButton.setText("O");
    			thisButton.setTextColor(0xFFFFFF00);
    		}
    		else
    		{
    			thisButton.setText("X");
    			thisButton.setTextColor(0xFF00FF00);
    		}
    		thisButton.setClickable(false);
    		int win=checkWin();
    		if(win==1||win==-1)
    		{
    			showResults(v);
    			return;
    		}
    		Referee.switchTurn();
    		if(Referee.turnNumber == 1)
			{
				WhosTurn.setText("Player 1's Turn");
			}
			else
			{
				WhosTurn.setText("Player 2's Turn");
			}
    	}
    	else if(playedAt == 'X')
    	{
    		P1HintPlayed = false;
    	}
    	else if(playedAt == 'O')
    	{
    		P2HintPlayed = false;
    	}
    }
    
    public Button getButtonPlayedAt (char c)
    {
    	if(c=='7')//if coordinate played at ==7, return button 0
    		return (Button) findViewById(0x7f06000d);
    	else if(c=='8')//if coordinate played at == 8 , return button 1
    		return (Button) findViewById(0x7f06000e);
    	else if(c=='9')
    		return (Button) findViewById(0x7f06000f);
    	else if(c=='4')
    		return (Button) findViewById(0x7f060010);
    	else if(c=='5')
    		return (Button) findViewById(0x7f060011);
    	else if(c=='6')
    		return (Button) findViewById(0x7f060012);
    	else if(c=='1')
    		return (Button) findViewById(0x7f060013);
    	else if(c=='2')
    		return (Button) findViewById(0x7f060014);
    	else if(c=='3')
    		return (Button) findViewById(0x7f060015);
    	
    	return (Button) findViewById(0x7f060015);//should not get here ever  	
    }
    
    public char getBoardCoordinate(Button thisButton)
    {
    	if(thisButton.getId()==0x7f06000d)//if button 0, return coordinate 7
    		return '7';
    	else if(thisButton.getId()==0x7f06000e)//if button 1, return coordinate 8 ...
    		return '8';
    	else if(thisButton.getId()==0x7f06000f)
    		return '9';
    	else if(thisButton.getId()==0x7f060010)
    		return '4';
    	else if(thisButton.getId()==0x7f060011)
    		return '5';
    	else if(thisButton.getId()==0x7f060012)
    		return '6';
    	else if(thisButton.getId()==0x7f060013)
    		return '1';
    	else if(thisButton.getId()==0x7f060014)
    		return '2';
    	else if(thisButton.getId()==0x7f060015)//if button 8, return coordinate 3
    		return '3';
    	else return 'E';//should not get here
    }
    
    public void showResults(View v)
    {
    	Intent myIntent=new Intent(v.getContext(),ResultsActivity.class);
		startActivityForResult(myIntent,0);
		finish();
    }
    
    //Call AI checkVictory function. Returns 1 for victory,-1 for tie, 0 to continue game. 
    //In case of victory/tie, it sets the winner status in Referee class.
    public int checkWin()
    {
    	char result=ai.check_victory();
    	if(result=='X')
    	{
    		Referee.winner=1;
    		Referee.P1WinCount++;
    		return 1;
    	}
    	else if(result=='O')
    	{
    		Referee.winner=2;
    		Referee.P2WinCount++;
    		return 1;
    	}
    	else if(result=='t')
    	{
    		Referee.winner=-1;
    		return -1;
    	}
    	else
    	{
    		return 0;
    	}
    }
    //clears all text, and sets all buttons clickable
    public void clearBoard()
    {
    	BoardButton0.setText(" ");
        BoardButton1.setText(" ");
        BoardButton2.setText(" ");
        BoardButton3.setText(" ");
        BoardButton4.setText(" ");
        BoardButton5.setText(" ");
        BoardButton6.setText(" ");
        BoardButton7.setText(" ");
        BoardButton8.setText(" ");
        
        BoardButton0.setClickable(true);
        BoardButton1.setClickable(true);
        BoardButton2.setClickable(true);
        BoardButton3.setClickable(true);
        BoardButton4.setClickable(true);
        BoardButton5.setClickable(true);
        BoardButton6.setClickable(true);
        BoardButton7.setClickable(true);
        BoardButton8.setClickable(true);
        Referee.RoundCounter = 1;
        WhosTurn.setText("Player 1's Turn");
        RoundNumber.setText(" "+Referee.RoundCounter);    
    }
}
