package com.co.iatech.crm.sugarmovil.activities;

import java.util.ArrayList;
import java.util.List;

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

import com.co.iatech.crm.sugarmovil.R;
import com.co.iatech.crm.sugarmovil.adapters.DrawerAdapter;
import com.co.iatech.crm.sugarmovil.fragments.AccountsFragment;
import com.co.iatech.crm.sugarmovil.model.DrawerItem;
import com.co.iatech.crm.sugarmovil.model.User;
import com.co.iatech.crm.sugarmovil.util.GlobalClass;
import com.squareup.picasso.Picasso;


public class MainActivity extends AppCompatActivity {

    /**
     * Debug.
     */
    private static final String TAG = "MainActivity";

    /**
     * Member Variables.
     */
    private GlobalClass mGlobalVariable;
    private String mUrl;
    private User mUsuario;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private int mSelectedButton = 0;
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
        mUrl = mGlobalVariable.getUrl();
        mSelectedButton = mGlobalVariable.getmSelectedButton();
        Log.d(TAG, mUrl);

        // Main Toolbar
        mMainToolbar = (Toolbar) findViewById(R.id.toolbar_main);
        mMainTextView = (TextView) findViewById(R.id.text_main_toolbar);
        mMainSearchView = (SearchView) findViewById(R.id.search_view_main_toolbar);
        int searchPlateId = mMainSearchView.getContext().getResources().getIdentifier("android:id/search_plate", null, null);
        View searchPlate = mMainSearchView.findViewById(searchPlateId);
        int searchImgId = getResources().getIdentifier("android:id/search_button", null, null);
        ImageView searchIcon = (ImageView) mMainSearchView.findViewById(searchImgId);
        searchIcon.getLayoutParams().height = 56;
        searchIcon.getLayoutParams().width = 56;
        searchIcon.requestLayout();
        Picasso.with(getApplicationContext()).load(R.drawable.ic_search).resize(56, 56).into(searchIcon);
        int closeSearchImgId = getResources().getIdentifier("android:id/search_close_btn", null, null);
        ImageView closeSearchIcon = (ImageView) mMainSearchView.findViewById(closeSearchImgId);
        closeSearchIcon.getLayoutParams().height = 56;
        closeSearchIcon.getLayoutParams().width = 56;
        closeSearchIcon.requestLayout();
        Picasso.with(getApplicationContext()).load(R.drawable.ic_close_search).resize(56,56).into(closeSearchIcon);
        int searchTextId = searchPlate.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
        TextView searchText = (TextView) searchPlate.findViewById(searchTextId);
        searchText.setTextColor(Color.WHITE);
        searchText.setHintTextColor(Color.GRAY);

        // Drawer Layout
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
                GravityCompat.START);
        mDrawerList = (ListView) findViewById(R.id.drawer_list);
        mDataList = new ArrayList<>();
        mTitle = mDrawerTitle = getTitle();

        // Anhadir Drawer Item a mDataList
        mDataList.add(new DrawerItem("Cuentas", R.drawable.ic_accounts));
        mDataList.add(new DrawerItem("Contactos", R.drawable.ic_contacts));
        mDataList.add(new DrawerItem("Oportunidades", R.drawable.ic_opportunities));
        mDataList.add(new DrawerItem("Llamadas", R.drawable.ic_calls));
//        mDataList.add(new DrawerItem("Productos", R.drawable.ic_products));
//        mDataList.add(new DrawerItem("Clientes Potenciales", R.drawable.ic_leads));
//        mDataList.add(new DrawerItem("Tareas", R.drawable.ic_tasks));

        mDrawerAdapter = new DrawerAdapter(this, R.layout.item_drawer,
                mDataList);
        mDrawerList.setAdapter(mDrawerAdapter);

        // Eventos
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem(position);
                mSelectedButton = position;
            }
        });

        // Drawer Toggle
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mMainToolbar, R.string.drawer_open, R.string.drawer_close) {
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

        selectItem(mSelectedButton);
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
            Log.d(TAG, "Salir de la aplicación");
            finish();
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
        fragment = AccountsFragment.newInstance();
//        switch (position) {
//            case 0:
//                fragment = AccountsFragment.newInstance();
//                break;
//            case 1:
//                fragment = ContactsFragment.newInstance();
//                break;
//            case 2:
//                fragment = OpportunitiesFragment.newInstance();
//                break;
//            case 3:
//                fragment = CallsFragment.newInstance();
//                break;
//            case 4:
//                fragment = ProductsFragment.newInstance();
//                break;
//            case 5:
//                fragment = ClientsFragment.newInstance();
//                break;
//            case 6:
//                fragment = TasksFragment.newInstance();
//                break;
//            default:
//                break;
//        }

        fragment.setArguments(args);
        FragmentManager frgManager = getFragmentManager();
        frgManager.beginTransaction().replace(R.id.container, fragment)
                .commit();

        mDrawerList.setItemChecked(position, true);
        setTitle(mDataList.get(position).getItemName());
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
