package ensf409.lab09.TicTacToe;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.graphics.*;

public class ResultsActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results);
        Referee.turnNumber=1;//reset turn number to player 1
        
    	// setup buttons for results
        Button Replay = (Button)findViewById(R.id.ResultsButtonReplay);
        Button ResultsToMenu = (Button)findViewById(R.id.ResultsButtonMenu);
        Button ResultsToExit = (Button)findViewById(R.id.ResultsButtonExit);
        // setup text fields for results
        TextView Winner = (TextView)findViewById(R.id.ResultsEditWinner);
        TextView P1Wins = (TextView)findViewById(R.id.ResultsEditP1);
        TextView P2Wins = (TextView)findViewById(R.id.ResultsEditP2);
        
        if(Referee.winner>0)
        	Winner.setText("Player "+Referee.winner);
        else if(Referee.winner==-1)
        	Winner.setText("Game is a Tie");
        P1Wins.setText(" "+Referee.P1WinCount);//If you take out " ", it will crash because it cant convert a int to a CharSequence. 
        P2Wins.setText(" "+Referee.P2WinCount);
        Referee.winner=0;//reset winner
        
        // setup Menu Button to quit
        ResultsToExit.setOnClickListener(new View.OnClickListener() 
        {	
			public void onClick(View v) 
			{
				finish();
			}
		});
        
        ResultsToMenu.setOnClickListener(new View.OnClickListener() 
        {	
			public void onClick(View v) 
			{
				Intent mIntent=new Intent(v.getContext(), TicTacToeActivity.class);
        		startActivityForResult(mIntent,0);
				finish();
			}
		});
        
        Replay.setOnClickListener(new View.OnClickListener() 
        {	
			public void onClick(View v) 
			{
				Intent mIntent=new Intent(v.getContext(), BoardActivity.class);
        		startActivityForResult(mIntent,0);
				finish();
			}
		});      
    }
}
