package com.example.mobilepos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CatalogueListActivity extends Activity {
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
				Intent newActivity = new Intent(CatalogueListActivity.this,
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
						Intent newActivity = new Intent(CatalogueListActivity.this,
								SaleActivity.class);
						startActivity(newActivity);

					}
				});
				
		// Button5(cataloguelist)
				final Button btn_cataloguelist = (Button) findViewById(R.id.button5);
				// Perform action on click
				btn_cataloguelist.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {

						// Open cataloguelist
						Intent newActivity = new Intent(CatalogueListActivity.this,
								CatalogueListActivity.class);
						startActivity(newActivity);

					}
				});
	}
}
