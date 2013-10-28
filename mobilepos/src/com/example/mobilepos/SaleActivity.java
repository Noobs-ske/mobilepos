package com.example.mobilepos;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class SaleActivity extends Activity {

	//Item ID to Price
	ArrayList<HashMap<String, Double>> SaleList;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	// [Need Automated item in R for activity_sale]
	//	setContentView(R.layout.activity_sale);
	// [This one will display the table for item currently registered for sale]
	//	ShowSaleData();
	//  ShowTotalPrice();
	// 	[This one will be the Add Item button for this Sale activity]
	//	final Button btn_Add = (Button) findViewById(R.id.button3);
    //  	// Perform action on click
    //    btn_Add.setOnClickListener(new View.OnClickListener() {
    //        public void onClick(View v) {
    //       	
    //        	// Open Add  
    //        	Intent newActivity = new Intent(SaleActivity.this,AddItemActivity.class);
    //       	startActivity(newActivity);
    //    
    //        }
    //    });
	}

	public void ShowSaleData()
	{
//		DBClass myDb = new DBClass(this);
//		SaleList = myDb.SelectAllData();
//		
  //	// listView1
  //    ListView lisView1 = (ListView)findViewById(R.id.listView1); 
  //      
  //  SimpleAdapter sAdap;
  //   sAdap = new SimpleAdapter(SaleActivity.this, SaleList, R.layout.activity_column,
  //            new String[] {"ItemID", "Name", "Price"}, new int[] {R.id.ColItemID, R.id.ColName, R.id.ColPrice});      
  //     lisView1.setAdapter(sAdap);
  //      registerForContextMenu(lisView1);
	}
	
	public void ShowTotalPrice()
	{
	// [Search through array of data, then compare the Item ID to Hash map, add price from there, display]
	}
	
	 @Override
	    public boolean onContextItemSelected(MenuItem item) {
//		    AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
//		    int menuItemIndex = item.getItemId();
// [Is it possible to just, not use R.Array.Cmdmenu and add in the strong normally?]
//			String[] menuItems = getResources().getStringArray(R.array.CmdMenu);
//			String CmdName = menuItems[menuItemIndex];
//			String MemID = SaleList.get(info.position).get("MemberID").toString();
//		    //String MemName = MebmerList.get(info.position).get("Name").toString();
//		    
//	        // for Delete Command
//	        if ("Delete".equals(CmdName)) {
//
//	        	DBClass myDb = new DBClass(this);  
//	        	
//	        	long flg = myDb.DeleteData(MemID);
//	        	if(flg > 0)
//	        	{
//	        	 Toast.makeText(SaleActivity.this,"Delete Item Successfully",
//	        			 	Toast.LENGTH_LONG).show(); 
//	        	}
//	        	else
//	        	{
//	           	 Toast.makeText(SaleActivity.this,"Delete Item Failed.",
//	     			 	Toast.LENGTH_LONG).show(); 
//	        	}
//	        	
//	        	// Call Sale Data again
//	        	ShowSaleData();
//				ShowTotalPrice();
//	        }
//
	    	return true;
	    }
}
