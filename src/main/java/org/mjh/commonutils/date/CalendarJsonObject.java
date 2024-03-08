package org.mjh.commonutils.date;

/**
 * @author Neo Lia
 * @date 2024/3/7
 */
public class CalendarJsonObject {
    private String date;

    private Integer week;

    private Integer holiday_overtime;

    private Integer holiday_legal;

    private Integer holiday_recess;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public Integer getHoliday_overtime() {
        return holiday_overtime;
    }

    public void setHoliday_overtime(Integer holiday_overtime) {
        this.holiday_overtime = holiday_overtime;
    }

    public Integer getHoliday_legal() {
        return holiday_legal;
    }

    public void setHoliday_legal(Integer holiday_legal) {
        this.holiday_legal = holiday_legal;
    }

    public Integer getHoliday_recess() {
        return holiday_recess;
    }

    public void setHoliday_recess(Integer holiday_recess) {
        this.holiday_recess = holiday_recess;
    }
}
