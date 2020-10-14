package org.cong.congapp.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class DesignacaoPregacao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private LocalDateTime data;
	
	@ManyToOne
	private Publicador publicador1;
	
	@ManyToOne
	private Publicador publicador2;
	
	@ManyToOne
	private TerritorioPrincipal territorio;
	
	@ManyToOne(optional=true)
	private Publicador dirigente;
	
	@ManyToMany
	private List<TerritorioPropriedade> propriedades;

	public DesignacaoPregacao() {}

	public DesignacaoPregacao(LocalDateTime data, Publicador publicador1,
			Publicador publicador2, TerritorioPrincipal territorio, Publicador dirigente, 
			List<TerritorioPropriedade> propriedades) {
		this.data = data;
		this.publicador1 = publicador1;
		this.publicador2 = publicador2;
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

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public Publicador getPublicador1() {
		return publicador1;
	}

	public void setPublicador1(Publicador publicador1) {
		this.publicador1 = publicador1;
	}

	public Publicador getPublicador2() {
		return publicador2;
	}

	public void setPublicador2(Publicador publicador2) {
		this.publicador2 = publicador2;
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
