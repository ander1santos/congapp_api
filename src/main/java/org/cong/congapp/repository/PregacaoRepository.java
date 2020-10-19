package org.cong.congapp.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.cong.congapp.model.Pregacao;
import org.cong.congapp.model.TerritorioPrincipal;
import org.springframework.data.repository.CrudRepository;

public interface PregacaoRepository extends CrudRepository<Pregacao, Long> {

	Long countByTerritorioPropriedadeTerritorioPrincipalAndDataBetween(TerritorioPrincipal territorioPrincipal, LocalDateTime de, LocalDateTime para);

	List<Pregacao> findByTerritorioPropriedadeNumeroPropriedadeOrderByDataDesc(String numeroPropriedade);
	
	List<Pregacao> findByTerritorioPropriedadeTerritorioPrincipalIdOrderByDataDesc(Long principalId);
}
