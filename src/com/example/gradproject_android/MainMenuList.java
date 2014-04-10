package com.example.gradproject_android;

import java.net.MalformedURLException;
import java.util.List;

import com.microsoft.windowsazure.mobileservices.*;

import DataModel.Lists;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MainMenuList extends ListActivity{
	static String[] WISHLISTS = new String[]{};
	private Button btnAddList;
	private MobileServiceClient mClient;
	private MobileServiceTable<Lists> listTable;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		btnAddList = (Button)findViewById(R.id.addList_button);

		try {
			mClient = new MobileServiceClient(
					"https://gradproject1.azure-mobile.net/",
					"fyTgKWpAWOaujRNRziHMWWhhMYvsoY84",
					this
					);
			listTable = mClient.getTable(Lists.class);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}

		WISHLISTS = getUsersLists();
		//setListAdapter(new ArrayAdapter<String>(this, R.layout.main_menu,WISHLISTS));

		ListView listView = getListView();
		listView.setTextFilterEnabled(true);

		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				// selected item 
				String product = ((TextView) view).getText().toString();

				// Launching new Activity on selecting single List Item
				Intent i = new Intent(getApplicationContext(), IndividualList.class);
				// sending data to new activity
				i.putExtra("product", product);
				startActivity(i);
			}
		});
	}


	private String[] getUsersLists()
	{

		listTable.where().field("UserID").eq(1).execute(new TableQueryCallback<Lists>(){

			@Override
			public void onCompleted(List<Lists> result, int count,
					Exception exception, ServiceFilterResponse response) {
				if (exception == null) {


				}
				else{

				}
			}
		});
		return null;
	}

}

