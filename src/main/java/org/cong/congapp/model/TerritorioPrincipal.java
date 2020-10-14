package org.cong.congapp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class TerritorioPrincipal implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String logradouro;
	
	private String logrSimples;
	
	@ManyToOne
	private Congregacao congregacao;
	
	public TerritorioPrincipal() {
		super();
	}
	
	public TerritorioPrincipal(String logradouro, String logrSimples, Congregacao congregacao) {
		super();
		this.logradouro = logradouro;
		this.logrSimples = logrSimples;
		this.congregacao = congregacao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getLogrSimples() {
		return logrSimples;
	}

	public void setLogrSimples(String logrSimples) {
		this.logrSimples = logrSimples;
	}

	public Congregacao getCongregacao() {
		return congregacao;
	}

	public void setCongregacao(Congregacao congregacao) {
		this.congregacao = congregacao;
	}

}
