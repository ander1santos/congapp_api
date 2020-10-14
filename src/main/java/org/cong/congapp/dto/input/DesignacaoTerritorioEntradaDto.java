package org.cong.congapp.dto.input;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.cong.congapp.exception.RegistroNotFoundException;
import org.cong.congapp.model.DesignacaoTerritorio;
import org.cong.congapp.model.Publicador;
import org.cong.congapp.model.TerritorioPrincipal;
import org.cong.congapp.model.TerritorioPropriedade;
import org.cong.congapp.repository.PublicadorRepository;
import org.cong.congapp.repository.TerritorioPrincipalRepository;
import org.cong.congapp.repository.TerritorioPropriedadeRepository;

public class DesignacaoTerritorioEntradaDto {
	
	private Long id;
	private Long territorioId;
	private Long dirigenteId;
	private List<Long> propriedades;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getTerritorioId() {
		return territorioId;
	}
	public void setTerritorioId(Long territorioId) {
		this.territorioId = territorioId;
	}
	public Long getDirigenteId() {
		return dirigenteId;
	}
	public void setDirigenteId(Long dirigenteId) {
		this.dirigenteId = dirigenteId;
	}
	public List<Long> getPropriedades() {
		return propriedades;
	}
	public void setPropriedades(List<Long> propriedades) {
		this.propriedades = propriedades;
	}
	
	public DesignacaoTerritorio build(TerritorioPrincipalRepository territPrincRepository, 
			TerritorioPropriedadeRepository territPropriedadeRepository,
			PublicadorRepository publicadorRepository) throws RegistroNotFoundException {
		
		TerritorioPrincipal principal = territPrincRepository.findById(this.territorioId)
				.orElseThrow(() -> new RegistroNotFoundException("Nao foi possivel encontrar o id do territorio designado: " + this.territorioId));
		Optional<Publicador> dirigente = publicadorRepository.findById(this.dirigenteId);
		
		LocalDate data = LocalDate.now();
		
		List<TerritorioPropriedade> propriedades = new ArrayList<TerritorioPropriedade>();
		TerritorioPropriedade propriedade;
		for (Long propriedadeId : this.propriedades) {
			propriedade = territPropriedadeRepository.findById(propriedadeId)
					.orElseThrow(() -> new RegistroNotFoundException("Nao foi possivel encontrar o id do imovel: " + propriedadeId));
			propriedades.add(propriedade);					
		}
		
		return new DesignacaoTerritorio(data,principal,dirigente.orElse(null),propriedades);
		
	}
	

}
