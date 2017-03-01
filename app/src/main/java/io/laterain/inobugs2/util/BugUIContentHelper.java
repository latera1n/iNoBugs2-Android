package io.laterain.inobugs2.util;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.laterain.inobugs2.R;
import io.laterain.inobugs2.dao.SpinnerItem;

/**
 * Created by dengyuchi on 2/28/17.
 */

public class BugUIContentHelper {

    private static BugUIContentHelper sInstance = null;

    private static List<List<SpinnerItem>> mCropBugSelectionList;
    private static List<Map<String, String>> mCropBugNamesMapList;

    private BugUIContentHelper() {
    }

    public static BugUIContentHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new BugUIContentHelper();
            mCropBugSelectionList = new ArrayList<>();
            mCropBugSelectionList.add(XMLStringArrayHelper.buildSpinnerItemListFromKeysAndValue(context, R.array._0_rice_bug_keys, R.array._0_rice_bug_names));
            mCropBugSelectionList.add(XMLStringArrayHelper.buildSpinnerItemListFromKeysAndValue(context, R.array._1_wheat_bug_keys, R.array._1_wheat_bug_names));
            mCropBugSelectionList.add(XMLStringArrayHelper.buildSpinnerItemListFromKeysAndValue(context, R.array._2_corn_bug_keys, R.array._2_corn_bug_names));
            mCropBugSelectionList.add(XMLStringArrayHelper.buildSpinnerItemListFromKeysAndValue(context, R.array._3_soybean_bug_keys, R.array._3_soybean_bug_names));
            mCropBugNamesMapList = new ArrayList<>();
            mCropBugNamesMapList.add(XMLStringArrayHelper.buildMapFromKeysAndValue(context, R.array._0_rice_bug_keys, R.array._0_rice_bug_names));
            mCropBugNamesMapList.add(XMLStringArrayHelper.buildMapFromKeysAndValue(context, R.array._1_wheat_bug_keys, R.array._1_wheat_bug_names));
            mCropBugNamesMapList.add(XMLStringArrayHelper.buildMapFromKeysAndValue(context, R.array._2_corn_bug_keys, R.array._2_corn_bug_names));
            mCropBugNamesMapList.add(XMLStringArrayHelper.buildMapFromKeysAndValue(context, R.array._3_soybean_bug_keys, R.array._3_soybean_bug_names));
        }
        return sInstance;
    }

    public List<SpinnerItem> getBugSelectionSpinnerItemsListByCrop(int crop) {
        return mCropBugSelectionList.get(crop);
    }

    public String getBugName(int crop, String bugNameKey) {
        return mCropBugNamesMapList.get(crop).get(bugNameKey);
    }

}
