package com.example.gradproject_android;

import java.net.MalformedURLException;
import java.util.List;

import DataModel.Users;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.microsoft.windowsazure.mobileservices.*;
import com.example.gradproject_android.R;

public class LoginActivity extends Activity {
	Button btnLogin;
	Button btnLinkToRegister;
	EditText inputEmail;
	EditText inputPassword;
	TextView loginErrorMsg;

	// JSON Response node names
	private static String KEY_SUCCESS = "success";
	private static String KEY_ERROR = "error";
	private static String KEY_ERROR_MSG = "error_msg";
	private static String KEY_UID = "uid";
	private static String KEY_NAME = "name";
	private static String KEY_EMAIL = "email";
	private static String KEY_CREATED_AT = "created_at";
	
	private MobileServiceTable<Users> userTable;
	private MobileServiceClient mClient;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		try {
			mClient = new MobileServiceClient(
				      "https://gradproject1.azure-mobile.net/",
				      "fyTgKWpAWOaujRNRziHMWWhhMYvsoY84",
				      this
				);
			userTable = mClient.getTable(Users.class);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		
		// Importing all assets like buttons, text fields
		inputEmail = (EditText) findViewById(R.id.loginEmail);
		inputPassword = (EditText) findViewById(R.id.loginPassword);
		btnLogin = (Button) findViewById(R.id.btnLogin);
		btnLinkToRegister = (Button) findViewById(R.id.btnLinkToRegisterScreen);
		loginErrorMsg = (TextView) findViewById(R.id.login_error);

		// Login button Click Event
		btnLogin.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				final String email = inputEmail.getText().toString();
				final String password = inputPassword.getText().toString();
				System.out.println("on click");
				Thread thread = new Thread(new Runnable(){
				    @Override
				    public void run() {
				        try {
							loginUser(email, password);
				        } catch (Exception e) {
				            e.printStackTrace();
				        }
				    }
				});

				thread.start(); 
			}
		});

		// Link to Register Screen
		btnLinkToRegister.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				Intent i = new Intent(getApplicationContext(),
						RegisterActivity.class);
				startActivity(i);
				finish();
			}
		});
	}

	private void loginUser(String theUsername, String thePassword)
	{
		userTable.where().field("Username").eq(theUsername).and().field("Password").eq(thePassword).execute(new TableQueryCallback<Users>()
				{

					@Override
					public void onCompleted(List<Users> result, int count,
							Exception exception, ServiceFilterResponse response) {
						if (exception == null) {
			                  // Insert succeeded
							if(!result.isEmpty()){
								Intent myIntent = new Intent(LoginActivity.this, MainMenuList.class);
								LoginActivity.this.startActivity(myIntent);
								System.out.println("Found user with that password");
							}
							else{
								System.out.println("No password");
								Toast t = Toast.makeText(getApplicationContext(), "Incorrect username/password",Toast.LENGTH_LONG);
								t.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER, 0, 0);
								t.show();
							}
			            } else {
			                  // Insert failed
			            	System.out.println("Failure: " + exception);
			            	System.out.println("Failure: " + exception.getCause());
			            }
						
					}
			
				});
	}
}
