package org.cong.congapp.dto.output;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.cong.congapp.model.TerritorioPropriedade;

public class PropriedadesSaidaDto {

	private Long id;
	private String numeroPropriedade;
	private String agrupamento;
	private LocalDateTime ultimaPregacao;
	
	public PropriedadesSaidaDto(TerritorioPropriedade propriedade) {
		this.id = propriedade.getId();
		this.numeroPropriedade = propriedade.getNumeroPropriedade();
		this.agrupamento = propriedade.getAgrupamento();
		this.ultimaPregacao = propriedade.getUltimaPregacao();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroPropriedade() {
		return numeroPropriedade;
	}

	public void setNumeroPropriedade(String numeroPropriedade) {
		this.numeroPropriedade = numeroPropriedade;
	}

	public String getAgrupamento() {
		return agrupamento;
	}

	public void setAgrupamento(String agrupamento) {
		this.agrupamento = agrupamento;
	}

	public LocalDateTime getUltimaPregacao() {
		return ultimaPregacao;
	}

	public void setUltimaPregacao(LocalDateTime ultimaPregacao) {
		this.ultimaPregacao = ultimaPregacao;
	}

	public static List<PropriedadesSaidaDto> listarPropriedades(List<TerritorioPropriedade> propriedades){
		return propriedades.stream()
				.map(PropriedadesSaidaDto::new)
				.collect(Collectors.toList());
	}
	
}
