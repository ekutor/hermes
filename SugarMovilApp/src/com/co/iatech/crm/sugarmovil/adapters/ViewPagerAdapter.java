package com.co.iatech.crm.sugarmovil.adapters;

import java.util.Locale;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.co.iatech.crm.sugarmovil.core.Info;
import com.co.iatech.crm.sugarmovil.fragments.AccountFragmentActivity;
import com.co.iatech.crm.sugarmovil.fragments.AccountStrategyFragmentActivity;
import com.co.iatech.crm.sugarmovil.model.CuentaDetalle;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private CuentaDetalle cuentaActual;

	public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    public void setActualAccount(CuentaDetalle cuentaActual){
    	this.cuentaActual = cuentaActual;
    }

    @Override
    public int getCount() {
        // Returns the number of tabs
        return 2;
    }

    @Override
    public Fragment getItem(int position) {
        // Returns a new instance of the fragment
    	Fragment f= null;
    	Bundle args = new Bundle();
        args.putParcelable(Info.OBJECT.name(), cuentaActual);
        
        switch (position) {
            case 0:
            	f = new AccountFragmentActivity();
            	
                f.setArguments(args);
                break;
            case 1:
            	f = new AccountStrategyFragmentActivity();
            	
                f.setArguments(args);
                break;
            
        }
        return f;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Locale l = Locale.getDefault();
        switch (position) {
            case 0:
                return "Informacion General";
            case 1:
                return "Informacion Estrategica";
        }
        return null;
    }
}
