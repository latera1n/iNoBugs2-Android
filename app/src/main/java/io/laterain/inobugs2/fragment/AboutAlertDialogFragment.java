package io.laterain.inobugs2.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.widget.Toast;

import io.laterain.inobugs2.R;

/**
 * Created by dengyuchi on 2/17/17.
 */

public class AboutAlertDialogFragment extends AppCompatDialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        setCancelable(false);
        return new AlertDialog.Builder(getActivity())
                .setTitle(getString(R.string.dialog_about_title))
                .setMessage(getString(R.string.dialog_about_message))
                .setPositiveButton(getString(R.string.dialog_about_button_1_text), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(), getString(R.string.dialog_about_button_1_toast_message), Toast.LENGTH_SHORT).show();
                    }
                })
                .setNeutralButton((getString(R.string.dialog_about_button_2_text)), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(), getString(R.string.dialog_about_button_2_toast_message), Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton(getString(R.string.dialog_about_button_3_text), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(), getString(R.string.dialog_about_button_3_toast_message), Toast.LENGTH_SHORT).show();
                    }
                }).create();
    }

}
