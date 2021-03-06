package org.cong.congapp.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TipoContato implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String descricao;
	
	private boolean restricaoContato = false;
	
	private int mesesRestricao = 0;
	
	public TipoContato() {}
	
	public TipoContato(String descricao, boolean restricaoContato, int mesesRestricao) {
		super();
		this.descricao = descricao;
		this.restricaoContato = restricaoContato;
		this.mesesRestricao = mesesRestricao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isRestricaoContato() {
		return restricaoContato;
	}

	public void setRestricaoContato(boolean restricaoContato) {
		this.restricaoContato = restricaoContato;
	}

	public int getMesesRestricao() {
		return mesesRestricao;
	}

	public void setMesesRestricao(int mesesRestricao) {
		this.mesesRestricao = mesesRestricao;
	}
	
	
	
}
