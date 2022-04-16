package com.sip.ams.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sip.ams.entities.Direction;

@Repository
public interface DirectionRepository extends CrudRepository<Direction , Long> {

}
