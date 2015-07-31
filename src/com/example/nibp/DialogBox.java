package com.example.nibp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

public class DialogBox extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		Intent i = getIntent();
		char c = i.getCharExtra("dialogResolver", 'q');
		
		switch(c){
		case 'c':
			setTitle("Cuff Pressure");
			setContentView(R.layout.dialog_box_cuff);
			break;
		case 's':
			setTitle("Systolic Pressure");
			setContentView(R.layout.dialog_box_systolic);
			break;
		case 'd':
			setTitle("Diastolic Pressure");
			setContentView(R.layout.dialog_box_diastolic);
			break;
		default:
			Toast.makeText(getApplicationContext(), "Sorry nothing for dialog box", Toast.LENGTH_SHORT).show();
		
		}
		
		
	}

}
