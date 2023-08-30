package com.ibezrati.shorturl.entity;

import javax.persistence.*;

import static com.ibezrati.shorturl.entity.ShortUrl.COLUMN_NAME_EXPANDED;

@Entity
@Table(
        uniqueConstraints = {@UniqueConstraint(name = ShortUrl.CONSTRAINT_NAME_UNIQUE_EXPANDED, columnNames = {COLUMN_NAME_EXPANDED})},
        indexes = {@Index(name = ShortUrl.INDEX_NAME_UNIQUE_EXPANDED, columnList = ShortUrl.COLUMN_NAME_SHORTENED)}
)
public class ShortUrl {

    public static final String CONSTRAINT_NAME_UNIQUE_EXPANDED = "unique_expanded";
    public static final String INDEX_NAME_UNIQUE_EXPANDED = "index_shortened";
    public static final String COLUMN_NAME_EXPANDED = "expanded";
    public static final String COLUMN_NAME_SHORTENED = "shortened";

    @Id
    @GeneratedValue
    private Long id;

    private String shortened;
    private String expanded;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShortened() {
        return shortened;
    }

    public void setShortened(String shortened) {
        this.shortened = shortened;
    }

    public String getExpanded() {
        return expanded;
    }

    public void setExpanded(String expanded) {
        this.expanded = expanded;
    }
}
