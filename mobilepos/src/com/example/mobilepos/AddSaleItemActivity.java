package com.example.mobilepos;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;

public class AddSaleItemActivity  extends Activity{
	ArrayList<HashMap<String, String>> MebmerList;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_sale_item_add);
// [Upon clicking this button, display the items from Catalogs and uses Context command]
	//	CatalogActivity.ShowListData();  //Show the current DB table
	//	this.MebmerList = CatalogActivity.getMebmerList();
		//[Uses context code, but change it]
		
	}
	
	//Display menu when you hold on the item
	 public void onCreateContextMenu(ContextMenu menu, View v,
	    		ContextMenuInfo menuInfo) {
//	    	    AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
//	    		menu.setHeaderTitle("Command for : " + MebmerList.get(info.position).get("Name").toString());
//	    		String[] menuItems = getResources().getStringArray(R.array.SaleMenu); 
//	    		for (int i = 0; i<menuItems.length; i++) {
//	    			menu.add(Menu.NONE, i, i, menuItems[i]);  
				}
	 
	//Command for selected menu. We will only need Add for now
	 public boolean onContextItemSelected(MenuItem item) {
//		    AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
//		    int menuItemIndex = item.getItemId();
//			String[] menuItems = getResources().getStringArray(R.array.CmdMenu);
//			String CmdName = menuItems[menuItemIndex];
//			String MemID = MebmerList.get(info.position).get("MemberID").toString();
		    //String MemName = MebmerList.get(info.position).get("Name").toString();
		    
		    // Check Add Command
			//  if ("Edit".equals(Add)) {
//
	    	  	//Show on new activity	For this, we add an Item to Sale Activity Hashmap and list
//	        	Intent newActivity = new Intent(AddSaleItemActivity.this,SaleActivity.class);
//	        	startActivity(newActivity);
//	        
//	        } 
	        

	    	return true;
	    }
	    	
	    


	 
}
