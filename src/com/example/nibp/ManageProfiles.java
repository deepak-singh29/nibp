package com.example.nibp;
//database with table users(Name,Gender,Age,Height,Weight)
import android.content.Intent;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.SQLException;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class ManageProfiles extends ActionBarActivity {
	private static final String TAG = "Manage Profiles";
	// xml attribute declaration
	EditText etName,etAge,etWeight,etHeight;
	RadioButton rbMale,rbFemale;
	String gender,name,age,height,weight;
	Cursor resultSet;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.manage_profiles);
		
		
		// xml file attribute
		etName = (EditText) findViewById(R.id.etName);
		etAge = (EditText) findViewById(R.id.etAge);
		etHeight = (EditText) findViewById(R.id.etHeight);
		etWeight = (EditText) findViewById(R.id.etWeight);
		rbMale = (RadioButton) findViewById(R.id.rbMale);
		rbFemale = (RadioButton) findViewById(R.id.rbFemale);
		/*if(rbMale.isChecked())
			gender = rbMale.getText().toString();
		if(rbFemale.isChecked())
			gender = rbFemale.getText().toString();*/
		clearForm();
		textEditable(false);
		// selecting some rows from database
		try{
			resultSet = AppSettings.myDb.rawQuery("Select * from users",null);
			resultSet.moveToFirst();
			displayData();
			Log.d(TAG, "Successfully fetched first record");
			}catch(CursorIndexOutOfBoundsException e){
				Toast.makeText(getApplicationContext(), "Cursor Index out of bound ", Toast.LENGTH_SHORT).show();
			}
	}
	
	public void onClickbtNext(View view){
		textEditable(false);
		if(resultSet.moveToNext()){
			displayData();
			}else{
				Toast.makeText(getApplicationContext(), " Sorry no further rows ", Toast.LENGTH_SHORT).show();
			}
		
	}
	
	public void onClickbtPrev(View view){
		textEditable(false);
		if(resultSet.moveToPrevious()){
			displayData();
		}else{
		Toast.makeText(getApplicationContext(), " Sorry no other rows ", Toast.LENGTH_SHORT).show();
		}
		
	}
	public void onClickbtEdit(View view){
		textEditable(true);
		// storing previous record
				name = etName.getText().toString();
				age = etAge.getText().toString();
				height = etHeight.getText().toString();
				weight = etWeight.getText().toString();
				if(rbMale.isChecked())
					gender = "Male";
				else
					gender = "Female";		
	}
	public void onClickbtSave(View view){
		
		// update using previous record data 
		//database with table users(Name,Gender,Age,Height,Weight)
		if(rbMale.isChecked())
			gender = "Male";
		else
			gender = "Female";
		try{
		AppSettings.myDb.execSQL("UPDATE users SET Name = "+"'"+etName.getText().toString()+"'"+","+"Gender = "+"'"+gender+"'"+","+"Age = "+"'"+etAge.getText()+"'"+","+"Height = "+"'"+etHeight.getText()+"'"+","+"Weight = "+"'"+etWeight.getText()+"'" +"WHERE Name = "+"'"+name+"'"+ "AND Age ="+"'"+age+"'"+";");
		}catch(SQLException e){
			Log.d(TAG,"SQL Exception while updated save");
		}
		// starting user activity
				Intent userIntent = new Intent(getApplicationContext(),Users.class);
				startActivity(userIntent);
	}
	public void onClickbtDelete(View view){
		// code to delete the current record
		try{	
			
			AppSettings.myDb.execSQL("DELETE FROM users WHERE Name = "+"'"+etName.getText().toString()+"'");	
			
			}catch(SQLException e){
				Toast.makeText(getApplicationContext(), "Exception to run delete command", Toast.LENGTH_SHORT).show();
			}
			Log.d(TAG, "Record deleted Succesfully");	
			Toast.makeText(getApplicationContext(), "User deleted succesfully ", Toast.LENGTH_SHORT).show();
		clearForm();
		// starting user activity
				Intent userIntent = new Intent(getApplicationContext(),Users.class);
				startActivity(userIntent);
		
	}
	public void clearForm(){
		etName.setText("");
		etAge.setText("");
		etHeight.setText("");
		etWeight.setText("");
	}
	public void textEditable(boolean flag){
		etName.setEnabled(flag);
		etName.setClickable(flag);
		etAge.setEnabled(flag);
		etAge.setClickable(flag);
		etHeight.setEnabled(flag);
		etHeight.setClickable(flag);
		etWeight.setEnabled(flag);
		etWeight.setClickable(flag);
	}
	public void displayData(){
		//database with table users(Name,Gender,Age,Height,Weight)
		String temp;
		etName.setText(resultSet.getString(0));
		temp = resultSet.getString(1);
		Toast.makeText(getApplicationContext(), "Gender "+temp, Toast.LENGTH_SHORT).show();
		etAge.setText(resultSet.getString(2));
		etHeight.setText(resultSet.getString(3));
		etWeight.setText(resultSet.getString(4));
		if(temp == "Male")
			rbMale.setSelected(true);
		else
			rbFemale.setSelected(true);
	}
	
	/*public void onRadioButtonClicked(View view) {
	    // Is the button now checked?
	    boolean checked = ((RadioButton) view).isChecked();
	    
	    // Check which radio button was clicked
	    switch(view.getId()) {
	        case R.id.rbFemale:
	            if (checked)
	            	gender = "Female";
	            break;
	        case R.id.rbMale:
	            if (checked)
	                gender = "Male";
	                
	            break;
	    }
	}*/
	
}
