package com.quickpoll.service;

import com.quickpoll.domain.Poll;
import com.quickpoll.repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PollService {

    @Autowired
    PollRepository pollRepository;

    public ResponseEntity<Iterable<Poll>> getPolls(){
        Iterable<Poll> allPolls = pollRepository.findAll();
        return new ResponseEntity(pollRepository.findAll(), HttpStatus.OK);
    }

    public void createPoll(Poll poll){
        pollRepository.save(poll);
    }

    public Optional<Poll> getPoll(Long pollId){
        return pollRepository.findById(pollId);
    }

    public Poll updatePoll(Long id, Poll poll){
        return pollRepository.save(poll);
    }

    public void deletePoll(Long id){
        pollRepository.deleteById(id);
    }

}
