package org.griddynamics.javaforqaproject.tools;

import java.util.Date;

public class Evaluator {

    private static int differenceInHours(long difference_In_Time){
        return (int) (difference_In_Time / (1000 * 60 * 60));
    }

    private static int countHolidaysOnCourseDuration(Date startDate, int duration){
        int dayOfWeek = startDate.getDay();
        switch (dayOfWeek){
            case 1:
                if (duration > 40) {
                    if (duration % 40 == 0) return 48 * (duration / 40 - 1);
                    else return 48 * (duration / 40);
                }
                else return 0;
            case 2:
                if (duration > 32) {
                    if ((duration + 8) % 40 == 0) return 48 * ((duration + 8) / 40 - 1);
                    else return 48 * ((duration + 8) / 40);
                }
                else return 0;
            case 3:
                if (duration > 24) {
                    if ((duration + 16) % 40 == 0) return 48 * ((duration + 16) / 40 - 1);
                    else return 48 * ((duration + 16) / 40);
                }
                else return 0;
            case 4:
                if (duration > 16) {
                    if ((duration + 24) % 40 == 0) return 48 * ((duration + 24) / 40 - 1);
                    else return 48 * ((duration + 24) / 40);
                }
                else return 0;
            case 5:
                if (duration > 8) {
                    if ((duration + 32) % 40 == 0) return 48 * ((duration + 32) / 40 - 1);
                    else return 48 * ((duration + 32) / 40);
                }
                else return 0;
            default: return -1;
        }
    }

    public static Date calculateEndDate(Date startDate, int duration){
        Date endDate;
        int holidaysHours = countHolidaysOnCourseDuration(startDate, duration);

        if (duration % 8 == 0) {
            endDate = new Date(startDate.getTime() + (1000 * 60 * 60 * (duration / 8 * 24L - 16 + holidaysHours)));
        }
        else {
            endDate = new Date(startDate.getTime() + (1000 * 60 * 60 * (duration / 8 * 24L + duration % 8 + holidaysHours)));
        }
        return endDate;
    }


    private int countHolidaysOnDateDifference(Date firstDate, Date secondDate, int duration){
        int endDayOfWeek = secondDate.getDay();
        Date helper = firstDate;
        int countHolidays = 0;

        while (helper.before(secondDate)) {
            if (endDayOfWeek < helper.getDay() || duration > 168 || (endDayOfWeek == helper.getDay() && duration == 168)) {
                countHolidays++;
                duration -= 168;
            }
            helper = new Date(helper.getTime() + (1000 * 60 * 60 * 24 * 7));
        }
        return countHolidays;
    }

    public int calculateCalendarTime(Date firstDate, Date secondDate){
        long differenceInTime = secondDate.getTime() - firstDate.getTime();
        return differenceInHours(differenceInTime);
    }

    public int calculateCourseTime(Date reportDate, Date startDate, int duration){

        long differenceInTime = reportDate.getTime() - startDate.getTime();
        int differenceInHours = differenceInHours(differenceInTime);

        int holidaysHours = countHolidaysOnDateDifference(startDate, reportDate, duration) * 48;
        int spentFullWorkDays = (differenceInHours - holidaysHours) / 24 * 8;

        int spentHoursLastDay;
        if(reportDate.getHours() < 10) {
            spentHoursLastDay = 0;
        }
        else {
            if (reportDate.getHours() > 18) {
                spentHoursLastDay = 8;
            }
            else {
                spentHoursLastDay = reportDate.getHours() - 10;
            }
        }

        int remain = duration - spentFullWorkDays - spentHoursLastDay;
        return remain;
    }
}
