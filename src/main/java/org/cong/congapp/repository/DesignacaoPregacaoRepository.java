package org.cong.congapp.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.cong.congapp.model.DesignacaoPregacao;
import org.springframework.data.repository.CrudRepository;

public interface DesignacaoPregacaoRepository extends CrudRepository<DesignacaoPregacao, Long> {

	default List<DesignacaoPregacao> findByData(LocalDate data){
		return findByDataBetween(data.atStartOfDay(), data.plusDays(1).atStartOfDay());
	};
	
	List<DesignacaoPregacao> findByDataBetween(LocalDateTime de, LocalDateTime para);
}
