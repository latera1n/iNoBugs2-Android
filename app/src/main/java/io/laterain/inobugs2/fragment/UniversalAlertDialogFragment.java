package io.laterain.inobugs2.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import io.laterain.inobugs2.R;

/**
 * Created by dengyuchi on 3/1/17.
 */

public class UniversalAlertDialogFragment extends DialogFragment {

    private final static String STR_ARG_TITLE_KEY = "title";
    private final static String STR_ARG_MSG_KEY = "msg";
    private static DialogInterface.OnClickListener mOnClickListener;

    public static UniversalAlertDialogFragment newInstance(int title, int message, DialogInterface.OnClickListener onClickListener) {
        UniversalAlertDialogFragment frag = new UniversalAlertDialogFragment();
        Bundle args = new Bundle();
        args.putInt(STR_ARG_TITLE_KEY, title);
        args.putInt(STR_ARG_MSG_KEY, message);
        frag.setArguments(args);
        mOnClickListener = onClickListener;
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        setCancelable(false);
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        if (getArguments().getInt(STR_ARG_TITLE_KEY) != -1) {
            dialogBuilder.setTitle(getArguments().getInt(STR_ARG_TITLE_KEY));
        }
        if (getArguments().getInt(STR_ARG_MSG_KEY) != -1) {
            dialogBuilder.setMessage(getArguments().getInt(STR_ARG_MSG_KEY));
        }
        return dialogBuilder.setNegativeButton(R.string.dialog_universal_negative_button_text, mOnClickListener).create();
    }

}
