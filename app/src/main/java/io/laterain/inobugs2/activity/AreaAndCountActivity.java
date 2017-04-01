package io.laterain.inobugs2.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import io.laterain.inobugs2.R;
import io.laterain.inobugs2.dao.DiagnoseRecord;
import io.laterain.inobugs2.dao.SpinnerItem;
import io.laterain.inobugs2.util.XMLStringArrayHelper;

public class AreaAndCountActivity extends AppCompatActivity {

    private static final double[] UNITS = {
            1.0,
            100.0 * 2 / 3,
            10_000.0,
            1_000_000.0
    };

    private TextView mTextViewTotalAreaLabel;
    private TextView mTextViewAffectedAreaLabel;
    private EditText mEditTextTotalArea;
    private EditText mEditTextAffectedArea;
    private Spinner mSpinnerTotalArea;
    private Spinner mSpinnerAffectedArea;

    private TextView mTextViewTotalCropCountLabel;
    private TextView mTextViewAffectedBugCropCountLabel;
    private TextView mTextViewAffectedEggCropCountLabel;
    private EditText mEditTextTotalCropCount;
    private EditText mEditTextAffectedBugCropCount;
    private EditText mEditTextAffectedEggCropCount;

    private boolean mIsEditing;
    private DiagnoseRecord mRecord;
    private int mMethod;
    private int mHarm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_and_count);

        mIsEditing = getIntent().getBooleanExtra(getString(R.string.extra_is_editing), false);
        mRecord = (DiagnoseRecord) getIntent().getSerializableExtra(getString(R.string.extra_record_key));
        mMethod = mRecord.getMethod();
        mHarm = mRecord.getHarm();

        mTextViewTotalAreaLabel = (TextView) findViewById(R.id.text_view_area_and_count_total_area_label);
        mTextViewAffectedAreaLabel = (TextView) findViewById(R.id.text_view_area_and_count_affected_area_label);
        mEditTextTotalArea = (EditText) findViewById(R.id.edit_text_area_and_count_total_area);
        mEditTextAffectedArea = (EditText) findViewById(R.id.edit_text_area_and_count_affected_area);
        mSpinnerTotalArea = (Spinner) findViewById(R.id.spinner_area_and_count_total_area);
        mSpinnerAffectedArea = (Spinner) findViewById(R.id.spinner_area_and_count_affected_area);

        mTextViewTotalCropCountLabel = (TextView) findViewById(R.id.text_view_area_and_count_total_crop_count_label);
        mTextViewAffectedBugCropCountLabel = (TextView) findViewById(R.id.text_view_area_and_count_affected_bug_crop_count_label);
        mTextViewAffectedEggCropCountLabel = (TextView) findViewById(R.id.text_view_area_and_count_affected_egg_crop_count_label);
        mEditTextTotalCropCount = (EditText) findViewById(R.id.edit_text_area_and_count_total_crop_count);
        mEditTextAffectedBugCropCount = (EditText) findViewById(R.id.edit_text_area_and_count_affected_bug_crop_count);
        mEditTextAffectedEggCropCount = (EditText) findViewById(R.id.edit_text_area_and_count_affected_egg_crop_count);

        hideViews();
        initSpinners();
        initFloatingActionButton();

        if (mIsEditing) {
            System.out.println(mRecord.getIdCopy());
            initEditingModeData();
        }
    }

    private void hideViews() {
        if (mHarm == DiagnoseRecord.Harm.DISEASES.ordinal()) {
            mTextViewAffectedBugCropCountLabel.setText(getString(R.string.area_and_count_affected_disease_crop_number_label));
            mTextViewAffectedEggCropCountLabel.setVisibility(View.GONE);
            mEditTextAffectedEggCropCount.setVisibility(View.GONE);
        }
        if (mMethod == DiagnoseRecord.Method.BY_AREA.ordinal()) {
            mTextViewTotalCropCountLabel.setVisibility(View.GONE);
            mTextViewAffectedBugCropCountLabel.setVisibility(View.GONE);
            mTextViewAffectedEggCropCountLabel.setVisibility(View.GONE);
            mEditTextTotalCropCount.setVisibility(View.GONE);
            mEditTextAffectedBugCropCount.setVisibility(View.GONE);
            mEditTextAffectedEggCropCount.setVisibility(View.GONE);
        }
        if (mMethod == DiagnoseRecord.Method.BY_CROP_COUNT.ordinal()) {
            mTextViewTotalAreaLabel.setVisibility(View.GONE);
            mTextViewAffectedAreaLabel.setVisibility(View.GONE);
            mEditTextTotalArea.setVisibility(View.GONE);
            mEditTextAffectedArea.setVisibility(View.GONE);
            mSpinnerTotalArea.setVisibility(View.GONE);
            mSpinnerAffectedArea.setVisibility(View.GONE);
        }
    }

    private void initSpinners() {
        List<SpinnerItem> areaSpinnerList = XMLStringArrayHelper.buildSpinnerItemListFromArray(getBaseContext(), R.array.area_units);

        mSpinnerTotalArea.setAdapter(new ArrayAdapter<>(getBaseContext(), R.layout.spinner_text_view_smaller_text_size, areaSpinnerList));
        mSpinnerAffectedArea.setAdapter(new ArrayAdapter<>(getBaseContext(), R.layout.spinner_text_view_smaller_text_size, areaSpinnerList));
    }

    private void initFloatingActionButton() {
        final FloatingActionButton fabNext = (FloatingActionButton) findViewById(R.id.floating_action_button_area_and_count_next);
        fabNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMethod == DiagnoseRecord.Method.BY_AREA.ordinal() || mMethod == DiagnoseRecord.Method.BOTH.ordinal()) {
                    double totalArea = 0.0;
                    double affectedArea = 0.0;
                    try {
                        totalArea = Double.parseDouble(mEditTextTotalArea.getText().toString());
                        affectedArea = Double.parseDouble(mEditTextAffectedArea.getText().toString());
                    } catch (NumberFormatException e) {
                        Toast.makeText(AreaAndCountActivity.this, getString(R.string.toast_message_invalid_area_invalid_format), Toast.LENGTH_SHORT).show();
                    }

                    if (totalArea < 1 || affectedArea < 1) {
                        Toast.makeText(AreaAndCountActivity.this, getString(R.string.toast_message_invalid_area_greater_than_zero), Toast.LENGTH_SHORT).show();
                        return;
                    }

                    int totalAreaUnitIndex = 0;
                    int affectedAreaUnitIndex = 0;
                    try {
                        totalAreaUnitIndex = Integer.parseInt(((SpinnerItem) (mSpinnerTotalArea.getSelectedItem())).getId());
                        affectedAreaUnitIndex = Integer.parseInt(((SpinnerItem) (mSpinnerAffectedArea.getSelectedItem())).getId());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (totalArea * UNITS[totalAreaUnitIndex] < affectedArea * UNITS[affectedAreaUnitIndex]) {
                        Toast.makeText(AreaAndCountActivity.this, getString(R.string.toast_message_invalid_area_total_greater_than_affected), Toast.LENGTH_SHORT).show();
                        return;
                    }

                    mRecord.addOrUpdateAreaInfo(totalArea, affectedArea);
                }
                if (mMethod == DiagnoseRecord.Method.BY_CROP_COUNT.ordinal() || mMethod == DiagnoseRecord.Method.BOTH.ordinal()) {
                    int totalCropCount = -1;
                    int affectedBugCropCount = -1;
                    int affectedEggCropCount = -1;
                    if (mHarm == DiagnoseRecord.Harm.DISEASES.ordinal()) {
                        try {
                            totalCropCount = Integer.parseInt(mEditTextTotalCropCount.getText().toString());
                            affectedBugCropCount = Integer.parseInt(mEditTextAffectedBugCropCount.getText().toString());
                        } catch (NumberFormatException e) {
                            Toast.makeText(AreaAndCountActivity.this, getString(R.string.toast_message_invalid_crop_count_invalid_format), Toast.LENGTH_SHORT).show();
                        }

                        if (totalCropCount < 1 || affectedBugCropCount < 1) {
                            Toast.makeText(AreaAndCountActivity.this, getString(R.string.toast_message_invalid_crop_count_greater_than_zero), Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if (totalCropCount < affectedBugCropCount) {
                            Toast.makeText(AreaAndCountActivity.this, getString(R.string.toast_message_invalid_crop_count_total_greater_than_affected_disease), Toast.LENGTH_SHORT).show();
                            return;
                        }
                    } else {
                        try {
                            totalCropCount = Integer.parseInt(mEditTextTotalCropCount.getText().toString());
                            affectedBugCropCount = Integer.parseInt(mEditTextAffectedBugCropCount.getText().toString());
                            affectedEggCropCount = Integer.parseInt(mEditTextAffectedEggCropCount.getText().toString());
                        } catch (NumberFormatException e) {
                            Toast.makeText(AreaAndCountActivity.this, getString(R.string.toast_message_invalid_crop_count_invalid_format), Toast.LENGTH_SHORT).show();
                        }

                        if (totalCropCount < 1 || affectedBugCropCount < 1 || affectedEggCropCount < 1) {
                            Toast.makeText(AreaAndCountActivity.this, getString(R.string.toast_message_invalid_crop_count_greater_than_zero), Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if (totalCropCount < affectedBugCropCount + affectedEggCropCount) {
                            Toast.makeText(AreaAndCountActivity.this, getString(R.string.toast_message_invalid_crop_count_total_greater_than_affected), Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }

                    mRecord.addOrUpdateCropCountInfo(totalCropCount, affectedBugCropCount, affectedEggCropCount);
                }

                Intent intent;
                if (mHarm == DiagnoseRecord.Harm.DISEASES.ordinal()) {
                    intent = new Intent(getBaseContext(), DiseaseSelectionActivity.class);
                    intent.putExtra(getString(R.string.extra_disease_round_key), 0).putExtra(getString(R.string.extra_record_key), mRecord);
                } else {
                    intent = new Intent(getBaseContext(), BugSelectionActivity.class);
                    intent.putExtra(getString(R.string.extra_record_key), mRecord);
                }
                if (mIsEditing) {
                    intent.putExtra(getString(R.string.extra_is_editing), true);
                }
                startActivity(intent);

            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void initEditingModeData() {
        if (mMethod == DiagnoseRecord.Method.BY_AREA.ordinal() || mMethod == DiagnoseRecord.Method.BOTH.ordinal()) {
            mEditTextTotalArea.setText("" + mRecord.getTotalArea());
            mEditTextAffectedArea.setText("" + mRecord.getAffectedArea());
        }
        if (mMethod == DiagnoseRecord.Method.BY_CROP_COUNT.ordinal() || mMethod == DiagnoseRecord.Method.BOTH.ordinal()) {
            mEditTextTotalCropCount.setText("" + mRecord.getTotalCropCount());
            mEditTextAffectedBugCropCount.setText("" + mRecord.getAffectedBugCropCount());
            if (mHarm == DiagnoseRecord.Harm.BUGS.ordinal()) {
                mEditTextAffectedEggCropCount.setText("" + mRecord.getAffectedEggCropCount());
            }
        }
    }

}
