package org.mjh.commonutils.date;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 日历表
 * Created by 12433 on 2024-03-07.
 */
public class SysCalendar {
    /**
     * 自增主键ID
     */
    private Long id;

    /**
     * 年份（如：2024）
     */
    private String year;

    /**
     * 日期（如：2024-03-07）
     */
    private LocalDate date;

    /**
     * 周几（1-周一；7-周日）
     */
    private Integer dayofweek;

    /**
     * 是否调休补班（1-是；0-否）
     */
    private Integer makeupWork;

    /**
     * 是否法定节假日（1-是；0-否）
     */
    private Integer legalHoliday;

    /**
     * 是否假期节假日（1-是；0-否）
     */
    private Integer recessHoliday;

    /**
     * 创建日期
     */
    private LocalDateTime creationTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getDayofweek() {
        return dayofweek;
    }

    public void setDayofweek(Integer dayofweek) {
        this.dayofweek = dayofweek;
    }

    public Integer getMakeupWork() {
        return makeupWork;
    }

    public void setMakeupWork(Integer makeupWork) {
        this.makeupWork = makeupWork;
    }

    public Integer getLegalHoliday() {
        return legalHoliday;
    }

    public void setLegalHoliday(Integer legalHoliday) {
        this.legalHoliday = legalHoliday;
    }

    public Integer getRecessHoliday() {
        return recessHoliday;
    }

    public void setRecessHoliday(Integer recessHoliday) {
        this.recessHoliday = recessHoliday;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }
}
