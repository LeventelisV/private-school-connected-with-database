/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author vaggelis
 */
public class AssignmentStudentCourse {
    private int id;
    private int courseStudentId;
    private int courseAssignmentId;
    private double mark;

    public AssignmentStudentCourse(int courseStudentId, int courseAssignmentId) {
        this.courseStudentId = courseStudentId;
        this.courseAssignmentId = courseAssignmentId;
    }

    public AssignmentStudentCourse(int id, int courseStudentId, int courseAssignmentId, double mark) {
        this.id = id;
        this.courseStudentId = courseStudentId;
        this.courseAssignmentId = courseAssignmentId;
        this.mark = mark;
    }

    public AssignmentStudentCourse(int courseStudentId, int courseAssignmentId, double mark) {
        this.courseStudentId = courseStudentId;
        this.courseAssignmentId = courseAssignmentId;
        this.mark = mark;
    }

    public int getId() {
        return id;
    }

    public int getCourseStudentId() {
        return courseStudentId;
    }

    public int getCourseAssignmentId() {
        return courseAssignmentId;
    }

    public double getMark() {
        return mark;
    }

    

   
    
    
}
