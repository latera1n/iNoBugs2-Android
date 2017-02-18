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
     *                  1: by the number of crops,
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

}
