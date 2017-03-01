package io.laterain.inobugs2.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.w3c.dom.Text;

import io.laterain.inobugs2.R;
import io.laterain.inobugs2.dao.DiagnoseRecord;
import io.laterain.inobugs2.util.BugUIContentHelper;
import io.laterain.inobugs2.util.DateHelper;

public class ResultActivity extends AppCompatActivity {

    private DiagnoseRecord mRecord;
    private int mHarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mRecord = (DiagnoseRecord) getIntent().getSerializableExtra(getString(R.string.extra_record_key));
        setContentView(mRecord.getHarm() == DiagnoseRecord.Harm.DISEASES.ordinal() ?
                R.layout.activity_result_disease : R.layout.activity_result_bug);
        this.mHarm = mRecord.getHarm();

        mRecord.calculateAndSaveResults();
        fillUIContents();
    }

    @SuppressLint("SetTextI18n")
    private void fillUIContents() {
        if (mHarm == DiagnoseRecord.Harm.DISEASES.ordinal()) {

        } else {
            ((TextView) findViewById(R.id.text_view_result_bug_basic_info_type_content)).setText(
                    getResources().getStringArray(R.array.crop_categories)[mRecord.getCrop()]
                            + getResources().getStringArray(R.array.harm_categories)[mRecord.getHarm()]
            );
            ((TextView) findViewById(R.id.text_view_result_bug_basic_info_date_and_location_content)).setText(
                    DateHelper.convertToFormattedDateString(mRecord.getTimeStamp()) + " - " + mRecord.getLocation()
            );
            ((TextView) findViewById(R.id.text_view_result_bug_basic_info_method_and_mode_content)).setText(
                    getResources().getStringArray(R.array.method_categories)[mRecord.getMethod()]
                            + " - " + getResources().getStringArray(R.array.mode_categories)[mRecord.getMode()]
            );
            ((TextView) findViewById(R.id.text_view_result_bug_bug_name_content)).setText(
                    BugUIContentHelper.getInstance(getBaseContext()).getBugName(mRecord.getCrop(), mRecord.getInfo0())
            );

            // TODO: Finish filling UI components.
        }
    }

}
