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
		final String Titel = intentMap1.getStringExtra("Titel");
		final String OZ1 = intentMap1.getStringExtra("OH1");
		final String OZ2 = intentMap1.getStringExtra("OH2");
		final String OZ3 = intentMap1.getStringExtra("OH3");
		Bundle b = getIntent().getExtras();
		if (Titel != null) {
			map.moveCamera(CameraUpdateFactory.newLatLngZoom(
					new LatLng(b.getDouble("Lat"), b.getDouble("Long")), 16));
			map.addMarker(new MarkerOptions()
					.icon(BitmapDescriptorFactory.fromResource(R.drawable.star))
					.position(
							new LatLng(b.getDouble("Lat"), b.getDouble("Long")))
					.anchor(0.5f, 0.5f));
//					.title(Titel)
//					.snippet(
//							"Öffnungszeiten: Mo-Fr: " + OZ1 + ",  Sa: " + OZ2
//									+ ", So: " + OZ3));
            map.setInfoWindowAdapter(new InfoWindowAdapter() {
                
                @Override
                public View getInfoWindow(Marker arg0) {
                return null;
                }

                @Override
                public View getInfoContents(Marker marker) {
                       
                  
                      
                      record = Titel;
                      snippet1 = OZ1;
                      snippet2 = OZ2;
                      snippet3 = OZ3;
               
                      View v = getLayoutInflater().inflate(R.layout.marker, null);
               
                      TextView title= (TextView) v.findViewById(R.id.title);
                      TextView snippet= (TextView) v.findViewById(R.id.snippet);
                      title.setText(record);
                      snippet.setText("Öffnungszeiten:\nMo-Fr " + snippet1 +"\nSa " + snippet2 + "\nSo " + snippet3);
               
                return v;
                }
                });

		}

	}

	public void onClickShowOnMap(View view) {
		// Datenbankabfrage
		GoogleMap map = ((MapFragment) getFragmentManager().findFragmentById(
				R.id.map)).getMap();
		map.clear();
		try {
			database = dbHelper.getDataBase();

			// DB Abfrage
			dbCursor = database
					.rawQuery(
							"SELECT  Name, Oeffnungszeiten_Montag_bis_Freitag, Oeffnungszeiten_Samstag, Oeffnungszeiten_Sonntag, Long, Lat FROM Gesundheit;",
							null);
			dbCursor.moveToFirst();
			final int index = dbCursor.getColumnIndex("Name");
			final int index2 = dbCursor
					.getColumnIndex("Oeffnungszeiten_Montag_bis_Freitag");
			final int index3 = dbCursor
					.getColumnIndex("Oeffnungszeiten_Samstag");
			final int index4 = dbCursor
					.getColumnIndex("Oeffnungszeiten_Sonntag");
			int index5 = dbCursor.getColumnIndex("Long");
			int index6 = dbCursor.getColumnIndex("Lat");

			int counter = 0;

			while (!dbCursor.isAfterLast()) {

				String lgn = dbCursor.getString(index5);
				String lat = dbCursor.getString(index6);

				Double Long_d = Double.parseDouble(lgn);
				Double Lat_d = Double.parseDouble(lat);

				map.addMarker(new MarkerOptions()
						.icon(BitmapDescriptorFactory
								.fromResource(R.drawable.star))
						.position(new LatLng(Lat_d, Long_d)).anchor(0.5f, 0.5f)
						.title("" + counter));

				dbCursor.moveToNext();
				counter++;
			}

			map.setInfoWindowAdapter(new InfoWindowAdapter() {

				@Override
				public View getInfoWindow(Marker arg0) {
					return null;
				}

				@Override
				public View getInfoContents(Marker marker) {

					int id = Integer.parseInt(marker.getTitle());

					dbCursor.moveToPosition(id);

					record = dbCursor.getString(index);
					snippet1 = dbCursor.getString(index2);
					snippet2 = dbCursor.getString(index3);
					snippet3 = dbCursor.getString(index4);

					View v = getLayoutInflater().inflate(R.layout.marker, null);

					TextView title = (TextView) v.findViewById(R.id.title);
					TextView snippet = (TextView) v.findViewById(R.id.snippet);
					title.setText(record);
					snippet.setText("Öffnungszeiten:\nMo-Fr " + snippet1
							+ "\nSa " + snippet2 + "\nSo " + snippet3);

					return v;
				}
			});

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
			database = dbHelper.getDataBase();

			// DB Abfrage
			dbCursor = database
					.rawQuery(
							"SELECT  Name, Oeffnungszeiten_Montag_bis_Freitag, Oeffnungszeiten_Samstag, Oeffnungszeiten_Sonntag, Long, Lat FROM Hunger;",
							null);
			dbCursor.moveToFirst();
			final int index = dbCursor.getColumnIndex("Name");
			final int index2 = dbCursor
					.getColumnIndex("Oeffnungszeiten_Montag_bis_Freitag");
			final int index3 = dbCursor
					.getColumnIndex("Oeffnungszeiten_Samstag");
			final int index4 = dbCursor
					.getColumnIndex("Oeffnungszeiten_Sonntag");
			int index5 = dbCursor.getColumnIndex("Long");
			int index6 = dbCursor.getColumnIndex("Lat");

			int counter = 0;

			while (!dbCursor.isAfterLast()) {

				String lgn = dbCursor.getString(index5);
				String lat = dbCursor.getString(index6);

				Double Long_d = Double.parseDouble(lgn);
				Double Lat_d = Double.parseDouble(lat);

				map.addMarker(new MarkerOptions()
						.icon(BitmapDescriptorFactory
								.fromResource(R.drawable.star))
						.position(new LatLng(Lat_d, Long_d)).anchor(0.5f, 0.5f)
						.title("" + counter));

				dbCursor.moveToNext();
				counter++;
			}

			map.setInfoWindowAdapter(new InfoWindowAdapter() {

				@Override
				public View getInfoWindow(Marker arg0) {
					return null;
				}

				@Override
				public View getInfoContents(Marker marker) {

					int id = Integer.parseInt(marker.getTitle());

					dbCursor.moveToPosition(id);

					record = dbCursor.getString(index);
					snippet1 = dbCursor.getString(index2);
					snippet2 = dbCursor.getString(index3);
					snippet3 = dbCursor.getString(index4);

					View v = getLayoutInflater().inflate(R.layout.marker, null);

					TextView title = (TextView) v.findViewById(R.id.title);
					TextView snippet = (TextView) v.findViewById(R.id.snippet);
					title.setText(record);
					snippet.setText("Öffnungszeiten:\nMo-Fr " + snippet1
							+ "\nSa " + snippet2 + "\nSo " + snippet3);

					return v;
				}
			});

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
			database = dbHelper.getDataBase();

			// DB Abfrage
			dbCursor = database
					.rawQuery(
							"SELECT  Name, Oeffnungszeiten_Montag_bis_Freitag, Oeffnungszeiten_Samstag, Oeffnungszeiten_Sonntag, Long, Lat FROM Sehenswuerdigkeiten;",
							null);
			dbCursor.moveToFirst();
			final int index = dbCursor.getColumnIndex("Name");
			final int index2 = dbCursor
					.getColumnIndex("Oeffnungszeiten_Montag_bis_Freitag");
			final int index3 = dbCursor
					.getColumnIndex("Oeffnungszeiten_Samstag");
			final int index4 = dbCursor
					.getColumnIndex("Oeffnungszeiten_Sonntag");
			int index5 = dbCursor.getColumnIndex("Long");
			int index6 = dbCursor.getColumnIndex("Lat");

			int counter = 0;

			while (!dbCursor.isAfterLast()) {

				String lgn = dbCursor.getString(index5);
				String lat = dbCursor.getString(index6);

				Double Long_d = Double.parseDouble(lgn);
				Double Lat_d = Double.parseDouble(lat);

				map.addMarker(new MarkerOptions()
						.icon(BitmapDescriptorFactory
								.fromResource(R.drawable.star))
						.position(new LatLng(Lat_d, Long_d)).anchor(0.5f, 0.5f)
						.title("" + counter));

				dbCursor.moveToNext();
				counter++;
			}

			map.setInfoWindowAdapter(new InfoWindowAdapter() {

				@Override
				public View getInfoWindow(Marker arg0) {
					return null;
				}

				@Override
				public View getInfoContents(Marker marker) {

					int id = Integer.parseInt(marker.getTitle());

					dbCursor.moveToPosition(id);

					record = dbCursor.getString(index);
					snippet1 = dbCursor.getString(index2);
					snippet2 = dbCursor.getString(index3);
					snippet3 = dbCursor.getString(index4);

					View v = getLayoutInflater().inflate(R.layout.marker, null);

					TextView title = (TextView) v.findViewById(R.id.title);
					TextView snippet = (TextView) v.findViewById(R.id.snippet);
					title.setText(record);
					snippet.setText("Öffnungszeiten:\nMo-Fr " + snippet1
							+ "\nSa " + snippet2 + "\nSo " + snippet3);

					return v;
				}
			});

		} finally {
			if (database != null) {
				dbHelper.close();
			}
		}
	}
}