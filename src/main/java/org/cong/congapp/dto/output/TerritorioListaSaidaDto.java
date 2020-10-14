package org.cong.congapp.dto.output;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.cong.congapp.model.TerritorioPrincipal;

public class TerritorioListaSaidaDto {

	private Long id;
	private String logradouro;
	private String logrSimples;
	
	public TerritorioListaSaidaDto(TerritorioPrincipal territorio) {
		this.id = territorio.getId();
		this.logradouro = territorio.getLogradouro();
		this.logrSimples = territorio.getLogrSimples();
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
	
	public static List<TerritorioListaSaidaDto> listarTerritorio(Iterable<TerritorioPrincipal> territorios) {
		List<TerritorioPrincipal> territoriosList = new ArrayList<>();
		territorios.forEach(territoriosList::add);
		return territoriosList.stream()
				.map(TerritorioListaSaidaDto::new)
				.collect(Collectors.toList());
	}
}
