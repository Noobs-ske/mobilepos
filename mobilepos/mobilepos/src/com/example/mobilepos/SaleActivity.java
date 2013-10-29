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
	ArrayList<HashMap<String, String>> MebmerList;
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
			map.put("MemberID", arrData[0]);
			map.put("Name", arrData[1]);
			map.put("Tel", arrData[2]);
			total += Double.parseDouble(arrData[2]);
			MyArrList.add(map);
		}
		MebmerList = MyArrList;
		// listView1
		ListView lisView1 = (ListView) findViewById(R.id.listView1);

		SimpleAdapter sAdap;
		sAdap = new SimpleAdapter(SaleActivity.this, MebmerList,
				R.layout.activity_salecolumn, new String[] { "MemberID",
						"Name", "Tel" }, new int[] { R.id.ItemID, R.id.Name,
						R.id.Price });
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
				+ MebmerList.get(info.position).get("Name").toString());
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
		String MemID = MebmerList.get(info.position).get("MemberID").toString();
		String MemName = MebmerList.get(info.position).get("Name").toString();

		if ("Delete".equals(CmdName)) {

			MebmerList.remove(MebmerList.get(info.position));
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
