package com.quickpoll.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VOTE_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "OPTION_ID")
    private Poll_Options pollOptions;

    public Vote() {
    }

    public Vote(Long id, Poll_Options pollOptions) {
        this.id = id;
        this.pollOptions = pollOptions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Poll_Options getOption() {
        return pollOptions;
    }

    public void setOption(Poll_Options pollOptions) {
        this.pollOptions = pollOptions;
    }


}
