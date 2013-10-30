package com.example.mobilepos;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddActivity extends Activity {

	private Button btnScan;
    private TextView txtResult;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);

		 //connect btnScan and txtResult to View
        btnScan = (Button)findViewById(R.id.buttonScan);
        txtResult = (TextView)findViewById(R.id.txtMemberID);
 
        //define Event when press btnScan
        btnScan.setOnClickListener(new OnClickListener() {
 
            public void onClick(View v) {
                // TODO Auto-generated method stub
                try {
                    //define intent to call Barcode Scanner
                    Intent intent = new Intent("com.google.zxing.client.android.SCAN");
                    //send Mode to Scan with Barcode Scanner
                    intent.putExtra("SCAN_MODE", "ONE_D_MODE");
                    
                
                    //start Activity from intent which define by requestCode = 0
                    startActivityForResult(intent, 0);
                } catch (Exception e) {
                    // TODO: handle exception
                    //if don't install Barcode Scanner there will push a message Please Install Barcode Scanner
                    Toast.makeText(getBaseContext(),"Please Install Barcode Scanner",Toast.LENGTH_SHORT).show();
                }
 
            }
        });
    }
 
    //when complete the scan it will call function onActivityResult
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        // TODO Auto-generated method stub
        if (requestCode == 0) //test requestCode and Barcode Scanner are same with value that we got
        {
            if (resultCode == RESULT_OK) //if Barcode Scanner complete
            {
                    //Receive Barcode Scanner that got by scan
                    String contents = intent.getStringExtra("SCAN_RESULT");
                    //Receieve Barcode Scanner that got each type
                    String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
                    //show in txtResult
                    txtResult.setText("" + contents);
            }
        }
    
		
		
		// btnSave (Save)
		final Button save = (Button) findViewById(R.id.btnSave);
		save.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				// If Save Complete
				if (SaveData()) {
					// Open Form Main
					Intent newActivity = new Intent(AddActivity.this,
							CatalogActivity.class);
					startActivity(newActivity);
				}
			}
		});

		// btnCancel (Cancel)
		final Button cancel = (Button) findViewById(R.id.btnCancel);
		cancel.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// Open Form Main
				Intent newActivity = new Intent(AddActivity.this,
						CatalogActivity.class);
				startActivity(newActivity);
			}
		});

	}

	public boolean SaveData() {
		// txtMemberID, txtName, txtTel
		final EditText tMemberID = (EditText) findViewById(R.id.txtMemberID);
		final EditText tName = (EditText) findViewById(R.id.txtName);
		final EditText tTel = (EditText) findViewById(R.id.txtTel);

		// Dialog
		final AlertDialog.Builder adb = new AlertDialog.Builder(this);
		AlertDialog ad = adb.create();

		// Check MemberID
		if (tMemberID.getText().length() == 0) {
			ad.setMessage("Please input [ItemID] ");
			ad.show();
			tMemberID.requestFocus();
			return false;
		}

		// Check Name
		if (tName.getText().length() == 0) {
			ad.setMessage("Please input [Name] ");
			ad.show();
			tName.requestFocus();
			return false;
		}

		// Check Tel
		if (tTel.getText().length() == 0) {
			ad.setMessage("Please input [Price] ");
			ad.show();
			tTel.requestFocus();
			return false;
		}

		// new Class DB
		final DBClass myDb = new DBClass(this);

		// Check Data (MemberID exists)
		String arrData[] = myDb.SelectData(tMemberID.getText().toString());
		if (arrData != null) {
			ad.setMessage("ItemID already exists!  ");
			ad.show();
			tMemberID.requestFocus();
			return false;
		}

		// Save Data
		long saveStatus = myDb.InsertData(tMemberID.getText().toString(), tName
				.getText().toString(), tTel.getText().toString());
		if (saveStatus <= 0) {
			ad.setMessage("Error!! ");
			ad.show();
			return false;
		}

		Toast.makeText(AddActivity.this, "Add Data Successfully. ",
				Toast.LENGTH_SHORT).show();

		return true;
	}

}