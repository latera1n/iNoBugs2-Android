package io.laterain.inobugs2.activity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import io.laterain.inobugs2.R;
import io.laterain.inobugs2.dao.DiagnoseRecord;
import io.laterain.inobugs2.fragment.UniversalAlertDialogFragment;
import io.laterain.inobugs2.util.BugUIContentHelper;
import io.laterain.inobugs2.util.DateHelper;
import io.laterain.inobugs2.util.DiseaseUIContentHelper;

public class ResultActivity extends AppCompatActivity {

    private final static String STR_TEXT_FIELD_BUG_AGE_X_ID_PREFIX = "text_view_result_bug_count_detail_age_";
    private final static String STR_TEXT_FIELD_BUG_AGE_X_ID_SUFFIX = "_content";
    private final static String STR_TEXT_FIELD_DISEASE_DIAGNOSE_RESULT_X_ID_PREFIX = "text_view_result_disease_diagnose_result_";
    private final static String STR_TEXT_FIELD_DISEASE_DIAGNOSE_RESULT_X_ID_SUFFIX = "_content";
    private final static String STR_TEXT_FIELD_DISEASE_DETAIL_PART_X_ID_PREFIX = "text_view_result_disease_disease_detail_part_";
    private final static String STR_TEXT_FIELD_DISEASE_DETAIL_PART_X_ID_LABEL_SUFFIX = "_label";
    private final static String STR_TEXT_FIELD_DISEASE_DETAIL_PART_X_ID_CONTENT_SUFFIX = "_content";

    private final static String STR_DISEASE_RESULT_PERCENTAGE_SUFFIX = "%";

    private DiagnoseRecord mRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mRecord = (DiagnoseRecord) getIntent().getSerializableExtra(getString(R.string.extra_record_key));
        setContentView(mRecord.getHarm() == DiagnoseRecord.Harm.DISEASES.ordinal() ?
                R.layout.activity_result_disease : R.layout.activity_result_bug);

