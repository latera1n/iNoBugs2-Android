package io.laterain.inobugs2.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import io.laterain.inobugs2.R;
import io.laterain.inobugs2.adapter.AllRecordsRecyclerViewAdapter;
import io.laterain.inobugs2.dao.DiagnoseRecord;

/**
 * Modified by dengyuchi on 3/24/17.
 */

public class AllRecordsFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    public AllRecordsFragment() {
    }

    @SuppressWarnings("unused")
    public static AllRecordsFragment newInstance(int columnCount) {
        AllRecordsFragment fragment = new AllRecordsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_records_list, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_fragment_all_records_list);
        if (recyclerView != null) {
            Context context = view.getContext();
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            List<DiagnoseRecord> diagnoseRecordList = new ArrayList<>();
            try {
                diagnoseRecordList = DiagnoseRecord.findWithQuery(DiagnoseRecord.class, getString(R.string.sql_select_all_diagnose_records));
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (diagnoseRecordList.size() > 0) {
                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
                recyclerView.addItemDecoration(dividerItemDecoration);
                recyclerView.setAdapter(new AllRecordsRecyclerViewAdapter(getContext(), diagnoseRecordList, mListener));
            } else {
                recyclerView.setVisibility(View.GONE);
                view.findViewById(R.id.text_view_fragment_all_records_list_empty_label).setVisibility(View.VISIBLE);
                recyclerView.setAdapter(new AllRecordsRecyclerViewAdapter(getContext(), diagnoseRecordList, mListener));
            }
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        void onAllRecordsFragmentInteraction(DiagnoseRecord item);
    }
}
