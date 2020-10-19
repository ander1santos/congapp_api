package org.cong.congapp.repository;

import java.util.List;
import java.util.Optional;

import org.cong.congapp.model.TerritorioPrincipal;
import org.cong.congapp.model.TerritorioPropriedade;
import org.springframework.data.repository.CrudRepository;

public interface TerritorioPropriedadeRepository extends CrudRepository<TerritorioPropriedade, Long> {

	List<TerritorioPropriedade> findByTerritorioPrincipal(TerritorioPrincipal territorioPrincipal);

	Optional<TerritorioPropriedade> findByTerritorioPrincipalLogrSimplesAndNumeroPropriedade(String logrSimples,String numeroPropriedade);

	Optional<TerritorioPropriedade> findByTerritorioPrincipalIdAndNumeroPropriedade(Long principalId,String numeroPropriedade);

}
