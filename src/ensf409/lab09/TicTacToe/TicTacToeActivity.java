package ensf409.lab09.TicTacToe;

import android.app.Activity;
import android.app.LocalActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import android.graphics.*;
import android.app.ActivityGroup;

public class TicTacToeActivity extends Activity 
{
    /** Called when the activity is first created. */
	// static LocalActivityManager lam;
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // setup buttons for Menu
        Button Play = (Button)findViewById(R.id.MenuButtonPlay);
        Button MenuToSettings = (Button)findViewById(R.id.MenuButtonSettings);
        Button MenuToExit = (Button)findViewById(R.id.MenuButtonExit);
        Button MenuToAbout = (Button)findViewById(R.id.MenuButtonAbout);

        // setup Menu Button to about
        MenuToAbout.setOnClickListener(new View.OnClickListener() 
        {	
			public void onClick(View v) 
			{
				Intent mIntent=new Intent(v.getContext(), AboutActivity.class);
        		startActivityForResult(mIntent, 0);
			}
		});
        
        // setup Menu Button to play
        Play.setOnClickListener(new View.OnClickListener() 
        {	
			public void onClick(View v) 
			{
				Intent mIntent=new Intent(v.getContext(), BoardActivity.class);
        		startActivityForResult(mIntent,0);       	
        		//if(Referee.quit == true)
        			finish();	
			}
		});
        
        // setup Menu Button to settings view
        MenuToSettings.setOnClickListener(new View.OnClickListener()
        {
        	public void	onClick(View v)
        	{
        		Intent myIntent=new Intent(v.getContext(), SettingsActivity.class);
        		startActivityForResult(myIntent, 0);
        	}
        });
          
        // setup Menu Button to quit
        MenuToExit.setOnClickListener(new View.OnClickListener() 
        {	
			public void onClick(View v) 
			{
				finish();
			}
		});     
    }     
}