package org.cong.congapp.dto.output;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.cong.congapp.model.DesignacaoTerritorio;
import org.cong.congapp.model.Publicador;
import org.cong.congapp.repository.PregacaoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

public class TerritorioDesignadoSaidaDto {

	private Long id;
	private LocalDate data;
	private String logradouroSimples;
	private String logradouroCompleto;
	private int qtdImoveis;
	private Long qtdPregacaoImoveis;
	private String nomeDirigente;
	
	private List<PropriedadesSaidaDto> propriedades = new ArrayList<>();
	
	public TerritorioDesignadoSaidaDto(DesignacaoTerritorio territorio) {
		this.id = territorio.getId();
		this.data = territorio.getData();
		this.logradouroSimples = territorio.getTerritorio().getLogrSimples();
		this.logradouroCompleto= territorio.getTerritorio().getLogradouro();
		this.qtdImoveis = territorio.getPropriedades().size();
		this.qtdPregacaoImoveis = Long.valueOf(0);
		if(territorio.getDirigente() == null) {
			territorio.setDirigente(new Publicador());
		}
		this.nomeDirigente = territorio.getDirigente().getNome();		
		this.propriedades = PropriedadesSaidaDto.listarPropriedades(territorio.getPropriedades());
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

	public String getLogradouroSimples() {
		return logradouroSimples;
	}

	public void setLogradouroSimples(String logradouroSimples) {
		this.logradouroSimples = logradouroSimples;
	}

	public String getLogradouroCompleto() {
		return logradouroCompleto;
	}

	public void setLogradouroCompleto(String logradouroCompleto) {
		this.logradouroCompleto = logradouroCompleto;
	}

	public int getQtdImoveis() {
		return qtdImoveis;
	}

	public void setQtdImoveis(int qtdImoveis) {
		this.qtdImoveis = qtdImoveis;
	}
	
	public Long getQtdPregacaoImoveis() {
		return qtdPregacaoImoveis;
	}

	public void setQtdPregacaoImoveis(Long qtdPregacaoImoveis) {
		this.qtdPregacaoImoveis = qtdPregacaoImoveis;
	}

	public String getNomeDirigente() {
		return nomeDirigente;
	}

	public void setNomeDirigente(String nomeDirigente) {
		this.nomeDirigente = nomeDirigente;
	}

	public List<PropriedadesSaidaDto> getPropriedades() {
		return propriedades;
	}

	public void setPropriedades(List<PropriedadesSaidaDto> propriedades) {
		this.propriedades = propriedades;
	}
	
	public static List<TerritorioDesignadoSaidaDto> listarTerritorios(List<DesignacaoTerritorio> territorios){
		return territorios.stream()
				.map(TerritorioDesignadoSaidaDto::new)
				.collect(Collectors.toList());
	}

	public static Page<TerritorioDesignadoSaidaDto> paginarTerritorios(Page<DesignacaoTerritorio> territorio,
			PregacaoRepository pregacaoRepository) {
		
		List<DesignacaoTerritorio> lista = territorio.getContent();
		List<TerritorioDesignadoSaidaDto> listaDto = new ArrayList<>();
		TerritorioDesignadoSaidaDto territorioDto; 
		
		for (DesignacaoTerritorio designacao : lista) {
			territorioDto = new TerritorioDesignadoSaidaDto(designacao);
			territorioDto.setQtdPregacaoImoveis(
					pregacaoRepository.countByTerritorioPropriedadeTerritorioPrincipalAndDataBetween(
							designacao.getTerritorio(), designacao.getData().atStartOfDay(), 
							designacao.getData().plusDays(1).atStartOfDay()));
			listaDto.add(territorioDto);
		}
		
		Page<TerritorioDesignadoSaidaDto> paginaDto = new PageImpl<TerritorioDesignadoSaidaDto>(listaDto,territorio.getPageable(),territorio.getTotalElements());
		
		
		return paginaDto;

	}
}
