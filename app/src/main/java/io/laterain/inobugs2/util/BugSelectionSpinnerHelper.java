package io.laterain.inobugs2.util;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import io.laterain.inobugs2.R;
import io.laterain.inobugs2.dao.SpinnerItem;

/**
 * Created by dengyuchi on 2/28/17.
 */

public class BugSelectionSpinnerHelper {

    private static BugSelectionSpinnerHelper sInstance = null;

    private static List<List<SpinnerItem>> mCropBugSelectionList;

    private BugSelectionSpinnerHelper() {
    }

    public static BugSelectionSpinnerHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new BugSelectionSpinnerHelper();
            mCropBugSelectionList = new ArrayList<>();
            mCropBugSelectionList.add(XMLStringArrayHelper.buildSpinnerItemListFromKeysAndValue(context, R.array._0_rice_bug_keys, R.array._0_rice_bug_names));
            mCropBugSelectionList.add(XMLStringArrayHelper.buildSpinnerItemListFromKeysAndValue(context, R.array._1_wheat_bug_keys, R.array._1_wheat_bug_names));
            mCropBugSelectionList.add(XMLStringArrayHelper.buildSpinnerItemListFromKeysAndValue(context, R.array._2_corn_bug_keys, R.array._2_corn_bug_names));
            mCropBugSelectionList.add(XMLStringArrayHelper.buildSpinnerItemListFromKeysAndValue(context, R.array._3_soybean_bug_keys, R.array._3_soybean_bug_names));
        }
        return sInstance;
    }

    public List<SpinnerItem> getBugSelectionSpinnerItemsListByCrop(int crop) {
        return mCropBugSelectionList.get(crop);
    }

}
