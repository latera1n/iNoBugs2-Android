package io.laterain.inobugs2.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import io.laterain.inobugs2.R;
import io.laterain.inobugs2.dao.DiagnoseRecord;
import io.laterain.inobugs2.fragment.AllRecordsFragment.OnListFragmentInteractionListener;
import io.laterain.inobugs2.util.DateHelper;

public class AllRecordsRecyclerViewAdapter extends RecyclerView.Adapter<AllRecordsRecyclerViewAdapter.RecordViewHolder> {

    private final static String STR_LIST_CELL_INFO_SPLITTER = " - ";

    private final Context mContext;
    private final List<DiagnoseRecord> mDiagnoseRecordList;
    private final OnListFragmentInteractionListener mListener;
    private final String[] mListCellInfoMethodStrList;
    private final String[] mListCellInfoModeStrList;

    public AllRecordsRecyclerViewAdapter(Context context, List<DiagnoseRecord> diagnoseRecordList, OnListFragmentInteractionListener listener) {
        this.mContext = context;
        this.mDiagnoseRecordList = diagnoseRecordList;
        this.mListener = listener;
        this.mListCellInfoMethodStrList = context.getResources().getStringArray(R.array.method_categories);
        this.mListCellInfoModeStrList = context.getResources().getStringArray(R.array.mode_categories);
    }

    @Override
    public RecordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_all_records, parent, false);
        return new RecordViewHolder(view);
    }

    @Override
    @SuppressWarnings("SetTextI18n")
    public void onBindViewHolder(final RecordViewHolder holder, int position) {
        DiagnoseRecord diagnoseRecord = mDiagnoseRecordList.get(position);
        holder.mRecord = diagnoseRecord;
        if (diagnoseRecord.getHarm() == DiagnoseRecord.Harm.DISEASES.ordinal()) {
            holder.mImageView.setImageResource(R.mipmap.ic_list_virus);
        }
        holder.mTitleView.setText(mContext.getResources().getStringArray(R.array.crop_categories)[diagnoseRecord.getCrop()]
                + mContext.getResources().getStringArray(R.array.harm_categories)[diagnoseRecord.getHarm()]);
        holder.mInfoView.setText(DateHelper.convertToFormattedDateString(holder.mRecord.getTimeStamp())
                + STR_LIST_CELL_INFO_SPLITTER + holder.mRecord.getLocation()
                + STR_LIST_CELL_INFO_SPLITTER + mListCellInfoMethodStrList[holder.mRecord.getMethod()]
                + STR_LIST_CELL_INFO_SPLITTER + mListCellInfoModeStrList[holder.mRecord.getMode()]);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onAllRecordsFragmentInteraction(holder.mRecord);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDiagnoseRecordList.size();
    }

    class RecordViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        final ImageView mImageView;
        final TextView mTitleView;
        final TextView mInfoView;
        DiagnoseRecord mRecord;

        RecordViewHolder(View view) {
            super(view);
            mView = view;
            mImageView = (ImageView) view.findViewById(R.id.image_view_fragment_all_records);
            mTitleView = (TextView) view.findViewById(R.id.text_view_fragment_all_records_title_label);
            mInfoView = (TextView) view.findViewById(R.id.text_view_fragment_all_records_info);
        }

        @Override
        public String toString() {
            return mRecord.toString();
        }
    }
}
