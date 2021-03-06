package io.laterain.inobugs2.activity;

import android.annotation.SuppressLint;
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
import io.laterain.inobugs2.util.BugUIContentHelper;

public class BugSelectionActivity extends AppCompatActivity {

    private final static String STR_EDIT_TEXT_ID_PREFIX = "edit_text_bug_selection_count_age_";

    private Spinner mSpinnerBugSelection;
    private EditText mEditTextInvisible;

    private boolean mIsEditing;
    private DiagnoseRecord mRecord;
    private int mCrop;
    private int mMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bug_selection);

        mIsEditing = getIntent().getBooleanExtra(getString(R.string.extra_is_editing), false);
        mRecord = (DiagnoseRecord) getIntent().getSerializableExtra(getString(R.string.extra_record_key));
        mCrop = mRecord.getCrop();
        mMode = mRecord.getMode();

        mSpinnerBugSelection = (Spinner) findViewById(R.id.spinner_bug_selection);
        mEditTextInvisible = (EditText) findViewById(R.id.edit_text_bug_selection_invisible);

        initUIContent();
        initFloatingActionButton();

        if (mIsEditing) {
            System.out.println(mRecord.getIdCopy());
            initEditingModeData();
        }
    }

    private void initUIContent() {
        if (mMode == DiagnoseRecord.Mode.NORMAL.ordinal()) {
            mSpinnerBugSelection.setAdapter(
                    new ArrayAdapter<>(getBaseContext(), R.layout.spinner_text_view_smaller_text_size,
                            BugUIContentHelper.getInstance(getBaseContext()).getBugSelectionSpinnerItemsListByCrop(mCrop)));
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
                String[] bugCountArray = new String[DiagnoseRecord.NUM_INFO_FIELDS - 2];
                for (int i = 0; i < DiagnoseRecord.NUM_INFO_FIELDS - 2; i++) {
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
                    bugCountArray[i] = currentBugCountText;
                }
                String eggCount = ((EditText) findViewById(R.id.edit_text_bug_selection_count_egg)).getText().toString();
                if (eggCount.length() == 0) {
                    Toast.makeText(BugSelectionActivity.this, getString(R.string.toast_message_invalid_bug_or_egg_count_invalid_format), Toast.LENGTH_SHORT).show();
                    return;
                }
                try{
                    int _ = Integer.parseInt(eggCount);
                } catch (Exception e) {
                    Toast.makeText(BugSelectionActivity.this, getString(R.string.toast_message_invalid_bug_or_egg_count_invalid_format), Toast.LENGTH_SHORT).show();
                    return;
                }
                mRecord.addEggAndBugInfo(bugKeyOrName, eggCount, bugCountArray);
                startActivity(new Intent(getBaseContext(), ResultActivity.class).putExtra(getString(R.string.extra_record_key), mRecord));
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void initEditingModeData() {
        int[] eggAndBugCountArray = mRecord.getEggAndBugCountArray();
        for (int i = 0; i < DiagnoseRecord.NUM_INFO_FIELDS - 2; i++) {
            ((EditText) findViewById(getResources().getIdentifier(STR_EDIT_TEXT_ID_PREFIX + (i + 1), "id", getPackageName()))).setText("" + eggAndBugCountArray[i]);
        }
    }

}
