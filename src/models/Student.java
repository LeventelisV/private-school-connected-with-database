/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.*;
import java.time.*;

/**
 *
 * @author vaggelis
 */
public class Student {
    
    private int id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private int tuitionFees;
    
     public Student(int id,String firstName, String lastName, LocalDate dateOfBirth, int tuitionFees) {
        this.id=id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.tuitionFees = tuitionFees;

    }

    public Student(int id, String firstName, String lastName,LocalDate dateOfBirth) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    
    public Student(String firstName, String lastName, LocalDate dateOfBirth, int tuitionFees) {
        
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.tuitionFees = tuitionFees;

    }
     

    public int getId() {
        return id;
    }

    public int getTuitionFees() {
        return tuitionFees;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public int getFees() {
        return tuitionFees;}
    
}
