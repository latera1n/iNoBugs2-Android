package io.laterain.inobugs2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import io.laterain.inobugs2.R;
import io.laterain.inobugs2.dao.DiagnoseRecord;
import io.laterain.inobugs2.dao.SpinnerItem;
import io.laterain.inobugs2.util.BugSelectionSpinnerHelper;

public class BugSelectionActivity extends AppCompatActivity {

    private final static String STR_EDIT_TEXT_ID_PREFIX = "edit_text_bug_selection_count_age_";

    Spinner mSpinnerBugSelection;
    EditText mEditTextInvisible;

    private DiagnoseRecord mRecord;
    private int mCrop;
    private int mMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bug_selection);
        this.mSpinnerBugSelection = (Spinner) findViewById(R.id.spinner_bug_selection);
        this.mEditTextInvisible = (EditText) findViewById(R.id.edit_text_bug_selection_invisible);
        this.mRecord = (DiagnoseRecord) getIntent().getSerializableExtra(getString(R.string.extra_record_key));
        this.mCrop = mRecord.getCrop();
        this.mMode = mRecord.getMode();

        initUIContent();
        initFloatingActionButton();
    }

    private void initUIContent() {
        if (mMode == DiagnoseRecord.Mode.NORMAL.ordinal()) {
            mSpinnerBugSelection.setAdapter(
                    new ArrayAdapter<>(getBaseContext(), R.layout.spinner_text_view_smaller_text_size,
                            BugSelectionSpinnerHelper.getInstance(getBaseContext()).getBugSelectionSpinnerItemsListByCrop(mCrop)));
        } else {
            mEditTextInvisible.setVisibility(View.VISIBLE);
            mSpinnerBugSelection.setVisibility(View.GONE);
        }
    }

    private void initFloatingActionButton() {
        findViewById(R.id.floating_action_button_bug_selection_finish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bugKeyOrName;
                if (mMode == DiagnoseRecord.Mode.NORMAL.ordinal()) {
                    bugKeyOrName = ((SpinnerItem) mSpinnerBugSelection.getSelectedItem()).getId();
                } else {
                    bugKeyOrName = mEditTextInvisible.getText().toString();
                }
                String[] bugAndEggCount = new String[DiagnoseRecord.NUM_BUG_FIELDS + 1];
                for (int i = 0; i < DiagnoseRecord.NUM_BUG_FIELDS; i++) {
                    String currentBugCountText = ((EditText) findViewById(getResources().getIdentifier(
                            STR_EDIT_TEXT_ID_PREFIX + (i + 1), "id", getPackageName()))).getText().toString();
                    if (currentBugCountText.length() == 0) {
                        Toast.makeText(BugSelectionActivity.this, getString(R.string.toast_message_invalid_bug_or_egg_count_invalid_format), Toast.LENGTH_SHORT).show();
                        return;
                    }
                    try{
                        int _ = Integer.parseInt(currentBugCountText);
                    } catch (Exception e) {
                        Toast.makeText(BugSelectionActivity.this, getString(R.string.toast_message_invalid_bug_or_egg_count_invalid_format), Toast.LENGTH_SHORT).show();
                        return;
                    }
                    bugAndEggCount[i] = currentBugCountText;
                }
                String eggCountText = ((EditText) findViewById(R.id.edit_text_bug_selection_count_egg)).getText().toString();
                if (eggCountText.length() == 0) {
                    Toast.makeText(BugSelectionActivity.this, getString(R.string.toast_message_invalid_bug_or_egg_count_invalid_format), Toast.LENGTH_SHORT).show();
                    return;
                }
                try{
                    int _ = Integer.parseInt(eggCountText);
                } catch (Exception e) {
                    Toast.makeText(BugSelectionActivity.this, getString(R.string.toast_message_invalid_bug_or_egg_count_invalid_format), Toast.LENGTH_SHORT).show();
                    return;
                }
                bugAndEggCount[DiagnoseRecord.NUM_BUG_FIELDS] = eggCountText;
                mRecord.addBugInfo(bugKeyOrName, bugAndEggCount);
                startActivity(new Intent(getBaseContext(), ResultActivity.class).putExtra(getString(R.string.extra_record_key), mRecord));
            }
        });
    }

}
