package com.co.iatech.crm.sugarmovil.activities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.co.iatech.crm.sugarmovil.R;
import com.co.iatech.crm.sugarmovil.activities.tasks.GenericTask;
import com.co.iatech.crm.sugarmovil.activities.ui.Message;
import com.co.iatech.crm.sugarmovil.activtities.modules.ContactsModuleActions;
import com.co.iatech.crm.sugarmovil.activtities.modules.Modules;
import com.co.iatech.crm.sugarmovil.adapters.RecyclerGenericAdapter;
import com.co.iatech.crm.sugarmovil.adapters.search.AdapterSearchUtil;
import com.co.iatech.crm.sugarmovil.conex.TypeInfoServer;
import com.co.iatech.crm.sugarmovil.core.data.DataManager;
import com.co.iatech.crm.sugarmovil.model.Contacto;
import com.co.iatech.crm.sugarmovil.util.ListsHolder;
import com.co.iatech.crm.sugarmovil.util.Utils;
import com.co.iatech.crm.sugarmovil.util.ListsHolder.ListsHolderType;
import com.software.shell.fab.ActionButton;
import com.squareup.picasso.Picasso;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

public class ListContactActivity extends ContactsModuleActions {
	/**
	 * Debug.
	 */
	private static final String TAG = "ListContactActivity";
	/**
	 * Member Variables.
	 */

	private String idCuentaActual;

	/**
	 * UI References.
	 */
	private Toolbar mToolbar;
	private TextView mToolbarTextView;
	private SearchView mSearchView;
	private RecyclerView mRecyclerView;
	private RecyclerView.Adapter mRecyclerViewAdapter;
	private RecyclerView.LayoutManager mRecyclerViewLayoutManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_contact);
		try {
			// SoftKey
			this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

			Intent intent = getIntent();
			idCuentaActual = intent.getStringExtra(Modules.ACCOUNTS.name());
			
			// Main Toolbar
			mToolbar = (Toolbar) findViewById(R.id.toolbar_list_contact);
			setSupportActionBar(mToolbar);
			getSupportActionBar().setDisplayHomeAsUpEnabled(false);
			getSupportActionBar().setHomeButtonEnabled(false);
			mToolbarTextView = (TextView) findViewById(R.id.text_toolbar_list_contact);

			// SearchView
			mSearchView = (SearchView) findViewById(R.id.search_view_list_contact);
			int searchPlateId = mSearchView.getContext().getResources().getIdentifier("android:id/search_plate", null,
					null);
			View searchPlate = mSearchView.findViewById(searchPlateId);
			int searchImgId = getResources().getIdentifier("android:id/search_button", null, null);
			ImageView searchIcon = (ImageView) mSearchView.findViewById(searchImgId);
			searchIcon.getLayoutParams().height = 56;
			searchIcon.getLayoutParams().width = 56;
			searchIcon.requestLayout();
			Picasso.with(getApplicationContext()).load(R.drawable.ic_search).resize(56, 56).into(searchIcon);
			int closeSearchImgId = getResources().getIdentifier("android:id/search_close_btn", null, null);
			ImageView closeSearchIcon = (ImageView) mSearchView.findViewById(closeSearchImgId);
			closeSearchIcon.getLayoutParams().height = 56;
			closeSearchIcon.getLayoutParams().width = 56;
			closeSearchIcon.requestLayout();
			Picasso.with(getApplicationContext()).load(R.drawable.ic_close_search).resize(56, 56).into(closeSearchIcon);
			int searchTextId = searchPlate.getContext().getResources().getIdentifier("android:id/search_src_text", null,
					null);
			TextView searchText = (TextView) searchPlate.findViewById(searchTextId);
			searchText.setTextColor(Color.WHITE);
			searchText.setHintTextColor(Color.GRAY);

			// Recycler
			mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_list_contact);
			mRecyclerView.setHasFixedSize(true);

			mRecyclerViewLayoutManager = new LinearLayoutManager(ListContactActivity.this);
			mRecyclerView.setLayoutManager(mRecyclerViewLayoutManager);

			// Eventos
			mSearchView.setOnSearchClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					mToolbarTextView.setVisibility(View.GONE);
				}
			});

			mSearchView.setOnCloseListener(new SearchView.OnCloseListener() {
				@Override
				public boolean onClose() {
					mToolbarTextView.setVisibility(View.VISIBLE);

					InputMethodManager imm = (InputMethodManager) ListContactActivity.this
							.getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(mSearchView.getWindowToken(), 0);

					try {
						((RecyclerGenericAdapter) mRecyclerView.getAdapter()).flushFilter();
					} catch (Exception e) {
						Log.d(TAG, "Error removiendo el filtro de busqueda");
					}

					return false;
				}
			});

			mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
				@Override
				public boolean onQueryTextSubmit(String query) {
					try {
						// Filtro para select
						((RecyclerGenericAdapter) mRecyclerView.getAdapter()).setFilter(query);
					} catch (Exception e) {
						Log.d(TAG, "Error añadiendo el filtro de busqueda");
					}

					return false;
				}

				@Override
				public boolean onQueryTextChange(String newText) {
					try {
						// Filtro para select
						((RecyclerGenericAdapter) mRecyclerView.getAdapter()).setFilter(newText);
					} catch (Exception e) {

					}

					return false;
				}
			});
		} catch (Exception e) {
			Message.showFinalMessage(getFragmentManager(), Utils.errorToString(e), this, MODULE);
		}

	}

	@Override
	protected void onResume() {
		this.chargeViewInfo();
		super.onResume();
	}
	
	@Override
	public void chargeViewInfo() {
		String[] params = { "idAccount", idCuentaActual };
		this.executeTask(params, TypeInfoServer.getContactsxAccount);
	}

	@Override
	public void addInfo(String serverResponse) {
		
		DataManager manager = DataManager.getInstance();
		try {

			JSONObject jObj = new JSONObject(serverResponse);
			JSONArray jArr = jObj.getJSONArray(RESPONSE_TEXT_CORECT_ID);
			manager.contactsxAccountsInfo.clear();
			for (int i = 0; i < jArr.length(); i++) {
				JSONObject obj = jArr.getJSONObject(i);
				manager.contactsxAccountsInfo.add(new Contacto(obj));
			}
			if (manager.contactsxAccountsInfo.size() > 0) {
				ListsHolder.saveList(ListsHolderType.CONTACTS_ACCOUNTS, DataManager.getInstance().contactsxAccountsInfo);
				RecyclerView.Adapter rv = new RecyclerGenericAdapter(this.getApplicationContext(),
						AdapterSearchUtil.transform(manager.contactsxAccountsInfo), MODULE);
				this.mRecyclerView.setAdapter(rv);
			} else {
				Message.showShortExt("Esta cuenta no tiene contactos asociados", getApplicationContext());
			}
		} catch (Exception e) {
			Message.showShortExt(Utils.errorToString(e), getApplicationContext());
		}

	}

	@Override
	public void applyActions() {
	}
}
