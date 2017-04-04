package io.laterain.inobugs2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import io.laterain.inobugs2.R;
import io.laterain.inobugs2.dao.DiagnoseRecord;
import io.laterain.inobugs2.dao.SpinnerItem;
import io.laterain.inobugs2.util.DiseaseUIContentHelper;

public class DiseaseSelectionActivity extends AppCompatActivity {

    private Spinner mSpinnerCropPartSymptoms;
    private TextView mTextViewCropPartSymptom;

    private int mSelectionRound;
    private DiagnoseRecord mRecord;
    private int mCrop;
    private int mMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_selection);
        this.mSelectionRound = getIntent().getIntExtra(getString(R.string.extra_disease_round_key), 0);
        this.mRecord = (DiagnoseRecord) getIntent().getSerializableExtra(getString(R.string.extra_record_key));
        this.mCrop = mRecord.getCrop();
        this.mMode = mRecord.getMode();

        initUIContent();
        initFloatingActionButton();
    }

    private void initUIContent() {
        System.out.println(mRecord.getMode());
        mSpinnerCropPartSymptoms = (Spinner) findViewById(R.id.spinner_disease_selection);
        mTextViewCropPartSymptom = (TextView) findViewById(R.id.edit_text_disease_selection);
        TextView textViewCropPartLabel = (TextView) findViewById(R.id.text_view_disease_selection_label);

        DiseaseUIContentHelper helper = DiseaseUIContentHelper.getInstance(this);
        textViewCropPartLabel.setText(helper.getDiseaseSelectionCropPartLabel(mCrop, mSelectionRound));

        if (mMode == DiagnoseRecord.Mode.NORMAL.ordinal()) {
            mTextViewCropPartSymptom.setVisibility(View.GONE);
            mSpinnerCropPartSymptoms.setAdapter(new ArrayAdapter<>(getBaseContext(),
                    R.layout.spinner_text_view_smaller_text_size,
                    helper.getDiseaseSelectionCropPartSymptomSpinnerItemsList(mCrop, mSelectionRound)));
        } else {
            mSpinnerCropPartSymptoms.setVisibility(View.GONE);
        }
    }

    private void initFloatingActionButton() {
        final FloatingActionButton fabNext = (FloatingActionButton) findViewById(R.id.floating_action_button_disease_selection_next);

        if (mSelectionRound == DiseaseUIContentHelper.NUM_PARTS_FOR_CROP[mCrop] - 1) {
            fabNext.setImageDrawable(getDrawable(R.mipmap.ic_check_white_24dp));
        }

        fabNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strContent;
                if (mMode == DiagnoseRecord.Mode.NORMAL.ordinal()) {
                    strContent = ((SpinnerItem) mSpinnerCropPartSymptoms.getSelectedItem()).getId();
                } else {
                    strContent = mTextViewCropPartSymptom.getText().toString();
                }
                mRecord.addDiseaseInfoForSelectionRound(mSelectionRound, strContent);
                // TODO: add a input round for disease name under EXPERT MODE.
                if (++mSelectionRound < DiseaseUIContentHelper.NUM_PARTS_FOR_CROP[mCrop]) {
                    startActivity(new Intent(getBaseContext(), DiseaseSelectionActivity.class)
                            .putExtra(getString(R.string.extra_disease_round_key), mSelectionRound)
                            .putExtra(getString(R.string.extra_record_key), mRecord));
                } else {
                    startActivity(new Intent(getBaseContext(), ResultActivity.class)
                            .putExtra(getString(R.string.extra_record_key), mRecord));
                }
            }
        });
    }
}
