package io.laterain.inobugs2.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.support.v7.app.AppCompatActivity;

import io.laterain.inobugs2.R;
import io.laterain.inobugs2.dao.DiagnoseRecord;
import io.laterain.inobugs2.util.BugSelectionSpinnerHelper;

public class BugSelectionActivity extends AppCompatActivity {

    private DiagnoseRecord mRecord;
    private int mCrop;
    private int mMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bug_selection);
        this.mRecord = (DiagnoseRecord) getIntent().getSerializableExtra(getString(R.string.extra_record_key));
        this.mCrop = mRecord.getCrop();
        this.mMode = mRecord.getMode();

        initUIContent();
        initFloatingActionButton();
    }

    private void initUIContent() {
        Spinner spinnerBugSelection = (Spinner) findViewById(R.id.spinner_bug_selection);
        if (mMode == DiagnoseRecord.Mode.NORMAL.ordinal()) {
            spinnerBugSelection.setAdapter(
                    new ArrayAdapter<>(getBaseContext(), R.layout.spinner_text_view_smaller_text_size,
                            BugSelectionSpinnerHelper.getInstance(getBaseContext()).getBugSelectionSpinnerItemsListByCrop(mCrop)));
        } else {
            (findViewById(R.id.edit_text_bug_selection_invisible)).setVisibility(View.VISIBLE);
            spinnerBugSelection.setVisibility(View.GONE);
        }
    }

    private void initFloatingActionButton() {
        findViewById(R.id.floating_action_button_bug_selection_finish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Add input validations and save bug info.
                if (mMode == DiagnoseRecord.Mode.NORMAL.ordinal()) {

                } else {

                }
            }
        });
    }

}
