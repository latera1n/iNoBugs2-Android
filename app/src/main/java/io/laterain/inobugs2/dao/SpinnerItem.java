package io.laterain.inobugs2.dao;

/**
 * Created by dengyuchi on 2/17/17.
 */

public class SpinnerItem {

    private String mId;
    private String mDisplayValue;

    public SpinnerItem(String id, String displayValue) {
        mId = id;
        mDisplayValue = displayValue;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getDisplayValue() {
        return mDisplayValue;
    }

    public void setDisplayValue(String displayValue) {
        mDisplayValue = displayValue;
    }

    /**
     * Ovrride toString() method for callers to get the display values.
     *
     * @return the display value of this spinner item
     */
    @Override
    public String toString() {
        return mDisplayValue;
    }
}
