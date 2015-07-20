package ensf409.lab09.TicTacToe;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.graphics.*;

public class AboutActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);

        // setup buttons for about
        Button AboutToMenu = (Button)findViewById(R.id.AboutButtonMenu);
        
        // setup About Button to Menu
        AboutToMenu.setOnClickListener(new View.OnClickListener()
        {
        	public void	onClick(View v)
        	{
        		finish();
        	}
        });
    }
}
