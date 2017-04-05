package io.laterain.inobugs2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.ParseException;

import io.laterain.inobugs2.R;
import io.laterain.inobugs2.dao.DiagnoseRecord;
import io.laterain.inobugs2.dao.SpinnerItem;
import io.laterain.inobugs2.util.DateHelper;
import io.laterain.inobugs2.util.XMLStringArrayHelper;

public class AddRecordActivity extends AppCompatActivity {

    private Spinner mSpinnerCrop;
    private RadioButton mRadioButtonHarmDisease;
    private RadioButton mRadioButtonModeNormal;
    private Spinner mSpinnerMethod;
    private EditText mEditTextTime;
    private EditText mEditTextLocation;
    private EditText mEditTextNote;


    private boolean mIsEditing;
    private DiagnoseRecord mRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        mSpinnerCrop = (Spinner) findViewById(R.id.spinner_add_record_crop);
        mRadioButtonHarmDisease = (RadioButton) findViewById(R.id.radio_button_add_record_harm_disease);
        mRadioButtonModeNormal = (RadioButton) findViewById(R.id.radio_button_add_record_mode_normal);
        mSpinnerMethod = (Spinner) findViewById(R.id.spinner_add_record_method);
        mEditTextTime = (EditText) findViewById(R.id.edit_text_add_record_time);
        mEditTextLocation = (EditText) findViewById(R.id.edit_text_add_record_location);
        mEditTextNote = (EditText) findViewById(R.id.edit_text_add_record_note);

        initSpinners();
        initDate();
        initFloatingActionButton();
        mIsEditing = getIntent().getBooleanExtra(getString(R.string.extra_is_editing), false);
        if (mIsEditing) {
            mRecord = (DiagnoseRecord) getIntent().getSerializableExtra(getString(R.string.extra_record_key));
            System.out.println(mRecord.getIdCopy());
            initEditingModeData();
        } else {
            mRecord = null;
        }
    }

    private void initSpinners() {
        mSpinnerCrop.setAdapter(new ArrayAdapter<>(getBaseContext(),
                R.layout.spinner_text_view_smaller_text_size,
                XMLStringArrayHelper.buildSpinnerItemListFromArray(getBaseContext(), R.array.crop_categories)));
        mSpinnerMethod.setAdapter(new ArrayAdapter<>(getBaseContext(),
                R.layout.spinner_text_view_smaller_text_size,
                XMLStringArrayHelper.buildSpinnerItemListFromArray(getBaseContext(), R.array.method_categories)));
    }

    private void initDate() {
        EditText editTextDate = (EditText) findViewById(R.id.edit_text_add_record_time);
        editTextDate.setText(DateHelper.getFormattedDateStringForNow());
    }

    private void initFloatingActionButton() {
        final FloatingActionButton fabNext = (FloatingActionButton) findViewById(R.id.floating_action_button_add_record_next);
        fabNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int crop = 0;
                int method = 0;
                try {
                    crop = Integer.parseInt(((SpinnerItem) mSpinnerCrop.getSelectedItem()).getId());
                    method = Integer.parseInt(((SpinnerItem) mSpinnerMethod.getSelectedItem()).getId());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                int harm = mRadioButtonHarmDisease.isChecked() ? 0 : 1;
                int mode = mRadioButtonModeNormal.isChecked() ? 0 : 1;

                String timeStr = mEditTextTime.getText().toString();
                String location = mEditTextLocation.getText().toString();
                String note = mEditTextNote.getText().toString();

                long timestamp;
                try {
                    timestamp = DateHelper.convertToTimeStampFromString(timeStr);
                } catch (ParseException e) {
                    Toast.makeText(AddRecordActivity.this, getString(R.string.toast_message_invalid_date), Toast.LENGTH_SHORT).show();
                    return;
                }

                if (crop >= 0 && crop <= 3 && method >= 0 && method <= 2 && timestamp > 0 && location.length() > 0) {
                    if (mIsEditing) {
                        mRecord.updateBasicInfo(crop, harm, mode, method, timestamp, location, note);
                        startActivity(new Intent(getBaseContext(), AreaAndCountActivity.class)
                                .putExtra(getString(R.string.extra_record_key), mRecord)
                                .putExtra(getString(R.string.extra_is_editing), true));
                    } else {
                        DiagnoseRecord record = new DiagnoseRecord(crop, harm, mode, method, timestamp, location, note);
                        startActivity(new Intent(getBaseContext(), AreaAndCountActivity.class).putExtra(getString(R.string.extra_record_key), record));
                    }
                } else {
                    Toast.makeText(AddRecordActivity.this, getString(R.string.toast_message_incomplete_input), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initEditingModeData() {
        if (mRecord != null) {
            RadioButton radioButtonHarmBug = (RadioButton) findViewById(R.id.radio_button_add_record_harm_bug);
            RadioButton radioButtonModeExpert = (RadioButton) findViewById(R.id.radio_button_add_record_mode_expert);
            mSpinnerCrop.setSelection(mRecord.getCrop());
            if (mRecord.getHarm() == DiagnoseRecord.Harm.BUGS.ordinal()) {
                mRadioButtonHarmDisease.setChecked(false);
                radioButtonHarmBug.setChecked(true);
            }
            if (mRecord.getMode() == DiagnoseRecord.Mode.EXPERT.ordinal()) {
                mRadioButtonModeNormal.setChecked(false);
                radioButtonModeExpert.setChecked(true);
            }
            mSpinnerMethod.setSelection(mRecord.getMethod());
            mEditTextTime.setText(DateHelper.convertToFormattedDateString(mRecord.getTimeStamp()));
            mEditTextLocation.setText(mRecord.getLocation());
            mEditTextNote.setText(mRecord.getNote());

            mSpinnerCrop.setEnabled(false);
            mRadioButtonHarmDisease.setEnabled(false);
            radioButtonHarmBug.setEnabled(false);
            mRadioButtonModeNormal.setEnabled(false);
            radioButtonModeExpert.setEnabled(false);
        }
    }

}
