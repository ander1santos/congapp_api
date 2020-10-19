package org.cong.congapp.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.cong.congapp.model.DesignacaoPregacao;
import org.cong.congapp.model.Publicador;
import org.springframework.data.repository.CrudRepository;

public interface DesignacaoPregacaoRepository extends CrudRepository<DesignacaoPregacao, Long> {

	default List<DesignacaoPregacao> findByData(LocalDate data){
		return findByDataBetween(data.atStartOfDay(), data.plusDays(1).atStartOfDay());
	};
	
	default Optional<DesignacaoPregacao> findByPublicadorAndData(Publicador publicador,LocalDate data) {
		return findTop1ByDataBetweenAndPublicador1OrDataBetweenAndPublicador2(data.atStartOfDay(),data.plusDays(1).atStartOfDay(),publicador,data.atStartOfDay(),data.plusDays(1).atStartOfDay(),publicador);
	};
	
	
	List<DesignacaoPregacao> findByDataBetween(LocalDateTime de, LocalDateTime para);
	
	Optional<DesignacaoPregacao> findTop1ByDataBetweenAndPublicador1OrDataBetweenAndPublicador2(LocalDateTime de1,LocalDateTime para1,Publicador publicador1, LocalDateTime de2,LocalDateTime para2, Publicador publicador2);
}
