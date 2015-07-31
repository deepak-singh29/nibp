package com.example.nibp;

import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class NewUser extends ActionBarActivity {
	
	private static final String TAG = "New Screen";
	// xml attribute declaration
		EditText etName,etAge,etWeight,etHeight;
		RadioButton rbMale,rbFemale;
		String gender,name,age,height,weight;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_user);
		// xml file attribute
		etName = (EditText) findViewById(R.id.etName);
		etAge = (EditText) findViewById(R.id.etAge);
		etHeight = (EditText) findViewById(R.id.etHeight);
		etWeight = (EditText) findViewById(R.id.etWeight);
		rbMale = (RadioButton) findViewById(R.id.rbMale);
		rbFemale = (RadioButton) findViewById(R.id.rbFemale);
		
		clearForm();
		
		// database creation with table users(Name,Gender,Age,Height,Weight)
		AppSettings.myDb = openOrCreateDatabase("details", MODE_PRIVATE, null);
		AppSettings.myDb.execSQL("CREATE TABLE IF NOT EXISTS users(Name TEXT,Gender TEXT,Age INTEGER,Height INTEGER,Weight INTEGER);");
	}
	public void onClickbtSave(View view){
		if(rbFemale.isChecked())
			gender = rbFemale.getText().toString();
		else
			gender = rbMale.getText().toString();
		// SQLite code to save data into users table
		try{
			AppSettings.myDb.execSQL("INSERT INTO users VALUES("+"'"+etName.getText().toString()+"'"+","+"'"+gender+"'"+","+"'"+etAge.getText()+"'"+","+"'"+etHeight.getText()+"'"+","+"'"+etWeight.getText()+"'"+");");
			
			Toast.makeText(getApplicationContext(), "Succesful insertion", Toast.LENGTH_SHORT).show();
			Log.d(TAG, "Successful Insertion");
			clearForm();
			}catch(SQLException e){
				Log.e(TAG, "Exception while inserting");
				e.printStackTrace();
			}
		clearForm();
		Toast.makeText(getApplicationContext(), "New User created successfully", Toast.LENGTH_SHORT).show();
		// starting user activity
		Intent userIntent = new Intent(getApplicationContext(),Users.class);
		startActivity(userIntent);
		// can be also done 
		// write code which automatically redirects to user screen..........
		
	}
	public void clearForm(){
		etName.setText("");
		etAge.setText("");
		etHeight.setText("");
		etWeight.setText("");
	}
}
