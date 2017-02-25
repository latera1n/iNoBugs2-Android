package io.laterain.inobugs2.util;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import io.laterain.inobugs2.dao.SpinnerItem;

/**
 * Created by dengyuchi on 2/17/17.
 */

public class XMLStringArrayHelper {

    public static List<SpinnerItem> buildSpinnerItemListFromArray(Context context, int resource) {
        String[] strArray = context.getResources().getStringArray(resource);
        List<SpinnerItem> spinnerItemList = new ArrayList<>();
        int i = 0;
        for (String str : strArray) {
            spinnerItemList.add(new SpinnerItem("" + i, str));
            i++;
        }
        return spinnerItemList;
    }

    public static List<SpinnerItem> buildSpinnerItemListFromKeysAndValue(Context context, int keysResource, int valuesResource) {
        String[] keysArray = context.getResources().getStringArray(keysResource);
        String[] valuesArray = context.getResources().getStringArray(valuesResource);
        int length = keysArray.length;
        List<SpinnerItem> spinnerItemList = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            spinnerItemList.add(new SpinnerItem(keysArray[i], valuesArray[i]));
        }
        return spinnerItemList;
    }

}
