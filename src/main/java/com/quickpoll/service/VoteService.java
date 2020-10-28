package com.quickpoll.service;

import com.quickpoll.domain.Vote;
import com.quickpoll.dto.OptionCount;
import com.quickpoll.dto.VoteResult;
import com.quickpoll.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class VoteService {
    @Autowired
    VoteRepository voteRepository;

    public void createVote(Vote vote){
        voteRepository.save(vote);
    }

    public Iterable<Vote> getAllVotes(Long pollId){
        return voteRepository.findByPoll(pollId);
    }

    public VoteResult computeResult(Long pollId){
        VoteResult voteResult = new VoteResult();
        Iterable<Vote> allVotes = voteRepository.findByPoll(pollId);

        int totalVotes = 0;
        Map<Long, OptionCount> tempMap = new HashMap<Long, OptionCount>();
        for (Vote v: allVotes) {
            totalVotes++;
            OptionCount optionCount = tempMap.get(v.getOption().getId());
            if(optionCount == null){
                optionCount = new OptionCount();
                optionCount.setOptionId(v.getOption().getId());
                tempMap.put(v.getOption().getId(), optionCount);
            }
            optionCount.setCount(optionCount.getCount() + 1);
        }
        voteResult.setTotalVotes(totalVotes);
        voteResult.setResults(tempMap.values());
        return voteResult;
    }
}
