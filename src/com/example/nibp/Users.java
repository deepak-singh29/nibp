package com.example.nibp;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.SQLException;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Users extends ActionBarActivity {
private static final String TAG = "Users Screen";
Button usr1,usr2,usr3,usr4;
Cursor resultSet;
boolean anyBtnClicked = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.users);
		usr1 = (Button)findViewById(R.id.btnUser1);
		usr2 = (Button)findViewById(R.id.btnUser2);
		usr3 = (Button)findViewById(R.id.btnUser3);
		usr4 = (Button)findViewById(R.id.btnUser4);
		usr1.setVisibility(View.INVISIBLE);
		usr2.setVisibility(View.INVISIBLE);
		usr3.setVisibility(View.INVISIBLE);
		usr4.setVisibility(View.INVISIBLE);
		try{
			// database opening or creation
			AppSettings.myDb = openOrCreateDatabase("details", MODE_PRIVATE, null);
			resultSet = AppSettings.myDb.rawQuery("Select * from users",null);
			Log.d(TAG, "Successfully fetched last record");
			}catch(NullPointerException e){
				e.printStackTrace();
				Toast.makeText(getApplicationContext(), "perhephs no users add new users ", Toast.LENGTH_SHORT).show();
			}
			catch(CursorIndexOutOfBoundsException e){
				Toast.makeText(getApplicationContext(), "Cursor Index out of bound ", Toast.LENGTH_SHORT).show();
			}
		catch(SQLiteException e){
			Toast.makeText(getApplicationContext(), "No Users please create new users ", Toast.LENGTH_SHORT).show();
		}
		
		
		// write code to fetch user names from Database and assign to user button
		try {
			resultSet.moveToFirst();
			usr1.setText(resultSet.getString(0));
			usr1.setVisibility(View.VISIBLE);
			if(resultSet.moveToNext()){
				usr2.setText(resultSet.getString(0));
				usr2.setVisibility(View.VISIBLE);
			}
			if(resultSet.moveToNext()){
				usr3.setText(resultSet.getString(0));
				usr3.setVisibility(View.VISIBLE);
			}
			if(resultSet.moveToNext()){
				usr4.setText(resultSet.getString(0));
				usr4.setVisibility(View.VISIBLE);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(getApplicationContext(), "Exception in curso movement", Toast.LENGTH_SHORT).show();
		}
		// setting selected user data in appSettings for report and starting the mainscreen activity
		
		
		// opening of mainscreen on any user button click
		try{
		resultSet.moveToFirst();
		usr1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(resultSet.moveToPosition(0))
				{
					AppSettings.Name = resultSet.getString(0);
					AppSettings.Gender = resultSet.getString(1);
					AppSettings.Age = resultSet.getString(2);
					AppSettings.Height = resultSet.getString(3);
					AppSettings.Weight = resultSet.getString(4);
				}
				userSelect();
			}
		});
		usr2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(resultSet.moveToPosition(1))
				{
					AppSettings.Name = resultSet.getString(0);
					AppSettings.Gender = resultSet.getString(1);
					AppSettings.Age = resultSet.getString(2);
					AppSettings.Height = resultSet.getString(3);
					AppSettings.Weight = resultSet.getString(4);
				}
				userSelect();
			}
		});
		usr3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(resultSet.moveToPosition(2))
				{
					AppSettings.Name = resultSet.getString(0);
					AppSettings.Gender = resultSet.getString(1);
					AppSettings.Age = resultSet.getString(2);
					AppSettings.Height = resultSet.getString(3);
					AppSettings.Weight = resultSet.getString(4);
				}
				userSelect();
			}
		});
		usr4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(resultSet.moveToPosition(3))
				{
					AppSettings.Name = resultSet.getString(0);
					AppSettings.Gender = resultSet.getString(1);
					AppSettings.Age = resultSet.getString(2);
					AppSettings.Height = resultSet.getString(3);
					AppSettings.Weight = resultSet.getString(4);
				}
				userSelect();
			}
		});
		}catch(NullPointerException e){
			e.printStackTrace();
			Toast.makeText(getApplicationContext(), "perhephs no users add new users ", Toast.LENGTH_SHORT).show();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		}

	public void textClick_ManageUsers(View view){
		Intent manageUser = new Intent(Users.this,ManageProfiles.class);
		startActivity(manageUser);
	}
	public void textClick_NewUser(View view){
		Intent newUser = new Intent(Users.this,NewUser.class);
		startActivity(newUser);
	}
	// function called after any user selection
	private void userSelect(){
		Toast.makeText(getApplicationContext(), "Welcome " +AppSettings.Name, Toast.LENGTH_SHORT).show();
		Intent mainScreen = new Intent(getApplicationContext(),MainScreen.class);
		startActivity(mainScreen);
	}
}
