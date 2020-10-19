package org.cong.congapp.dto.output;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.cong.congapp.model.Pregacao;

public class PregacaoSaidaDto {

	private Long id;
	private LocalDateTime data = LocalDateTime.now();	
	private String numeroPropriedade;
	private String publicador1;
	private String publicador2;	
	private String nomeMorador;
	private String tipoContato;
	private String descricaoModalidade;
	private String dirigente;
	private String detalhes;
	
	public PregacaoSaidaDto(Pregacao pregacao) {
		this.id = pregacao.getId();
		this.data = pregacao.getData();
		this.numeroPropriedade = pregacao.getTerritorioPropriedade() != null ? pregacao.getTerritorioPropriedade().getNumeroPropriedade() : "";
		this.publicador1 = pregacao.getPublicador1() != null ? pregacao.getPublicador1().getNome() : "";
		this.publicador2 = pregacao.getPublicador2() != null ? pregacao.getPublicador2().getNome() : "";
		this.nomeMorador = pregacao.getMorador() != null ? pregacao.getMorador().getNome() : "";
		this.tipoContato = pregacao.getMorador() != null && pregacao.getMorador().getTipoMorador() != null ? pregacao.getMorador().getTipoMorador().getDescricao() : "";
		this.descricaoModalidade = pregacao.getModalidade() != null ? pregacao.getModalidade().getDescricao() : "";
		this.dirigente = pregacao.getDirigente() != null ? pregacao.getDirigente().getNome() : "";
		this.detalhes = pregacao.getDetalhes();
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

	public String getNumeroPropriedade() {
		return numeroPropriedade;
	}

	public void setNumeroPropriedade(String numeroPropriedade) {
		this.numeroPropriedade = numeroPropriedade;
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

	public String getNomeMorador() {
		return nomeMorador;
	}

	public void setNomeMorador(String nomeMorador) {
		this.nomeMorador = nomeMorador;
	}
	
	public String getTipoContato() {
		return tipoContato;
	}

	public void setTipoContato(String tipoContato) {
		this.tipoContato = tipoContato;
	}

	public String getDescricaoModalidade() {
		return descricaoModalidade;
	}

	public void setDescricaoModalidade(String descricaoModalidade) {
		this.descricaoModalidade = descricaoModalidade;
	}

	public String getDirigente() {
		return dirigente;
	}

	public void setDirigente(String dirigente) {
		this.dirigente = dirigente;
	}

	public String getDetalhes() {
		return detalhes;
	}

	public void setDetalhes(String detalhes) {
		this.detalhes = detalhes;
	}
	
	public static List<PregacaoSaidaDto> listarPregacao(List<Pregacao> pregacoes){
		return pregacoes.stream()
				.map(PregacaoSaidaDto::new)
				.collect(Collectors.toList());
	}
}
