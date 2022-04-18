package com.sip.ams.repositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.sip.ams.entities.Sousdirection;




@Repository
public interface SousdirectionRepository extends CrudRepository <Sousdirection, Long> {

}
