package org.cong.congapp.repository;

import java.util.List;
import java.util.Optional;

import org.cong.congapp.model.Morador;
import org.cong.congapp.model.TerritorioPropriedade;
import org.springframework.data.repository.CrudRepository;

public interface MoradorRepository extends CrudRepository<Morador, Long> {

	List<Morador> findByPropriedade(TerritorioPropriedade propriedade);

	Optional<Morador> findByNomeAndPropriedade(String nomeMorador, TerritorioPropriedade propriedade);

}
