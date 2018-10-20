package com.fallendeductionist.notasapp.models;

import com.orm.dsl.Table;

@Table
public class Note {

    private Long id;
    private String title;
    private String description;
    private Boolean favorite;


    public Note() {
    }

    public Note(String title, String description, Boolean favorite) {
        this.setTitle(title);
        this.setDescription(description);
        this.setFavorite(favorite);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
