package com.Susi.villageguide;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;



public class DatabaseActivity extends ListActivity {

	SQLiteDatabase database = null;
	Cursor dbCursor;
	DatabaseHelper dbHelper = new DatabaseHelper(this);

	
	// Action Bar
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.map, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_map:
			Intent intentMap = new Intent(this, MapActivity.class);
			startActivity(intentMap);}
		switch (item.getItemId()){
		case R.id.action_list:
			
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_database);
	}

// �ffnen des Listviews1
	
	public void onClickOpenListView1(View view) {
//		ListView lv1= (ListView)findViewById(R.id.list1);
// Zugriff auf DB
		try {
			dbHelper.createDataBase();
		} catch (IOException ioe) {
		}
		List<String> list_values = new ArrayList<String>();
		try {
			database = dbHelper.getDataBase();
			// DB Abfrage
			dbCursor = database.rawQuery(
					"SELECT Name FROM Hunger;", null);		
			dbCursor.moveToFirst();
			int index = dbCursor.getColumnIndex("Name");
			while (!dbCursor.isAfterLast()) {
				String record = dbCursor.getString(index);
				list_values.add(record);
				dbCursor.moveToNext();
			}
		} finally {
			if (database != null) {
				dbHelper.close();
			}
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.list_item, list_values);
//		lv1.setAdapter(adapter);
		setListAdapter(adapter);
		
	}

	
//�ffnen ListView2
	
	public void onClickOpenListView2(View view) {
	
		try {
			dbHelper.createDataBase();
		} catch (IOException ioe) {
		}
		List<String> list_values = new ArrayList<String>();
		try {
			database = dbHelper.getDataBase();
			// DB Abfrage
			dbCursor = database.rawQuery(
					"SELECT Name FROM Sehenswuerdigkeiten;", null);		
			dbCursor.moveToFirst();
			int index = dbCursor.getColumnIndex("Name");
			while (!dbCursor.isAfterLast()) {
				String record = dbCursor.getString(index);
				list_values.add(record);
				dbCursor.moveToNext();
			}
		} finally {
			if (database != null) {
				dbHelper.close();
			}
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.list_item, list_values);
		setListAdapter(adapter);
	
	}

//�ffnen ListView3

	public void onClickOpenListView3(View view) {
		try {
			dbHelper.createDataBase();
		} catch (IOException ioe) {
		}
		List<String> list_values = new ArrayList<String>();
		try {
			database = dbHelper.getDataBase();
			// DB Abfrage
			dbCursor = database.rawQuery(
					"SELECT Name FROM Gesundheit;", null);		
			dbCursor.moveToFirst();
			int index = dbCursor.getColumnIndex("Name");
			while (!dbCursor.isAfterLast()) {
				String record = dbCursor.getString(index);
				list_values.add(record);
				dbCursor.moveToNext();
			}
		} finally {
			if (database != null) {
				dbHelper.close();
			}
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.list_item, list_values);
		setListAdapter(adapter);
	
	}

	
	
	
// Anklicken eines LiestView-Items
	public void onListItemClick(ListView l, View v, int pos, long id) {

		Log.d("Fehler", String.valueOf(pos));
		Log.d("Fehler", String.valueOf(id));

		try {
			dbHelper.createDataBase();
		} catch (IOException ioe) {
		}
		try {
			database = dbHelper.getDataBase();
			// DB Abfrage
			dbCursor = database.rawQuery(
					"SELECT Name, Oeffnungszeiten_Montag_bis_Freitag, Oeffnungszeiten_Samstag, Oeffnungszeiten_Sonntag, Long, Lat FROM Hunger WHERE ID=" + String.valueOf(pos) + ";", null);

			dbCursor.moveToFirst();
			int index = dbCursor.getColumnIndex("Name");
			int index2 = dbCursor.getColumnIndex("Oeffnungszeiten_Montag_bis_Freitag");
			int index3 = dbCursor.getColumnIndex("Oeffnungszeiten_Samstag");
			int index4 = dbCursor.getColumnIndex("Oeffnungszeiten_Sonntag");
			int index5 = dbCursor.getColumnIndex("Long");
			int index6 = dbCursor.getColumnIndex("Lat");
			while (!dbCursor.isAfterLast()) {
				String record = dbCursor.getString(index);
				String snippet1 = dbCursor.getString(index2);
				String snippet2 = dbCursor.getString(index3);
				String snippet3 = dbCursor.getString(index4);
				String lgn = dbCursor.getString(index5);
				String lat = dbCursor.getString(index6);
				dbCursor.moveToNext();
				
				Intent intentMap1 = new Intent(this, MapActivity.class);
				intentMap1.putExtra("Titel", record);
				intentMap1.putExtra("OH1", snippet1);
				intentMap1.putExtra("OH2", snippet2);
				intentMap1.putExtra("OH3", snippet3);
				intentMap1.putExtra("Longitude", lgn);
				intentMap1.putExtra("Latitude", lat);
				startActivity(intentMap1);
			}
			
		} finally {
			if (database != null) {
				dbHelper.close();
			}
		}

	}
}