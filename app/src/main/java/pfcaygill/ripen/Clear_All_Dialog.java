package pfcaygill.ripen;

import android.app.Activity;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

/**
 * Created by PFCaygill on 16/12/2017.
 */

public class Clear_All_Dialog extends android.support.v4.app.DialogFragment {

    public interface SimpleDialogListener{
        public void onDialogPositiveClick(DialogFragment dialog);
        public void onDialogNegativeClick(DialogFragment dialog);
    }
    SimpleDialogListener mListener;

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        //if the context implements our chosen simple listener
        try{
            mListener = (SimpleDialogListener) context;
        }catch(ClassCastException e){
            //it cannot be cast and therefore does not implement the chosen listener
            throw new ClassCastException(context.toString()
                    +"does not implement simpleDialogListener interface");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
    /**
     * Activate a Dialogue for the user to choose to continue or not
     * */
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.clear_all_dialog_message)
                .setTitle(R.string.clear_all_dialog_title);
        //Set the buttons up for the dialog box
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                mListener.onDialogPositiveClick(Clear_All_Dialog.this);
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
                mListener.onDialogNegativeClick(Clear_All_Dialog.this);
            }
        });
        return builder.create();
     }

}
