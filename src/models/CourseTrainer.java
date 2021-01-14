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
public class CourseTrainer {
    private int id;
    private int courseId;
    private int trainerId;

    public CourseTrainer(int id, int courseId, int trainerId) {
        this.id = id;
        this.courseId = courseId;
        this.trainerId = trainerId;
    }

    public CourseTrainer(int courseId, int trainerId) {
        this.courseId = courseId;
        this.trainerId = trainerId;
    }

    public int getId() {
        return id;
    }

    public int getCourseId() {
        return courseId;
    }

    public int getTrainerId() {
        return trainerId;
    }
    
    
}
