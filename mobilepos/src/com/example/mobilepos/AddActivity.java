package com.example.mobilepos;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AddActivity extends Activity  {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);
	
		
		// btnSave (Save)
        final Button save = (Button) findViewById(R.id.btnSave);
        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	// If Save Complete
            	if(SaveData())
            	{
                	// Open Form Main
                	Intent newActivity = new Intent(AddActivity.this,CatalogActivity.class);
                	startActivity(newActivity);            		
            	}
            }
        });
		
		
		// btnCancel (Cancel)
        final Button cancel = (Button) findViewById(R.id.btnCancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {        	
            	// Open Form Main
            	Intent newActivity = new Intent(AddActivity.this,CatalogActivity.class);
            	startActivity(newActivity);
            }
        });
        
	}
	
	public boolean SaveData()
	{
		// txtMemberID, txtName, txtTel
		final EditText tMemberID = (EditText) findViewById(R.id.txtMemberID);
		final EditText tName = (EditText) findViewById(R.id.txtName);
		final EditText tTel = (EditText) findViewById(R.id.txtTel);
				
		// Dialog
		final AlertDialog.Builder adb = new AlertDialog.Builder(this);
		AlertDialog ad = adb.create();
		
		// Check MemberID
		if(tMemberID.getText().length() == 0)
		{
            ad.setMessage("Please input [MemberID] ");
            ad.show();
            tMemberID.requestFocus();
            return false;
		}
		
		// Check Name
		if(tName.getText().length() == 0)
		{
            ad.setMessage("Please input [Name] ");
            ad.show();
            tName.requestFocus();
            return false;
		}	
		
		// Check Tel
		if(tTel.getText().length() == 0)
		{
            ad.setMessage("Please input [Tel] ");
            ad.show();
            tTel.requestFocus();
            return false;
		}		
		
		// new Class DB
		final DBClass myDb = new DBClass(this);
		
		// Check Data (MemberID exists)
		String arrData[] = myDb.SelectData(tMemberID.getText().toString());
    	if(arrData != null)
    	{
    		ad.setMessage("MemberID already exists!  ");
    		ad.show();
    		tMemberID.requestFocus();
   		 	return false; 
    	}
    		

    	
    	
    	
    	// Save Data
    	long saveStatus = myDb.InsertData(tMemberID.getText().toString(),
    			tName.getText().toString(),
    			tTel.getText().toString());
    	if(saveStatus <=  0)
    	{
            ad.setMessage("Error!! ");
            ad.show();
            return false;
    	}
    	
		 Toast.makeText(AddActivity.this,"Add Data Successfully. ",
	 		     Toast.LENGTH_SHORT).show();   
		
		return true;
	}
    
}