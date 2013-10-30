package com.example.mobilepos;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class CatalogActivity extends Activity {

	//list of item in catalog
	ArrayList<HashMap<String, String>> ItemList;
	
	//list of purchase's item in catalog
	ArrayList<String> PurchaseList = new ArrayList<String>();

	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_catalog);

		// Call Show List All Data
		ShowListData();

		// Button1(SaleButton)
		final Button btn_Sale = (Button) findViewById(R.id.button1);
		// Perform action on click
		btn_Sale.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				// [Open Sale -- Might be best to change MainActivity to
				// SaleActivity]
				Intent newActivity = new Intent(CatalogActivity.this,
						SaleActivity.class);
				newActivity.putStringArrayListExtra("PurchaseList",
						PurchaseList);
				PurchaseList = new ArrayList<String>();
				startActivity(newActivity);

			}
		});

		// Button3 (AddButton)

		final Button btn_Add = (Button) findViewById(R.id.button3);
		// Perform action on click
		btn_Add.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				// Open Add
				Intent newActivity = new Intent(CatalogActivity.this,
						AddActivity.class);
				startActivity(newActivity);

			}
		});
		
		// Button4(HistoryButton)
		final Button btn_History = (Button) findViewById(R.id.button4);
		// Perform action on click
		btn_History.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				// Open History
				Intent newActivity = new Intent(CatalogActivity.this,
						HistoryActivity.class);
				startActivity(newActivity);

			}
		});
		
		
		// Button5(NewsButton)
		final Button btn_News = (Button) findViewById(R.id.button5);
		// Perform action on click
		btn_News.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				// Open News
				Intent newActivity = new Intent(CatalogActivity.this,
						NewsActivity.class);
				startActivity(newActivity);

			}
		});

	}

	

	// [Might need an injection get current List method to use in
	// AddSaleItemActivity]
	public ArrayList<HashMap<String, String>> getMebmerList() {
		return ItemList;
	}

	// Show List data
	public void ShowListData() {
		final DBClass myDb = new DBClass(this);
		ItemList = myDb.SelectAllData();

		// listView1
		ListView lisView1 = (ListView) findViewById(R.id.listView1);

		SimpleAdapter sAdap;
		sAdap = new SimpleAdapter(CatalogActivity.this, ItemList,
				R.layout.activity_column, new String[] { "MemberID", "Name",
						"Tel" }, new int[] { R.id.ColMemberID, R.id.ColName,
						R.id.ColTel });
		lisView1.setAdapter(sAdap);
		registerForContextMenu(lisView1);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// if (v.getId()==R.id.list) {
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
		menu.setHeaderTitle("Command for : "
				+ ItemList.get(info.position).get("Name").toString());
		String[] menuItems = getResources().getStringArray(R.array.CmdMenu);
		for (int i = 0; i < menuItems.length; i++) {
			menu.add(Menu.NONE, i, i, menuItems[i]);
		}
		// }
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
				.getMenuInfo();
		int menuItemIndex = item.getItemId();
		String[] menuItems = getResources().getStringArray(R.array.CmdMenu);
		String CmdName = menuItems[menuItemIndex];
		String MemID = ItemList.get(info.position).get("MemberID").toString();
		String MemName = ItemList.get(info.position).get("Name").toString();

		// Check Event Command
		if ("Purchase".equals(CmdName)) {
			boolean chk = true;
			for (int i = 0; i < PurchaseList.size(); i++) {
				if (PurchaseList.get(i).equals(MemID)) {
					chk = false;
					break;
				}

			}
			if (chk)
				PurchaseList.add(MemID);
			else {
				Toast.makeText(CatalogActivity.this,
						"You're already Purchase.", Toast.LENGTH_LONG).show();
			}
		}

		else if ("Edit".equals(CmdName)) {

			// Show on new activity
			Intent newActivity = new Intent(CatalogActivity.this,
					UpdateActivity.class);
			newActivity.putExtra("MemID",
					ItemList.get(info.position).get("MemberID").toString());
			startActivity(newActivity);

			// for Delete Command
		} else if ("Delete".equals(CmdName)) {

			DBClass myDb = new DBClass(this);

			long flg = myDb.DeleteData(MemID);
			if (flg > 0) {
				Toast.makeText(CatalogActivity.this,
						"Delete Data Successfully", Toast.LENGTH_LONG).show();
			} else {
				Toast.makeText(CatalogActivity.this, "Delete Data Failed.",
						Toast.LENGTH_LONG).show();
			}

			// Call Show Data again
			ShowListData();
		}

		return true;
	}

}
