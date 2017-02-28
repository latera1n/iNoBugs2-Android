package io.laterain.inobugs2.dao;


import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by dengyuchi on 2/16/17.
 */

public class DiagnoseRecord extends SugarRecord implements Serializable {

    // Persistent fields
    private int mCrop;
    private int mHarm;
    private int mMode;
    private int mMethod;
    private long mTimeStamp;
    private String mLocation;
    private String mNote;

    private double mTotalArea;
    private double mAffectedArea;
    private int mTotalCropCount;
    private int mAffectedBugCropCount;
    private int mAffectedEggCropCount;

    private String mInfo0;
    private String mInfo1;
    private String mInfo2;
    private String mInfo3;
    private String mInfo4;
    private String mInfo5;
    private String mInfo6;
    private String mInfo7;

    private String mResult1;
    private String mResult2;
    private String mResult3;

    /**
     * Required empty constructor.
     */
    public DiagnoseRecord() {
        this.mLocation = "";
        this.mNote = "";
        this.mInfo0 = "";
        this.mInfo1 = "";
        this.mInfo2 = "";
        this.mInfo3 = "";
        this.mInfo4 = "";
        this.mInfo5 = "";
        this.mInfo6 = "";
        this.mInfo7 = "";
        this.mResult1 = "";
        this.mResult2 = "";
        this.mResult3 = "";
    }

    /**
     * Constructor for basic info input at the initial page.
     *
     * @param crop      the crop type should be
     *                  0: rice,
     *                  1: wheat,
     *                  2: corn,
     *                  3: soybean
     * @param harm      the harm type should be
     *                  0: bugs,
     *                  1: diseases
     * @param mode      the statistic mode should be
     *                  0: normal,
     *                  1: expert
     * @param method    the statistic method should be
     *                  0: by area,
     *                  1: by crop count,
     *                  2: both
     * @param timeStamp the epoch time stamp in seconds
     * @param location  the location of this record
     * @param note      the note for this record
     */
    public DiagnoseRecord(int crop, int harm, int mode, int method, long timeStamp, String location, String note) {
        this();
        this.mCrop = crop;
        this.mHarm = harm;
        this.mMode = mode;
        this.mMethod = method;
        this.mTimeStamp = timeStamp;
        this.mLocation = location;
        this.mNote = note;
    }

    public int getCrop() {
        return mCrop;
    }

    public void setCrop(int crop) {
        mCrop = crop;
    }

    public int getHarm() {
        return mHarm;
    }

    public void setHarm(int harm) {
        mHarm = harm;
    }

    public int getMode() {
        return mMode;
    }

    public void setMode(int mode) {
        mMode = mode;
    }

    public int getMethod() {
        return mMethod;
    }

    public void setMethod(int method) {
        mMethod = method;
    }

    public long getTimeStamp() {
        return mTimeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        mTimeStamp = timeStamp;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String location) {
        mLocation = location;
    }

    public String getNote() {
        return mNote;
    }

    public void setNote(String note) {
        mNote = note;
    }

    public double getTotalArea() {
        return mTotalArea;
    }

    public void setTotalArea(double totalArea) {
        mTotalArea = totalArea;
    }

    public double getAffectedArea() {
        return mAffectedArea;
    }

    public void setAffectedArea(double affectedArea) {
        mAffectedArea = affectedArea;
    }

    public int getTotalCropCount() {
        return mTotalCropCount;
    }

    public void setTotalCropCount(int totalCropCount) {
        mTotalCropCount = totalCropCount;
    }

    public int getAffectedBugCropCount() {
        return mAffectedBugCropCount;
    }

    public void setAffectedBugCropCount(int affectedBugCropCount) {
        mAffectedBugCropCount = affectedBugCropCount;
    }

    public int getAffectedEggCropCount() {
        return mAffectedEggCropCount;
    }

    public void setAffectedEggCropCount(int affectedEggCropCount) {
        mAffectedEggCropCount = affectedEggCropCount;
    }

    public String getInfo0() {
        return mInfo0;
    }

    public void setInfo0(String info0) {
        mInfo0 = info0;
    }

