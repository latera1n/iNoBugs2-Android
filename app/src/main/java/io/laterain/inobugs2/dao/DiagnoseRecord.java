package io.laterain.inobugs2.dao;


import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by dengyuchi on 2/16/17.
 */

public class DiagnoseRecord extends SugarRecord implements Serializable {

    @Ignore
    public final static int NUM_INFO_FIELDS = 8;
    public final static String STR_DISEASE_KEY_SEPARATOR = ",";
    public final static String STR_RESULT_SEPARATOR = ":";
    private final static int NUM_RESULTS_FIELDS = 3;

    // Persistent fields
    private long mIdCopy;
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
        this.mIdCopy = 0L;
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

    public long getIdCopy() {
        return mIdCopy;
    }

    public void setIdCopy(long idCopy) {
        mIdCopy = idCopy;
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
                ", mIdCopy=" + mIdCopy +
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

    public void addDiseaseInfoForSelectionRound(int selectionRound, String StrContent) {
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

    public void addEggAndBugInfo(String bugKeyOrName, String eggCount, String[] bugCountArray) {
        mInfo0 = bugKeyOrName;
        mInfo7 = eggCount;
        for (int i = 0; i < NUM_INFO_FIELDS - 2; i++) {
            String StrFroInfo = bugCountArray[i];
            switch (i) {
                case 0:
                    mInfo1 = StrFroInfo;
                    break;
                case 1:
                    mInfo2 = StrFroInfo;
                    break;
                case 2:
                    mInfo3 = StrFroInfo;
                    break;
                case 3:
                    mInfo4 = StrFroInfo;
                    break;
                case 4:
                    mInfo5 = StrFroInfo;
                    break;
                case 5:
                    mInfo6 = StrFroInfo;
                    break;
                default:
                    break;
            }
        }
    }

    public void calculateAndSaveResults() {
        if (mHarm == Harm.DISEASES.ordinal() && mMode == Mode.NORMAL.ordinal()) {
            List<String> diseaseSymptomsList = new ArrayList<>();
            diseaseSymptomsList.add(mInfo0);
            diseaseSymptomsList.add(mInfo1);
            diseaseSymptomsList.add(mInfo2);
            diseaseSymptomsList.add(mInfo3);
            diseaseSymptomsList.add(mInfo4);
            diseaseSymptomsList.add(mInfo5);
            diseaseSymptomsList.add(mInfo6);
            diseaseSymptomsList.add(mInfo7);
            Map<Integer, Integer> diseaseProbabilitiesMap = new HashMap<>();
            for (String diseaseSymptom : diseaseSymptomsList) {
                char[] diseaseSymptomChars = diseaseSymptom.toCharArray();
                int keyLength = diseaseSymptom.length();
                int currentKey = 0;
                for (int i = 0; i < keyLength - 3; i++) {
                    currentKey *= 10;
                    currentKey += diseaseSymptomChars[i] - '0';
                }
                int currentScore = (diseaseSymptomChars[keyLength - 2] - '0') * 10 + diseaseSymptomChars[keyLength - 1] - '0';
                if (diseaseProbabilitiesMap.containsKey(currentKey)) {
                    diseaseProbabilitiesMap.put(currentKey, diseaseProbabilitiesMap.get(currentKey) + currentScore);
                } else {
                    diseaseProbabilitiesMap.put(currentKey, currentScore);
                }

            }
            Map<Integer, String> sortedDiseaseProbabilitiesMap = new TreeMap<>(new Comparator<Integer>() {
                @Override
                public int compare(Integer integer, Integer t1) {
                    return t1 - integer;
                }
            });
            for (Map.Entry<Integer, Integer> diseaseEntry : diseaseProbabilitiesMap.entrySet()) {
                Integer key = diseaseEntry.getKey();
                Integer value = diseaseEntry.getValue();
                if (sortedDiseaseProbabilitiesMap.containsKey(key)) {
                    sortedDiseaseProbabilitiesMap.put(value, sortedDiseaseProbabilitiesMap.get(key) + STR_DISEASE_KEY_SEPARATOR + key + "000");
                } else {
                    sortedDiseaseProbabilitiesMap.put(value, "" + key + "000");
                }
            }
            int count = 0;
            for (Map.Entry<Integer, String> sortedDiseaseEntry : sortedDiseaseProbabilitiesMap.entrySet()) {
                if (count >= NUM_RESULTS_FIELDS) {
                    break;
                }
                switch (count++) {
                    case 0:
                        mResult1 += sortedDiseaseEntry.getKey() + STR_RESULT_SEPARATOR + sortedDiseaseEntry.getValue();
                        break;
                    case 1:
                        mResult2 += sortedDiseaseEntry.getKey() + STR_RESULT_SEPARATOR + sortedDiseaseEntry.getValue();
                        break;
                    case 2:
                        mResult3 += sortedDiseaseEntry.getKey() + STR_RESULT_SEPARATOR + sortedDiseaseEntry.getValue();
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public int[] getEggAndBugCountArray() {
        int[] eggAndBugCount = new int[NUM_INFO_FIELDS - 1];
        int count = 0;
        while (count < NUM_INFO_FIELDS - 1) {
            try {
                switch (count++) {
                    case 0:
                        eggAndBugCount[0] = Integer.parseInt(mInfo7);
                        break;
                    case 1:
                        eggAndBugCount[1] = Integer.parseInt(mInfo1);
                        break;
                    case 2:
                        eggAndBugCount[2] = Integer.parseInt(mInfo2);
                        break;
                    case 3:
                        eggAndBugCount[3] = Integer.parseInt(mInfo3);
                        break;
                    case 4:
                        eggAndBugCount[4] = Integer.parseInt(mInfo4);
                        break;
                    case 5:
                        eggAndBugCount[5] = Integer.parseInt(mInfo5);
                        break;
                    case 6:
                        eggAndBugCount[6] = Integer.parseInt(mInfo6);
                        break;
                    default:
                        break;
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return eggAndBugCount;
    }

    public enum Crop {
        RICE,
        WHEAT,
        CORN,
        SOYBEAN
    }

    public enum Harm {
        DISEASES,
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

    @Override
    public long save() {
        long id = super.save();
        mIdCopy = id;
        return id;
    }

    @Override
    public boolean delete() {
        setId(mIdCopy);
        return super.delete();
    }

    public void updateBasicInfo(int crop, int harm, int mode, int method, long timeStamp, String location, String note) {
        mCrop = crop;
        mHarm = harm;
        mMode = mode;
        mMethod = method;
        mTimeStamp = timeStamp;
        mLocation = location;
        mNote = note;
    }

}
