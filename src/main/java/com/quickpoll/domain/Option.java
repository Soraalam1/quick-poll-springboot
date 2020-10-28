package com.quickpoll.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import javax.persistence.*;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OPTION_ID")
    private Long id;

    @Column(name = "OPTION_VALUE")
    //@JsonProperty("value")
    private String value;

    public Option() {
    }


    public Option(Long id, String value) {
        this.id = id;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


}
