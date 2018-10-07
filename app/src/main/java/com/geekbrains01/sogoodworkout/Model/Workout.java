package com.geekbrains01.sogoodworkout.Model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Workout {

    private String title;
    private String description;
    private String imageUrl;
    private int recordRepsCount;
    private Date recordDate;
    private int recordWeight;

    public Workout(String title) {
        this.title = title;
    }

    public Workout(String title, String description, int recordRepsCount, Date recordDate, int recordWeight) {
        this.title = title;
        this.description = description;
        this.recordRepsCount = recordRepsCount;
        this.recordDate = recordDate;
        this.recordWeight = recordWeight;
    }

    public String getFormattedRecordDate(){
        return new SimpleDateFormat("dd MMMM yyyy", Locale.ROOT).format(recordDate);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getRecordRepsCount() {
        return recordRepsCount;
    }

    public void setRecordRepsCount(int recordRepsCount) {
        this.recordRepsCount = recordRepsCount;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public int getRecordWeight() {
        return recordWeight;
    }

    public void setRecordWeight(int recordWeight) {
        this.recordWeight = recordWeight;
    }
}
