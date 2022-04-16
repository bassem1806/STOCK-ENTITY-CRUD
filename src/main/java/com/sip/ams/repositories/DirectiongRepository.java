package com.sip.ams.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sip.ams.entities.Directiong;


@Repository
public interface DirectiongRepository extends CrudRepository <Directiong , Long>{

}
