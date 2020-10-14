package org.cong.congapp.dto.input;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.cong.congapp.exception.RegistroNotFoundException;
import org.cong.congapp.model.DesignacaoPregacao;
import org.cong.congapp.model.DesignacaoTerritorio;
import org.cong.congapp.model.Publicador;
import org.cong.congapp.model.TerritorioPropriedade;
import org.cong.congapp.repository.DesignacaoTerritorioRepository;
import org.cong.congapp.repository.PublicadorRepository;
import org.cong.congapp.repository.TerritorioPropriedadeRepository;

public class DesignacaoPregacaoEntradaDto {

	private Long id;
	private Long publicadorId1;
	private Long publicadorId2;
	private Long territorioId;
	private Long dirigenteId;
	private List<Long> propriedades;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPublicadorId1() {
		return publicadorId1;
	}
	public void setPublicadorId1(Long publicadorId1) {
		this.publicadorId1 = publicadorId1;
	}
	public Long getPublicadorId2() {
		return publicadorId2;
	}
	public void setPublicadorId2(Long publicadorId2) {
		this.publicadorId2 = publicadorId2;
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
	
	public DesignacaoPregacao build(DesignacaoTerritorioRepository desigTerritRepository, 
			TerritorioPropriedadeRepository territPropriedadeRepository,
			PublicadorRepository publicadorRepository) throws RegistroNotFoundException {
		
		DesignacaoTerritorio designacaoterritorio = desigTerritRepository.findById(this.territorioId)
				.orElseThrow(() -> new RegistroNotFoundException("Nao foi possivel encontrar o id do territorio designado: " + this.territorioId));
		Publicador publicador1 = publicadorRepository.findById(this.publicadorId1)
				.orElseThrow(() -> new RegistroNotFoundException("Nao foi possivel encontrar o id do publicador: " + this.publicadorId1));
		Optional<Publicador> publicador2 = publicadorRepository.findById(this.publicadorId2);
		Optional<Publicador> dirigente = publicadorRepository.findById(this.dirigenteId);
		
		LocalDateTime data = LocalDateTime.now();
		
		List<TerritorioPropriedade> propriedades = new ArrayList<TerritorioPropriedade>();
		TerritorioPropriedade propriedade;
		for (Long propriedadeId : this.propriedades) {
			propriedade = territPropriedadeRepository.findById(propriedadeId)
					.orElseThrow(() -> new RegistroNotFoundException("Nao foi possivel encontrar o id do imovel: " + propriedadeId));
			propriedades.add(propriedade);					
		}
		
		return new DesignacaoPregacao(data,
				publicador1,
				//segundo publicador pode ser nulo
				publicador2.orElse(null),
				designacaoterritorio.getTerritorio(),
				//dirigente pode ser nulo
				dirigente.orElse(null),
				propriedades);
		
	}
	
}
