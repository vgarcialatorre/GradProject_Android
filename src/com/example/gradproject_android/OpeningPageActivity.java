package com.example.gradproject_android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class OpeningPageActivity extends Activity {

	Button btnLogin;
	Button btnGuestVisit;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.visit_type_selection);
		btnLogin = (Button) findViewById(R.id.btnLogin);
		btnGuestVisit = (Button) findViewById(R.id.btnGuest);

		btnLogin.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				Intent myIntent = new Intent(OpeningPageActivity.this, LoginActivity.class);
				OpeningPageActivity.this.startActivity(myIntent);
			}

		});
		
		btnGuestVisit.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0){
				
			}
		});

	}
}
