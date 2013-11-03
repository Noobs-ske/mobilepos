package com.example.mobilepos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NewsActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news);

		// Button2(CatalogButton)
		final Button btn_Catalog = (Button) findViewById(R.id.button2);
		// Perform action on click
		btn_Catalog.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				// Open Catalog
				Intent newActivity = new Intent(NewsActivity.this,
						CatalogActivity.class);
				startActivity(newActivity);

			}
		});
		
		// Button4(HistoryButton)
				final Button btn_History = (Button) findViewById(R.id.button4);
				// Perform action on click
				btn_History.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {

						// Open History
						Intent newActivity = new Intent(NewsActivity.this,
								HistoryActivity.class);
						startActivity(newActivity);

					}
				});
		
		// Button1(Sale Button)
				final Button btn_Sale = (Button) findViewById(R.id.button1);
				// Perform action on click
				btn_Sale.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {

						// Open Sale
						Intent newActivity = new Intent(NewsActivity.this,
								SaleActivity.class);
						startActivity(newActivity);

					}
				});
				
				
}
}
