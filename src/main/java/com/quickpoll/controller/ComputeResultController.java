package com.quickpoll.controller;

import com.quickpoll.dto.VoteResult;
import com.quickpoll.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ComputeResultController {

    @Autowired
    VoteService voteService;

    @RequestMapping(value = "/polls/{pollId}/computeresult", method = RequestMethod.GET)
    public ResponseEntity<?> computeResult(@PathVariable(value = "pollId") Long pollId){
        VoteResult voteResult = voteService.computeResult(pollId);

        return new ResponseEntity<VoteResult>(voteResult, HttpStatus.OK);
    }
}
