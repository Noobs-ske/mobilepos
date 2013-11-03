package com.example.mobilepos;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class SaleActivity extends Activity {
	// list of item in catalog
	ArrayList<HashMap<String, String>> ItemList;
	//list of sale's item
	ArrayList<String> SaleList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sale);

		Intent intent = getIntent();
		SaleList = intent.getStringArrayListExtra("PurchaseList");

		ShowAllData();
		// Button1
		final Button btn_ChkOut = (Button) findViewById(R.id.button1);
		// Perform action on click
		btn_ChkOut.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				Checkout();
				// [Open Sale -- Might be best to change MainActivity to
				// SaleActivity]
				Intent newActivity = new Intent(SaleActivity.this,
						CatalogActivity.class);
				startActivity(newActivity);

			}
		});
		
		// Button3(SaleButton)
		final Button btn_Sale = (Button) findViewById(R.id.button3);
		// Perform action on click
		btn_Sale.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				// Open History
				Intent newActivity = new Intent(SaleActivity.this,
						SaleActivity.class);
				startActivity(newActivity);

			}
		});
		
		
		
		// Button2(CistoryButton)
		final Button btn_Catalog = (Button) findViewById(R.id.button2);
		// Perform action on click
		btn_Catalog.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				// Open History
				Intent newActivity = new Intent(SaleActivity.this,
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
				Intent newActivity = new Intent(SaleActivity.this,
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
				Intent newActivity = new Intent(SaleActivity.this,
						NewsActivity.class);
				startActivity(newActivity);

			}
		});

	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sale, menu);
		return true;
	}

	/*
	 * public ArrayList<HashMap<String, String>> getSaleList() { return
	 * SaleList; }
	 */
	public void Checkout() {
		DBClass myDb = new DBClass(this);

		for (int i = 0; i < SaleList.size(); i++) {
			myDb.DeleteData(SaleList.get(i));
		}
		SaleList = null;

	}

	public void ShowAllData() {
		double total = 0;
		ArrayList<HashMap<String, String>> MyArrList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map;

		DBClass myDb = new DBClass(this);
		for (int i = 0; i < SaleList.size(); i++) {
			String arrData[] = myDb.SelectData(SaleList.get(i));
			map = new HashMap<String, String>();
			map.put("ItemID", arrData[0]);
			map.put("Name", arrData[1]);
			map.put("Quantity", arrData[2]);
			map.put("Price", arrData[3]);
			total += Double.parseDouble(arrData[2])*Double.parseDouble(arrData[3]) ;
			MyArrList.add(map);
		}
		ItemList = MyArrList;
		// listView1
		ListView lisView1 = (ListView) findViewById(R.id.listView1);

		SimpleAdapter sAdap;
		sAdap = new SimpleAdapter(SaleActivity.this, ItemList,
				R.layout.activity_salecolumn, new String[] { "ItemID",
						"Name", "Quantity","Price" }, new int[] { R.id.ColItemID, R.id.ColName,
						R.id.ColQuantity ,R.id.ColPrice });
		lisView1.setAdapter(sAdap);
		registerForContextMenu(lisView1);

		// Show total
		TextView text_TotalPrice = (TextView) findViewById(R.id.textView2);
		text_TotalPrice.setText("" + total);
	}

	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// if (v.getId()==R.id.list) {
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
		menu.setHeaderTitle("Command for : "
				+ ItemList.get(info.position).get("Name").toString());
		String[] menuItems = getResources().getStringArray(R.array.CmdMenu);
		menu.add(Menu.NONE, 2, 2, menuItems[2]);
		// }
	}

	public boolean onContextItemSelected(MenuItem item) {
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
				.getMenuInfo();
		int menuItemIndex = item.getItemId();
		String[] menuItems = getResources().getStringArray(R.array.CmdMenu);
		String CmdName = menuItems[menuItemIndex];
		String MemID = ItemList.get(info.position).get("MemberID").toString();
		String MemName = ItemList.get(info.position).get("Name").toString();

		if ("Delete".equals(CmdName)) {

			ItemList.remove(ItemList.get(info.position));
			for (int i = 0; i < SaleList.size(); i++) {
				if (MemID.equals(SaleList.get(i))) {
					SaleList.remove(i);
				}
			}
			// Call Show Data again
			ShowAllData();
		}

		return true;
	}

}
