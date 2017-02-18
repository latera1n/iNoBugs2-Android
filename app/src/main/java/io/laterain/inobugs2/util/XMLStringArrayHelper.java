package io.laterain.inobugs2.util;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import io.laterain.inobugs2.R;
import io.laterain.inobugs2.dao.SpinnerItem;

/**
 * Created by dengyuchi on 2/17/17.
 */

public class XMLStringArrayHelper {

    public static List<SpinnerItem> buildSpinnerItemListFromResource(Context context, int resource) {
        String[] strArray = context.getResources().getStringArray(resource);
        List<SpinnerItem> spinnerItemList = new ArrayList<>();
        int i = 0;
        for (String str : strArray) {
            spinnerItemList.add(new SpinnerItem(i++, str));
        }
        return spinnerItemList;
    }

}
