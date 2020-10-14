package org.cong.congapp.repository;

import java.util.List;

import org.cong.congapp.model.TerritorioPrincipal;
import org.cong.congapp.model.TerritorioPropriedade;
import org.springframework.data.repository.CrudRepository;

public interface TerritorioPropriedadeRepository extends CrudRepository<TerritorioPropriedade, Long> {

	List<TerritorioPropriedade> findByTerritorioPrincipal(TerritorioPrincipal territorioPrincipal);

}
