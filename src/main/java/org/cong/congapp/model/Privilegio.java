package org.cong.congapp.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Privilegio implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String descricao;
	
	private boolean telaPregacaoTerritorio = false;
	
	private boolean telaPregacaoDesignacao = false;
	
	private boolean telaPregacaoAtividade = true;
	
	private boolean telaPregacaoLista = true;
	
	private boolean telaCadastroTerritorio = false;
	
	private boolean telaCadastroPublicador = false;
	
	private boolean telaCadastroPrivilegio = false;
	
	private boolean telaCadastroModalidade = false;
	
	private boolean telaCadastroTipocontato = false;
	
	

	public Privilegio() {}
	
	public Privilegio(String descricao, boolean telaPregacaoTerritorio, boolean telaPregacaoDesignacao,
			boolean telaPregacaoAtividade, boolean telaPregacaoLista, boolean telaCadastroTerritorio,
			boolean telaCadastroPublicador, boolean telaCadastroPrivilegio, boolean telaCadastroModalidade,
			boolean telaCadastroTipocontato) {
		super();
		this.descricao = descricao;
		this.telaPregacaoTerritorio = telaPregacaoTerritorio;
		this.telaPregacaoDesignacao = telaPregacaoDesignacao;
		this.telaPregacaoAtividade = telaPregacaoAtividade;
		this.telaPregacaoLista = telaPregacaoLista;
		this.telaCadastroTerritorio = telaCadastroTerritorio;
		this.telaCadastroPublicador = telaCadastroPublicador;
		this.telaCadastroPrivilegio = telaCadastroPrivilegio;
		this.telaCadastroModalidade = telaCadastroModalidade;
		this.telaCadastroTipocontato = telaCadastroTipocontato;
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

	public boolean isTelaPregacaoTerritorio() {
		return telaPregacaoTerritorio;
	}

	public void setTelaPregacaoTerritorio(boolean telaPregacaoTerritorio) {
		this.telaPregacaoTerritorio = telaPregacaoTerritorio;
	}

	public boolean isTelaPregacaoDesignacao() {
		return telaPregacaoDesignacao;
	}

	public void setTelaPregacaoDesignacao(boolean telaPregacaoDesignacao) {
		this.telaPregacaoDesignacao = telaPregacaoDesignacao;
	}

	public boolean isTelaPregacaoAtividade() {
		return telaPregacaoAtividade;
	}

	public void setTelaPregacaoAtividade(boolean telaPregacaoAtividade) {
		this.telaPregacaoAtividade = telaPregacaoAtividade;
	}

	public boolean isTelaPregacaoLista() {
		return telaPregacaoLista;
	}

	public void setTelaPregacaoLista(boolean telaPregacaoLista) {
		this.telaPregacaoLista = telaPregacaoLista;
	}

	public boolean isTelaCadastroTerritorio() {
		return telaCadastroTerritorio;
	}

	public void setTelaCadastroTerritorio(boolean telaCadastroTerritorio) {
		this.telaCadastroTerritorio = telaCadastroTerritorio;
	}

	public boolean isTelaCadastroPublicador() {
		return telaCadastroPublicador;
	}

	public void setTelaCadastroPublicador(boolean telaCadastroPublicador) {
		this.telaCadastroPublicador = telaCadastroPublicador;
	}

	public boolean isTelaCadastroPrivilegio() {
		return telaCadastroPrivilegio;
	}

	public void setTelaCadastroPrivilegio(boolean telaCadastroPrivilegio) {
		this.telaCadastroPrivilegio = telaCadastroPrivilegio;
	}

	public boolean isTelaCadastroModalidade() {
		return telaCadastroModalidade;
	}

	public void setTelaCadastroModalidade(boolean telaCadastroModalidade) {
		this.telaCadastroModalidade = telaCadastroModalidade;
	}

	public boolean isTelaCadastroTipocontato() {
		return telaCadastroTipocontato;
	}

	public void setTelaCadastroTipocontato(boolean telaCadastroTipocontato) {
		this.telaCadastroTipocontato = telaCadastroTipocontato;
	}	
}
