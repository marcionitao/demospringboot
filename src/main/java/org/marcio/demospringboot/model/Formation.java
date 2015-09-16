package org.marcio.demospringboot.model;

import javax.persistence.*;

@Entity
public class Formation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String theme;
    private String description;

    public Formation(){
        super();
    }

    public Formation(String theme, String description) {
        this.theme = theme;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
