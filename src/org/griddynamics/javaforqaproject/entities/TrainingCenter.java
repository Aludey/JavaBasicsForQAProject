package org.griddynamics.javaforqaproject.entities;

import org.griddynamics.javaforqaproject.tools.Rapporteur;

import java.util.*;

public class TrainingCenter {

    private final ArrayList<Student> students;
    public Rapporteur rapporteur = new Rapporteur();

    TrainingCenter(ArrayList<Student> students){
        this.students = students;
    }

    public ArrayList<Student> getStudentsList() {
        return students;
    }
}
