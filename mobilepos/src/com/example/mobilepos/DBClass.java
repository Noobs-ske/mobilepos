package com.example.mobilepos;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

public class DBClass extends SQLiteOpenHelper {
	

    // Database Version
    private static final int DATABASE_VERSION = 3;
 
    // Database Model
    private static final String DATABASE_Model = "test2";
 
    // Table Model
    private static final String TABLE_MEMBER = "mobile";

	public DBClass(Context context) {
		super(context, DATABASE_Model, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		// Create Table Model
	    db.execSQL("CREATE TABLE " + TABLE_MEMBER + 
		          "(IMEI INTEGER PRIMARY KEY AUTOINCREMENT," +
		          " Model TEXT(100)," +
		          " Price TEXT(100));");
	   
	    Log.d("CREATE TABLE","Create Table Successfully.");
	}
	
	// Insert Data
	public long InsertData(String strIMEI, String strModel, String strPrice) {
		// TODO Auto-generated method stub
		
		 try {
			SQLiteDatabase db;
     		db = this.getWritableDatabase(); // Write Data
     		
     		
     		//  for API 11 and above
			SQLiteStatement insertCmd;
			String strSQL = "INSERT INTO " + TABLE_MEMBER
					+ "(IMEI,Model,Price) VALUES (?,?,?)";
			
			insertCmd = db.compileStatement(strSQL);
			insertCmd.bindString(1, strIMEI);
			insertCmd.bindString(2, strModel);
			insertCmd.bindString(3, strPrice);
			return insertCmd.executeInsert();
			
     		/*
			ContentValues Val = new ContentValues();
			Val.put("IMEI", strIMEI); 
			Val.put("Model", strModel);
			Val.put("Price", strPrice);

			long rows = db.insert(TABLE_MEMBER, null, Val);
			
			db.close();
			return rows; // return rows inserted.
			*/
           
		 } catch (Exception e) {
		    return -1;
		 }

	}
	
	
	// Select Data
	public String[] SelectData(String strIMEI) {
		// TODO Auto-generated method stub
		 try {
			String arrData[] = null;	
			
			 SQLiteDatabase db;
			 db = this.getReadableDatabase(); // Read Data
				
			 Cursor cursor = db.query(TABLE_MEMBER, new String[] { "*" }, 
					 	"IMEI=?",
			            new String[] { String.valueOf(strIMEI) }, null, null, null, null);
			 
			 	if(cursor != null)
			 	{
					if (cursor.moveToFirst()) {
						arrData = new String[cursor.getColumnCount()];
						/***
						 *  0 = IMEI
						 *  1 = Model
						 *  2 = Price
						 */
						arrData[0] = cursor.getString(0);
						arrData[1] = cursor.getString(1);
						arrData[2] = cursor.getString(2);
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
	public long DeleteData(String strIMEI) {
		// TODO Auto-generated method stub
		
		 try {
			
			SQLiteDatabase db;
     		db = this.getWritableDatabase(); // Write Data
     		
     		
     		// * for API 11 and above
			SQLiteStatement insertCmd;
			String strSQL = "DELETE FROM " + TABLE_MEMBER
					+ " WHERE IMEI = ? ";
			
			insertCmd = db.compileStatement(strSQL);
			insertCmd.bindString(1, strIMEI);
			
			return insertCmd.executeUpdateDelete();
			
			/*	
     		long rows = db.delete(TABLE_MEMBER, "IMEI = ?",
		            new String[] { String.valueOf(strIMEI) });
     		
     		db.close();
     		return rows; // return rows deleted.
				*/
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
				
			 String strSQL = "SELECT  * FROM " + TABLE_MEMBER;
			 Cursor cursor = db.rawQuery(strSQL, null);
			 
			 	if(cursor != null)
			 	{
			 	    if (cursor.moveToFirst()) {
			 	        do {
			 	        	map = new HashMap<String, String>();
			 	        	map.put("IMEI", cursor.getString(0));
				 	        map.put("Model", cursor.getString(1));
				 	        map.put("Price", cursor.getString(2));
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
	public long UpdateData(String strIMEI,String strModel,String strPrice) {
		// TODO Auto-generated method stub
		
		 try {
			
			SQLiteDatabase db;
     		db = this.getWritableDatabase(); // Write Data
     		
     		
     		// *  for API 11 and above
			SQLiteStatement insertCmd;
			String strSQL = "UPDATE " + TABLE_MEMBER
					+ " SET Model = ? "
					+ " , Price = ? "
					+ " WHERE IMEI = ? ";
			
			insertCmd = db.compileStatement(strSQL);
			insertCmd.bindString(1, strModel);
			insertCmd.bindString(2, strPrice);
			insertCmd.bindString(3, strIMEI);
				
			return insertCmd.executeUpdateDelete();
			
			/*
            ContentValues Val = new ContentValues();
            Val.put("Model", strModel);
            Val.put("Price", strPrice);
     
            long rows = db.update(TABLE_MEMBER, Val, " IMEI = ?",
                    new String[] { String.valueOf(strIMEI) });
            
     		db.close();
     		return rows; // return rows updated.
				*/
		 }
		 catch (Exception e) {
		    return -1;
		 }

	}	
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEMBER);
        
        // Re Create on method  onCreate
        onCreate(db);
	}

}