package org.mjh.commonutils.date;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.JSONWriter;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Neo Lia
 * @date 2024/3/7
 */
public class CalendarUtils {
    public static List<CalendarJsonObject> loadDataFromFile(String path) {
        StringBuilder jsonBuilder = new StringBuilder();
        try (InputStream inputStream = new BufferedInputStream(new FileInputStream(path))) {
            byte[] buff = new byte[1024];
            int size;
            while ((size = inputStream.read(buff)) != -1) {
                String temp = new String(buff, 0, size, StandardCharsets.UTF_8);
                jsonBuilder.append(temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("jsonBuilder" + jsonBuilder);
        String data = JSON.parseObject(jsonBuilder.toString()).getJSONObject("data").get("list").toString();
        System.out.println("data: " + data);
        List<CalendarJsonObject> calendarJsonObjects = JSON.parseArray(data, CalendarJsonObject.class);
        System.out.println("calendarJsonObjects:" + JSON.toJSONString(calendarJsonObjects));
        System.out.println("calendarJsonObjects:" + calendarJsonObjects.size());
        return calendarJsonObjects;
    }

    public static void main(String[] args) {
        String path = "C:\\Users\\12433\\Desktop\\chinese_calendar_2023.txt";
        List<CalendarJsonObject> calendarJsonObjects = loadDataFromFile(path);
        if (calendarJsonObjects.isEmpty()) {
            return;
        }
        List<SysCalendar> sysCalendars = new ArrayList<>(calendarJsonObjects.size());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDateTime nowDateTime = LocalDateTime.now();
        for (CalendarJsonObject calendarJsonObject : calendarJsonObjects) {
            LocalDate date = LocalDate.parse(calendarJsonObject.getDate(), dateTimeFormatter);
            SysCalendar sysCalendar = new SysCalendar();
            sysCalendar.setYear(String.valueOf(date.getYear()));
            sysCalendar.setDate(date);
            sysCalendar.setDayofweek(calendarJsonObject.getWeek());
            sysCalendar.setMakeupWork(calendarJsonObject.getHoliday_overtime().equals(10) ? 0 : 1);
            sysCalendar.setLegalHoliday(calendarJsonObject.getHoliday_legal().equals(1) ? 1 : 0);
            sysCalendar.setRecessHoliday(calendarJsonObject.getHoliday_recess().equals(1) ? 1 : 0);
            sysCalendar.setCreationTime(nowDateTime);
            sysCalendars.add(sysCalendar);
        }

        System.out.println("sysCalendars:" + JSON.toJSONString(sysCalendars));
    }
}
