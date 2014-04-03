package com.example.gradproject_android;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.gradproject_android.R;
import com.example.library.DatabaseHandler;
import com.example.library.UserFunctions;

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

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

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
				//				UserFunctions userFunction = new UserFunctions();
				//				Log.d("Button", "Login");
				//				JSONObject json = userFunction.loginUser(email, password);
				//
				//				// check for login response
				//				try {
				//					if (json.getString(KEY_SUCCESS) != null) {
				//						loginErrorMsg.setText("");
				//						String res = json.getString(KEY_SUCCESS); 
				//						if(Integer.parseInt(res) == 1){
				//							// user successfully logged in
				//							// Store user details in SQLite Database
				//							DatabaseHandler db = new DatabaseHandler(getApplicationContext());
				//							JSONObject json_user = json.getJSONObject("user");
				//							
				//							// Clear all previous data in database
				//							userFunction.logoutUser(getApplicationContext());
				//							db.addUser(json_user.getString(KEY_NAME), json_user.getString(KEY_EMAIL), json.getString(KEY_UID), json_user.getString(KEY_CREATED_AT));						
				//							
				//							// Launch Dashboard Screen
				//							Intent dashboard = new Intent(getApplicationContext(), DashboardActivity.class);
				//							
				//							// Close all views before launching D nkashboard
				//							dashboard.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				//							startActivity(dashboard);
				//							
				//							// Close Login Screen
				//							finish();
				//						}else{
				//							// Error in login
				//							loginErrorMsg.setText("Incorrect username/password");
				//						}
				//					}
				//				} catch (JSONException e) {
				//					e.printStackTrace();
				//				}
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
		String json = "{\"username\":\""+ theUsername + "\"}";
	    HttpClient httpClient = new DefaultHttpClient();

	    try {
	        HttpPost request = new HttpPost("http://10.0.2.2/GradProject/DB_BabyList.php?function=checkUser");
	        StringEntity params =new StringEntity("message=" + json);

	        request.addHeader("content-type", "application/x-www-form-urlencoded");

	        request.setEntity(params);

	        HttpResponse response = httpClient.execute(request);
 
	        System.out.println(response);
	        // handle response here...

	       
	    } catch (Exception ex) {

	    	System.out.println("Exception: " + ex);
	    } finally {
	        httpClient.getConnectionManager().shutdown();
	    }
	}
}
