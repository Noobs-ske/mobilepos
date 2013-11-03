package com.example.mobilepos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HistoryActivity extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_history);

		// Button2(CatalogButton)
		final Button btn_Catalog = (Button) findViewById(R.id.button2);
		// Perform action on click
		btn_Catalog.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				// Open Catalog
				Intent newActivity = new Intent(HistoryActivity.this,
						CatalogActivity.class);
				startActivity(newActivity);

			}
		});
		
		// Button1(Sale Button)
				final Button btn_Sale = (Button) findViewById(R.id.button1);
				// Perform action on click
				btn_Sale.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {

						// Open Sale
						Intent newActivity = new Intent(HistoryActivity.this,
								SaleActivity.class);
						startActivity(newActivity);

					}
				});
				
		// Button5(NewsButton)
				final Button btn_News = (Button) findViewById(R.id.button5);
				// Perform action on click
				btn_News.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {

						// Open News
						Intent newActivity = new Intent(HistoryActivity.this,
								NewsActivity.class);
						startActivity(newActivity);

					}
				});
	}
}
