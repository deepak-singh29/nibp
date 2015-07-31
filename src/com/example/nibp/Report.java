package com.example.nibp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Report extends ActionBarActivity {
	TextView tvDate,tvName,tvAge,tvGender,tvHeight,tvWeight,tvBP;
	//Reading and correcting the month-value
    final Calendar c = Calendar.getInstance();
    int month = c.get(Calendar.MONTH)+1;
    String date;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.report);
		tvDate = (TextView) findViewById(R.id.tvDate);
		tvName = (TextView) findViewById(R.id.tvName);
		tvWeight = (TextView) findViewById(R.id.tvWeight);
		tvHeight = (TextView) findViewById(R.id.tvHeight);
		tvGender = (TextView) findViewById(R.id.tvGender);
		tvAge = (TextView) findViewById(R.id.tvAge);
		tvBP = (TextView) findViewById(R.id.tvBP);
		
		date = c.get(Calendar.DATE)+"/"+month+"/"+c.get(Calendar.YEAR)+"   "+c.get(Calendar.HOUR)+":"+c.get(Calendar.MINUTE)+":"+c.get(Calendar.SECOND);
		tvDate.setText(date);
		tvName.setText(AppSettings.Name);
		tvWeight.setText(AppSettings.Weight+"  "+"(Kg)");
		tvHeight.setText(AppSettings.Height+"  "+"(cms)");
		tvGender.setText(AppSettings.Gender);
		tvAge.setText(AppSettings.Age);
		tvBP.setText(AppSettings.bloodPressure);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.common_menu, menu);
		// can also use report menu
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch (item.getItemId()) {
		
		case R.id.ScreenShot:
			Bitmap bitmap = takeScreenshot();
		    saveBitmap(bitmap);
		    Toast.makeText(getApplicationContext(),"Screenshot saved in"+"   \""+AppSettings.directory+"\"  ", Toast.LENGTH_LONG).show();
		    break;
		case R.id.mail:
			Intent mailIntent = new Intent(getApplicationContext(),Mail.class);
			startActivity(mailIntent);
			Toast.makeText(getApplicationContext(), "please fill details to send mail", Toast.LENGTH_SHORT).show();
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
//  methods for screenshot
  public Bitmap takeScreenshot() {
		   View rootView = findViewById(android.R.id.content).getRootView();
		   rootView.setDrawingCacheEnabled(true);
		   return rootView.getDrawingCache();
		}
//methods for screenshot
	public void saveBitmap(Bitmap bitmap) {
		        
      File imgDirectory = new File(AppSettings.directory);
  	imgDirectory.mkdirs();
      AppSettings.lastImage = AppSettings.directory + "Nibp_Report_" + c.get(Calendar.DAY_OF_MONTH)+"-"+month+"-"+c.get(Calendar.YEAR) + "_" + c.get(Calendar.HOUR_OF_DAY)+"-"+c.get(Calendar.MINUTE) + "-" + c.get(Calendar.SECOND) + ".png";
		
	    File imagePath = new File(AppSettings.lastImage);
	    FileOutputStream fos;
	    try {
	        fos = new FileOutputStream(imagePath);
	        bitmap.compress(CompressFormat.JPEG, 100, fos);
	        fos.flush();
	        fos.close();
	    } catch (FileNotFoundException e) {
	        Log.e("GREC", e.getMessage(), e);
	    } catch (IOException e) {
	        Log.e("GREC", e.getMessage(), e);
	    }
	    
	}
}
