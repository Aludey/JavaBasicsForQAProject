package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static  com.company.CourseSet.*;

public class Main {

    public static void main(String[] args) {
        TrainingCenter ourCenter = new TrainingCenter();
        ourCenter.students.add(new Student("Ivanov Ivan", new Date(2021-1900, Calendar.JANUARY,1,10,0), JAVA_DEVELOPER));
        ourCenter.students.add(new Student("Sidorov Ivan", new Date(2021-1900, Calendar.JANUARY,15,10,0), AQE));

        System.out.println("Choose report mode: no parameter or parameter 0 - short report, otherwise - a full report.");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            String param = reader.readLine();
            ourCenter.Report(param);
        } catch (IOException e){
            System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }
}
