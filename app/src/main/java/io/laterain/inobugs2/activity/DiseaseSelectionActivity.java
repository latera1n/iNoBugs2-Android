package io.laterain.inobugs2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import io.laterain.inobugs2.R;
import io.laterain.inobugs2.dao.DiagnoseRecord;
import io.laterain.inobugs2.dao.SpinnerItem;
import io.laterain.inobugs2.util.DiseaseUIContentHelper;

public class DiseaseSelectionActivity extends AppCompatActivity {

    private Spinner mSpinnerCropPartSymptoms;
    private EditText mEditTextCropPartSymptom;

    private boolean mIsEditing;
    private boolean mIsExpertModeFinalRound;
    private int mSelectionRound;
    private DiagnoseRecord mRecord;
    private int mCrop;
    private int mMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_selection);

        mIsEditing = getIntent().getBooleanExtra(getString(R.string.extra_is_editing), false);
        mIsExpertModeFinalRound = getIntent().getBooleanExtra(getString(R.string.extra_disease_expert_mode_final_round), false);
        mSelectionRound = getIntent().getIntExtra(getString(R.string.extra_disease_round_key), 0);
        mRecord = (DiagnoseRecord) getIntent().getSerializableExtra(getString(R.string.extra_record_key));
        mCrop = mRecord.getCrop();
        mMode = mRecord.getMode();

        initUIContent();
        initFloatingActionButton();

        if (mIsEditing) {
            System.out.println(mRecord.getIdCopy());
            initEditingModeData();
        }
    }

    private void initUIContent() {
        System.out.println(mRecord.getMode());
        mSpinnerCropPartSymptoms = (Spinner) findViewById(R.id.spinner_disease_selection);
        mEditTextCropPartSymptom = (EditText) findViewById(R.id.edit_text_disease_selection);
        TextView textViewCropPartLabel = (TextView) findViewById(R.id.text_view_disease_selection_label);

        DiseaseUIContentHelper helper = DiseaseUIContentHelper.getInstance(this);
        if (mIsExpertModeFinalRound) {
            textViewCropPartLabel.setText(R.string.result_disease_disease_expert_mode_final_round_disease_name_label);
        } else {
            textViewCropPartLabel.setText(helper.getDiseaseSelectionCropPartLabel(mCrop, mSelectionRound));
        }

        if (mMode == DiagnoseRecord.Mode.NORMAL.ordinal()) {
            mEditTextCropPartSymptom.setVisibility(View.GONE);
            mSpinnerCropPartSymptoms.setAdapter(new ArrayAdapter<>(getBaseContext(),
                    R.layout.spinner_text_view_smaller_text_size,
                    helper.getDiseaseSelectionCropPartSymptomSpinnerItemsList(mCrop, mSelectionRound)));
        } else {
            mSpinnerCropPartSymptoms.setVisibility(View.GONE);
        }
    }

    private void initFloatingActionButton() {
        final FloatingActionButton fabNext = (FloatingActionButton) findViewById(R.id.floating_action_button_disease_selection_next);

        if (mMode == DiagnoseRecord.Mode.NORMAL.ordinal() && mSelectionRound == DiseaseUIContentHelper.NUM_PARTS_FOR_CROP[mCrop] - 1
                || mIsExpertModeFinalRound) {
            fabNext.setImageDrawable(getDrawable(R.mipmap.ic_check_white_24dp));
        }

        fabNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strContent;
                if (mMode == DiagnoseRecord.Mode.NORMAL.ordinal()) {
                    strContent = ((SpinnerItem) mSpinnerCropPartSymptoms.getSelectedItem()).getId();
                } else {
                    strContent = mEditTextCropPartSymptom.getText().toString();
                }
                if (mIsExpertModeFinalRound) {
                    mRecord.setResult1(strContent);
                } else {
                    mRecord.addDiseaseInfoForSelectionRound(mSelectionRound, strContent);
                }
                Intent intent;
                if (++mSelectionRound < DiseaseUIContentHelper.NUM_PARTS_FOR_CROP[mCrop]) {
                    intent = new Intent(getBaseContext(), DiseaseSelectionActivity.class)
                            .putExtra(getString(R.string.extra_disease_round_key), mSelectionRound)
                            .putExtra(getString(R.string.extra_record_key), mRecord);
                    if (mIsEditing) {
                        intent.putExtra(getString(R.string.extra_is_editing), true);
                    }
                } else {
                    if (mMode == DiagnoseRecord.Mode.NORMAL.ordinal() || mIsExpertModeFinalRound) {
                        intent = new Intent(getBaseContext(), ResultActivity.class)
                                .putExtra(getString(R.string.extra_record_key), mRecord);
                    } else {
                        intent = new Intent(getBaseContext(), DiseaseSelectionActivity.class)
                                .putExtra(getString(R.string.extra_disease_round_key), mSelectionRound)
                                .putExtra(getString(R.string.extra_record_key), mRecord)
                                .putExtra(getString(R.string.extra_disease_expert_mode_final_round), true);
                        if (mIsEditing) {
                            intent.putExtra(getString(R.string.extra_is_editing), true);
                        }
                    }
                }
                startActivity(intent);
            }
        });
    }

    private void initEditingModeData() {
        if (mIsExpertModeFinalRound) {
            mEditTextCropPartSymptom.setText(mRecord.getResult1());
        } else {
            String symptomIdOrDescriptionStr = mRecord.getPartSymptomKeysArray()[mSelectionRound];
            if (mMode == DiagnoseRecord.Mode.NORMAL.ordinal()) {
                int position = 0;
                List<SpinnerItem> spinnerItemList = DiseaseUIContentHelper.getInstance(getBaseContext())
                        .getDiseaseSelectionCropPartSymptomSpinnerItemsList(mCrop, mSelectionRound);
                for (SpinnerItem spinnerItem : spinnerItemList) {
                    if (spinnerItem.getId().equals(symptomIdOrDescriptionStr)) {
                        mSpinnerCropPartSymptoms.setSelection(position);
                        break;
                    }
                    position++;
                }
            } else {
                    mEditTextCropPartSymptom.setText(symptomIdOrDescriptionStr);
            }
        }
    }

}
