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
    private String mInfo0;
    private String mInfo1;
    private String mInfo2;
    private String mInfo3;
    private String mInfo4;
    private String mInfo5;
    private String mInfo6;
    private String mInfo7;
    private String mInfo8;
    private String mInfo9;
    private String mInfo10;
    private String mInfo11;
    private String mInfo12;
    private String mInfo13;
    private String mInfo14;
    private String mInfo15;
    private String mInfo16;
    private String mInfo17;
    private String mInfo18;
    private String mListViewLine1;
    private String mListViewLine2;
    private String mExpertName;
    private String mResult1;
    private String mResult2;
    private String mResult3;
    /**
     * Required empty constructor.
     */
    public DiagnoseRecord() {
    }
    /**
     * Constructor for basic info input at the initial page.
     *
     * @param crop      the crop type should be
     *                  0: rice,
     *                  1: wheat,
     *                  2: corn,
     *                  3: soy bean
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

    public String getInfo8() {
        return mInfo8;
    }

    public void setInfo8(String info8) {
        mInfo8 = info8;
    }

    public String getInfo9() {
        return mInfo9;
    }

    public void setInfo9(String info9) {
        mInfo9 = info9;
    }

    public String getInfo10() {
        return mInfo10;
    }

    public void setInfo10(String info10) {
        mInfo10 = info10;
    }

    public String getInfo11() {
        return mInfo11;
    }

    public void setInfo11(String info11) {
        mInfo11 = info11;
    }

    public String getInfo12() {
        return mInfo12;
    }

    public void setInfo12(String info12) {
        mInfo12 = info12;
    }

    public String getInfo13() {
        return mInfo13;
    }

    public void setInfo13(String info13) {
        mInfo13 = info13;
    }

    public String getInfo14() {
        return mInfo14;
    }

    public void setInfo14(String info14) {
        mInfo14 = info14;
    }

    public String getInfo15() {
        return mInfo15;
    }

    public void setInfo15(String info15) {
        mInfo15 = info15;
    }

    public String getInfo16() {
        return mInfo16;
    }

    public void setInfo16(String info16) {
        mInfo16 = info16;
    }

    public String getInfo17() {
        return mInfo17;
    }

    public void setInfo17(String info17) {
        mInfo17 = info17;
    }

    public String getInfo18() {
        return mInfo18;
    }

    public void setInfo18(String info18) {
        mInfo18 = info18;
    }

    public String getListViewLine1() {
        return mListViewLine1;
    }

    public void setListViewLine1(String listViewLine1) {
        mListViewLine1 = listViewLine1;
    }

    public String getListViewLine2() {
        return mListViewLine2;
    }

    public void setListViewLine2(String listViewLine2) {
        mListViewLine2 = listViewLine2;
    }

    public String getExpertName() {
        return mExpertName;
    }

    public void setExpertName(String expertName) {
        mExpertName = expertName;
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
                "_id='" + getId() + '\'' +
                ", mCrop=" + mCrop +
                ", mHarm=" + mHarm +
                ", mMode=" + mMode +
                ", mMethod=" + mMethod +
                ", mTimeStamp=" + mTimeStamp +
                ", mLocation='" + mLocation + '\'' +
                ", mNote='" + mNote + '\'' +
                ", mInfo0='" + mInfo0 + '\'' +
                ", mInfo1='" + mInfo1 + '\'' +
                ", mInfo2='" + mInfo2 + '\'' +
                ", mInfo3='" + mInfo3 + '\'' +
                ", mInfo4='" + mInfo4 + '\'' +
                ", mInfo5='" + mInfo5 + '\'' +
                ", mInfo6='" + mInfo6 + '\'' +
                ", mInfo7='" + mInfo7 + '\'' +
                ", mInfo8='" + mInfo8 + '\'' +
                ", mInfo9='" + mInfo9 + '\'' +
                ", mInfo10='" + mInfo10 + '\'' +
                ", mInfo11='" + mInfo11 + '\'' +
                ", mInfo12='" + mInfo12 + '\'' +
                ", mInfo13='" + mInfo13 + '\'' +
                ", mInfo14='" + mInfo14 + '\'' +
                ", mInfo15='" + mInfo15 + '\'' +
                ", mInfo16='" + mInfo16 + '\'' +
                ", mInfo17='" + mInfo17 + '\'' +
                ", mInfo18='" + mInfo18 + '\'' +
                ", mListViewLine1='" + mListViewLine1 + '\'' +
                ", mListViewLine2='" + mListViewLine2 + '\'' +
                ", mExpertName='" + mExpertName + '\'' +
                ", mResult1='" + mResult1 + '\'' +
                ", mResult2='" + mResult2 + '\'' +
                ", mResult3='" + mResult3 + '\'' +
                '}';
    }

    public enum Crop {
        RICE,
        WHEAT,
        CORN,
        SOY_BEAN
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
