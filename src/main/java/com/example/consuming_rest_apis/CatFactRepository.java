package com.example.consuming_rest_apis;

import org.springframework.data.repository.CrudRepository;

public interface CatFactRepository extends CrudRepository<CatFact,Long> {
}
