package ensf409.lab09.TicTacToe;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.graphics.*;

public class SettingsActivity extends Activity
{
    /** Called when the activity is first created. */
	//public Intent myIntent = new Intent();
	RadioButton P1HintOn ;
    RadioButton P1HintOff ;
    RadioButton P2IsAI ;
    RadioButton P2IsHuman;
    RadioButton P2HintOn;
    RadioButton P2HintOff;
    RadioButton P2Random ;
    RadioButton P2Blocking;
    RadioButton P2Smart;
    RadioButton P2Impossible;
    Button SettingsToMenu ;
	
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        
        // setup the buttons for settings
        P1HintOn = (RadioButton)findViewById(R.id.SettingsButtonP1HintOn);
      	P1HintOff = (RadioButton)findViewById(R.id.SettingsButtonP1HintOff);
        P2IsAI = (RadioButton)findViewById(R.id.SettingsButtonP2IsAI);
        P2IsHuman = (RadioButton)findViewById(R.id.SettingsButtonP2IsHuman);
        P2HintOn = (RadioButton)findViewById(R.id.SettingsButtonP2HintOn);
        P2HintOff = (RadioButton)findViewById(R.id.SettingsButtonP2HintOff);
       	P2Random = (RadioButton)findViewById(R.id.SettingsButtonP2AIRandom);
     	P2Blocking = (RadioButton)findViewById(R.id.SettingsButtonP2AIBlocking);
       	P2Smart = (RadioButton)findViewById(R.id.SettingsButtonP2AISmart);
       	P2Impossible= (RadioButton) findViewById(R.id.SettingsButtonP2AIImpossible);
        SettingsToMenu = (Button)findViewById(R.id.SettingsButtonMenu);
        
        setButtons();
        Referee.P1WinCount=0;
        Referee.P2WinCount=0;
        // setup Settings Button to Menu
        SettingsToMenu.setOnClickListener(new View.OnClickListener()
        {
        	public void	onClick(View v)
        	{
        		finish();
        	}
        });
        
        P1HintOn.setOnClickListener(new View.OnClickListener() 
        {	
			public void onClick(View v) 
			{
				Settings.P1Hints=true;
				P1HintOff.setChecked(false);
			}
		});
        
        P2HintOn.setOnClickListener(new View.OnClickListener() 
        {
			public void onClick(View v) 
			{
				Settings.P2Hints=true;
				Settings.P2AI=false;
				P2IsAI.setChecked(false);
				P2IsHuman.setChecked(true);
				P2HintOff.setChecked(false);
				P2Random.setChecked(false);
	        	P2Blocking.setChecked(false);
	        	P2Smart.setChecked(false);
	        	P2Impossible.setChecked(false);
			}
		});
        
        P1HintOff.setOnClickListener(new View.OnClickListener() 
        {
			public void onClick(View v) 
			{
				Settings.P1Hints=false;
				P1HintOn.setChecked(false);
			}
		});
        
        P2HintOff.setOnClickListener(new View.OnClickListener() 
        {
			public void onClick(View v) 
			{
				Settings.P2Hints=false;
				Settings.P2AI=false;
				P2IsAI.setChecked(false);
				P2IsHuman.setChecked(true);
				P2HintOn.setChecked(false);
				P2Random.setChecked(false);
	        	P2Blocking.setChecked(false);
	        	P2Smart.setChecked(false);
	        	P2Impossible.setChecked(false);
			}
		});
       
        P2IsAI.setOnClickListener(new View.OnClickListener() 
        {
			public void onClick(View v) 
			{
				Settings.P2AI=true;
				Settings.P2Hints=false;
				P2Blocking.setChecked(true);
				P2Random.setChecked(false);
				P2Smart.setChecked(false);
				P2Impossible.setChecked(false);
				Settings.AILevel=1;
				P2IsHuman.setChecked(false);
				P2HintOn.setChecked(false);
				P2HintOff.setChecked(false);
			}
		});
        
        P2IsHuman.setOnClickListener(new View.OnClickListener() 
        {
			public void onClick(View v) 
			{
				Settings.P2AI=false;
				Settings.P2Hints=false;
				P2HintOn.setChecked(false);
				P2HintOff.setChecked(true);
				P2IsAI.setChecked(false);
				P2Random.setChecked(false);
	        	P2Blocking.setChecked(false);
	        	P2Smart.setChecked(false);
	        	P2Impossible.setChecked(false);
			}
		});
        
