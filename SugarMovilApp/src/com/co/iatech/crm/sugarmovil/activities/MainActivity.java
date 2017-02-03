package com.co.iatech.crm.sugarmovil.activities;

import java.util.ArrayList;
import java.util.List;

import com.co.iatech.crm.sugarmovil.R;
import com.co.iatech.crm.sugarmovil.activtities.modules.Modules;
import com.co.iatech.crm.sugarmovil.adapters.DrawerAdapter;
import com.co.iatech.crm.sugarmovil.fragments.AccountsFragment;
import com.co.iatech.crm.sugarmovil.fragments.CalendarFragment;
import com.co.iatech.crm.sugarmovil.fragments.CallsFragment;
import com.co.iatech.crm.sugarmovil.fragments.ContactsFragment;
import com.co.iatech.crm.sugarmovil.fragments.LeadsFragment;
import com.co.iatech.crm.sugarmovil.fragments.NotesFragment;
import com.co.iatech.crm.sugarmovil.fragments.OpportunitiesFragment;
import com.co.iatech.crm.sugarmovil.fragments.ProductsFragment;
import com.co.iatech.crm.sugarmovil.fragments.SubTasksFragment;
import com.co.iatech.crm.sugarmovil.fragments.TasksFragment;
import com.co.iatech.crm.sugarmovil.model.DrawerItem;
import com.co.iatech.crm.sugarmovil.util.GlobalClass;
import com.squareup.picasso.Picasso;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

	/**
	 * Debug.
	 */
	private static final String TAG = "MainActivity";

	/**
	 * Member Variables.
	 */
	private GlobalClass mGlobalVariable;

	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private int selectedItem = 0;
	private DrawerAdapter mDrawerAdapter;
	private List<DrawerItem> mDataList;

	/**
	 * UI References.
	 */
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	private String[] mDrawerListItems;
	private Toolbar mMainToolbar;
	private TextView mMainTextView;
	private SearchView mMainSearchView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// Variable Global
		mGlobalVariable = (GlobalClass) getApplicationContext();
		selectedItem = mGlobalVariable.getSelectedItem();

		// Main Toolbar
		mMainToolbar = (Toolbar) findViewById(R.id.toolbar_main);
		mMainTextView = (TextView) findViewById(R.id.text_main_toolbar);
		mMainSearchView = (SearchView) findViewById(R.id.search_view_main_toolbar);
		int searchPlateId = mMainSearchView.getContext().getResources().getIdentifier("android:id/search_plate", null,
				null);
		View searchPlate = mMainSearchView.findViewById(searchPlateId);
		int searchImgId = getResources().getIdentifier("android:id/search_button", null, null);
		ImageView searchIcon = (ImageView) mMainSearchView.findViewById(searchImgId);
		searchIcon.getLayoutParams().height = 70;
		searchIcon.getLayoutParams().width = 70;
		searchIcon.requestLayout();
		Picasso.with(getApplicationContext()).load(R.drawable.ic_search).resize(70, 70).into(searchIcon);
		int closeSearchImgId = getResources().getIdentifier("android:id/search_close_btn", null, null);
		ImageView closeSearchIcon = (ImageView) mMainSearchView.findViewById(closeSearchImgId);
		closeSearchIcon.getLayoutParams().height = 70;
		closeSearchIcon.getLayoutParams().width = 70;
		closeSearchIcon.requestLayout();
		Picasso.with(getApplicationContext()).load(R.drawable.ic_close_search).resize(70, 70).into(closeSearchIcon);
		int searchTextId = searchPlate.getContext().getResources().getIdentifier("android:id/search_src_text", null,
				null);
		TextView searchText = (TextView) searchPlate.findViewById(searchTextId);
		searchText.setTextColor(Color.WHITE);
		searchText.setHintTextColor(Color.GRAY);

		// Drawer Layout
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
		mDrawerList = (ListView) findViewById(R.id.drawer_list);
		mDataList = new ArrayList<DrawerItem>();
		mTitle = mDrawerTitle = getTitle();

		// Anhadir Drawer Item a mDataList
		mDataList.add(new DrawerItem("Cuentas", R.drawable.ic_accounts));
		mDataList.add(new DrawerItem("Contactos", R.drawable.ic_contacts));
		mDataList.add(new DrawerItem("Oportunidades", R.drawable.ic_opportunities));
		mDataList.add(new DrawerItem("Clientes Potenciales", R.drawable.ic_leads));
		mDataList.add(new DrawerItem("Llamadas", R.drawable.ic_calls));
		mDataList.add(new DrawerItem("Productos", R.drawable.ic_products));
		mDataList.add(new DrawerItem("Tareas", R.drawable.ic_tasks));
		mDataList.add(new DrawerItem("Subtareas", R.drawable.ic_subtasks));
		mDataList.add(new DrawerItem("Notas", R.drawable.ic_notes));
		mDataList.add(new DrawerItem("Calendario", R.drawable.calendarimg));
		mDataList.add(new DrawerItem("Salir", R.drawable.ic_action_cancel));

		mDrawerAdapter = new DrawerAdapter(this, R.layout.item_drawer, mDataList);
		mDrawerList.setAdapter(mDrawerAdapter);

		// Eventos
		mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				selectItem(position);
				selectedItem = position;
			}
		});

		// Drawer Toggle
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mMainToolbar, R.string.drawer_open,
				R.string.drawer_close) {
			public void onDrawerClosed(View view) {
				super.onDrawerClosed(view);
				invalidateOptionsMenu();
				syncState();
			}

			public void onDrawerOpened(View view) {
				super.onDrawerOpened(view);
				invalidateOptionsMenu();
				syncState();
			}
		};

		mDrawerLayout.setDrawerListener(mDrawerToggle);
		setSupportActionBar(mMainToolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setDisplayShowTitleEnabled(false);
		getSupportActionBar().setHomeButtonEnabled(true);
		mDrawerToggle.syncState();

		selectItem(selectedItem);
	}

	@Override
	public void onBackPressed() {
		FragmentManager fm = getFragmentManager();
		if (fm.getBackStackEntryCount() > 0) {
			Log.d(TAG, "Backstack");
			fm.popBackStack();
		} else {
			Log.d(TAG, "Nada en Backstack, Salir");
			// DialogFragment closeFragment = new CloseAppDialogFragment();
			// closeFragment.show(getFragmentManager(), "close");
			Log.d(TAG, "Salir de la aplicacion");
			this.selectItem(0);
		}
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) { 
		case android.R.id.home:
			if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
				mDrawerLayout.closeDrawer(mDrawerList);
			} else {
				mDrawerLayout.openDrawer(mDrawerList);
			}
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void selectItem(int position) {
		Fragment fragment = null;
		Bundle args = new Bundle();

		switch (position) {
			case 0:
				ActivitiesMediator.getInstance().defineActualModule(Modules.ACCOUNTS);
				fragment = AccountsFragment.newInstance();
				break;
			case 1:
				ActivitiesMediator.getInstance().defineActualModule(Modules.CONTACTS);
				fragment = ContactsFragment.newInstance();
				break;
			case 2:
				ActivitiesMediator.getInstance().defineActualModule(Modules.OPPORTUNITIES);
				fragment = OpportunitiesFragment.newInstance();
				break;
			case 3:
				ActivitiesMediator.getInstance().defineActualModule(Modules.LEADS);
				fragment = LeadsFragment.newInstance();
				break;
			case 4:
				ActivitiesMediator.getInstance().defineActualModule(Modules.CALLS);
				fragment = CallsFragment.newInstance();
				break;
			case 5:
				ActivitiesMediator.getInstance().defineActualModule(Modules.PRODUCTS);
				fragment = ProductsFragment.newInstance();
				break;
			case 6:
				ActivitiesMediator.getInstance().defineActualModule(Modules.TASKS);
				fragment = TasksFragment.newInstance();
				break;
			case 7:
				ActivitiesMediator.getInstance().defineActualModule(Modules.SUBTASKS);
				fragment = SubTasksFragment.newInstance();
				break;
			case 8:
				ActivitiesMediator.getInstance().defineActualModule(Modules.NOTES);
				fragment = NotesFragment.newInstance();
				break;
			case 9:
				ActivitiesMediator.getInstance().defineActualModule(Modules.CALENDAR);
				fragment = CalendarFragment.newInstance();
				break;
			case 10:
				System.exit(0);
				break;

		default:
			position = 0;
			fragment = AccountsFragment.newInstance();
			break;
		}
		if(fragment != null){
			fragment.setArguments(args);
			FragmentManager frgManager = getFragmentManager();
			frgManager.beginTransaction().replace(R.id.container, fragment).commit();
			
		}
		
		mDrawerList.setItemChecked(position, true);
		try {
			setTitle(mDataList.get(position).getItemName());
		} catch (java.lang.ArrayIndexOutOfBoundsException aie) {
			setTitle(mDataList.get(0).getItemName());
		}
		mDrawerLayout.closeDrawer(mDrawerList);
	}

	public TextView getMainTextView() {
		return mMainTextView;
	}

	public void setMainTextView(TextView mMainTextView) {
		this.mMainTextView = mMainTextView;
	}

	public SearchView getMainSearchView() {
		return mMainSearchView;
	}

	public void setMainSearchView(SearchView mMainSearchView) {
		this.mMainSearchView = mMainSearchView;
	}
}