    public String getInfo1() {
        return mInfo1;
    }

    public void setInfo1(String info1) {
        mInfo1 = info1;
    }

    public String getInfo2() {
        return mInfo2;
    }

    public void setInfo2(String info2) {
        mInfo2 = info2;
    }

    public String getInfo3() {
        return mInfo3;
    }

    public void setInfo3(String info3) {
        mInfo3 = info3;
    }

    public String getInfo4() {
        return mInfo4;
    }

    public void setInfo4(String info4) {
        mInfo4 = info4;
    }

    public String getInfo5() {
        return mInfo5;
    }

    public void setInfo5(String info5) {
        mInfo5 = info5;
    }

    public String getInfo6() {
        return mInfo6;
    }

    public void setInfo6(String info6) {
        mInfo6 = info6;
    }

    public String getInfo7() {
        return mInfo7;
    }

    public void setInfo7(String info7) {
        mInfo7 = info7;
    }

    public String getResult1() {
        return mResult1;
    }

    public void setResult1(String result1) {
        mResult1 = result1;
    }

    public String getResult2() {
        return mResult2;
    }

    public void setResult2(String result2) {
        mResult2 = result2;
    }

    public String getResult3() {
        return mResult3;
    }

    public void setResult3(String result3) {
        mResult3 = result3;
    }

    @Override
    public String toString() {
        return "DiagnoseRecord{" +
                "id=" + getId() +
                ", mCrop=" + mCrop +
                ", mHarm=" + mHarm +
                ", mMode=" + mMode +
                ", mMethod=" + mMethod +
                ", mTimeStamp=" + mTimeStamp +
                ", mLocation='" + mLocation + '\'' +
                ", mNote='" + mNote + '\'' +
                ", mTotalArea=" + mTotalArea +
                ", mAffectedArea=" + mAffectedArea +
                ", mTotalCropCount=" + mTotalCropCount +
                ", mAffectedBugCropCount=" + mAffectedBugCropCount +
                ", mAffectedEggCropCount=" + mAffectedEggCropCount +
                ", mInfo0='" + mInfo0 + '\'' +
                ", mInfo1='" + mInfo1 + '\'' +
                ", mInfo2='" + mInfo2 + '\'' +
                ", mInfo3='" + mInfo3 + '\'' +
                ", mInfo4='" + mInfo4 + '\'' +
                ", mInfo5='" + mInfo5 + '\'' +
                ", mInfo6='" + mInfo6 + '\'' +
                ", mInfo7='" + mInfo7 + '\'' +
                ", mResult1='" + mResult1 + '\'' +
                ", mResult2='" + mResult2 + '\'' +
                ", mResult3='" + mResult3 + '\'' +
                '}';
    }

    public void addAreaInfo(double totalArea, double affectedArea) {
        mTotalArea = totalArea;
        mAffectedArea = affectedArea;
    }

    public void addCropCountInfo(int totalCropCount, int affectedBugCropCount, int affectedEggCropCount) {
        mTotalCropCount = totalCropCount;
        mAffectedBugCropCount = affectedBugCropCount;
        mAffectedEggCropCount = affectedEggCropCount;
    }

    public void addInfoForSelectionRound(int selectionRound, String StrContent) {
        switch (selectionRound) {
            case 0:
                mInfo0 = StrContent;
                break;
            case 1:
                mInfo1 = StrContent;
                break;
            case 2:
                mInfo2 = StrContent;
                break;
            case 3:
                mInfo3 = StrContent;
                break;
            case 4:
                mInfo4 = StrContent;
                break;
            case 5:
                mInfo5 = StrContent;
                break;
            case 6:
                mInfo6 = StrContent;
                break;
            case 7:
                mInfo7 = StrContent;
                break;
            default:
                break;
        }
    }

    public enum Crop {
        RICE,
        WHEAT,
        CORN,
        SOYBEAN
    }

    public enum Harm {
        DISESES,
        BUGS
    }

    public enum Mode {
        NORMAL,
        EXPERT
    }

    public enum Method {
        BY_AREA,
        BY_CROP_COUNT,
        BOTH
    }

}