       P2Random.setOnClickListener(new View.OnClickListener() 
        {
			public void onClick(View v) 
			{
				Settings.AILevel=0;
				P2Blocking.setChecked(false);
				P2Smart.setChecked(false);
				P2Impossible.setChecked(false);
				Settings.P2AI=true;
				Settings.P2Hints=false;
				P2IsAI.setChecked(true);
				P2IsHuman.setChecked(false);
				P2HintOn.setChecked(false);
				P2HintOff.setChecked(false);
			}
		});
        
        P2Blocking.setOnClickListener(new View.OnClickListener() 
        {
			public void onClick(View v) 
			{
				Settings.AILevel=1;
				P2Random.setChecked(false);
				P2Smart.setChecked(false);
				P2Impossible.setChecked(false);
				Settings.P2AI=true;
				Settings.P2Hints=false;
				P2IsAI.setChecked(true);
				P2IsHuman.setChecked(false);
				P2HintOn.setChecked(false);
				P2HintOff.setChecked(false);
			}
		});
        
        P2Smart.setOnClickListener(new View.OnClickListener() 
        {
			public void onClick(View v) 
			{
				Settings.AILevel=2;
				P2Blocking.setChecked(false);
				P2Random.setChecked(false);
				P2Impossible.setChecked(false);
				Settings.P2AI=true;
				Settings.P2Hints=false;
				P2IsAI.setChecked(true);
				P2IsHuman.setChecked(false);
				P2HintOn.setChecked(false);
				P2HintOff.setChecked(false);
			}
		});
        
        P2Impossible.setOnClickListener(new View.OnClickListener() 
        {
			public void onClick(View v) 
			{
				Settings.AILevel=3;
				P2Blocking.setChecked(false);
				P2Smart.setChecked(false);
				P2Random.setChecked(false);
				Settings.P2AI=true;
				Settings.P2Hints=false;
				P2IsAI.setChecked(true);
				P2IsHuman.setChecked(false);
				P2HintOn.setChecked(false);
				P2HintOff.setChecked(false);
			}
		});
        
    }
    
    private void setButtons()
    {
        // setup button conditions
        if(Settings.P1Hints == true)
        {
        	P1HintOn.setChecked(true);
        	P1HintOff.setChecked(false);
        }
        else
        {
        	P1HintOn.setChecked(false);
        	P1HintOff.setChecked(true);
        }
        if(Settings.P2AI == false)
        {
        	P2IsAI.setChecked(false);
        	P2IsHuman.setChecked(true);
        	P2Random.setChecked(false);
        	P2Blocking.setChecked(false);
        	P2Smart.setChecked(false);
        	P2Impossible.setChecked(false);
        	if(Settings.P2Hints == true)
        	{
        		P2HintOn.setChecked(true);
        		P2HintOff.setChecked(false);
        	}
        	else
        	{
        		P2HintOn.setChecked(false);
        		P2HintOff.setChecked(true);
        	}
        }
        else
        {
        	P2IsAI.setChecked(true);
        	P2IsHuman.setChecked(false);
        	P2HintOn.setChecked(false);
    		P2HintOff.setChecked(false);
            if(Settings.AILevel == 0)
            {
            	P2Random.setChecked(true);
            	P2Blocking.setChecked(false);
            	P2Smart.setChecked(false);
            	P2Impossible.setChecked(false);
            }
            else if(Settings.AILevel == 1)
            {
            	P2Random.setChecked(false);
            	P2Blocking.setChecked(true);
            	P2Smart.setChecked(false);
            	P2Impossible.setChecked(false);
            }
            else if(Settings.AILevel == 2)
            {
            	P2Random.setChecked(false);
            	P2Blocking.setChecked(false);
            	P2Smart.setChecked(true);
            }
            else
            {
            	P2Random.setChecked(false);
            	P2Blocking.setChecked(false);
            	P2Smart.setChecked(false);
            	P2Impossible.setChecked(true);
            }
        }    	
    }    
}
    
