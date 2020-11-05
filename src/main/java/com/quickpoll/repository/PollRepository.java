package com.quickpoll.repository;

import com.quickpoll.domain.Poll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:4200")
@Repository
public interface PollRepository extends CrudRepository<Poll, Long> {
}
