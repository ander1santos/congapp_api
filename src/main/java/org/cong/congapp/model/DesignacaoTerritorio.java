package org.cong.congapp.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class DesignacaoTerritorio implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private LocalDate data;
	
	@ManyToOne
	private TerritorioPrincipal territorio;
	
	@ManyToOne(optional=true)
	private Publicador dirigente;
	
	@ManyToMany
	private List<TerritorioPropriedade> propriedades;

	public DesignacaoTerritorio() {}

	public DesignacaoTerritorio(LocalDate data, TerritorioPrincipal territorio, Publicador dirigente,
			List<TerritorioPropriedade> propriedades) {
		this.data = data;
		this.territorio = territorio;
		this.dirigente = dirigente;		
		this.propriedades = propriedades;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}
	
	public TerritorioPrincipal getTerritorio() {
		return territorio;
	}

	public void setTerritorio(TerritorioPrincipal territorio) {
		this.territorio = territorio;
	}

	public Publicador getDirigente() {
		return dirigente;
	}

	public void setDirigente(Publicador dirigente) {
		this.dirigente = dirigente;
	}

	public List<TerritorioPropriedade> getPropriedades() {
		return propriedades;
	}

	public void setPropriedades(List<TerritorioPropriedade> propriedades) {
		this.propriedades = propriedades;
	}
}
