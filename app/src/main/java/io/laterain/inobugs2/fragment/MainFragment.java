package io.laterain.inobugs2.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.laterain.inobugs2.R;
import io.laterain.inobugs2.activity.AddRecordActivity;
import io.laterain.inobugs2.dao.DiagnoseRecord;

/**
 * A simple {@link Fragment} subclass.
 * <p>
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {

    private final static String STR_NUM_RECORDS_PREFIX = "x ";

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MainFragment.
     */
    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.floating_button_main_new_record);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity().getBaseContext(), AddRecordActivity.class));
            }
        });

        fillUIContents(view);

        return view;
    }

    @SuppressLint("SetTextI18n")
    private void fillUIContents(View view) {
        long numDiseaseRecords = DiagnoseRecord.count(DiagnoseRecord.class, getString(R.string.sql_count_disease_diagnose_records_where_clause), null);
        long numBugRecords = DiagnoseRecord.count(DiagnoseRecord.class, getString(R.string.sql_count_bug_diagnose_records_where_clause), null);
        long numTotalRecords = numDiseaseRecords + numBugRecords;
        ((TextView) view.findViewById(R.id.text_view_view_fragment_main_total_number_record)).setText("" + numTotalRecords);
        if (numTotalRecords > 0) {
            view.findViewById(R.id.text_view_fragment_main_click_to_add_record_label).setVisibility(View.GONE);
            ((TextView) view.findViewById(R.id.text_view_fragment_main_disease_count)).setText(STR_NUM_RECORDS_PREFIX + numDiseaseRecords);
            ((TextView) view.findViewById(R.id.text_view_fragment_main_bug_count)).setText(STR_NUM_RECORDS_PREFIX + numBugRecords);
        } else {
            view.findViewById(R.id.linear_layout_fragment_main_count_view).setVisibility(View.GONE);
        }
    }

}
