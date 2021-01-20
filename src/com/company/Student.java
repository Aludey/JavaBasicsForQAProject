package com.company;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;


public class Student {
    private String name; // Ivanov Ivan
    private String curriculum; // Java Developer
    private CourseSet courseSet;
    private int workingStartTime = 10;
    private int workingEndTime = 18;

    private Date startDate;
    private Date endDate;

    public Student(String name){
        this.name = name;
        this.curriculum = "None";
        this.startDate = null;
        this.courseSet = null;
        this.endDate = null;
    }

    public Student(String name, Date startDate, CourseSet courseList){
        this.name = name;
        this.curriculum = courseList.getName();
        this.courseSet = courseList;
        this.startDate = startDate;

        int duration = courseList.getCourseSetDuration();
        int holidaysHours = countHolidaysOnCourseDuration(startDate, duration);

        if (duration % 8 == 0) this.endDate = new Date(startDate.getTime() + (1000 * 60 * 60 * (duration / 8 * 24L - 16 + holidaysHours)));
        else this.endDate = new Date(startDate.getTime() + (1000 * 60 * 60 * (duration / 8 * 24L + duration % 8 + holidaysHours)));
    }

    private int countHolidaysOnCourseDuration(Date startDate, int duration){
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

    private int countHolidaysOnDateDifference(Date currentDate, Date endDate, int duration){
        int endDayOfWeek = endDate.getDay();
        Date helper = currentDate;
        int countHolidays = 0;

        while (helper.before(endDate)) {
            if (endDayOfWeek < helper.getDay() || duration > 168 || (endDayOfWeek == helper.getDay() && duration == 168)) {
                countHolidays++;
                duration -= 168;
            }
            helper = new Date(helper.getTime() + (1000 * 60 * 60 * 24 * 7));
        }
        return countHolidays;
    }

    private int differenceInDays(long difference_In_Time){
        return (int) (difference_In_Time / (1000 * 60 * 60 * 24)) % 365;
    }
    private int differenceInHours(long difference_In_Time){
        return (int) (difference_In_Time / (1000 * 60 * 60)) % 24;
    }

    public void shortReportCourseTimeRemain(){
        Date currentDate = new GregorianCalendar().getTime();
        int duration = courseSet.getCourseSetDuration();
        long difference_In_Time;
        int difference_In_Days;
        int difference_In_Hours;

        if (startDate != null && endDate != null){
            if (currentDate.before(endDate)){
                if (startDate.before(currentDate)){
                    difference_In_Time = currentDate.getTime() - startDate.getTime();
                    difference_In_Hours = (int) (difference_In_Time / (1000 * 60 * 60));

                    int holidaysHours = countHolidaysOnDateDifference(startDate, currentDate, duration) * 48;
                    int spentFullWorkDays = (difference_In_Hours - holidaysHours) / 24 * 8;

                    int spentHoursLastDay;
                    if(currentDate.getHours() < 10) spentHoursLastDay = 0;
                    else if (currentDate.getHours() > 18) spentHoursLastDay = 8;
                    else spentHoursLastDay = currentDate.getHours() - 10;

                    int remain = duration - spentFullWorkDays - spentHoursLastDay;

                    System.out.println(name + " (" + curriculum + ") - Training is not finished. " + remain / 24 +" days and " + remain % 24 +" hours are left until the end.");

                }
                else {
                    System.out.println(name + " (" + curriculum + ") - Course wasn't started yet.");
                }
            }
            else {
                difference_In_Time = currentDate.getTime() - endDate.getTime();
                difference_In_Days = differenceInDays(difference_In_Time);
                difference_In_Hours = differenceInHours(difference_In_Time);

                System.out.println(name + " (" + curriculum + ") - Training completed. "+ difference_In_Days +" days and "+ difference_In_Hours +" hours have passed since the end.");
            }
        }
        else {
            System.out.println(name + " (" + curriculum + ") - Not started any course.");
        }
    }

    public void shortReport(){
        Date currentDate = new GregorianCalendar().getTime();
        long difference_In_Time;
        int difference_In_Days;
        int difference_In_Hours;

        if (startDate != null && endDate != null){
            if (currentDate.before(endDate)){
                if (currentDate.after(startDate)){
                    difference_In_Time = endDate.getTime() - currentDate.getTime();
                    difference_In_Days = differenceInDays(difference_In_Time);
                    difference_In_Hours = differenceInHours(difference_In_Time);

                    System.out.println(name + " (" + curriculum + ") - Training is not finished. " + difference_In_Days +" days and " + difference_In_Hours +" hours are left until the end.");

                }
                else {
                    System.out.println(name + " (" + curriculum + ") - Course wasn't started yet.");
                }
            }
            else {
                difference_In_Time = currentDate.getTime() - endDate.getTime();
                difference_In_Days = differenceInDays(difference_In_Time);
                difference_In_Hours = differenceInHours(difference_In_Time);

                System.out.println(name + " (" + curriculum + ") - Training completed. "+ difference_In_Days +" days and "+ difference_In_Hours +" hours have passed since the end.");
            }
        }
        else {
            System.out.println(name + " (" + curriculum + ") - Not started any course.");
        }
    }

    public void fullReport(SimpleDateFormat formatter){
        System.out.println();
        System.out.println("Student name: " + name);
        System.out.println("Working time: from " + workingStartTime + " to " + workingEndTime);
        System.out.println("Program name: " + courseSet.getName());
        System.out.println("Program duration: From (" + formatter.format(startDate) + ") To (" + formatter.format(endDate) + ")");
        System.out.println("Hours: " + courseSet.getCourseSetDuration());
        shortReport();
    }

    public String getName(){
        return name;
    }
    public String getCurriculum(){
        return curriculum;
    }
    public Date getStartDate(){
        return startDate;
    }
    public Date getEndDate(){
        return endDate;
    }
    public CourseSet getCourseSet(){
        return courseSet;
    }
}
