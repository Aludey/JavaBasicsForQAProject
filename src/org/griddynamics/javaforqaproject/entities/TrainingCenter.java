package org.griddynamics.javaforqaproject.entities;

import org.griddynamics.javaforqaproject.tools.Rapporteur;

import java.util.*;

public class TrainingCenter {

    private final ArrayList<Student> students;

    TrainingCenter(ArrayList<Student> students){
        this.students = students;
    }

    public ArrayList<Student> getStudentsList() {
        return students;
    }

    public void useRapporteur(String param, Date reportDate){
        Rapporteur rapporteur = new Rapporteur();
        rapporteur.report(param,this, reportDate);
    }
}
