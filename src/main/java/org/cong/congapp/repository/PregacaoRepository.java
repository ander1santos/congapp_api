package org.cong.congapp.repository;

import java.time.LocalDateTime;

import org.cong.congapp.model.Pregacao;
import org.cong.congapp.model.TerritorioPrincipal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface PregacaoRepository extends CrudRepository<Pregacao, Long> {

	Long countByTerritorioPropriedadeTerritorioPrincipalAndDataBetween(TerritorioPrincipal territorioPrincipal, LocalDateTime de, LocalDateTime para);

	Page<Pregacao> findByTerritorioPropriedadeNumeroPropriedade(String numeroPropriedade,Pageable page);
	
	Page<Pregacao> findByTerritorioPropriedadeTerritorioPrincipalId(Long principalId,Pageable page);
}