        mRecord.calculateAndSaveResults();
        fillUIContents();
        initFloatingActionButton();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (getIntent().getBooleanExtra(getString(R.string.extra_should_show_delete_record_button), false)) {
            getMenuInflater().inflate(R.menu.menu_result_view, menu);
        } else {
            getMenuInflater().inflate(R.menu.menu_result, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_result_close) {
            UniversalAlertDialogFragment.newInstance(R.string.dialog_universal_discard_saving_record_title,
                    R.string.dialog_universal_discard_saving_record_message, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        }
                    }).show(getSupportFragmentManager(), null);
        } else if (id == R.id.action_result_delete) {
            UniversalAlertDialogFragment.newInstance(R.string.dialog_universal_delete_title,
                    R.string.dialog_universal_delete_message, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            System.out.println(mRecord.getIdCopy());
                            if (mRecord.delete()) {
                                Toast.makeText(ResultActivity.this, R.string.toast_message_deleting_record_success, Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(ResultActivity.this, R.string.toast_message_deleting_record_error, Toast.LENGTH_SHORT).show();
                            }
                            Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        }
                    }).show(getSupportFragmentManager(), null);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("SetTextI18n")
    private void fillUIContents() {
        if (mRecord.getHarm() == DiagnoseRecord.Harm.DISEASES.ordinal()) {
            ((TextView) findViewById(R.id.text_view_result_disease_basic_info_type_content)).setText(
                    getResources().getStringArray(R.array.crop_categories)[mRecord.getCrop()]
                            + getResources().getStringArray(R.array.harm_categories)[mRecord.getHarm()]
            );
            ((TextView) findViewById(R.id.text_view_result_disease_basic_info_date_and_location_content)).setText(
                    DateHelper.convertToFormattedDateString(mRecord.getTimeStamp()) + " - " + mRecord.getLocation()
            );
            ((TextView) findViewById(R.id.text_view_result_disease_basic_info_method_and_mode_content)).setText(
                    getResources().getStringArray(R.array.method_categories)[mRecord.getMethod()]
                            + " - " + getResources().getStringArray(R.array.mode_categories)[mRecord.getMode()]
            );
            if (mRecord.getMode() == DiagnoseRecord.Mode.NORMAL.ordinal()) {
                String[] diagnoseResults = mRecord.get3DiagnoseResults();
                for (int i = 0; i < diagnoseResults.length; i++) {
                    String textViewId = STR_TEXT_FIELD_DISEASE_DIAGNOSE_RESULT_X_ID_PREFIX + i + STR_TEXT_FIELD_DISEASE_DIAGNOSE_RESULT_X_ID_SUFFIX;
                    if (diagnoseResults[i] != null && diagnoseResults[i].length() > 0) {
                        String[] resultPartsArray = diagnoseResults[i].split(DiagnoseRecord.STR_RESULT_SEPARATOR);
                        if (resultPartsArray.length == 2) {
                            ((TextView) findViewById(getResources().getIdentifier(textViewId, "id", getPackageName())))
                                    .setText(DiseaseUIContentHelper.getInstance(getBaseContext()).getDiseaseName(mRecord.getCrop(), resultPartsArray[1])
                                            + DiagnoseRecord.STR_RESULT_SEPARATOR_DISPLAY + resultPartsArray[0] + STR_DISEASE_RESULT_PERCENTAGE_SUFFIX);
                        }
                    } else {
                        if (i > 0) {
                            findViewById(getResources().getIdentifier(textViewId, "id", getPackageName())).setVisibility(View.GONE);
                        }
                    }
                }
            } else {
                ((TextView) findViewById(R.id.text_view_result_disease_diagnose_result_title_label)).setText(getString(R.string.result_disease_disease_name_title_label));
                ((TextView) findViewById(R.id.text_view_result_disease_diagnose_result_0_content)).setText(mRecord.getResult1());
                findViewById(R.id.text_view_result_disease_diagnose_result_1_content).setVisibility(View.GONE);
                findViewById(R.id.text_view_result_disease_diagnose_result_2_content).setVisibility(View.GONE);
            }
            int numParts = DiseaseUIContentHelper.NUM_PARTS_FOR_CROP[mRecord.getCrop()];
            for (int i = DiagnoseRecord.NUM_INFO_FIELDS - 1; i >= numParts; i--) {
                findViewById(getResources().getIdentifier(STR_TEXT_FIELD_DISEASE_DETAIL_PART_X_ID_PREFIX + i + STR_TEXT_FIELD_DISEASE_DETAIL_PART_X_ID_LABEL_SUFFIX, "id", getPackageName())).setVisibility(View.GONE);
                findViewById(getResources().getIdentifier(STR_TEXT_FIELD_DISEASE_DETAIL_PART_X_ID_PREFIX + i + STR_TEXT_FIELD_DISEASE_DETAIL_PART_X_ID_CONTENT_SUFFIX, "id", getPackageName())).setVisibility(View.GONE);
            }
            String[] partLabelsArray = DiseaseUIContentHelper.getInstance(getBaseContext()).getDiseaseSelectionCropPartLabelsArray(mRecord.getCrop());
            String[] partDiseaseContentArray = mRecord.getPartSymptomKeysArray();
            for (int i = 0; i < numParts; i++) {
                ((TextView) findViewById(getResources().getIdentifier(STR_TEXT_FIELD_DISEASE_DETAIL_PART_X_ID_PREFIX + i + STR_TEXT_FIELD_DISEASE_DETAIL_PART_X_ID_LABEL_SUFFIX, "id", getPackageName()))).setText(partLabelsArray[i]);
                if (mRecord.getMode() == DiagnoseRecord.Mode.NORMAL.ordinal()) {
                    ((TextView) findViewById(getResources().getIdentifier(STR_TEXT_FIELD_DISEASE_DETAIL_PART_X_ID_PREFIX + i + STR_TEXT_FIELD_DISEASE_DETAIL_PART_X_ID_CONTENT_SUFFIX, "id", getPackageName())))
                            .setText(DiseaseUIContentHelper.getInstance(getBaseContext()).getSymptomDescription(mRecord.getCrop(), partDiseaseContentArray[i]));
                } else {
                    ((TextView) findViewById(getResources().getIdentifier(STR_TEXT_FIELD_DISEASE_DETAIL_PART_X_ID_PREFIX + i + STR_TEXT_FIELD_DISEASE_DETAIL_PART_X_ID_CONTENT_SUFFIX, "id", getPackageName())))
                            .setText(partDiseaseContentArray[i]);
                }
            }
            int method = mRecord.getMethod();
            if (method == DiagnoseRecord.Method.BY_AREA.ordinal()) {
                findViewById(R.id.text_view_result_disease_count_result_total_crop_count_label).setVisibility(View.GONE);
                findViewById(R.id.text_view_result_disease_count_result_total_crop_count_content).setVisibility(View.GONE);
                findViewById(R.id.text_view_result_disease_count_result_affected_disease_crop_count_label).setVisibility(View.GONE);
                findViewById(R.id.text_view_result_disease_count_result_affected_disease_crop_count_content).setVisibility(View.GONE);
                findViewById(R.id.text_view_result_disease_count_result_affected_disease_crop_count_percentage_label).setVisibility(View.GONE);
                findViewById(R.id.text_view_result_disease_count_result_affected_disease_crop_count_percentage_content).setVisibility(View.GONE);
            }
            if (method == DiagnoseRecord.Method.BY_CROP_COUNT.ordinal()) {
                findViewById(R.id.text_view_result_disease_count_result_total_area_label).setVisibility(View.GONE);
                findViewById(R.id.text_view_result_disease_count_result_total_area_content).setVisibility(View.GONE);
                findViewById(R.id.text_view_result_disease_count_result_affected_area_label).setVisibility(View.GONE);
                findViewById(R.id.text_view_result_disease_count_result_affected_area_content).setVisibility(View.GONE);
                findViewById(R.id.text_view_result_disease_count_result_affected_area_percentage_label).setVisibility(View.GONE);
                findViewById(R.id.text_view_result_disease_count_result_affected_area_percentage_content).setVisibility(View.GONE);
            }
            if (method == DiagnoseRecord.Method.BY_AREA.ordinal() || method == DiagnoseRecord.Method.BOTH.ordinal()) {
                ((TextView) findViewById(R.id.text_view_result_disease_count_result_total_area_content)).setText(String.format("%.2f", mRecord.getTotalArea()));
                ((TextView) findViewById(R.id.text_view_result_disease_count_result_affected_area_content)).setText(String.format("%.2f", mRecord.getAffectedArea()));
                ((TextView) findViewById(R.id.text_view_result_disease_count_result_affected_area_percentage_content)).setText(String.format("%.2f", (mRecord.getAffectedArea() / mRecord.getTotalArea() * 100.0)) + "%");
            }
            if (method == DiagnoseRecord.Method.BY_CROP_COUNT.ordinal() || method == DiagnoseRecord.Method.BOTH.ordinal()) {
                ((TextView) findViewById(R.id.text_view_result_disease_count_result_total_crop_count_content)).setText("" + mRecord.getTotalCropCount());
                ((TextView) findViewById(R.id.text_view_result_disease_count_result_affected_disease_crop_count_content)).setText("" + mRecord.getAffectedBugCropCount());
                ((TextView) findViewById(R.id.text_view_result_disease_count_result_affected_disease_crop_count_percentage_content)).setText(String.format("%.2f", (mRecord.getAffectedBugCropCount() * 100.0 / mRecord.getTotalCropCount())) + "%");
            }
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
            if (mRecord.getMode() == DiagnoseRecord.Mode.NORMAL.ordinal()) {
                ((TextView) findViewById(R.id.text_view_result_bug_bug_name_content)).setText(
                        BugUIContentHelper.getInstance(getBaseContext()).getBugName(mRecord.getCrop(), mRecord.getInfo0())
                );
            } else {
                ((TextView) findViewById(R.id.text_view_result_bug_bug_name_content)).setText(mRecord.getInfo0());
            }
            int[] bugAndEggCount = mRecord.getEggAndBugCountArray();
            int bugCountSum = 0;
            for (int i = 1; i < DiagnoseRecord.NUM_INFO_FIELDS - 1; i++) {
                String textFieldId = STR_TEXT_FIELD_BUG_AGE_X_ID_PREFIX + i + STR_TEXT_FIELD_BUG_AGE_X_ID_SUFFIX;
                int currentBugCount = bugAndEggCount[i];
                bugCountSum += currentBugCount;
                ((TextView) findViewById(getResources().getIdentifier(textFieldId, "id", getPackageName()))).setText("" + currentBugCount);
            }
            ((TextView) findViewById(R.id.result_bug_count_detail_bug_count_total_content)).setText("" + bugCountSum);
            ((TextView) findViewById(R.id.result_bug_count_detail_egg_count_total_content)).setText("" + bugAndEggCount[0]);
            int method = mRecord.getMethod();
            if (method == DiagnoseRecord.Method.BY_AREA.ordinal()) {
                findViewById(R.id.text_view_result_bug_count_result_total_crop_count_label).setVisibility(View.GONE);
                findViewById(R.id.text_view_result_bug_count_result_total_crop_count_content).setVisibility(View.GONE);
                findViewById(R.id.text_view_result_bug_count_result_affected_bug_crop_count_label).setVisibility(View.GONE);
                findViewById(R.id.text_view_result_bug_count_result_affected_bug_crop_count_content).setVisibility(View.GONE);
                findViewById(R.id.text_view_result_bug_count_result_affected_egg_crop_count_label).setVisibility(View.GONE);
                findViewById(R.id.text_view_result_bug_count_result_affected_egg_crop_count_content).setVisibility(View.GONE);
                findViewById(R.id.text_view_result_bug_count_result_affected_bug_crop_count_percentage_label).setVisibility(View.GONE);
                findViewById(R.id.text_view_result_bug_count_result_affected_bug_crop_count_percentage_content).setVisibility(View.GONE);
                findViewById(R.id.text_view_result_bug_count_result_affected_egg_crop_count_percentage_label).setVisibility(View.GONE);
                findViewById(R.id.text_view_result_bug_count_result_affected_egg_crop_count_percentage_content).setVisibility(View.GONE);
            }
            if (method == DiagnoseRecord.Method.BY_CROP_COUNT.ordinal()) {
                findViewById(R.id.text_view_result_bug_count_result_total_area_label).setVisibility(View.GONE);
                findViewById(R.id.text_view_result_bug_count_result_total_area_content).setVisibility(View.GONE);
                findViewById(R.id.text_view_result_bug_count_result_affected_area_label).setVisibility(View.GONE);
                findViewById(R.id.text_view_result_bug_count_result_affected_area_content).setVisibility(View.GONE);
                findViewById(R.id.text_view_result_bug_count_result_affected_area_percentage_label).setVisibility(View.GONE);
                findViewById(R.id.text_view_result_bug_count_result_affected_area_percentage_content).setVisibility(View.GONE);
            }
            if (method == DiagnoseRecord.Method.BY_AREA.ordinal() || method == DiagnoseRecord.Method.BOTH.ordinal()) {
                ((TextView) findViewById(R.id.text_view_result_bug_count_result_total_area_content)).setText(String.format("%.2f", mRecord.getTotalArea()));
                ((TextView) findViewById(R.id.text_view_result_bug_count_result_affected_area_content)).setText(String.format("%.2f", mRecord.getAffectedArea()));
                ((TextView) findViewById(R.id.text_view_result_bug_count_result_affected_area_percentage_content)).setText(String.format("%.2f", (mRecord.getAffectedArea() / mRecord.getTotalArea() * 100.0)) + "%");
            }
            if (method == DiagnoseRecord.Method.BY_CROP_COUNT.ordinal() || method == DiagnoseRecord.Method.BOTH.ordinal()) {
                ((TextView) findViewById(R.id.text_view_result_bug_count_result_total_crop_count_content)).setText("" + mRecord.getTotalCropCount());
                ((TextView) findViewById(R.id.text_view_result_bug_count_result_affected_bug_crop_count_content)).setText("" + mRecord.getAffectedBugCropCount());
                ((TextView) findViewById(R.id.text_view_result_bug_count_result_affected_egg_crop_count_content)).setText("" + mRecord.getAffectedEggCropCount());
                ((TextView) findViewById(R.id.text_view_result_bug_count_result_affected_bug_crop_count_percentage_content)).setText(String.format("%.2f", (mRecord.getAffectedBugCropCount() * 100.0 / mRecord.getTotalCropCount())) + "%");
                ((TextView) findViewById(R.id.text_view_result_bug_count_result_affected_egg_crop_count_percentage_content)).setText(String.format("%.2f", (mRecord.getAffectedEggCropCount() * 100.0 / mRecord.getTotalCropCount())) + "%");
            }
        }
        String recordNote = mRecord.getNote();
        if (recordNote.length() > 0) {
            ((TextView) findViewById(R.id.text_view_result_bug_note_content)).setText(recordNote);
        }
    }

    private void initFloatingActionButton() {
        final FloatingActionButton fabSave;
        if (mRecord.getHarm() == DiagnoseRecord.Harm.DISEASES.ordinal()) {
            fabSave = (FloatingActionButton) findViewById(R.id.floating_action_button_result_disease_next);
        } else {
            fabSave = (FloatingActionButton) findViewById(R.id.floating_action_button_result_bug_next);
        }
        if (getIntent().getBooleanExtra(getString(R.string.extra_should_show_edit_fab_button), false)) {
            fabSave.setImageDrawable(getDrawable(R.mipmap.ic_edit_white_24dp));
            fabSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.out.println(mRecord.getIdCopy());
                    startActivity(new Intent(ResultActivity.this, AddRecordActivity.class)
                            .putExtra(getString(R.string.extra_record_key), mRecord)
                            .putExtra(getString(R.string.extra_is_editing), true));
                }
            });
        } else {
            fabSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    long id = mRecord.getIdCopy();
                    if (id > 0) {
                        mRecord.setId(id);
                    }
                    if (mRecord.save() > 0) {
                        UniversalAlertDialogFragment.newInstance(R.string.dialog_universal_save_record_success_title, -1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                            }
                        }).show(getSupportFragmentManager(), null);
                    }
                }
            });
        }
    }

}
