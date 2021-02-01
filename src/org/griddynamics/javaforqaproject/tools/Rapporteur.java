package org.griddynamics.javaforqaproject.tools;

import org.griddynamics.javaforqaproject.entities.Student;
import org.griddynamics.javaforqaproject.entities.TrainingCenter;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Rapporteur {

    private final SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy, EEEE, kk:mm");

    public void report(String a, TrainingCenter center, Date reportDate){
        switch (a) {
            case "", "0" -> {
                System.out.printf("Short Report (Generating report date - %s):%n", formatter.format(reportDate));
                for (Student student:center.getStudentsList()) shortReport(student, reportDate);
            }
            default -> {
                System.out.printf("Full Report (Generating report date - %s):%n", formatter.format(reportDate));
                for (Student student:center.getStudentsList()) fullReport(student,reportDate);
            }
        }
    }

    private void shortReport(Student student, Date reportDate){

        Date startDate = student.getCourseSet().getStartDate();
        Date endDate = student.getCourseSet().getEndDate();

        if (startDate != null && endDate != null){
            Evaluator evaluator = new Evaluator();
            if (reportDate.before(endDate)){
                if (reportDate.after(startDate)){
                   // int time = evaluator.calculateCalendarTime(reportDate, endDate);
                    int time = evaluator.calculateCourseTime(reportDate,startDate,student.getCourseSet().getDuration());
                    System.out.printf("%s (%s) - Training is not finished. %d days and %d hours are left until the end.%n", student.getName(), student.getCurriculum(), time / 24 , time % 24);
                }
                else {
                    System.out.printf("%s (%s) - Course wasn't started yet.%n", student.getName(),student.getCurriculum());
                }
            }
            else {
                int time = evaluator.calculateCalendarTime(endDate, reportDate);
                System.out.printf("%s (%s) - Training completed. %d days and %d hours have passed since the end.%n", student.getName(),student.getCurriculum(), time / 24, time % 24);
            }
        }
        else {
            System.out.printf("%s (%s) - Not started any course.%n", student.getName(), student.getCurriculum());
        }
    }

    private void fullReport(Student student, Date reportDate){
        System.out.printf("%nStudent name: %s%n", student.getName());
        System.out.printf("Working time: from %d to %d%n", student.getWorkingStartTime(), student.getWorkingEndTime());
        System.out.printf("Program name: %s%n", student.getCurriculum());
        System.out.printf("Program duration: From ( %s ) To ( %s )%n", formatter.format(student.getCourseSet().getStartDate()),formatter.format(student.getCourseSet().getEndDate()));
        System.out.printf("Hours: %d%n", student.getCourseSet().getDuration());
        shortReport(student,reportDate);
    }
}
