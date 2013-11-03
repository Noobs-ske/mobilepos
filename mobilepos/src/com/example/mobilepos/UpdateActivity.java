package com.example.mobilepos;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update);

		// Read var from Intent
		Intent intent = getIntent();
		final String MemID = intent.getStringExtra("MemID");

		// Show Data
		ShowData(MemID);

		// btnSave (Save)
		final Button save = (Button) findViewById(R.id.btnSave);
		save.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// If Save Complete
				if (UpdateData(MemID)) {
					// Open Form ListUpdate
					Intent newActivity = new Intent(UpdateActivity.this,
							CatalogActivity.class);
					startActivity(newActivity);
				}
			}
		});

		// btnCancel (Cancel)
		final Button cancel = (Button) findViewById(R.id.btnCancel);
		cancel.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// Open Form ListUpdate
				Intent newActivity = new Intent(UpdateActivity.this,
						CatalogActivity.class);
				startActivity(newActivity);
			}
		});

	}

	public void ShowData(String MemID) {
		// txtMemberID, txtName, txtTel
		final TextView tItemID = (TextView) findViewById(R.id.txtItemID);
		final EditText tName = (EditText) findViewById(R.id.txtName);
		final EditText tQuantity = (EditText) findViewById(R.id.txtQuantity);
		final EditText tPrice = (EditText) findViewById(R.id.txtPrice);

		// new Class DB
		final DBClass myDb = new DBClass(this);

		// Show Data
		String arrData[] = myDb.SelectData(MemID);
		if (arrData != null) {
			tItemID.setText(arrData[0]);
			tName.setText(arrData[1]);
			tQuantity.setText(arrData[2]);
			tPrice.setText(arrData[3]);
		}

	}

	public boolean UpdateData(String MemID) {

		// txtName, txtTel
		final EditText tName = (EditText) findViewById(R.id.txtName);
		final EditText tQuantity = (EditText) findViewById(R.id.txtQuantity);
		final EditText tPrice = (EditText) findViewById(R.id.txtPrice);

		// Dialog
		final AlertDialog.Builder adb = new AlertDialog.Builder(this);
		AlertDialog ad = adb.create();

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

		// Save Data
		long saveStatus = myDb.UpdateData(MemID, tName.getText().toString(),
				tQuantity.getText().toString(), tPrice.getText().toString());
		if (saveStatus <= 0) {
			ad.setMessage("Error!! ");
			ad.show();
			return false;
		}

		Toast.makeText(UpdateActivity.this, "Update Data Successfully. ",
				Toast.LENGTH_SHORT).show();

		return true;

	}

}
