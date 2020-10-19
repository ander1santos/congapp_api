package org.cong.congapp.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Pregacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private LocalDateTime data = LocalDateTime.now();
	
	@ManyToOne
	private TerritorioPropriedade territorioPropriedade;
	
	@ManyToOne
	private Publicador publicador1;
	
	@ManyToOne
	private Publicador publicador2;	
	
	@ManyToOne
	private Morador morador;
	
	@ManyToOne
	private Modalidade modalidade;
	
	@ManyToOne(optional=true)
	private Publicador dirigente;
	
	private String detalhes;
	
	private LocalDateTime dataAlteracao;

	public Pregacao() {	}

	public Pregacao(LocalDateTime data, TerritorioPropriedade territorioPropriedade, Publicador publicador1,
			Publicador publicador2, Morador morador, Modalidade modalidade, Publicador dirigente, String detalhes,
			LocalDateTime dataAlteracao) {
		super();
		this.data = data;
		this.territorioPropriedade = territorioPropriedade;
		this.publicador1 = publicador1;
		this.publicador2 = publicador2;
		this.morador = morador;
		this.modalidade = modalidade;
		this.dirigente = dirigente;
		this.detalhes = detalhes;
		this.dataAlteracao = dataAlteracao;
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

	public TerritorioPropriedade getTerritorioPropriedade() {
		return territorioPropriedade;
	}

	public void setTerritorioPropriedade(TerritorioPropriedade territorioPropriedade) {
		this.territorioPropriedade = territorioPropriedade;
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

	public Morador getMorador() {
		return morador;
	}

	public void setMorador(Morador morador) {
		this.morador = morador;
	}

	public Modalidade getModalidade() {
		return modalidade;
	}

	public void setModalidade(Modalidade modalidade) {
		this.modalidade = modalidade;
	}

	public Publicador getDirigente() {
		return dirigente;
	}

	public void setDirigente(Publicador dirigente) {
		this.dirigente = dirigente;
	}

	public String getDetalhes() {
		return detalhes;
	}

	public void setDetalhes(String detalhes) {
		this.detalhes = detalhes;
	}

	public LocalDateTime getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(LocalDateTime dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}	
}
