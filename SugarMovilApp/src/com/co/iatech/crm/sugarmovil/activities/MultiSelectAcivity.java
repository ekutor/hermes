package com.co.iatech.crm.sugarmovil.activities;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import com.co.iatech.crm.sugarmovil.R;
import com.co.iatech.crm.sugarmovil.activities.ui.Message;
import com.co.iatech.crm.sugarmovil.activtities.modules.Modules;
import com.co.iatech.crm.sugarmovil.adapters.ContactsAdapter;
import com.co.iatech.crm.sugarmovil.core.data.ContactsListClass;
import com.co.iatech.crm.sugarmovil.model.ContactObject;
import com.co.iatech.crm.sugarmovil.util.ListsConversor;
import com.co.iatech.crm.sugarmovil.util.ListsConversor.ConversorsType;
import com.co.iatech.crm.sugarmovil.util.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.widget.Toolbar;
import android.widget.SearchView;
import android.widget.TextView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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
import android.widget.Toast;

public class MultiSelectAcivity extends Activity {
	Context context = null;
	ContactsAdapter objAdapter;
	ListView lv = null;
	EditText edtSearch = null;
	LinearLayout llContainer = null;
	Button btnOK = null;
	RelativeLayout rlPBContainer = null;
	public static String LIST_TYPE="LIST_TYPE";
	
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
					// TODO Auto-generated method stub
					getSelectedContacts();
				}
			});
			// Main Toolbar
	        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_list_multiselect);
	        TextView toolbarTextView = (TextView) findViewById(R.id.text_toolbar_list_multiselect);
	        
	        searchView = (SearchView) findViewById(R.id.search_view_list_multiselect);
	        
	        
	        searchView.setOnSearchClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	                //toolbarTextView.setVisibility(View.GONE);
	            }
	        });

	        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
	            @Override
	            public boolean onClose() {
	                //toolbarTextView.setVisibility(View.VISIBLE);

	                InputMethodManager imm = (InputMethodManager) MultiSelectAcivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
	                imm.hideSoftInputFromWindow(searchView.getWindowToken(), 0);

	                try {
	                   // ((RecyclerGenericAdapter) mRecyclerView.getAdapter()).flushFilter();
	                } catch (Exception e) {
	                   
	                }

	                return false;
	            }
	        });

	        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
	            @Override
	            public boolean onQueryTextSubmit(String query) {
	                try {
	                    // Filtro para select
	                  //  ((RecyclerGenericAdapter) mRecyclerView.getAdapter()).setFilter(query);

	                     objAdapter.filter(query);
	                } catch (Exception e) {
	                    
	                }

	                return false;
	            }

	            @Override
	            public boolean onQueryTextChange(String newText) {
	                try {
	                    // Filtro para select
	                    //((RecyclerGenericAdapter) mRecyclerView.getAdapter()).setFilter(newText);

	                      objAdapter.filter(newText);

	                } catch (Exception e) {
	                   
	                }

	                return false;
	            }
	        });
			showInfo();
		} catch (Exception e) {
			Message.showFinalMessage(getFragmentManager(), Utils.errorToString(e), MultiSelectAcivity.this, Modules.CALENDAR);
		}

	}

	private void getSelectedContacts() {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		for (ContactObject bean : ContactsListClass.phoneList) {
			if (bean.isSelected()) {
				sb.append(bean.getName());
				sb.append(",");
			}
		}
		String s = sb.toString().trim();
		if (TextUtils.isEmpty(s)) {
			Toast.makeText(context, "Select atleast one Contact", Toast.LENGTH_SHORT).show();
		} else {
			s = s.substring(0, s.length() - 1);
			Toast.makeText(context, "Selected Contacts : " + s, Toast.LENGTH_SHORT).show();
		}
	}

	private void showInfo() {
		Thread thread = new Thread() {
			@Override
			public void run() {
				showPB();
				try {
					
				/*	Intent intent = getIntent();
                    String listInfo = intent.getStringExtra(LIST_TYPE);
                    ConversorsType listSelected = ConversorsType.valueOf(listInfo);
                    
                    List<String> list = ListsConversor.getValuesList(listSelected);
               */     
					Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
							null, null, null);
					try {
						ContactsListClass.phoneList.clear();
					} catch (Exception e) {
					}
					while (phones.moveToNext()) {
						String phoneName = phones
								.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
						String phoneNumber = phones
								.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
						String phoneImage = phones
								.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID));

						ContactObject cp = new ContactObject();

						cp.setName(phoneName);
						cp.setNumber(phoneNumber);
						cp.setImage(phoneImage);
						ContactsListClass.phoneList.add(cp);
					}
					phones.close();
					lv = new ListView(context);
					lv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							// TODO Auto-generated method stub
							llContainer.addView(lv);
						}
					});
					Collections.sort(ContactsListClass.phoneList, new Comparator<ContactObject>() {
						@Override
						public int compare(ContactObject lhs, ContactObject rhs) {
							return lhs.getName().compareTo(rhs.getName());
						}
					});
					objAdapter = new ContactsAdapter(MultiSelectAcivity.this, ContactsListClass.phoneList);
					lv.setAdapter(objAdapter);
					lv.setOnItemClickListener(new OnItemClickListener() {
						@Override
						public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
							CheckBox chk = (CheckBox) view.findViewById(R.id.contactcheck);
							ContactObject bean = ContactsListClass.phoneList.get(position);
							if (bean.isSelected()) {
								bean.setSelected(false);
								chk.setChecked(false);
							} else {
								bean.setSelected(true);
								chk.setChecked(true);
							}
						}
					});
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
				// TODO Auto-generated method stub
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
				// TODO Auto-generated method stub
				rlPBContainer.setVisibility(View.GONE);
				edtSearch.setVisibility(View.VISIBLE);
				btnOK.setVisibility(View.VISIBLE);
			}
		});
	}
}