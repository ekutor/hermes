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
 * Clase para la creacion de msgs al usuario.
 */
@SuppressLint("ValidFragment")
public class ResponseDialogFragment extends DialogFragment {

	public enum DialogType {EDITED,CREATED,NO_CREATED, NO_EDITED, UNDEFINED}
	private DialogType type;
	private Activity activity;
	private Modules module;
	private String message;
	
	public ResponseDialogFragment( DialogType type, Activity activity , Modules module ){
		this.type = type ;
		this.activity = activity;
		this.module = module;
	}
	public ResponseDialogFragment( Activity activity , Modules module, String message ){
		this.type = DialogType.UNDEFINED ;
		this.activity = activity;
		this.module = module;
		this.message = message;
	}
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        CharSequence msg = "";
        CharSequence titulo= "";
        Context c = activity.getApplicationContext();
       switch(type){
       case EDITED:
    	   titulo = c.getText(R.string.dialog_activity_edited);
    	   msg =  c.getText(R.string.dialog_message_edit_ok); 
    	   msg = String.format("%s la %s Exitosamente", msg,  module.getVisualName().toLowerCase());
        	break;
        	
       case CREATED:
        	titulo =  c.getText(R.string.dialog_activity_created);
        	msg = c.getText(R.string.dialog_message_save_ok); 
        	msg = String.format("%s la %s Exitosamente", msg,  module.getVisualName().toLowerCase());
        	break;
       case NO_CREATED:
           	titulo =  c.getText(R.string.dialog_activity_error);
           	msg = String.format("%s la %s ", c.getText(R.string.dialog_message_error),  module.getVisualName().toLowerCase());
       	break;
       case NO_EDITED:
          	titulo =  c.getText(R.string.dialog_activity_edit_error);
          	msg = String.format("%s la %s ", c.getText(R.string.dialog_message_edit_error),  module.getVisualName().toLowerCase());
      	break;
      	default:
      		titulo =  c.getText(R.string.dialog_activity_error);
      		msg = message;
      		break;
       }
       titulo = module.getVisualName() + " " +titulo;
       
        builder.setTitle(titulo)
                .setMessage(msg)
                .setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        activity.finish();
                    }
                });

        return builder.create();
    }
}