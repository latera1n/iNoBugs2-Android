package io.laterain.inobugs2.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import io.laterain.inobugs2.R;
import io.laterain.inobugs2.dao.DiagnoseRecord;
import io.laterain.inobugs2.util.DiseaseSelectionUIContentHelper;

public class DiseaseSelectionActivity extends AppCompatActivity {

    private int mSelectionRound;
    private DiagnoseRecord mRecord;
    private int mCrop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_selection);
        this.mSelectionRound = getIntent().getIntExtra(getString(R.string.extra_disease_round_key), 0);
        this.mRecord = (DiagnoseRecord) getIntent().getSerializableExtra(getString(R.string.extra_record_key));
        this.mCrop = mRecord.getCrop();

        initUIContent();
    }

    private void initUIContent() {
        DiseaseSelectionUIContentHelper helper = DiseaseSelectionUIContentHelper.getInstance(this);
        TextView cropPartLabel = (TextView) findViewById(R.id.text_view_disease_selection_label);
        cropPartLabel.setText(helper.getDiseaseSelectionCropPartLabel(mCrop, mSelectionRound));
    }
}
