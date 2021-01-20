package com.company;

import java.text.SimpleDateFormat;
import java.util.*;

public class TrainingCenter {
    public ArrayList<Student> students = new ArrayList<>();

    public void shortReport(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy, EEEE, kk:mm");
        Date currentDate = new GregorianCalendar().getTime();

        System.out.println("Short Report (Generating report date - " + formatter.format(currentDate) + "): ");
        for (Student student:students){
//           student.shortReportCourseTimeRemain();
           student.shortReport();
        }
    }
    public void fullReport(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy, EEEE, kk:mm");
        Date currentDate = new GregorianCalendar().getTime();

        System.out.println("Full Report (Generating report date - " + formatter.format(currentDate) + "): ");
        for (Student student:students){
            student.fullReport(formatter);
        }
    }

    public void Report(String a){
        switch (a) {
            case "", "0" -> shortReport();
            default -> fullReport();
        }
    }
}
