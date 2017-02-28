package io.laterain.inobugs2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
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

    private DiagnoseRecord mRecord;
    private int mMethod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_and_count);
        this.mRecord = (DiagnoseRecord) getIntent().getSerializableExtra(getString(R.string.extra_record_key));
        this.mMethod = mRecord.getMethod();

        hideViews();
        initSpinners();
        initFloatingActionButton();
    }

    private void hideViews() {
        if (mMethod == DiagnoseRecord.Method.BY_AREA.ordinal()) {
            findViewById(R.id.text_view_area_and_count_total_crop_count_label).setVisibility(View.GONE);
            findViewById(R.id.text_view_area_and_count_affected_bug_crop_count_label).setVisibility(View.GONE);
            findViewById(R.id.edit_text_area_and_count_total_crop_count).setVisibility(View.GONE);
            findViewById(R.id.edit_text_area_and_count_affected_bug_crop_count).setVisibility(View.GONE);
            findViewById(R.id.edit_text_area_and_count_affected_egg_crop_count).setVisibility(View.GONE);
        } else if (mMethod == DiagnoseRecord.Method.BY_CROP_COUNT.ordinal()) {
            findViewById(R.id.text_view_area_and_count_total_area_label).setVisibility(View.GONE);
            findViewById(R.id.text_view_area_and_count_affected_area_label).setVisibility(View.GONE);
            findViewById(R.id.edit_text_area_and_count_total_area).setVisibility(View.GONE);
            findViewById(R.id.edit_text_area_and_count_affected_area).setVisibility(View.GONE);
            findViewById(R.id.spinner_area_and_count_total_area).setVisibility(View.GONE);
            findViewById(R.id.spinner_area_and_count_affected_area).setVisibility(View.GONE);
        }
    }

    private void initSpinners() {
        Spinner spinnerTotalArea = (Spinner) findViewById(R.id.spinner_area_and_count_total_area);
        Spinner spinnerAffectedArea = (Spinner) findViewById(R.id.spinner_area_and_count_affected_area);
        List<SpinnerItem> areaSpinnerList = XMLStringArrayHelper.buildSpinnerItemListFromArray(getBaseContext(), R.array.area_units);

        spinnerTotalArea.setAdapter(new ArrayAdapter<>(getBaseContext(), R.layout.spinner_text_view_smaller_text_size, areaSpinnerList));
        spinnerAffectedArea.setAdapter(new ArrayAdapter<>(getBaseContext(), R.layout.spinner_text_view_smaller_text_size, areaSpinnerList));
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
                        totalArea = Integer.parseInt(((EditText) findViewById(R.id.edit_text_area_and_count_total_area)).getText().toString());
                        affectedArea = Integer.parseInt(((EditText) findViewById(R.id.edit_text_area_and_count_affected_area)).getText().toString());
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
                        totalAreaUnitIndex = Integer.parseInt(((SpinnerItem) ((Spinner) findViewById(R.id.spinner_area_and_count_total_area)).getSelectedItem()).getId());
                        affectedAreaUnitIndex = Integer.parseInt(((SpinnerItem) ((Spinner) findViewById(R.id.spinner_area_and_count_affected_area)).getSelectedItem()).getId());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (totalArea * UNITS[totalAreaUnitIndex] < affectedArea * UNITS[affectedAreaUnitIndex]) {
                        Toast.makeText(AreaAndCountActivity.this, getString(R.string.toast_message_invalid_area_total_greater_than_affected), Toast.LENGTH_SHORT).show();
                        return;
                    }

                    mRecord.addAreaInfo(totalArea, affectedArea);
                }
                if (mMethod == DiagnoseRecord.Method.BY_CROP_COUNT.ordinal() || mMethod == DiagnoseRecord.Method.BOTH.ordinal()) {
                    int totalCropCount = 0;
                    int affectedBugCropCount = 0;
                    int affectedEggCropCount = 0;
                    try {
                        totalCropCount = Integer.parseInt(((EditText) findViewById(R.id.edit_text_area_and_count_total_crop_count)).getText().toString());
                        affectedBugCropCount = Integer.parseInt(((EditText) findViewById(R.id.edit_text_area_and_count_affected_bug_crop_count)).getText().toString());
                        affectedEggCropCount = Integer.parseInt(((EditText) findViewById(R.id.edit_text_area_and_count_affected_egg_crop_count)).getText().toString());
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

                    mRecord.addCropCountInfo(totalCropCount, affectedBugCropCount, affectedEggCropCount);
                }

                if (mRecord.getHarm() == DiagnoseRecord.Harm.DISESES.ordinal()) {
                    startActivity(new Intent(getBaseContext(), DiseaseSelectionActivity.class)
                            .putExtra(getString(R.string.extra_disease_round_key), 0)
                            .putExtra(getString(R.string.extra_record_key), mRecord));
                } else {
                    startActivity(new Intent(getBaseContext(), BugSelectionActivity.class)
                            .putExtra(getString(R.string.extra_record_key), mRecord));
                }
            }
        });
    }

}
