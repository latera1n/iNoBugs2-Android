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

    Spinner mSpinnerCrop;
    Spinner mSpinnerMethod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);
        initSpinners();
        initDate();
        initFloatingActionButton();
    }

    private void initSpinners() {
        mSpinnerCrop = (Spinner) findViewById(R.id.spinner_add_record_crop);
        mSpinnerMethod = (Spinner) findViewById(R.id.spinner_add_record_method);

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
                RadioButton radioButtonHarmDisease = (RadioButton) findViewById(R.id.radio_button_add_record_harm_disease);
                RadioButton radioButtonModeNormal = (RadioButton) findViewById(R.id.radio_button_add_record_mode_normal);
                EditText editTextTime = (EditText) findViewById(R.id.edit_text_add_record_time);
                EditText editTextLocation = (EditText) findViewById(R.id.edit_text_add_record_location);
                EditText editTextNote = (EditText) findViewById(R.id.edit_text_add_record_note);

                int crop = 0;
                int method = 0;
                try {
                    crop = Integer.parseInt(((SpinnerItem) mSpinnerCrop.getSelectedItem()).getId());
                    method = Integer.parseInt(((SpinnerItem) mSpinnerMethod.getSelectedItem()).getId());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                int harm = radioButtonHarmDisease.isChecked() ? 0 : 1;
                int mode = radioButtonModeNormal.isChecked() ? 0 : 1;

                String timeStr = editTextTime.getText().toString();
                String location = editTextLocation.getText().toString();
                String note = editTextNote.getText().toString();

                long timestamp;
                try {
                    timestamp = DateHelper.convertToTimeStampFromString(timeStr);
                } catch (ParseException e) {
                    Toast.makeText(AddRecordActivity.this, getString(R.string.toast_message_invalid_date), Toast.LENGTH_SHORT).show();
                    return;
                }

                if (crop >= 0 && crop <= 3 && method >= 0 && method <= 2 && timestamp > 0 && location.length() > 0) {
                    DiagnoseRecord record = new DiagnoseRecord(crop, harm, mode, method, timestamp, location, note);
                    startActivity(new Intent(getBaseContext(), AreaAndCountActivity.class).putExtra(getString(R.string.extra_record_key), record));
                } else {
                    Toast.makeText(AddRecordActivity.this, getString(R.string.toast_message_incomplete_input), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
