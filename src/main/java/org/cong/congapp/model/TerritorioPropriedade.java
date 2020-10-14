package org.cong.congapp.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class TerritorioPropriedade implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	
	@ManyToOne
	private TerritorioPrincipal territorioPrincipal;
	
	@Column(nullable=false)
	private String numeroPropriedade;
	
	private String agrupamento;
	
	private LocalDateTime ultimaPregacao;

	public TerritorioPropriedade() {}

	public TerritorioPropriedade(TerritorioPrincipal territorioPrincipal, String numeroPropriedade, String agrupamento,
			LocalDateTime ultimaPregacao) {
		super();
		this.territorioPrincipal = territorioPrincipal;
		this.numeroPropriedade = numeroPropriedade;
		this.agrupamento = agrupamento;
		this.ultimaPregacao = ultimaPregacao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TerritorioPrincipal getTerritorioPrincipal() {
		return territorioPrincipal;
	}

	public void setTerritorioPrincipal(TerritorioPrincipal territorioPrincipal) {
		this.territorioPrincipal = territorioPrincipal;
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
}
