package com.example.mobilepos;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Button1(Sale Button)
		final Button btn_Sale = (Button) findViewById(R.id.button1);
		// Perform action on click
		btn_Sale.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				// Open Sale
				Intent newActivity = new Intent(MainActivity.this,
						SaleActivity.class);
				startActivity(newActivity);
				finish();

			}
		});

		// Button2(CatalogButton)
		final Button btn_Catalog = (Button) findViewById(R.id.button2);
		// Perform action on click
		btn_Catalog.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				// Open Catalog
				Intent newActivity = new Intent(MainActivity.this,
						CatalogActivity.class);
				startActivity(newActivity);
				finish();
			}
		});

		
		// Button4(HistoryButton)
		final Button btn_History = (Button) findViewById(R.id.button4);
		// Perform action on click
		btn_History.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				// Open History
				Intent newActivity = new Intent(MainActivity.this,
						HistoryActivity.class);
				startActivity(newActivity);
				finish();
			}
		});

		
		// Button5(catalogue)
		final Button btn_cataloguelist = (Button) findViewById(R.id.button5);
		// Perform action on click
		btn_cataloguelist.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				// Open catalogue
				Intent newActivity = new Intent(MainActivity.this,
						CatalogueListActivity.class);
				startActivity(newActivity);
				finish();
			}
		});

	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
