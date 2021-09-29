package com.gitlab.emradbuba.training.mpeek.persistance;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiCallRepository extends CrudRepository<ApiCallEntity, String> {
    // no custom logic goes here
}
