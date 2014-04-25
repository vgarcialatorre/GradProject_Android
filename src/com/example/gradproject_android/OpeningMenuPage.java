package com.example.gradproject_android;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OpeningMenuPage extends Activity {
	
	private Button btnHost;
	private Button btnGuest;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_menu);
		
		getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM); 
		getActionBar().setCustomView(R.layout.action_bar_layout);
		
		btnHost = (Button) findViewById(R.id.btnHost);
		btnGuest = (Button) findViewById(R.id.btnGuest);
		
		btnHost.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// open the login page
				Intent myIntent = new Intent(OpeningMenuPage.this, LoginActivity.class);
				OpeningMenuPage.this.startActivity(myIntent);
			}
			
		});
		
		btnGuest.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TO PAGE THAT GUESTS WILL SEE
			}
			
		});
	}
}
