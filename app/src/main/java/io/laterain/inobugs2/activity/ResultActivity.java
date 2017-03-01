package io.laterain.inobugs2.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import io.laterain.inobugs2.R;
import io.laterain.inobugs2.dao.DiagnoseRecord;

public class ResultActivity extends AppCompatActivity {

    private DiagnoseRecord mRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRecord = (DiagnoseRecord) getIntent().getSerializableExtra(getString(R.string.extra_record_key));

        setContentView(mRecord.getHarm() == DiagnoseRecord.Harm.DISESES.ordinal() ?
                R.layout.activity_result_disease : R.layout.activity_result_bug);
    }
}
