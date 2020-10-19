package org.cong.congapp.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Morador implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String nome;
	
	@ManyToOne
	private TipoContato tipoContato;
	
	@ManyToOne
	private TerritorioPropriedade propriedade;
	
	public Morador() {}
	
	public Morador(String nome, TipoContato tipoContato, TerritorioPropriedade propriedade) {
		super();
		this.nome = nome;
		this.tipoContato = tipoContato;
		this.propriedade = propriedade;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public TipoContato getTipoMorador() {
		return tipoContato;
	}

	public void setTipoMorador(TipoContato tipoContato) {
		this.tipoContato = tipoContato;
	}

	public TerritorioPropriedade getPropriedade() {
		return propriedade;
	}

	public void setPropriedade(TerritorioPropriedade propriedade) {
		this.propriedade = propriedade;
	}
}
