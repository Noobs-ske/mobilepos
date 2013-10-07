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
		// txtIMEI, txtName, txtTel
		final EditText tIMEI = (EditText) findViewById(R.id.txtIMEI);
		final EditText tModel = (EditText) findViewById(R.id.txtModel);
		final EditText tPrice = (EditText) findViewById(R.id.txtPrice);
				
		// Dialog
		final AlertDialog.Builder adb = new AlertDialog.Builder(this);
		AlertDialog ad = adb.create();
		
		// Check IMEI
		if(tIMEI.getText().length() == 0)
		{
            ad.setMessage("Please input [IMEI] ");
            ad.show();
            tIMEI.requestFocus();
            return false;
		}
		
		// Check Model
		if(tModel.getText().length() == 0)
		{
            ad.setMessage("Please input [Model] ");
            ad.show();
            tModel.requestFocus();
            return false;
		}	
		
		// Check Price
		if(tPrice.getText().length() == 0)
		{
            ad.setMessage("Please input [Price] ");
            ad.show();
            tPrice.requestFocus();
            return false;
		}		
		
		// new Class DB
		final DBClass myDb = new DBClass(this);
		
		// Check Data (IMEI exists)
		String arrData[] = myDb.SelectData(tIMEI.getText().toString());
    	if(arrData != null)
    	{
    		ad.setMessage("IMEI already exists!  ");
    		ad.show();
    		tIMEI.requestFocus();
   		 	return false; 
    	}
    		
    	// Save Data
    	long saveStatus = myDb.InsertData(tIMEI.getText().toString(),
    			tModel.getText().toString(),
    			tPrice.getText().toString());
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