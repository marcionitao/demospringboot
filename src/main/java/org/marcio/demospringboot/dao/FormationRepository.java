package org.marcio.demospringboot.dao;

import org.marcio.demospringboot.model.Formation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Marcio on 08/09/2015.
 */
public interface FormationRepository extends CrudRepository<Formation, Long> {
    List<Formation> findByTheme(String theme);
}
