package com.Susi.villageguide;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.model.*;

public class MapActivity extends Activity {
	SQLiteDatabase database = null;
	Cursor dbCursor;
	DatabaseHelper dbHelper = new DatabaseHelper(this);
	String record;
	String snippet1;
	String snippet2;
	String snippet3;
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.map, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_map:
			
		}
		switch (item.getItemId()) {
		case R.id.action_list:
			Intent intentDB = new Intent(this, DatabaseActivity.class);
			startActivity(intentDB);
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		GoogleMap map = ((MapFragment) getFragmentManager().findFragmentById(
				R.id.map)).getMap();
		map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		map.setMyLocationEnabled(true);
		map.getUiSettings().setZoomControlsEnabled(true);
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(50.86572,
				13.021379), 14));

		Intent intentMap1 = getIntent();
		String Titel = intentMap1.getStringExtra("Titel");
		String OZ1 = intentMap1.getStringExtra("OH1");
		String OZ2 = intentMap1.getStringExtra("OH2");
		String OZ3 = intentMap1.getStringExtra("OH3");
		Bundle b = getIntent().getExtras();
		if (Titel!=null) {
			map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(b.getDouble("Lat"),b.getDouble("Long")), 16));
			map.addMarker(new MarkerOptions()
			 .icon(BitmapDescriptorFactory.fromResource(R.drawable.star))
			 .position(new LatLng(b.getDouble("Lat"), b.getDouble("Long")))
			 .anchor(0.5f, 0.5f)
			 .title(Titel)
			 .snippet(
			 "Öffnungszeiten: Mo-Fr: " + OZ1 + ",  Sa: " + OZ2
			 + ", So: " + OZ3));
		}
		
	
	
	}

	public void onClickShowOnMap(View view) {
		// Datenbankabfrage
		GoogleMap map = ((MapFragment) getFragmentManager().findFragmentById(
				R.id.map)).getMap();
		map.clear();
		try {
			dbHelper.createDataBase();
		} catch (IOException ioe) {
		}
		try {
			database = dbHelper.getDataBase();

			// DB Abfrage
			dbCursor = database
					.rawQuery(
							"SELECT  Name, Oeffnungszeiten_Montag_bis_Freitag, Oeffnungszeiten_Samstag, Oeffnungszeiten_Sonntag, Long, Lat FROM Gesundheit;",
							null);
			// dbCursor = database.rawQuery("SELECT university FROM db_table",
			// null);
			dbCursor.moveToFirst();
			int index = dbCursor.getColumnIndex("Name");
			int index2 = dbCursor
					.getColumnIndex("Oeffnungszeiten_Montag_bis_Freitag");
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
				Double Long_d = Double.parseDouble(lgn);
				Double Lat_d = Double.parseDouble(lat);
				map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(50.86572,
						13.021379), 14));
				map.addMarker(new MarkerOptions()
						.icon(BitmapDescriptorFactory
								.fromResource(R.drawable.star))
						.position(new LatLng(Lat_d, Long_d))
						.anchor(0.5f, 0.5f)
						.title(record)
						.snippet(
								"Öffnungszeiten: Mo-Fr: " + snippet1
										+ ",  Sa: " + snippet2 + ", So: "
										+ snippet3));
				dbCursor.moveToNext();
			}
		} finally {
			if (database != null) {
				dbHelper.close();
			}
		}

	}

	public void onClickShowOnMap1(View view) {
		// Datenbankabfrage
		GoogleMap map = ((MapFragment) getFragmentManager().findFragmentById(
				R.id.map)).getMap();
		map.clear();
		try {
			dbHelper.createDataBase();
		} catch (IOException ioe) {
		}
		try {
			database = dbHelper.getDataBase();

			// DB Abfrage
			dbCursor = database
					.rawQuery(
							"SELECT  Name, Oeffnungszeiten_Montag_bis_Freitag, Oeffnungszeiten_Samstag, Oeffnungszeiten_Sonntag, Long, Lat FROM Hunger;",
							null);
			// dbCursor = database.rawQuery("SELECT university FROM db_table",
			// null);
			dbCursor.moveToFirst();
			int index = dbCursor.getColumnIndex("Name");
			int index2 = dbCursor
					.getColumnIndex("Oeffnungszeiten_Montag_bis_Freitag");
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
				Double Long_d = Double.parseDouble(lgn);
				Double Lat_d = Double.parseDouble(lat);
				map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(50.86572,
						13.021379), 14));
				map.addMarker(new MarkerOptions()
						.icon(BitmapDescriptorFactory
								.fromResource(R.drawable.star))
						.position(new LatLng(Lat_d, Long_d))
						.anchor(0.5f, 0.5f)
						.title(record)
						.snippet(
								"Öffnungszeiten: Mo-Fr: " + snippet1
										+ ",  Sa: " + snippet2 + ", So: "
										+ snippet3));
				dbCursor.moveToNext();
			}
		} finally {
			if (database != null) {
				dbHelper.close();
			}
		}

	}

	public void onClickShowOnMap2(View view) {

		GoogleMap map = ((MapFragment) getFragmentManager().findFragmentById(
				R.id.map)).getMap();
		map.clear();

		try {
			dbHelper.createDataBase();
		} catch (IOException ioe) {
		}
		try {
			database = dbHelper.getDataBase();

			// DB Abfrage
			dbCursor = database
					.rawQuery(
							"SELECT  Name, Oeffnungszeiten_Montag_bis_Freitag, Oeffnungszeiten_Samstag, Oeffnungszeiten_Sonntag, Long, Lat FROM Sehenswuerdigkeiten;",
							null);
			// dbCursor = database.rawQuery("SELECT university FROM db_table",
			// null);
			dbCursor.moveToFirst();
			int index = dbCursor.getColumnIndex("Name");
			int index2 = dbCursor
					.getColumnIndex("Oeffnungszeiten_Montag_bis_Freitag");
			int index3 = dbCursor.getColumnIndex("Oeffnungszeiten_Samstag");
			int index4 = dbCursor.getColumnIndex("Oeffnungszeiten_Sonntag");
			int index5 = dbCursor.getColumnIndex("Long");
			int index6 = dbCursor.getColumnIndex("Lat");
			while (!dbCursor.isAfterLast()) {

				record = dbCursor.getString(index);
				snippet1 = dbCursor.getString(index2);
				snippet2 = dbCursor.getString(index3);
				snippet3 = dbCursor.getString(index4);
				String lgn = dbCursor.getString(index5);
				String lat = dbCursor.getString(index6);
				Double Long_d = Double.parseDouble(lgn);
				Double Lat_d = Double.parseDouble(lat);
				map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(50.86572,
						13.021379), 14));
				map.addMarker(new MarkerOptions()
						.icon(BitmapDescriptorFactory
								.fromResource(R.drawable.star))
						.position(new LatLng(Lat_d, Long_d))
						.anchor(0.5f, 0.5f));
//						.title(record)
//						.snippet(
//								"Öffnungszeiten: Mo-Fr: " + snippet1
//										+ ",  Sa: " + snippet2 + ", So: "
//										+ snippet3));
				// return (record, snippet1, snippet2, snippet3);

				 map.setInfoWindowAdapter(new InfoWindowAdapter() {
				 @Override
				 public
				 View getInfoWindow(Marker arg0) {
				 return null;
				 }
				 @Override
				 public View getInfoContents(Marker marker) {
				
				 View v = getLayoutInflater().inflate(R.layout.marker, null);
				
				 TextView title= (TextView) v.findViewById(R.id.title);
				 TextView snippet= (TextView) v.findViewById(R.id.snippet);
				 title.setText(record);
				 snippet.setText("Öffnungszeiten:\nMo-Fr " + snippet1 +
				 "\nSa " + snippet2 + "\nSo " + snippet3);
				
				 return v;
				 }
				 });

				dbCursor.moveToNext();
			}
		} finally {
			if (database != null) {
				dbHelper.close();
			}
		}

	}
}