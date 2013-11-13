package com.example.mobilepos;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class CatalogActivity extends Activity {

	// list of item in Inventory
	ArrayList<HashMap<String, String>> ItemList;

	// list of purchase's item in Inventory
	ArrayList<String> PurchaseList = new ArrayList<String>();

	private int PurchaseQuantity = 0;

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

				
				Intent newActivity = new Intent(CatalogActivity.this,
						SaleActivity.class);
				newActivity.putStringArrayListExtra("PurchaseList",
						PurchaseList);
				PurchaseList = new ArrayList<String>();
				startActivity(newActivity);
				finish();

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

		// Button5(cataloguelist)
		final Button btn_cataloguelist = (Button) findViewById(R.id.button5);
		// Perform action on click
		btn_cataloguelist.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				// Open cataloguelist
				Intent newActivity = new Intent(CatalogActivity.this,
						CatalogueListActivity.class);
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
				R.layout.activity_column, new String[] { "ItemID", "Name",
						"Quantity", "Price" }, new int[] { R.id.ColItemID,
						R.id.ColName, R.id.ColQuantity, R.id.TotalPrice });
		lisView1.setAdapter(sAdap);
		registerForContextMenu(lisView1);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {

		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
		menu.setHeaderTitle("Command for : "
				+ ItemList.get(info.position).get("Name").toString());
		String[] menuItems = getResources().getStringArray(R.array.CmdMenu);
		for (int i = 0; i < menuItems.length; i++) {
			menu.add(Menu.NONE, i, i, menuItems[i]);
		}

	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		final DBClass myDb = new DBClass(this);
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
				.getMenuInfo();
		int menuItemIndex = item.getItemId();
		String[] menuItems = getResources().getStringArray(R.array.CmdMenu);
		String CmdName = menuItems[menuItemIndex];
		final String MemID = ItemList.get(info.position).get("ItemID")
				.toString();
		final String MemName = ItemList.get(info.position).get("Name")
				.toString();
		final String MemQuantity = ItemList.get(info.position).get("Quantity")
				.toString();
		final String MemPrice = ItemList.get(info.position).get("Price")
				.toString();
		// Check Event Command
		if ("Purchase".equals(CmdName)) {
			boolean check = true;
			for (int i = 0; i < PurchaseList.size(); i++) {
				if (PurchaseList.get(i).equals(MemID)) {
					check = false;
					break;
				}

			}
			if (check) {
				PurchaseList.add(MemID);

				AlertDialog.Builder alert = new AlertDialog.Builder(this);

				alert.setTitle("Quantity");
				alert.setMessage("Please input the number");

				// Set an EditText view to get user input
				final EditText inputQuantity = new EditText(this);
				alert.setView(inputQuantity);

				alert.setPositiveButton("Confirm",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {

								try {
									PurchaseQuantity = Integer
											.parseInt(inputQuantity.getText()
													.toString());
									int n = Integer.parseInt(MemQuantity)
											- PurchaseQuantity;
									// double m =
									// n*(Double.parseDouble(MemPrice)/Double.parseDouble(MemQuantity))
									// ;
									String n2 = n + "";
									// String m2 = m+"";
									if (Integer.parseInt(n2) <= 0) {
										n2 = MemQuantity;
										Toast.makeText(getBaseContext(),
												"Can not Purchase",
												Toast.LENGTH_LONG).show();
									}
									myDb.UpdateData(MemID, MemName, n2,
											MemPrice);

									ShowListData();

								} catch (Exception e) {
									Toast.makeText(getBaseContext(),
											"INPUT THE NUMBER MORON !!",
											Toast.LENGTH_LONG).show();
								}
							}
						});

				alert.setNegativeButton("Cancel", null);

				alert.show();

			} else {
				Toast.makeText(CatalogActivity.this,
						"You're already Purchase.", Toast.LENGTH_LONG).show();
			}
		}

		else if ("Edit".equals(CmdName)) {

			// Show on new activity
			Intent newActivity = new Intent(CatalogActivity.this,
					UpdateActivity.class);
			newActivity.putExtra("MemID",
					ItemList.get(info.position).get("ItemID").toString());
			startActivity(newActivity);

			// for Delete Command
		} else if ("Delete".equals(CmdName)) {

			// DBClass myDb = new DBClass(this);

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
