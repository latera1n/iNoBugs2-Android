package io.laterain.inobugs2.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import io.laterain.inobugs2.R;
import io.laterain.inobugs2.dao.DiagnoseRecord;

public class AreaAndCountActivity extends AppCompatActivity {

    DiagnoseRecord record;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_and_count);
        record = (DiagnoseRecord) getIntent().getSerializableExtra(AddRecordActivity.STR_EXTRA_KEY);
    }

    

}
