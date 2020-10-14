package org.cong.congapp.dto.output;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.cong.congapp.model.DesignacaoPregacao;
import org.cong.congapp.model.TerritorioPropriedade;

public class DesignacaoPregacaoSaidaDto {
	
	private Long id;
	private LocalDateTime data;
	private String publicador1;
	private String publicador2;
	private String territorioPrincipal;
	private String dirigente;
	private int qtdePropriedade;
	private List<String> propriedades = new ArrayList<String>();
	
	public DesignacaoPregacaoSaidaDto(DesignacaoPregacao designacaoPregacao) {
		this.id = designacaoPregacao.getId();
		this.data = designacaoPregacao.getData();
		this.publicador1 = designacaoPregacao.getPublicador1().getNome();
		this.publicador2 = designacaoPregacao.getPublicador2() != null ? designacaoPregacao.getPublicador2().getNome() : null;
		this.territorioPrincipal = designacaoPregacao.getTerritorio().getLogrSimples();
		this.dirigente = designacaoPregacao.getDirigente() != null ? designacaoPregacao.getDirigente().getNome() : null;
		this.qtdePropriedade = designacaoPregacao.getPropriedades().size();
		for (TerritorioPropriedade propriedade : designacaoPregacao.getPropriedades()) {
			this.propriedades.add(propriedade.getNumeroPropriedade());
		}
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

	public String getPublicador1() {
		return publicador1;
	}

	public void setPublicador1(String publicador1) {
		this.publicador1 = publicador1;
	}

	public String getPublicador2() {
		return publicador2;
	}

	public void setPublicador2(String publicador2) {
		this.publicador2 = publicador2;
	}

	public String getTerritorioPrincipal() {
		return territorioPrincipal;
	}

	public void setTerritorioPrincipal(String territorioPrincipal) {
		this.territorioPrincipal = territorioPrincipal;
	}

	public String getDirigente() {
		return dirigente;
	}

	public void setDirigente(String dirigente) {
		this.dirigente = dirigente;
	}

	public int getQtdePropriedade() {
		return qtdePropriedade;
	}

	public void setQtdePropriedade(int qtdePropriedade) {
		this.qtdePropriedade = qtdePropriedade;
	}

	public List<String> getPropriedades() {
		return propriedades;
	}

	public void setPropriedades(List<String> propriedades) {
		this.propriedades = propriedades;
	}

	public static List<DesignacaoPregacaoSaidaDto> listarDesignacoes(List<DesignacaoPregacao> pregacoes) {
		return pregacoes.stream()
				.map(DesignacaoPregacaoSaidaDto::new)
				.collect(Collectors.toList());
	}
		
}
