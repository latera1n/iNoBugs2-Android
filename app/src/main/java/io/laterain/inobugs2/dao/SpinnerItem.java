package io.laterain.inobugs2.dao;

/**
 * Created by dengyuchi on 2/17/17.
 */

public class SpinnerItem {

    private int mId;
    private String mDisplayValue;

    public SpinnerItem(int id, String displayValue) {
        mId = id;
        mDisplayValue = displayValue;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
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
     * @return the display value of this spinner item
     */
    @Override
    public String toString() {
        return mDisplayValue;
    }
}
