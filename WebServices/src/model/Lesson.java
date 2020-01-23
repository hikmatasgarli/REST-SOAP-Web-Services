package model;

import model.AbstractModel;

public class Lesson extends AbstractModel {
    
    private String lessonName;
    private String lessonTime;
    private String lessonPrice;

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getLessonTime() {
        return lessonTime;
    }

    public void setLessonTime(String lessonTime) {
        this.lessonTime = lessonTime;
    }

    public String getLessonPrice() {
        return lessonPrice;
    }

    public void setLessonPrice(String lessonPrice) {
        this.lessonPrice = lessonPrice;
    }
}
