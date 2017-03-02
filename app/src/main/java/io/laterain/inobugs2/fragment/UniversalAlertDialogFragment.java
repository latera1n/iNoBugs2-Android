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

    public static UniversalAlertDialogFragment newInstance(int title, int message) {
        UniversalAlertDialogFragment frag = new UniversalAlertDialogFragment();
        Bundle args = new Bundle();
        args.putInt(STR_ARG_TITLE_KEY, title);
        args.putInt(STR_ARG_MSG_KEY, message);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int title = getArguments().getInt("title");
        int message = getArguments().getInt("message");
        setCancelable(false);
        return new AlertDialog.Builder(getActivity())
                .setTitle(title)
                .setMessage(message)
                .setNegativeButton(R.string.dialog_universal_negative_button_text,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                // ((FragmentAlertDialog)getActivity()).doNegativeClick();
                                // TODO: Add on click handler.
                            }
                        }
                )
                .create();
    }

}
