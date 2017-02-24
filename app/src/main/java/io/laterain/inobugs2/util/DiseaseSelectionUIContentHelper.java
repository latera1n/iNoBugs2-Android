package io.laterain.inobugs2.util;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import io.laterain.inobugs2.R;

/**
 * Created by dengyuchi on 2/24/17.
 */

public class DiseaseSelectionUIContentHelper {

    private static DiseaseSelectionUIContentHelper sInstance = null;
    private static List<String[]> cropPartLabel;

    /**
     * Required private constructor.
     */
    private DiseaseSelectionUIContentHelper() {
    }

    /**
     * Get the singleton instance of this class. If the instance is null, init it.
     *
     * @return the singleton instance of this class
     */
    public static DiseaseSelectionUIContentHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DiseaseSelectionUIContentHelper();
            cropPartLabel = new ArrayList<>();
            cropPartLabel.add(context.getResources().getStringArray(R.array._0_rice_part_names));
            cropPartLabel.add(context.getResources().getStringArray(R.array._1_wheat_parts_names));
            cropPartLabel.add(context.getResources().getStringArray(R.array._2_corn_parts_names));
            cropPartLabel.add(context.getResources().getStringArray(R.array._3_soybean_parts_names));
        }
        return sInstance;
    }

    public static String getDiseaseSelectionCropPartLabel(int crop, int selectionRound) {
        return cropPartLabel.get(crop)[selectionRound];
    }

}
