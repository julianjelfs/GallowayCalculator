package jelfs.android.gallowaycalculator;

import java.text.DecimalFormat;

import jelfs.android.gallowaycalculator.R;
import android.os.Bundle;
import android.os.IBinder;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	EditText distance;
	EditText runtime;
	EditText runpace;
	EditText walktime;
	EditText walkpace;
	EditText stoptime;
	TextView result;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		distance = (EditText) findViewById(R.id.distance);
		runtime = (EditText) findViewById(R.id.runtime);
		runpace = (EditText) findViewById(R.id.runpace);
		walktime = (EditText) findViewById(R.id.walktime);
		walkpace = (EditText) findViewById(R.id.walkpace);
		stoptime = (EditText) findViewById(R.id.stoptime);
		result = (TextView) findViewById(R.id.result);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	public void calculate(View view){
		hideKeyboardOnButtonClick(distance.getApplicationWindowToken());	
		double d = Float.parseFloat(distance.getText().toString());
		double rt = Float.parseFloat(runtime.getText().toString());
		double rp = Float.parseFloat(runpace.getText().toString());
		double wt = Float.parseFloat(walktime.getText().toString());
		double wp = Float.parseFloat(walkpace.getText().toString());
		double st = Float.parseFloat(stoptime.getText().toString());
		
		double ld = ((rt/60)/rp) + ((wt/60)/wp);
		double laps = d / ld;
		double tt = ((rt + wt) * laps) + (st * 60);
		
		DecimalFormat df = new DecimalFormat("00");
	    double hours = Math.floor(tt/3600);
	    double minutes =  Math.floor((tt/60) - (hours * 60));
	    double seconds = Math.floor(tt - (minutes * 60) - (hours * 3600));
	    
	    result.setText(df.format(hours)+ ":" + df.format(minutes) + ":" + df.format(seconds));
	}
	
	private void hideKeyboardOnButtonClick(IBinder token){
		InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(token, 0);
	}

}
