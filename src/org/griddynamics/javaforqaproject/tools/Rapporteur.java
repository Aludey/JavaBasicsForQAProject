package org.griddynamics.javaforqaproject.tools;

import org.griddynamics.javaforqaproject.entities.Student;
import org.griddynamics.javaforqaproject.entities.TrainingCenter;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Rapporteur {

    private SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy, EEEE, kk:mm");

    public void report(String a, TrainingCenter center, Date reportDate){
        switch (a) {
            case "", "0" -> {
                System.out.println(String.format("Short Report (Generating report date - %s):", formatter.format(reportDate)));
                for (Student student:center.getStudentsList()) shortReport(student, reportDate);
            }
            default -> {
                System.out.println(String.format("Full Report (Generating report date - %s):", formatter.format(reportDate)));
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
                    System.out.println(String.format("%s (%s) - Training is not finished. %d days and %d hours are left until the end.", student.getName(), student.getCurriculum(), time / 24 , time % 24));
                }
                else {
                    System.out.println(String.format("%s (%s) - Course wasn't started yet.", student.getName(),student.getCurriculum()));
                }
            }
            else {
                int time = evaluator.calculateCalendarTime(endDate, reportDate);
                System.out.println(String.format("%s (%s) - Training completed. %d days and %d hours have passed since the end.", student.getName(),student.getCurriculum(), time / 24, time % 24));
            }
        }
        else {
            System.out.println(String.format("%s (%s) - Not started any course.", student.getName(), student.getCurriculum()));
        }
    }

    private void fullReport(Student student, Date reportDate){
        System.out.println();
        System.out.println(String.format("Student name: %s", student.getName()));
        System.out.println(String.format("Working time: from %d to %d", student.getWorkingStartTime(), student.getWorkingEndTime()));
        System.out.println(String.format("Program name: %s", student.getCurriculum()));
        System.out.println(String.format("Program duration: From ( %s ) To ( %s )", formatter.format(student.getCourseSet().getStartDate()),formatter.format(student.getCourseSet().getEndDate())));
        System.out.println(String.format("Hours: %d", student.getCourseSet().getDuration()));
        shortReport(student,reportDate);
    }
}
