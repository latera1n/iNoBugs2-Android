package io.laterain.inobugs2.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import io.laterain.inobugs2.R;
import io.laterain.inobugs2.dao.DiagnoseRecord;

public class DiseaseSelectionActivity extends AppCompatActivity {

    private DiagnoseRecord mRecord;
    private int mSelectionRound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_selection);
        this.mRecord = (DiagnoseRecord) getIntent().getSerializableExtra(getString(R.string.extra_record_key));
        this.mSelectionRound = getIntent().getIntExtra(getString(R.string.extra_disease_round_key), 0);
    }
}
