package com.Susi.villageguide;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;

public class MapActivity extends Activity {
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

	}

	public void onClickShowOnMap(View view) {
		// mach was
		GoogleMap map = ((MapFragment) getFragmentManager().findFragmentById(
				R.id.map)).getMap();
		map.addMarker(new MarkerOptions()
				.icon(BitmapDescriptorFactory.fromResource(R.drawable.star))
				.position(new LatLng(50.86572, 13.021379)).anchor(0.5f, 0.5f)
				.title("H�L/S590").snippet("Computer Pool"));

	}
}