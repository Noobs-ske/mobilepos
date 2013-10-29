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
		
	
		// Button1
        final Button btn_Catalog = (Button) findViewById(R.id.button2);
        // Perform action on click
        btn_Catalog.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	// Open Catalog 
            	Intent newActivity = new Intent(MainActivity.this,CatalogActivity.class);
            	startActivity(newActivity);
        
            }
        });
        
        
        //TODO Sale Button
       
        
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    
    
}
