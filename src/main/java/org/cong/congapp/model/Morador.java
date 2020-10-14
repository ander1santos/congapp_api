package org.cong.congapp.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Morador implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	private TipoContato tipoContato;
	
	private TerritorioPropriedade territPropriedade;
	
	public Morador() {}
	
	public Morador(String nome, TipoContato tipoContato, TerritorioPropriedade territPropriedade) {
		super();
		this.nome = nome;
		this.tipoContato = tipoContato;
		this.territPropriedade = territPropriedade;
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

	public TerritorioPropriedade getterritPropriedade() {
		return territPropriedade;
	}

	public void setterritPropriedade(TerritorioPropriedade territPropriedade) {
		this.territPropriedade = territPropriedade;
	}
}
