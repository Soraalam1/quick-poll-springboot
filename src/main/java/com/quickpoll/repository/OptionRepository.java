package com.quickpoll.repository;

import com.quickpoll.domain.Poll_Options;
import org.springframework.data.repository.CrudRepository;

public interface OptionRepository extends CrudRepository<Poll_Options, Long> {
}
