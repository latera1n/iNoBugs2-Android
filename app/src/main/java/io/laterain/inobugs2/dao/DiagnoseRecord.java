package io.laterain.inobugs2.dao;

import com.orm.SugarRecord;
import com.orm.dsl.Table;

/**
 * Created by dengyuchi on 2/16/17.
 */

public class DiagnoseRecord extends SugarRecord {

    // Persistent fields
    private String mode;
    private int timeStamp;
    private String location;
    private String note;
    private String info0;
    private String info1;
    private String info2;
    private String info3;
    private String info4;
    private String info5;
    private String info6;
    private String info7;
    private String info8;
    private String info9;
    private String info10;
    private String info11;
    private String info12;
    private String info13;
    private String info14;
    private String info15;
    private String info16;
    private String info17;
    private String info18;
    private String listViewLine1;
    private String listViewLine2;
    private String expertName;
    private String result1;
    private String result2;
    private String result3;

    /**
     * Required empty constructor.
     */
    public DiagnoseRecord() {
    }

    /**
     * Constructor for basic info input at the initial page.
     * @param mode the mode should be 0: bugs or 1: diseases
     * @param timeStamp the epoch time stamp in seconds
     * @param location the location of this record
     * @param note the note for this record
     */
    public DiagnoseRecord(String mode, int timeStamp, String location, String note) {
        this.mode = mode;
        this.timeStamp = timeStamp;
        this.location = location;
        this.note = note;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public int getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(int timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getInfo0() {
        return info0;
    }

    public void setInfo0(String info0) {
        this.info0 = info0;
    }

    public String getInfo1() {
        return info1;
    }

    public void setInfo1(String info1) {
        this.info1 = info1;
    }

    public String getInfo2() {
        return info2;
    }

    public void setInfo2(String info2) {
        this.info2 = info2;
    }

    public String getInfo3() {
        return info3;
    }

    public void setInfo3(String info3) {
        this.info3 = info3;
    }

    public String getInfo4() {
        return info4;
    }

    public void setInfo4(String info4) {
        this.info4 = info4;
    }

    public String getInfo5() {
        return info5;
    }

    public void setInfo5(String info5) {
        this.info5 = info5;
    }

    public String getInfo6() {
        return info6;
    }

    public void setInfo6(String info6) {
        this.info6 = info6;
    }

    public String getInfo7() {
        return info7;
    }

    public void setInfo7(String info7) {
        this.info7 = info7;
    }

    public String getInfo8() {
        return info8;
    }

    public void setInfo8(String info8) {
        this.info8 = info8;
    }

    public String getInfo9() {
        return info9;
    }

    public void setInfo9(String info9) {
        this.info9 = info9;
    }

    public String getInfo10() {
        return info10;
    }

    public void setInfo10(String info10) {
        this.info10 = info10;
    }

    public String getInfo11() {
        return info11;
    }

    public void setInfo11(String info11) {
        this.info11 = info11;
    }

    public String getInfo12() {
        return info12;
    }

    public void setInfo12(String info12) {
        this.info12 = info12;
    }

    public String getInfo13() {
        return info13;
    }

    public void setInfo13(String info13) {
        this.info13 = info13;
    }

    public String getInfo14() {
        return info14;
    }

    public void setInfo14(String info14) {
        this.info14 = info14;
    }

    public String getInfo15() {
        return info15;
    }

    public void setInfo15(String info15) {
        this.info15 = info15;
    }

    public String getInfo16() {
        return info16;
    }

    public void setInfo16(String info16) {
        this.info16 = info16;
    }

    public String getInfo17() {
        return info17;
    }

    public void setInfo17(String info17) {
        this.info17 = info17;
    }

    public String getInfo18() {
        return info18;
    }

    public void setInfo18(String info18) {
        this.info18 = info18;
    }

    public String getListViewLine1() {
        return listViewLine1;
    }

    public void setListViewLine1(String listViewLine1) {
        this.listViewLine1 = listViewLine1;
    }

    public String getListViewLine2() {
        return listViewLine2;
    }

    public void setListViewLine2(String listViewLine2) {
        this.listViewLine2 = listViewLine2;
    }

    public String getExpertName() {
        return expertName;
    }

    public void setExpertName(String expertName) {
        this.expertName = expertName;
    }

    public String getResult1() {
        return result1;
    }

    public void setResult1(String result1) {
        this.result1 = result1;
    }

    public String getResult2() {
        return result2;
    }

    public void setResult2(String result2) {
        this.result2 = result2;
    }

    public String getResult3() {
        return result3;
    }

    public void setResult3(String result3) {
        this.result3 = result3;
    }

}
