package org.cong.congapp.repository;

import java.time.LocalDate;
import java.util.List;

import org.cong.congapp.model.DesignacaoTerritorio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface DesignacaoTerritorioRepository extends CrudRepository<DesignacaoTerritorio, Long> {

	List<DesignacaoTerritorio> findByData(LocalDate data);

	Page<DesignacaoTerritorio> findAll(Pageable pageable);

}
