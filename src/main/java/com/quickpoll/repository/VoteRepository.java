package com.quickpoll.repository;

import com.quickpoll.domain.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:4200")
@Repository
public interface VoteRepository extends CrudRepository<Vote, Long> {

    @Query(value = "select v.* from Poll_Options o, Vote v where o.POLL_ID = ?1 and v.OPTION_ID = o.OPTION_ID", nativeQuery = true)
         Iterable<Vote> findByPoll(Long pollId);
}
