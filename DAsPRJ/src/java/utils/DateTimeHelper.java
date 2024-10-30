/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import entity.assignment.SchedualCampaign;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author A A
 */
public class DateTimeHelper {

    public static List<Date> getDatesBetween(Date startDate, Date endDate) {
        LocalDate start = startDate.toLocalDate();
        LocalDate end = endDate.toLocalDate();

        List<Date> dates = new ArrayList<>();

        while (!start.isAfter(end)) {
            dates.add(Date.valueOf(start));
            start = start.plusDays(1);
        }
        return dates;
    }

    public static List<AbstractMap.SimpleEntry<Date, String>> compareAndFormatDates(List<Date> dateTimes, List<SchedualCampaign> scQuantity) {
        List<AbstractMap.SimpleEntry<Date, String>> resultList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for (Date date : dateTimes) {
            List<String> shiftsAndQuantities = new ArrayList<>();
            for (SchedualCampaign sc : scQuantity) {
                if (sdf.format(date).equals(sdf.format(sc.getDate()))) {
                    shiftsAndQuantities.add("Shift " + sc.getShift() + " : " + sc.getQuantity());
                }
            }
            if (!shiftsAndQuantities.isEmpty()) {
                String shiftQuantity = String.join(", ", shiftsAndQuantities);
                resultList.add(new AbstractMap.SimpleEntry<>(date, shiftQuantity));
            }
        }
        return resultList;
    }
}
