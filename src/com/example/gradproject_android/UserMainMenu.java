package com.example.gradproject_android;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import com.example.gradproject_android.R;
import com.microsoft.windowsazure.mobileservices.*;

import DataModel.Lists;
import android.app.Fragment;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class UserMainMenu extends ListFragment {

	public UserMainMenu(){}

	final ArrayList<String> wishlists = new ArrayList<String>();
	private Button btnAddList;
	private MobileServiceClient mClient;
	private MobileServiceTable<Lists> listTable;
	ArrayAdapter<String> yourListsAdapter;
	ArrayAdapter<String> invitedListsAdapter;
	private ListView myListView;
	private ListView invitedListView;
	
	@Override
	public void onCreate(Bundle savedInstance) {
		super.onCreate(savedInstance);
		
		yourListsAdapter = new ArrayAdapter<String>(this.getActivity(), R.layout.list_item, wishlists);
		invitedListsAdapter = new ArrayAdapter<String>(this.getActivity(), R.layout.list_item, wishlists);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.user_main_menu, container, false);
		btnAddList = (Button)rootView.findViewById(R.id.addList_button);

		myListView = (ListView) rootView.findViewById(android.R.id.list);
//		invitedListView = (ListView) rootView.findViewById(R.id.list_invited_list);

		getUsersLists();

		return rootView;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		myListView.setAdapter(yourListsAdapter);
		
	}
	

//	@Override
//	public  void onViewCreated(View view, Bundle savedInstanceState) {
//		super.onViewCreated(view, savedInstanceState);
//		ListView listView = getListView();
//		listView.setTextFilterEnabled(true);
//		//listView
//		listView.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view, int position,
//					long id) {
//				String product = ((TextView) view).getText().toString();
//
//				// Launching new Activity on selecting single List Item
//				Intent i = new Intent(getActivity(), IndividualList.class);
//				// sending data to new activity
//				i.putExtra("product", product);
//				startActivity(i);
//
//			}
//		});
//	}

	private void getUsersLists()
	{
		try {
			mClient = new MobileServiceClient(
					"https://gradproject1.azure-mobile.net/",
					"fyTgKWpAWOaujRNRziHMWWhhMYvsoY84",
					this.getActivity()
					);
			listTable = mClient.getTable(Lists.class);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		listTable.where().field("UserID").eq(1).execute(new TableQueryCallback<Lists>(){

			@Override
			public void onCompleted(List<Lists> result, int count,
					Exception exception, ServiceFilterResponse response) {
				if (exception == null) {
					for(Lists item : result){
						System.out.println("item: " + item.ListTitle);
						wishlists.add(item.ListTitle);
						yourListsAdapter.notifyDataSetChanged();
					}
					//onFinishLoading(wishlists);
				}
				else{

				}
			}
		});
	}


}
