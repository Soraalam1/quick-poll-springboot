package com.quickpoll.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Poll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POLL_ID")
    private Long id;

    @Column(name = "QUESTION")
    @NotEmpty
    private String question;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "POLL_ID")
    @OrderBy
    @Size(min = 2, max = 6)
    private Set<Poll_Options> pollOptions;

    public Long getId() {
        return id;
    }

    public Poll() {
    }

    public Poll(Long id, String question, Set<Poll_Options> pollOptions) {
        this.id = id;
        this.question = question;
        this.pollOptions = pollOptions;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Poll_Options> getOptions() {
        return pollOptions;
    }

    public void setOptions(Set<Poll_Options> pollOptions) {
        this.pollOptions = pollOptions;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Poll(String question, Set<Poll_Options> pollOptions) {
        this.question = question;
        this.pollOptions = pollOptions;
    }


}
