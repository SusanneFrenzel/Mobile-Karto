package com.Susi.villageguide;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class DatabaseActivity extends Activity {
	
//public class DatabaseActivity extends ListActivity {
//	SQLiteDatabase database = null;
//	Cursor dbCursor;
//	DatabaseHelper dbHelper = new DatabaseHelper(this);

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_database);
//		queryDataFromDatabase();
//		TextView test=(TextView) findViewById(R.id.text_view1);
//		test.setOnClickListener(new OnClickListener()) {
	}
	public void onClickOpenListView(View view){
		//mach was
		Intent intentMap = new Intent(this, MapActivity.class);
		startActivity(intentMap);
	
	}	
		
		}
//	public void queryDataFromDatabase() {
//		try {
//			dbHelper.createDataBase();
//		} catch (IOException ioe) {
//		}
//		List<String> list_values = new ArrayList<String>();
//		try {
//			database = dbHelper.getDataBase();
//			// DB Abfrage
//			dbCursor = database.rawQuery("SELECT university FROM db_table;",
//					null);
//			dbCursor.moveToFirst();
//			int index = dbCursor.getColumnIndex("university");
//			while (!dbCursor.isAfterLast()) {
//				String record = dbCursor.getString(index);
//				list_values.add(record);
//				dbCursor.moveToNext();
//			}
//		} finally {
//			if (database != null) {
//				dbHelper.close();
//			}
//		}
//		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//				R.layout.list_item, list_values);
//		setListAdapter(adapter);
//	}
//	
//	
//public void onListItemClick(ListView l, View v, int pos, long id){
//	Intent intentMap = new Intent(this, MapActivity.class);
//	startActivity(intentMap);
//}
	
