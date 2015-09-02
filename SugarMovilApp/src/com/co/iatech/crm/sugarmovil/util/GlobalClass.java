package com.co.iatech.crm.sugarmovil.util;

import com.co.iatech.crm.sugarmovil.model.User;

import android.app.Application;


public class GlobalClass extends Application {
	
    // Global Variables
    private int mSelectedButton;
    private User usuarioAutenticado;
    
    public GlobalClass(){
    	
    }


	public int getmSelectedButton() {
        return mSelectedButton;
    }

    public void setmSelectedButton(int mSelectedButton) {
        this.mSelectedButton = mSelectedButton;
    }


	public User getUsuarioAutenticado() {
		return usuarioAutenticado;
	}


	public void setUsuarioAutenticado(User usuarioAutenticado) {
		this.usuarioAutenticado = usuarioAutenticado;
	}
    
    

}
