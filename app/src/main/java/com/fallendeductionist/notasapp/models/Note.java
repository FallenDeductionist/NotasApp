package com.fallendeductionist.notasapp.models;

import com.orm.dsl.Table;

import java.text.SimpleDateFormat;
import java.util.Date;

@Table
public class Note {

    private Long id;
    private String date;
    private String title;
    private String description;
    private Boolean favorite;
    private Boolean archive;
    private Long linkCards;


    public Note() {
    }

    public Note(String date, String title, String description, Boolean favorite, Boolean archive, Long linkCards) {
        this.setDate(date);
        this.setTitle(title);
        this.setDescription(description);
        this.setFavorite(favorite);
        this.setArchive(archive);
        this.setLinkCards(linkCards);

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public Boolean getArchive() {
        return archive;
    }

    public void setArchive(Boolean archive) {
        this.archive = archive;
    }

    public Long getLinkCards() {
        return linkCards;
    }

    public void setLinkCards(Long linkCards) {
        this.linkCards = linkCards;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", favorite=" + favorite  +
                ", archive=" + archive  +
                ", linkCards=" + linkCards +
                '}';
    }


}
