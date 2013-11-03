package com.example.mobilepos;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBClass extends SQLiteOpenHelper {

	// Database Version
	private static final int DATABASE_VERSION = 3;

	// Database Name
	private static final String DATABASE_NAME = "mydatabase";

	// Table Name
	private static final String TABLE_PRODUCT = "Products";

	public DBClass(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		// Create Table Name
		db.execSQL("CREATE TABLE " + TABLE_PRODUCT
				+ "(ItemID INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ " Name TEXT(100)," + " Quantity TEXT(100), " 
				+ " Price TEXT(100));");
		

		Log.d("CREATE TABLE", "Create Table Successfully.");
	}

	// Insert Data
	public long InsertData(String strItemID, String strName, String strQuantity, String strPrice) {
		// TODO Auto-generated method stub

		try {
			SQLiteDatabase db;
			db = this.getWritableDatabase(); // Write Data

			/**
			 * for API 11 and above SQLiteStatement insertCmd; String strSQL =
			 * "INSERT INTO " + TABLE_MEMBER +
			 * "(ItemID,Name,Quantity,Price) VALUES (?,?,?)";
			 * 
			 * insertCmd = db.compileStatement(strSQL); insertCmd.bindString(1,
			 * strItemID); insertCmd.bindString(2, strName);
			 * insertCmd.bindString(3, strTel); return
			 * insertCmd.executeInsert();
			 */

			ContentValues Val = new ContentValues();
			Val.put("ItemID", strItemID);
			Val.put("Name", strName);
			Val.put("Quantity", strQuantity);
			Val.put("Price", strPrice);

			long rows = db.insert(TABLE_PRODUCT, null, Val);

			db.close();
			return rows; // return rows inserted.

		} catch (Exception e) {
			return -1;
		}

	}

	// Select Data
	public String[] SelectData(String strItemID) {
		// TODO Auto-generated method stub

		try {
			String arrData[] = null;

			SQLiteDatabase db;
			db = this.getReadableDatabase(); // Read Data

			Cursor cursor = db.query(TABLE_PRODUCT, new String[] { "*" },
					"ItemID=?", new String[] { String.valueOf(strItemID) },
					null, null, null, null);

			if (cursor != null) {
				if (cursor.moveToFirst()) {
					arrData = new String[cursor.getColumnCount()];
					/***
					 * 0 = ItemID , 1 = Name , 2 = Quantity , 3 = Price
					 */
					arrData[0] = cursor.getString(0);
					arrData[1] = cursor.getString(1);
					arrData[2] = cursor.getString(2);
					arrData[3] = cursor.getString(3);
				}
			}
			cursor.close();
			db.close();
			return arrData;

		} catch (Exception e) {
			return null;
		}

	}

	// Delete Data
	public long DeleteData(String strItemID) {
		// TODO Auto-generated method stub

		try {

			SQLiteDatabase db;
			db = this.getWritableDatabase(); // Write Data

			/**
			 * for API 11 and above SQLiteStatement insertCmd; String strSQL =
			 * "DELETE FROM " + TABLE_PRODUCT + " WHERE ItemID = ? ";
			 * 
			 * insertCmd = db.compileStatement(strSQL); insertCmd.bindString(1,
			 * strItemID);
			 * 
			 * return insertCmd.executeUpdateDelete();
			 * 
			 */

			long rows = db.delete(TABLE_PRODUCT, "ItemID = ?",
					new String[] { String.valueOf(strItemID) });

			db.close();
			return rows; // return rows deleted.

		} catch (Exception e) {
			return -1;
		}

	}

	// Show All Data
	public ArrayList<HashMap<String, String>> SelectAllData() {
		// TODO Auto-generated method stub

		try {

			ArrayList<HashMap<String, String>> MyArrList = new ArrayList<HashMap<String, String>>();
			HashMap<String, String> map;

			SQLiteDatabase db;
			db = this.getReadableDatabase(); // Read Data

			String strSQL = "SELECT  * FROM " + TABLE_PRODUCT;
			Cursor cursor = db.rawQuery(strSQL, null);

			if (cursor != null) {
				if (cursor.moveToFirst()) {
					do {
						map = new HashMap<String, String>();
						map.put("ItemID", cursor.getString(0));
						map.put("Name", cursor.getString(1));
						map.put("Quantity", cursor.getString(2));
						map.put("Price", cursor.getString(3));
						MyArrList.add(map);
					} while (cursor.moveToNext());
				}
			}
			cursor.close();
			db.close();
			return MyArrList;

		} catch (Exception e) {
			return null;
		}

	}

	// Update Data
	public long UpdateData(String strItemID, String strName, String strQuantity, String strPrice) {
		// TODO Auto-generated method stub

		try {

			SQLiteDatabase db;
			db = this.getWritableDatabase(); // Write Data

			/**
			 * for API 11 and above SQLiteStatement insertCmd; String strSQL =
			 * "UPDATE " + TABLE_MEMBER + " SET Name = ? " + " , Tel = ? " +
			 * " WHERE ItemID = ? ";
			 * 
			 * insertCmd = db.compileStatement(strSQL); insertCmd.bindString(1,
			 * strName); insertCmd.bindString(2, strTel);
			 * insertCmd.bindString(3, strItemID);
			 * 
			 * return insertCmd.executeUpdateDelete();
			 * 
			 */
			ContentValues Val = new ContentValues();
			Val.put("Name", strName);
			Val.put("Quantity", strQuantity);
			Val.put("Price", strPrice);

			long rows = db.update(TABLE_PRODUCT, Val, " ItemID = ?",
					new String[] { String.valueOf(strItemID) });

			db.close();
			return rows; // return rows updated.

		} catch (Exception e) {
			return -1;
		}

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCT);

		// Re Create on method onCreate
		onCreate(db);
	}

	

	

}