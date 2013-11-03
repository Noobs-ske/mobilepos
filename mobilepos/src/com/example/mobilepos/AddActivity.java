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
	private EditText tItemID;
	private EditText tName;
	private EditText tQuantity;
	private EditText tPrice;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);

		// connect btnScan and txtResult to View
		btnScan = (Button) findViewById(R.id.buttonScan);
		txtResult = (EditText) findViewById(R.id.txtItemID);

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

		// define Event when press btnScan
		btnScan.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					// define intent to call Barcode Scanner
					Intent intent = new Intent(
							"com.google.zxing.client.android.SCAN");
					// send Mode to Scan with Barcode Scanner
					intent.putExtra("SCAN_MODE", "ONE_D_MODE");

					// start Activity from intent which define by requestCode =
					// 0
					startActivityForResult(intent, 0);

				} catch (Exception e) {
					// TODO: handle exception
					// if don't install Barcode Scanner there will push a
					// message Please Install Barcode Scanner
					Toast.makeText(getBaseContext(),
							"Please Install Barcode Scanner",
							Toast.LENGTH_SHORT).show();
				}

			}
		});
	}

	// when complete the scan it will call function onActivityResult
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			Intent intent) {
		// TODO Auto-generated method stub
		if (requestCode == 0) // test requestCode and Barcode Scanner are same
								// with value that we got
		{
			if (resultCode == RESULT_OK) // if Barcode Scanner complete
			{
				// Receive Barcode Scanner that got by scan
				String contents = intent.getStringExtra("SCAN_RESULT");
				// Receieve Barcode Scanner that got each type
				String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
				// show in txtResult
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
		tItemID = (EditText) findViewById(R.id.txtItemID);
		tName = (EditText) findViewById(R.id.txtName);
		tQuantity = (EditText) findViewById(R.id.txtQuantity);
		tPrice = (EditText) findViewById(R.id.txtPrice);

		// Dialog
		final AlertDialog.Builder adb = new AlertDialog.Builder(this);
		AlertDialog ad = adb.create();

		// Check MemberID
		if (tItemID.getText().length() == 0) {
			ad.setMessage("Please input [ItemID] ");
			ad.show();
			tItemID.requestFocus();
			return false;
		}

		// Check Name
		if (tName.getText().length() == 0) {
			ad.setMessage("Please input [Name] ");
			ad.show();
			tName.requestFocus();
			return false;
		}

		// Check Quantity
		if (tQuantity.getText().length() == 0) {
			ad.setMessage("Please input [Quantity] ");
			ad.show();
			tQuantity.requestFocus();
			return false;
		}

		// Check Price
		if (tPrice.getText().length() == 0) {
			ad.setMessage("Please input [Price] ");
			ad.show();
			tPrice.requestFocus();
			return false;
		}

		// new Class DB
		final DBClass myDb = new DBClass(this);

		// Check Data (MemberID exists)
		String arrData[] = myDb.SelectData(tItemID.getText().toString());
		if (arrData != null) {
			ad.setMessage("ItemID already exists!  ");
			ad.show();
			tItemID.requestFocus();
			return false;
		}

		// Save Data
		long saveStatus = myDb.InsertData(tItemID.getText().toString(), tName
				.getText().toString(), tQuantity.getText().toString(), tPrice
				.getText().toString());
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