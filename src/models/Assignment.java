/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.time.LocalDate;

/**
 *
 * @author vaggelis
 */
public class Assignment {

    private int id;
    private String title;
    private String description;
    private LocalDate subDateTime;
    private double oralMark;
    private double totalMark;

    public Assignment(int id, String title, String description, LocalDate subDateTime, double oralMark, double totalMark) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.subDateTime = subDateTime;
        this.oralMark = oralMark;
        this.totalMark = totalMark;
    }

    public String getTitle() {
        return title;

    }

    public String getDescription() {
        return description;
    }

    public LocalDate getSubDateTime() {
        return subDateTime;
    }

    public double getOralMark() {
        return oralMark;
    }

    public double getTotalMark() {
        return totalMark;
    }

}
