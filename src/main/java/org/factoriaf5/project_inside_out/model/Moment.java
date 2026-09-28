package org.factoriaf5.project_inside_out.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Moment {
    private int id;
    private String title;
    private String description;
    private Emotion emotion;
    private LocalDate momentDate;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public Moment(int id, String title, String description, Emotion emotion, LocalDate momentDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.emotion = emotion;
        this.momentDate = momentDate;
        this.createdDate = LocalDateTime.now();
        this.modifiedDate = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Emotion getEmotion() {
        return emotion;
    }

    public LocalDate getMomentDate() {
        return momentDate;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

}
