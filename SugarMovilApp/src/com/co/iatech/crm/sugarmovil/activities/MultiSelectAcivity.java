package com.co.iatech.crm.sugarmovil.activities;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.co.iatech.crm.sugarmovil.R;
import com.co.iatech.crm.sugarmovil.activities.ui.Message;
import com.co.iatech.crm.sugarmovil.activtities.modules.Modules;
import com.co.iatech.crm.sugarmovil.adapters.MultipleListAdapter;
import com.co.iatech.crm.sugarmovil.core.data.ObjectListClass;
import com.co.iatech.crm.sugarmovil.model.ListInfo;
import com.co.iatech.crm.sugarmovil.util.ListsConversor;
import com.co.iatech.crm.sugarmovil.util.ListsConversor.ConversorsType;
import com.co.iatech.crm.sugarmovil.util.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

public class MultiSelectAcivity extends Activity {
	Context context = null;
	MultipleListAdapter objAdapter;
	ListView lv = null;
	EditText edtSearch = null;
	LinearLayout llContainer = null;
	Button btnOK = null;
	RelativeLayout rlPBContainer = null;
	public static String LIST_TYPE = "LIST_TYPE";
	public static String SELECTED_VALUES = "SELECTED_VALUES";

	private SearchView searchView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			context = this;
			setContentView(R.layout.activity_list_multiselect);
			rlPBContainer = (RelativeLayout) findViewById(R.id.pbcontainer);
			edtSearch = (EditText) findViewById(R.id.input_search);
			llContainer = (LinearLayout) findViewById(R.id.data_container);
			btnOK = (Button) findViewById(R.id.ok_button);
			btnOK.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					finish();
				}
			});
			// Main Toolbar
			Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_list_multiselect);
			TextView toolbarTextView = (TextView) findViewById(R.id.text_toolbar_list_multiselect);

			searchView = (SearchView) findViewById(R.id.search_view_list_multiselect);

			searchView.setOnSearchClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
				}
			});

			searchView.setOnCloseListener(new SearchView.OnCloseListener() {
				@Override
				public boolean onClose() {
				   InputMethodManager imm = (InputMethodManager) MultiSelectAcivity.this
							.getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(searchView.getWindowToken(), 0);
					return false;
				}
			});

			searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
				@Override
				public boolean onQueryTextSubmit(String query) {
					try {
						objAdapter.filter(query);
					} catch (Exception e) {

					}

					return false;
				}

				@Override
				public boolean onQueryTextChange(String newText) {
					try {
						// Filtro para select
						objAdapter.filter(newText);

					} catch (Exception e) {

					}

					return false;
				}
			});
			showInfo();
		} catch (Exception e) {
			Message.showFinalMessage(getFragmentManager(), Utils.errorToString(e), MultiSelectAcivity.this,
					Modules.CALENDAR);
		}

	}

	private void showInfo() {
		Thread thread = new Thread() {
			@Override
			public void run() {
				showPB();
				try {

					Intent intent = getIntent();
					String listInfo = intent.getStringExtra(LIST_TYPE);
					String selectedValues = intent.getStringExtra(SELECTED_VALUES);
					selectedValues = selectedValues == null?"":selectedValues;
					
					ConversorsType listSelected = ConversorsType.valueOf(listInfo);

					List<String> list = ListsConversor.getValuesList(listSelected);

					if (list != null && !list.isEmpty()) {
						list.remove(0);

						try {
							ObjectListClass.dataList.clear();
							ObjectListClass.type = listSelected;
						} catch (Exception e) {
						}

						for (String iList : list) {
							ListInfo li = new ListInfo();
							li.setListName(iList);
							if(selectedValues.contains(iList)){
								li.setSelected(true);
							}
							ObjectListClass.dataList.add(li);
						}

						lv = new ListView(context);
						lv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								llContainer.addView(lv);
							}
						});
						Collections.sort(ObjectListClass.dataList, new Comparator<ListInfo>() {
							@Override
							public int compare(ListInfo lhs, ListInfo rhs) {
								return lhs.getListName().compareTo(rhs.getListName());
							}
						});
						objAdapter = new MultipleListAdapter(MultiSelectAcivity.this, ObjectListClass.dataList);
						lv.setAdapter(objAdapter);
						lv.setOnItemClickListener(new OnItemClickListener() {
							@Override
							public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
								CheckBox chk = (CheckBox) view.findViewById(R.id.contactcheck);
								ListInfo bean = ObjectListClass.dataList.get(position);
								if (bean.isSelected()) {
									bean.setSelected(false);
									chk.setChecked(false);
								} else {
									bean.setSelected(true);
									chk.setChecked(true);
								}
							}
						});
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				hidePB();
			}
		};
		thread.start();
	}

	void showPB() {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				rlPBContainer.setVisibility(View.VISIBLE);
				edtSearch.setVisibility(View.GONE);
				btnOK.setVisibility(View.GONE);
			}
		});
	}

	void hidePB() {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				rlPBContainer.setVisibility(View.GONE);
				edtSearch.setVisibility(View.VISIBLE);
				btnOK.setVisibility(View.VISIBLE);
			}
		});
	}
}