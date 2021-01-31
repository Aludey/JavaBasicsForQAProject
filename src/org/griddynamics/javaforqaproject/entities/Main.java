package org.griddynamics.javaforqaproject.entities;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        if (args.length < 1 || args.length > 2) {
            throw new IllegalArgumentException("Requires 2 parameters: Date in format dd.mm.yyyy and parameter for report mode: empty or 0 for short report otherwise full report!");
        }

        Date currentDate;
        try {
            currentDate = readDate(args[0]);
        } catch (NumberFormatException e){
            System.out.println("Argument should be a date in format DD.MM.YYYY");
            return;
        } catch (IllegalArgumentException d){
            System.out.println(d);
            return;
        }

        String param = args[1];

        TrainingCenter ourCenter = new TrainingCenter(new ArrayList<>(Arrays.asList(
                new Student("Ivanov Ivan", "Java developer", new CourseSet(new ArrayList<>(Arrays.asList(
                        new Course("Java",16),
                        new Course("JDBC",24),
                        new Course("Spring",16))), new Date(2021-1900, Calendar.JANUARY,29,10,0))),
                new Student("Sidorov Ivan", "AQE", new CourseSet(new ArrayList<>(Arrays.asList(
                        new Course("Test design",10),
                        new Course("Page Object",16),
                        new Course("Selenium",16))), new Date(2021-1900, Calendar.JANUARY,21,10,0))))));

        ourCenter.rapporteur.report(param, ourCenter, currentDate);
    }

    private static Date readDate(String args){
        String[] date = args.split("\\.");

        int day,month,year;
        try{
            day = Integer.parseInt(date[0]);
            if (day < 1 || day > 31) {
                throw new IllegalArgumentException("Illegal day format. day should be in range [1; 31]");
            }
        } catch (NumberFormatException e){
            throw e;
        }
        try{
            month = Integer.parseInt(date[1]);
            if (month < 1 || month > 12) {
                throw new IllegalArgumentException("Illegal month format. Month should be in range [1; 12]");
            }
        } catch (NumberFormatException e){
            throw e;
        }
        try{
            year = Integer.parseInt(date[2]);
            if (year < 1900 || year > 2100) {
                throw new IllegalArgumentException("Illegal year format. Year should be in range [1900; 2100]");
            }
        } catch (NumberFormatException e){
            throw e;
        }

        if (month == 2 && day > 28 && year % 4 != 0) {
            throw new IllegalArgumentException("This month have only 28 days");
        }
        else if (month == 2 && year % 4 == 0 && day > 29) {
            throw new IllegalArgumentException("This month have only 29 days");
        }

        if (month < 8) {
            if (month % 2 == 0 && day > 30 ) {
                throw new IllegalArgumentException("This month have only 30 days");
            }
        } else if (month % 2 == 1 && day > 30 ) {
            throw new IllegalArgumentException("This month have only 30 days");
        }

        return new Date(year - 1900, month - 1 , day,10,0);
    }
}
