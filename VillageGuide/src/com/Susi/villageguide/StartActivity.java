package com.Susi.villageguide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class StartActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
	}
	public void
		onClickStartNewActivityMap(View view) {
		Intent intentMap = new Intent(this, MapActivity.class);
		startActivity(intentMap);

	}
	public void
	onClickStartNewActivityDatabase(View view) {
	Intent intentDB = new Intent(this, DatabaseActivity.class);
	startActivity(intentDB);

}
}
