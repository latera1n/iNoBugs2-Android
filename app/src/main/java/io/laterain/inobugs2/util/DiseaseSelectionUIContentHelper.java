package io.laterain.inobugs2.util;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import io.laterain.inobugs2.R;
import io.laterain.inobugs2.dao.SpinnerItem;

/**
 * Created by dengyuchi on 2/24/17.
 */

public class DiseaseSelectionUIContentHelper {

    private static final int NUM_CROPS = 4;
    public static final int[] NUM_PARTS_FOR_CROP = {
            8,
            8,
            8,
            7
    };
    private static final String[] STR_CROP_NAMES = {
            "rice",
            "wheat",
            "corn",
            "soybean"
    };

    private static DiseaseSelectionUIContentHelper sInstance = null;

    private static List<String[]> sCropPartLabel;
    private static List<List<List<SpinnerItem>>> sDiseaseSpinnerItemsList;

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
            sCropPartLabel = new ArrayList<>();
            sCropPartLabel.add(context.getResources().getStringArray(R.array._0_rice_part_names));
            sCropPartLabel.add(context.getResources().getStringArray(R.array._1_wheat_parts_names));
            sCropPartLabel.add(context.getResources().getStringArray(R.array._2_corn_parts_names));
            sCropPartLabel.add(context.getResources().getStringArray(R.array._3_soybean_parts_names));

            sDiseaseSpinnerItemsList = new ArrayList<>();
            for (int i = 0; i < NUM_CROPS; i++) {
                List<List<SpinnerItem>> currentCropDiseaseSpinnerItemsList = new ArrayList<>();
                for (int j = 0; j < NUM_PARTS_FOR_CROP[i]; j++) {
                    String idValues = "_" + i + "_" + STR_CROP_NAMES[i] + "_disease_descriptions_" + j;
                    String idKeys = idValues + "_keys";
                    currentCropDiseaseSpinnerItemsList.add(XMLStringArrayHelper.buildSpinnerItemListFromKeysAndValue(context,
                            context.getResources().getIdentifier(idKeys, "array", context.getPackageName()),
                            context.getResources().getIdentifier(idValues, "array", context.getPackageName())));
                }
                sDiseaseSpinnerItemsList.add(currentCropDiseaseSpinnerItemsList);
            }
        }
        return sInstance;
    }

    public String getDiseaseSelectionCropPartLabel(int crop, int selectionRound) {
        return sCropPartLabel.get(crop)[selectionRound];
    }

    public List<SpinnerItem> getDiseaseSelectionCropPartSymptonSpinnerItemsList(int crop, int selectionRound) {
        return sDiseaseSpinnerItemsList.get(crop).get(selectionRound);
    }

}
