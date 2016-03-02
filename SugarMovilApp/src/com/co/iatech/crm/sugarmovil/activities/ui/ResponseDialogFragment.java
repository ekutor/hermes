package com.co.iatech.crm.sugarmovil.activities.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import com.co.iatech.crm.sugarmovil.R;
import com.co.iatech.crm.sugarmovil.activtities.modules.Modules;

/**
 * Clase para la creacion de mensajes al usuario.
 */
@SuppressLint("ValidFragment")
public class ResponseDialogFragment extends DialogFragment {

	public enum DialogType {EDITED,CREATED,NO_CREATED, NO_EDITED}
	private DialogType type;
	private Activity activity;
	private Modules module;
	public ResponseDialogFragment( DialogType type, Activity activity , Modules module ){
		this.type = type ;
		this.activity = activity;
		this.module = module;
	}
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        CharSequence mensaje = "";
        CharSequence titulo= "";
        Context c = activity.getApplicationContext();
       switch(type){
       case EDITED:
    	   titulo = c.getText(R.string.dialog_activity_edited);
    	   mensaje =  c.getText(R.string.dialog_message_edit_ok); 
    	   mensaje = String.format("%s la %s Exitosamente", mensaje,  module.getVisualName().toLowerCase());
        	break;
        	
       case CREATED:
        	titulo =  c.getText(R.string.dialog_activity_created);
        	mensaje = c.getText(R.string.dialog_message_save_ok); 
        	mensaje = String.format("%s la %s Exitosamente", mensaje,  module.getVisualName().toLowerCase());
        	break;
       case NO_CREATED:
           	titulo =  c.getText(R.string.dialog_activity_error);
           	mensaje = String.format("%s la %s ", c.getText(R.string.dialog_message_error),  module.getVisualName().toLowerCase());
       	break;
       case NO_EDITED:
          	titulo =  c.getText(R.string.dialog_activity_edit_error);
          	mensaje = String.format("%s la %s ", c.getText(R.string.dialog_message_edit_error),  module.getVisualName().toLowerCase());
      	break;
        
       }
       titulo = module.getVisualName() + " " +titulo;
       
        builder.setTitle(titulo)
                .setMessage(mensaje)
                .setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        activity.finish();
                    }
                });

        return builder.create();
    }
}